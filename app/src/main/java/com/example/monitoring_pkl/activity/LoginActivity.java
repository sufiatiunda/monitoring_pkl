package com.example.monitoring_pkl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.monitoring_pkl.R;
import com.example.monitoring_pkl.api.ApiInterface;
import com.example.monitoring_pkl.api.Apiserver;
import com.example.monitoring_pkl.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    String textUser, textPass;
    EditText user, pass;
    AppCompatButton login, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.tllogin);
        btnRegister = findViewById(R.id.register);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textUser = user.getText().toString();
                textPass = pass.getText().toString();

                if (textUser.equals("")){
                    user.setError("mohon diisi");
                }else if (textPass.equals("")){
                    pass.setError("mohon diisi");
                }else {
                    moveToLogin(textUser, textPass);
                }

            }
        });
    }

    private void moveToLogin(String textUser, String textPass) {
        ApiInterface apiInterface = Apiserver.getClient().create(ApiInterface.class);
        Call<ResponseLogin> responseLoginCall = apiInterface.login(textUser, textPass);
        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }
}