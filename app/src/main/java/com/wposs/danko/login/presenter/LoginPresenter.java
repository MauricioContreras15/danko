package com.wposs.danko.login.presenter;

import com.wposs.danko.login.interactor.LoginInteractor;
import com.wposs.danko.login.interfaces.Login;

public class LoginPresenter implements Login.Presenter {

    private Login.View view;
    private Login.Interactor interactor;

    public LoginPresenter (Login.View view){
        this.view = view;
        interactor = new LoginInteractor(this);
    }

    @Override
    public void RequestLogin(String req) {
        interactor.consumoLogin(req);
    }

    @Override
    public void ResponseLogin(String resp) {
        view.showResultLogin(resp);
    }

    @Override
    public void showError(String error) {
        view.showError(error);
    }
}
