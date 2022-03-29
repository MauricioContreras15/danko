package com.wposs.danko.login.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.airbnb.lottie.L;
import com.google.gson.JsonObject;
import com.wposs.danko.R;
import com.wposs.danko.home.ActivityHome;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.io.ConsumeServicesExpress;
import com.wposs.danko.login.dto.BusinessDTO;
import com.wposs.danko.login.dto.CategoriasDTO;
import com.wposs.danko.login.dto.LoginDTO;
import com.wposs.danko.model.JsonResponse;
import com.wposs.danko.utils.Defines;
import com.wposs.danko.utils.Global;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private Button buttonEnter;
    private Button buttonInvitado;
    private EditText capUser;
    private EditText capPass;
    private ProgressDialog progressDialog;
    private Context context = ActivityLogin.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestPermission();
        initComponents();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonEnter.getId()) {
            validateField();
        }

        if (v.getId() == buttonInvitado.getId()) {

        }

    }

    private void initComponents() {
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonInvitado = findViewById(R.id.buttonInvitado);
        capUser = (EditText) findViewById(R.id.capUser);
        capPass = (EditText) findViewById(R.id.capPass);
        progressDialog = new ProgressDialog(ActivityLogin.this);

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(ActivityLogin.this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                1);
    }

    private void validateField() {
        String user = String.valueOf(capUser.getText());
        String psw = String.valueOf(capPass.getText());

        if (user != null && !user.trim().isEmpty() && psw != null && !psw.trim().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Advertencia!");
            builder.setMessage("¿Seguro que son tus credenciales?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialogInterface, int i) {
                    progressDialog = new ProgressDialog(ActivityLogin.this);
                    progressDialog.setTitle("Consultando...");
                    progressDialog.show();
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("user_app", true);
                        jsonObject.put("user", user);
                        jsonObject.put("password", psw);
                        jsonObject.put("device", getSerial());
                        login(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });
            builder.show();
        } else {
            showError("Debe ingresar usuario y contraseña");
            return;
        }
    }


    public String getSerial() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public void echoTest() {

        new ConsumeServicesExpress().consume_api(Defines.ECHOTEST, new OnResponseInterface() {
            @Override
            public void finish_consumer_services(JsonResponse jsonResponse) {

            }

            @Override
            public void finish_fail_consumer_services() {

            }
        });
    }

    public void showCategories(LoginDTO resp) {
        if (progressDialog.isShowing()){
            progressDialog.cancel();
        }
        addLocation(resp.getCategorias());
        Global.categoriasDTO = resp.getCategorias();
        Intent intent = new Intent(context, ActivityHome.class);
        startActivity(intent);
    }

    public void showError(String error) {
        if (progressDialog.isShowing()){
            progressDialog.cancel();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Advertencia!");
        builder.setMessage(error);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builder.show();
    }

    private void addLocation(ArrayList<CategoriasDTO> categoriasDTO){
        for (int i = 0; i < categoriasDTO.size(); i++){
            ArrayList<BusinessDTO> businessDTO = categoriasDTO.get(i).getBusinessDTOList();

            for (int j = 0; j < businessDTO.size(); j++) {
                String pais = businessDTO.get(j).getPais();
                String estado = businessDTO.get(j).getEstado();
                String ciudad = businessDTO.get(j).getCiudad();
                if (Global.pais.size() == 0) {
                    Global.pais.add(pais);
                } else {
                    boolean existe = false;
                    for (int x = 0; x < Global.pais.size(); x++) {
                        if (pais.equalsIgnoreCase(Global.pais.get(x))) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) Global.pais.add(pais);
                }
                if (Global.estado.size() == 0) {
                    Global.estado.add(estado);
                } else {
                    boolean existe = false;
                    for (int x = 0; x < Global.estado.size(); x++) {
                        if (estado.equalsIgnoreCase(Global.estado.get(x))) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) Global.estado.add(estado);
                }
                if (Global.ciudad.size() == 0) {
                    Global.ciudad.add(ciudad);
                } else {
                    boolean existe = false;
                    for (int x = 0; x < Global.ciudad.size(); x++) {
                        if (ciudad.equalsIgnoreCase(Global.ciudad.get(x))) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) Global.ciudad.add(ciudad);
                }
            }
        }
    }

    public void login(JSONObject jsonObject) {
        JsonResponse jsonResponse = new JsonResponse();
        LoginDTO loginDTO = llenarDTO(jsonResponse);
        showCategories(loginDTO);
        /*new ConsumeServicesExpress(jsonObject).consume_api(Defines.LOGIN, new OnResponseInterface() {
            @Override
            public void finish_consumer_services(JsonResponse jsonResponse) {
                ///HAY QUE OBTENER EL JSONRESPONSE
                if (progressDialog.isShowing()){
                    progressDialog.cancel();
                }
                LoginDTO loginDTO = llenarDTO(jsonResponse);
                showCategories(loginDTO);
            }

            @Override
            public void finish_fail_consumer_services() {

            }
        });*/

    }

    private LoginDTO llenarDTO(JsonResponse jsonResponse){ // se obtiene la información del JsonResponse para llenar DTO

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setResponse("1");

        ArrayList<CategoriasDTO> dtoList = new ArrayList<>();


        CategoriasDTO categoriasDTO1 = new CategoriasDTO();
        categoriasDTO1.setId("1");
        categoriasDTO1.setName("INSTITUCIONES MEDICAS");
        categoriasDTO1.setImg("https://static.wikia.nocookie.net/eswikia/images/2/27/Medicina.jpg/revision/latest?cb=20160830143330");
        ArrayList<BusinessDTO> businessDTOList = new ArrayList<>();
        BusinessDTO businessDTO1 = new BusinessDTO();
        businessDTO1.setName("CLINICA MEDICAL DUARTE");
        businessDTO1.setDescription("En Medical Duarte cada día laboramos orientados a la excelencia, a la innovación y al desarrollo social, para brindar a todos nuestros pacientes una experiencia superior en atención.");
        businessDTO1.setId("1");
        businessDTO1.setPais("Colombia");
        businessDTO1.setEstado("Norte de Santander");
        businessDTO1.setCiudad("Cúcuta");
        businessDTO1.setImg("https://yt3.ggpht.com/ytc/AKedOLSAllMJ6e4AtNd7T-XKqA8gO3_orNU_EtdNXvdv=s900-c-k-c0x00ffffff-no-rj");
        businessDTO1.setUrl("https://clinicamedicalduarte.com");
        businessDTOList.add(businessDTO1);
        BusinessDTO businessDTO12 = new BusinessDTO();
        businessDTO12.setName("BBBB");
        businessDTO12.setDescription("DESCRPICIÓN565");
        businessDTO12.setId("2");
        businessDTO12.setPais("Colombia");
        businessDTO12.setEstado("Norte de Santander");
        businessDTO12.setCiudad("Ocaña");
        businessDTOList.add(businessDTO12);
        categoriasDTO1.setBusinessDTOList(businessDTOList);
        dtoList.add(categoriasDTO1);
        loginDTO.setCategorias(dtoList);

        CategoriasDTO categoriasDTO2 = new CategoriasDTO();
        categoriasDTO2.setId("2");
        categoriasDTO2.setName("Mauricio");
        categoriasDTO2.setImg("----");
        ArrayList<BusinessDTO> businessDTOList2 = new ArrayList<>();
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setName("KKKK");
        businessDTO.setDescription("DESCRPICIÓN");
        businessDTO.setId("1");
        businessDTO.setPais("España");
        businessDTO.setEstado("Sevilla");
        businessDTO.setCiudad("Sevilla");
        businessDTOList2.add(businessDTO);
        BusinessDTO businessDTO2 = new BusinessDTO();
        businessDTO2.setName("LLLL");
        businessDTO2.setDescription("DESCRPICIÓN565");
        businessDTO2.setId("2");
        businessDTO2.setPais("España");
        businessDTO2.setEstado("Cataluña");
        businessDTO2.setCiudad("Barcelona");
        businessDTOList2.add(businessDTO2);
        categoriasDTO2.setBusinessDTOList(businessDTOList2);
        dtoList.add(categoriasDTO2);
        loginDTO.setCategorias(dtoList);

        CategoriasDTO categoriasDTO3 = new CategoriasDTO();
        categoriasDTO3.setId("3");
        categoriasDTO3.setName("Contreras");
        categoriasDTO3.setImg("----");
        ArrayList<BusinessDTO> businessDTOList3 = new ArrayList<>();
        BusinessDTO businessDTO3 = new BusinessDTO();
        businessDTO3.setName("ZZZZ");
        businessDTO3.setDescription("DESCRPICIÓNZZ");
        businessDTO3.setId("1");
        businessDTO3.setPais("Colombia");
        businessDTO3.setEstado("Antioquia");
        businessDTO3.setCiudad("Medellín");
        businessDTOList3.add(businessDTO3);
        BusinessDTO businessDTO31 = new BusinessDTO();
        businessDTO31.setName("ZXX");
        businessDTO31.setDescription("DESCRPICIÓNXX");
        businessDTO31.setId("2");
        businessDTO31.setPais("Colombia");
        businessDTO31.setEstado("Antioquia");
        businessDTO31.setCiudad("Itagui");
        businessDTOList3.add(businessDTO31);
        categoriasDTO3.setBusinessDTOList(businessDTOList3);
        dtoList.add(categoriasDTO3);
        loginDTO.setCategorias(dtoList);


        return loginDTO;
    }

}