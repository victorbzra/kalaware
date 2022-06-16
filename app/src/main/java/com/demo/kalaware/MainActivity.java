package com.demo.kalaware;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText Searchtext;
    private ExampleAdapter adapter;
    ImageButton bt_mic;
    private List<ExampleItem> exampleList;
    private List<ExampleItem> examples;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        fillExampleList();
        setUpRecyclerView();
        initToolbar();

        this.Searchtext = (EditText) findViewById(R.id.search_input);
        this.Searchtext.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                MainActivity.this.filterQuery(editable.toString());
            }
        });
    }

    private void fillExampleList() {
        this.exampleList = new ArrayList();
        this.exampleList.add(new ExampleItem(R.drawable.frango, "Frango", "1 peito (172 g) | 240kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.batata_frita, "Batata frita", "1 porção (71 g) | 180 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.cenoura, "Cenoura", "1 cenoura (61 g) | 25 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.arroz, "Arroz", "1 xícara (195 g) | 757 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.feijao, "Feijão", "1 xícara (193 g) | 670 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.batata, "Batatas cozidas", "1 batata (136 g) | 118 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.coca, "Coca Cola", "1 lata (350 ml) | 147 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.sanduiche, "Sanduíche", "1 sanduíche (83 g) | 252 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.coca, "Fanta", "1 lata (350 ml) | 137 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.ovo, "Ovo", "1 ovo (60 g) | 8 kcal"));
        this.exampleList.add(new ExampleItem(R.drawable.macarrao, "Macarrão", "1 xícara (245 g)| 56 kcal"));
    }



    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        this.adapter = new ExampleAdapter(this.exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.adapter);
    }

    private void initToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle((CharSequence) "kalaware");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void filterQuery(String text) {
        ArrayList<ExampleItem> filterdNames = new ArrayList<>();
        for (ExampleItem s : this.exampleList) {
            if (s.getText1().toLowerCase().contains(text) || s.getText2().toLowerCase().contains(text)) {
                filterdNames.add(s);
            }
        }
        this.adapter.setFilter(filterdNames);
    }
}