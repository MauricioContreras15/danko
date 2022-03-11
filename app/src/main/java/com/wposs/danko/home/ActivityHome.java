package com.wposs.danko.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wposs.danko.R;
import com.wposs.danko.login.dto.CategoriasDTO;
import com.wposs.danko.utils.Global;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityHome extends AppCompatActivity{

    private ArrayList<CategoriasDTO> categoriasDTO = Global.categoriasDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
}