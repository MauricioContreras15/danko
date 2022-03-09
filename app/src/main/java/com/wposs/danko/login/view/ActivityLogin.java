package com.wposs.danko.login.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.wposs.danko.R;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.io.ConsumeServicesExpress;
import com.wposs.danko.login.dto.LoginDTO;
import com.wposs.danko.login.interfaces.Login;
import com.wposs.danko.login.presenter.LoginPresenter;
import com.wposs.danko.utils.Defines;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, Login.View {

    private Button buttonEnter;
    private Button buttonInvitado;
    private EditText capUser;
    private EditText capPass;
    private Login.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestPermission();
        initComponents();
        //events();

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
    }

    private void initComponents() {
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonInvitado = findViewById(R.id.buttonInvitado);
        capUser = (EditText) findViewById(R.id.capUser);
        capPass = (EditText) findViewById(R.id.capPass);

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(ActivityLogin.this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                1);
    }

    private void validateField() {
        presenter = new LoginPresenter(this);
        String user = String.valueOf(capUser.getText());
        String psw = String.valueOf(capPass.getText());
        Toast.makeText(this, "SERIAL:" + getSerial(), Toast.LENGTH_SHORT).show();


        if (user != null && !user.trim().isEmpty() && psw != null && !psw.trim().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Advertencia!");
            builder.setMessage("¿Seguro que son tus credenciales?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialogInterface, int i) {
                    LoginDTO loginDTO = new LoginDTO();
                    loginDTO.setUser(user);
                    loginDTO.setPassword(psw);

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
            presenter.showError("Debe ingresar usuario y contraseña");
            return;
        }
    }


    public String getSerial() {
        Context context = ActivityLogin.this;
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }

    public void echoTest() {

        new ConsumeServicesExpress().consume_api(Defines.ECHOTEST, new OnResponseInterface() {
            @Override
            public void finish_consumer_services() {
                Toast.makeText(ActivityLogin.this, "Servidor En Linea", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void finish_fail_consumer_services() {
                Toast.makeText(ActivityLogin.this, "Servidor En falla", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showResultLogin(String resp) {

    }

    @Override
    public void showError(String error) {
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
}