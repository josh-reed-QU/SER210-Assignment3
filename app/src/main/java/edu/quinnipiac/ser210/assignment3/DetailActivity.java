package edu.quinnipiac.ser210.assignment3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

public class DetailActivity extends AppCompatActivity {
    private ShareActionProvider provider;
    private Intent intent;
    private int currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    /**
     * Creates toolbar for the main activity
     * Implements onItemSelectedListener
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem settings = menu.findItem(R.id.settings);

        provider = (ShareActionProvider) MenuItemCompat.getActionProvider((MenuItem) menu.findItem(R.id.share));

        Spinner colorPicker = (Spinner) settings.getActionView();
        // populates spinner with resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors_array,
                android.R.layout.simple_spinner_item);
        // sets adapter
        colorPicker.setAdapter(adapter);
        // sets listener
        colorPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                View temp = findViewById(R.id.inputLabel);
                View parent = temp.getRootView();
                switch(i) {
                    case 0:
                        parent.setBackgroundColor(Color.WHITE);
                        currentColor = Color.WHITE;
                        break;
                    case 1:
                        parent.setBackgroundColor(Color.CYAN);
                        currentColor = Color.CYAN;
                        break;
                    case 2:
                        parent.setBackgroundColor(Color.LTGRAY);
                        currentColor = Color.LTGRAY;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //intent = new Intent(this, DetailActivity.class);
        currentColor = Color.WHITE;

        return super.onCreateOptionsMenu(menu);
    }


}
