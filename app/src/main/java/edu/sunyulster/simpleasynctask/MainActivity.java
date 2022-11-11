package edu.sunyulster.simpleasynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import edu.sunyulster.simpleasynctask.databinding.ActivityMainBinding;

/**
 * The SimpleAsyncTask app contains a button that launches an AsyncTask
 * which sleeps in the asynchronous thread for a random amount of time.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TEXT_STATE = "currentText";
    private static final String PROGRESS_STATE = "currentProgress";

    /**
     * Initializes the activity.
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState != null) {
            binding.textView.setText(savedInstanceState.getString(TEXT_STATE));
            binding.progressBar.setProgress(savedInstanceState.getInt(PROGRESS_STATE));
        }

    }


    /**
     * Handles the onClick for the "Start Task" button. Launches the AsyncTask
     * which performs work off of the UI thread.
     *
     * @param view The view (Button) that was clicked.
     */
    public void startTask(View view) {
        binding.textView.setText(R.string.napping);
        new SimpleAsyncTask(binding.textView, binding.progressBar).execute();
    }


    /**
     * Saves the contents of the TextView to restore on configuration change.
     * @param outState The bundle in which the state of the activity is saved
     * when it is spontaneously destroyed.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the state of the TextView
        outState.putString(TEXT_STATE, binding.textView.getText().toString());
        outState.putInt(PROGRESS_STATE, binding.progressBar.getProgress());
    }
}