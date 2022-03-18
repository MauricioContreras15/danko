package com.wposs.danko.login.interactor;

import com.wposs.danko.login.dto.BusinessDTO;
import com.wposs.danko.login.dto.CategoriasDTO;
import com.wposs.danko.login.dto.LoginDTO;
import com.wposs.danko.login.interfaces.Login;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setResponse("1");

        ArrayList<CategoriasDTO> dtoList = new ArrayList<>();


        CategoriasDTO categoriasDTO1 = new CategoriasDTO();
        categoriasDTO1.setId("1");
        categoriasDTO1.setName("Andres");
        categoriasDTO1.setImg("----");
        ArrayList<BusinessDTO> businessDTOList = new ArrayList<>();
        BusinessDTO businessDTO1 = new BusinessDTO();
        businessDTO1.setName("AAAA");
        businessDTO1.setDescription("DESCRPICIÓN");
        businessDTO1.setId("1");
        businessDTOList.add(businessDTO1);
        BusinessDTO businessDTO12 = new BusinessDTO();
        businessDTO12.setName("BBBB");
        businessDTO12.setDescription("DESCRPICIÓN565");
        businessDTO12.setId("2");
        businessDTOList.add(businessDTO12);
        categoriasDTO1.setBusinessDTOList(businessDTOList);
        dtoList.add(categoriasDTO1);
        loginDTO.setCategorias(dtoList);

        CategoriasDTO categoriasDTO2 = new CategoriasDTO();
        categoriasDTO2.setId("2");
        categoriasDTO2.setName("Mauricio");
        categoriasDTO2.setImg("----");
        ArrayList<BusinessDTO> businessDTOList2 = new ArrayList<>();
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setName("KKKK");
        businessDTO.setDescription("DESCRPICIÓN");
        businessDTO.setId("1");
        businessDTOList2.add(businessDTO);
        BusinessDTO businessDTO2 = new BusinessDTO();
        businessDTO2.setName("LLLL");
        businessDTO2.setDescription("DESCRPICIÓN565");
        businessDTO2.setId("2");
        businessDTOList2.add(businessDTO2);
        categoriasDTO2.setBusinessDTOList(businessDTOList2);
        dtoList.add(categoriasDTO2);
        loginDTO.setCategorias(dtoList);

        CategoriasDTO categoriasDTO3 = new CategoriasDTO();
        categoriasDTO3.setId("3");
        categoriasDTO3.setName("Contreras");
        categoriasDTO3.setImg("----");
        ArrayList<BusinessDTO> businessDTOList3 = new ArrayList<>();
        BusinessDTO businessDTO3 = new BusinessDTO();
        businessDTO3.setName("ZZZZ");
        businessDTO3.setDescription("DESCRPICIÓNZZ");
        businessDTO3.setId("1");
        businessDTOList3.add(businessDTO3);
        BusinessDTO businessDTO31 = new BusinessDTO();
        businessDTO31.setName("ZXX");
        businessDTO31.setDescription("DESCRPICIÓNXX");
        businessDTO31.setId("2");
        businessDTOList3.add(businessDTO31);
        categoriasDTO3.setBusinessDTOList(businessDTOList3);
        dtoList.add(categoriasDTO3);
        loginDTO.setCategorias(dtoList);

        presenter.ResponseLogin(loginDTO);

        /*Retrofit retrofit = new Retrofit.Builder()
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
                    LoginDTO loginDTO = response.body();
                    if (loginDTO != null && loginDTO.getCategorias().size() > 0){
                        presenter.ResponseLogin(loginDTO);
                    } else {
                        presenter.showError("Usuario inexistente");
                    }
                } else {
                    presenter.showError("Usuario inexistente");
                }
            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {
                presenter.showError("Error de conexión");
            }
        });*/

    }
}
