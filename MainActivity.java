package in.udith.prog2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;

    ProgressBar progressBar;

    TextView textView;

    int progressStatus;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_alert);

        progressBar = findViewById(R.id.progressBar);

        textView = findViewById(R.id.value);

        // Progress Bar Code
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"%");

                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // Alert Dialog Code
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert!");
                builder.setPositiveButton("Accept", (dialog, which) ->
                {
                    finish();
                });

                builder.setNegativeButton("Reject", (dialog, which) -> {
                    dialog.cancel();
                });

                builder.setNeutralButton("Cancel", (dialog, which) ->
                {
                    dialog.cancel();
                });

                builder.setMessage("Are You Sure To Exit?");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.alert);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}