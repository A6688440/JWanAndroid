package com.example.jwanandroid.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jwanandroid.base.BaseActivity;
import com.example.jwanandroid.databinding.ActivityLoginBinding;
import com.example.jwanandroid.user.register.RegisterActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> implements MvpLogin.V {

    private ActivityLoginBinding binding;


    @Override
    protected LoginPresenter getInstantiate() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.Login(binding.mEditUserName.getText().toString(), binding.mEditPassword.getText().toString());
            }
        });
        binding.mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }



    @Override
    public void getMessage(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        if(Message.equals("登录成功")){
            finish();
        }
    }
}
