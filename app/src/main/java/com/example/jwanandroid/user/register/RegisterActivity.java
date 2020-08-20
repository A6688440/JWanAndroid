package com.example.jwanandroid.user.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jwanandroid.R;
import com.example.jwanandroid.base.BaseActivity;
import com.example.jwanandroid.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements MvpRegister.V {

    private ActivityRegisterBinding binding;

    @Override
    protected RegisterPresenter getInstantiate() {
        return new RegisterPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.mBtnRegisterOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.Register(binding.mEditUserName.getText().toString(),
                        binding.mReEditPassword.getText().toString(),
                        binding.mReEditPassword.getText().toString());
            }
        });
    }



    @Override
    public void getMessage(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }
}
