package chinna.sandyz.com.assignment112;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar,progressBar1,progressBar2,progressBar3;
    Button button;
    ParrlellProgress task1,task2,task3,task4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);

        progressBar =(ProgressBar)findViewById(R.id.progressBar);
        progressBar1 =(ProgressBar)findViewById(R.id.progressBar1);
        progressBar2 =(ProgressBar)findViewById(R.id.progressBar2);
        progressBar3 =(ProgressBar)findViewById(R.id.progressBar3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                progressBar1.setProgress(0);
                progressBar2.setProgress(0);
                progressBar3.setProgress(0);

                task1 = new ParrlellProgress(progressBar);
                task2= new ParrlellProgress(progressBar1);
                task3 = new ParrlellProgress(progressBar2);
                task4 = new ParrlellProgress(progressBar3);

                task1.executeOnExecutor(ParrlellProgress.THREAD_POOL_EXECUTOR);
                task2.executeOnExecutor(ParrlellProgress.THREAD_POOL_EXECUTOR);
                task3.executeOnExecutor(ParrlellProgress.THREAD_POOL_EXECUTOR);
                task4.executeOnExecutor(ParrlellProgress.THREAD_POOL_EXECUTOR);



            }
        });
    }


    class ParrlellProgress extends AsyncTask<Void,Integer,Void>{

        ProgressBar myProgressBar;

        public ParrlellProgress(ProgressBar progress){

            myProgressBar = progress;

        }

        @Override
        protected Void doInBackground(Void... params) {

            for (int i = 1 ; i<105 ; i++){
                publishProgress(i);
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
           myProgressBar.setProgress(values[0]);
        }
    }
}
