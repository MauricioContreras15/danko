package com.wposs.danko.login.interactor;

import com.wposs.danko.login.dto.LoginDTO;
import com.wposs.danko.login.interfaces.Login;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractor implements Login.Interactor{

    private Login.Presenter presenter;
    private static final String BASE_URL_LOGIN = "";

    public LoginInteractor (Login.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void consumoLogin(JSONObject jsonObject) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Login consumoApi = retrofit.create(Login.class);
        Call<LoginDTO> call = consumoApi.consultaLogin(body);
        call.enqueue(new Callback<LoginDTO>() {
            @Override
            public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                if(response.isSuccessful()){
                    presenter.ResponseLogin(response.body().getResponse());
                } else {
                    presenter.showError("Usuario inexistente");
                }
            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {
                presenter.showError("Error de conexión");
            }
        });

    }
}
