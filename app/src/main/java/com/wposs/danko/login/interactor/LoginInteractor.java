package com.wposs.danko.login.interactor;

import com.wposs.danko.login.interfaces.Login;

public class LoginInteractor implements Login.Interactor{

    private Login.Presenter presenter;

    public LoginInteractor (Login.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void consumoLogin(String query) {

    }
}
