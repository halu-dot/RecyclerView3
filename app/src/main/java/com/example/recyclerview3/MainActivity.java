package com.example.recyclerview3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
import android.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

//import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import static com.example.recyclerview3.R.menu.menu;

public class MainActivity extends AppCompatActivity implements WordsAdapter.SelectedWord{

   // MaterialSearchBar materialSearchBar;
    Toolbar toolbar;
    RecyclerView recyclerView;

    List <WordModel> wordModelList = new ArrayList<>();
    String[] words = {"AMANU", "ABAC", "AHAHAHA", "AWW"};

    WordsAdapter wordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        toolbar = findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("SANA GUMANA");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        for (String s: words){
            WordModel wordModel = new WordModel(s);

            wordModelList.add(wordModel);
        }
        wordsAdapter = new WordsAdapter(wordModelList, this);
        recyclerView.setAdapter(wordsAdapter);
    }

    @Override
    public void selectedWord(WordModel wordModel) {
        startActivity(new Intent(MainActivity.this, SelectedWordActivity.class).putExtra("data", wordModel));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.searchview);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                wordsAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean OnOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id == R.id.searchview){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}