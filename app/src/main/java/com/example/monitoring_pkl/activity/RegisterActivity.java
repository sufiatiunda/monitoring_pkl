package com.example.monitoring_pkl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoring_pkl.R;
import com.example.monitoring_pkl.api.ApiInterface;
import com.example.monitoring_pkl.api.Apiserver;
import com.example.monitoring_pkl.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    String user, pass, confirpass;
    EditText username, password, confirpassword;
    AppCompatButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirpassword = findViewById(R.id.confirpassword);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), username.getText().toString(), Toast.LENGTH_SHORT).show();
                user = username.getText().toString();
                pass = password.getText().toString();
                Log.d("TAG", "onClick: "+user+" "+pass);
                confirpass = confirpassword.getText().toString();
//                if (confirpass.equals(pass)){
                    moveToRegister(user, pass);
//                }else {
//                    password.setError("Password tidak sama");
//                }
            }
        });
    }

    private void moveToRegister(String user, String pass) {
       Log.d("TAG", "moveToRegister: "+user + ":" + pass);
        ApiInterface apiInterface = Apiserver.getClient().create(ApiInterface.class);
        Call<ResponseLogin> call = apiInterface.register(user, pass);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Log.d("TAG","onResponse: "+response.body().getMassage());
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else {
                    Toast.makeText(RegisterActivity.this, "Gagal mendaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}