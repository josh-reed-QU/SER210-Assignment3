package edu.quinnipiac.ser210.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider provider;
    private int currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        currentColor = Color.WHITE;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Message");
                provider.setShareIntent(shareIntent);
                break;
            case R.id.help:
                Intent helpIntent = new Intent(this, HelpScreen.class);
                helpIntent.putExtra("backgroundColor", currentColor);
                startActivity(helpIntent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}