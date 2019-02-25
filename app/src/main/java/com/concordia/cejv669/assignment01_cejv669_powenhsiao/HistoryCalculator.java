package com.concordia.cejv669.assignment01_cejv669_powenhsiao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryCalculator extends AppCompatActivity {

    List<String> myHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_calculator);

        ListView lv = findViewById(R.id.list_view_history);

        Intent i = getIntent();

        ArrayList myArrayList = i.getStringArrayListExtra("myArrayList");

        myHistoryList = myArrayList;

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myHistoryList);

        if (i != null) {
            lv.setAdapter(itemsAdapter);
            itemsAdapter.notifyDataSetChanged();
        }
    }
}
