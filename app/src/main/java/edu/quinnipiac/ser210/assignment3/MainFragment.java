package edu.quinnipiac.ser210.assignment3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainFragment extends Fragment implements View.OnClickListener {
    Bundle bundle;
    NavController navController = null;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        bundle = new Bundle();

        Spinner spinner = getView().findViewById(R.id.languageSpinner);
        // creates an instance of onItemSelectedListener and assigns it to the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bundle.putInt("langIndex", i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        bundle.putInt("langIndex", 0);

         */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.translateButton).setOnClickListener(this);
        bundle = new Bundle();
        Spinner spinner = getView().findViewById(R.id.languageSpinner);
        // creates an instance of onItemSelectedListener and assigns it to the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bundle.putInt("langIndex", i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.translateButton:
                EditText userInput = (EditText) getView().findViewById(R.id.textInput);
                String input = userInput.getText().toString();
                bundle.putString("textInput", input);
                navController.navigate(R.id.action_mainFragment_to_outputFragment, bundle);
                break;
            default:
                break;
        }
    }


}