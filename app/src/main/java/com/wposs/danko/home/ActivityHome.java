package com.wposs.danko.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import com.wposs.danko.R;
import com.wposs.danko.business.ActivityBusiness;
import com.wposs.danko.home.adapter.AdapterHome;
import com.wposs.danko.home.interfaces.Home_interface;
import com.wposs.danko.login.dto.BusinessDTO;
import com.wposs.danko.login.dto.CategoriasDTO;
import com.wposs.danko.utils.Global;

import java.util.ArrayList;

public class ActivityHome extends AppCompatActivity implements Home_interface.View {

    private ArrayList<CategoriasDTO> categoriasDTO = Global.categoriasDTO;
    private EditText cap_pais;
    private EditText cap_estado;
    private EditText cap_city;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        generarlista();
    }

    private void init(){
        cap_pais = findViewById(R.id.capPais);
        cap_estado = findViewById(R.id.capEstado);
        cap_city = findViewById(R.id.capCiudad);
        recyclerView = findViewById(R.id.recycler);
    }

    private void generarlista(){
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        AdapterHome adapterHome = new AdapterHome(categoriasDTO, this);
        recyclerView.setAdapter(adapterHome);
    }


    @Override
    public void showResponse(ArrayList<BusinessDTO> businessDTO) {
        Global.BusinessDTO = businessDTO;
        Intent intent = new Intent(this, ActivityBusiness.class);
        startActivity(intent);
    }


}