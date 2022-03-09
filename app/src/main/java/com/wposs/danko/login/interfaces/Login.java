package com.wposs.danko.login.interfaces;

public interface Login {

    interface View {
        void showResultLogin(String resp);
        void showError(String error);
    }

    interface Presenter {
        void RequestLogin(String req);
        void ResponseLogin(String resp);
        void showError(String error);
    }

    interface Interactor {
        void consumoLogin(String query);
    }
}
