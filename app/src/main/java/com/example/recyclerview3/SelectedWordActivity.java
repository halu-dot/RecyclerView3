package com.example.recyclerview3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedWordActivity extends AppCompatActivity {
    TextView tvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_word);

        tvWord = findViewById(R.id.selectedword);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            WordModel wordModel = (WordModel) intent.getSerializableExtra("data");
            tvWord.setText(wordModel.getWord());
        }
    }
}