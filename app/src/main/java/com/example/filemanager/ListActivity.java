package com.example.filemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView noFileTextView = findViewById(R.id.noFileTextView);

        String path = getIntent().getStringExtra("путь");

        File root = new File(path);
        File[] filesAndFolders = root.listFiles();

        if (filesAndFolders == null || filesAndFolders.length == 0) {
            noFileTextView.setVisibility(View.VISIBLE);
            return;
        }

        noFileTextView.setVisibility(View.INVISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), filesAndFolders));

    }
}
