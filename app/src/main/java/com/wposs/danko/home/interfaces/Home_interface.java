package com.wposs.danko.home.interfaces;

import com.wposs.danko.login.dto.BusinessDTO;

import java.util.ArrayList;

public interface Home_interface {

    interface View {
        void showResponse(ArrayList<BusinessDTO>  businessDTO);
    }

    interface Presenter {
        void setResponse(ArrayList<BusinessDTO> businessDTO);

    }

    interface Interactor {
        void setResponseBusiness(BusinessDTO businessDTO);
    }

}
