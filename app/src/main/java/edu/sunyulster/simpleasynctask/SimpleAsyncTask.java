package edu.sunyulster.simpleasynctask;

import android.os.AsyncTask;
import java.lang.ref.WeakReference;

import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    // The WeakReference ensures that the AsyncTask does
    // not prevent the Activity with these Views from being garbage collected
    // if it is destroyed (for example due to a config change)
    final private WeakReference<TextView> mTextView;
    final private WeakReference<ProgressBar> mProgressBar;

    public SimpleAsyncTask(TextView tv, ProgressBar progressBar) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // generate a random number to sleep for
        int number = (new Random().nextInt(10) + 1) * 200; // milliseconds to sleep will be between 200-2000 (0.5-2s)

        // sleep - publish progress every 20 ms
//        for (int i = 20; i <= number; i += 20) {
//            try {
//                Thread.sleep(20);
//                publishProgress((int) (float) i / number * 100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        int times = 10;
        for (int i = 1; i <= times; i++) {
            try {
                Thread.sleep((int) (float) number / times);
                publishProgress((int) (float) i / number * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // return message
        return String.format("Awake at last after sleeping for %s milliseconds!", number);
    }


    @Override
    protected void onPostExecute(String result) {
        // since mTextView is a WeakReference, get must be called to obtain the composed TextView
        mTextView.get().setText(result);
        mProgressBar.get().setProgress(0);
    }

    @Override
    protected void onProgressUpdate(Integer... integers) {
        mProgressBar.get().incrementProgressBy(integers[0]);
    }

}
