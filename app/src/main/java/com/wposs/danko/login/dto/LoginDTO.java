package com.wposs.danko.login.dto;

import org.json.JSONObject;

import java.util.List;

public class LoginDTO {

    private String response;
    private List<CategoriasDTO> categorias;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<CategoriasDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriasDTO> categorias) {
        this.categorias = categorias;
    }

}
