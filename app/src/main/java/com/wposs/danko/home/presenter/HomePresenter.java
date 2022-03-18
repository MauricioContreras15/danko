package com.wposs.danko.home.presenter;

import android.content.Context;

import com.wposs.danko.home.ActivityHome;
import com.wposs.danko.home.interfaces.Home_interface;
import com.wposs.danko.login.dto.BusinessDTO;
import java.util.ArrayList;

public class HomePresenter implements Home_interface.Presenter {

    private Home_interface.View view = new ActivityHome();


    @Override
    public void setResponse(ArrayList<BusinessDTO> businessDTO, Context contex) {
        view.showResponse(businessDTO, contex);
    }
}
