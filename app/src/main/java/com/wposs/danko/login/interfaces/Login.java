package com.wposs.danko.login.interfaces;

import com.wposs.danko.login.dto.LoginDTO;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Login {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("search/{body}")
    Call<LoginDTO> consultaLogin(@Body RequestBody body);

    interface View {
        void showResultLogin(LoginDTO resp);
        void showError(String error);
    }

    interface Presenter {
        void RequestLogin(JSONObject req);
        void ResponseLogin(LoginDTO resp);
        void showError(String error);
    }

    interface Interactor {
        void consumoLogin(JSONObject jsonObject);
    }
}
