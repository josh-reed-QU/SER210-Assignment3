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
import android.widget.TextView;
import android.widget.Toast;

public class OutputFragment extends Fragment {

    //Key for saving the state of the TextView
    private static final String ORIGINAL_TEXT_STATE = "";
    private static final String TRANSLATED_TEXT_STATE = "";

    // The TextView where we will show results
    private TextView originalText;
    public TextView translatedText;

    public String userInput;
    int langIndex = 0;
    NavController navController = null;


    public OutputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInput = getArguments().getString("textInput");
        langIndex = getArguments().getInt("langIndex");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_output, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        // updates original text with the original, inputted text
        ((TextView) view.findViewById(R.id.originalText)).setText(userInput);

        // updates text displayed on the screen with the translated text
        translatedText = getView().findViewById(R.id.translatedText);
        new TranslateActivity(userInput, translatedText, langIndex).execute();

        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null){
            originalText.setText(savedInstanceState.getString(ORIGINAL_TEXT_STATE));
            translatedText.setText(savedInstanceState.getString(TRANSLATED_TEXT_STATE));
        }
    }

    /**
     * Saves the contents of the TextView to restore on configuration change.
     * @param outState The bundle in which the state of the activity is saved when
     * it is spontaneously destroyed.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(ORIGINAL_TEXT_STATE, originalText.getText().toString());
        outState.putString(TRANSLATED_TEXT_STATE, translatedText.getText().toString());
    }
}