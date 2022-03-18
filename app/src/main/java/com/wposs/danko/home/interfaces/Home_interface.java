package com.wposs.danko.home.interfaces;

import android.content.Context;

import com.wposs.danko.login.dto.BusinessDTO;

import java.util.ArrayList;

public interface Home_interface {

    interface View {
        void showResponse(ArrayList<BusinessDTO>  businessDTO, Context context);
    }

    interface Presenter {
        void setResponse(ArrayList<BusinessDTO> businessDTO, Context contex);

    }

    interface Interactor {
        void setResponseBusiness(BusinessDTO businessDTO);
    }

}
