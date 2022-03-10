package com.wposs.danko.login.presenter;

import com.wposs.danko.login.dto.LoginDTO;
import com.wposs.danko.login.interactor.LoginInteractor;
import com.wposs.danko.login.interfaces.Login;

import org.json.JSONObject;

public class LoginPresenter implements Login.Presenter {

    private Login.View view;
    private Login.Interactor interactor;

    public LoginPresenter (Login.View view){
        this.view = view;
        interactor = new LoginInteractor(this);
    }

    @Override
    public void RequestLogin(JSONObject req) {
        interactor.consumoLogin(req);
    }

    @Override
    public void ResponseLogin(LoginDTO resp) {
        view.showResultLogin(resp);
    }

    @Override
    public void showError(String error) {
        view.showError(error);
    }
}
