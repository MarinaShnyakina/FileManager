package com.example.filemanager;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private Button btnStorage;
    private static final int PERMISSION_STORAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStorage = findViewById(R.id.btnStorage);

        if (PermissionUtils.hasPermissions(this)) {
            btnStorage.setText("Хранилище");
        } else {
            btnStorage.setText("Разрешение не предоставлено");
        }


        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (PermissionUtils.hasPermissions(MainActivity.this)) {
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    String path = Environment.getExternalStorageDirectory().getPath();
                    intent.putExtra("путь", path);
                    startActivity(intent);
                } else {
                    PermissionUtils.requestPermissions(MainActivity.this, PERMISSION_STORAGE);
                }
            }
        });

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if (requestCode == PERMISSION_STORAGE) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (PermissionUtils.hasPermissions(this)) {
                        btnStorage.setText("Хранилище");
                    } else {
                        btnStorage.setText("Разрешение не предоставлено");
                    }
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }



    }

