package com.example.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.speedrecords.db.AppDatabase;
import com.example.speedrecords.model.User;
import com.example.speedrecords.util.AppExecutors;

import java.util.Locale;

public class ADDRECORD extends AppCompatActivity {
    private EditText distance_EditText,duration_EditText;
    private Button save_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d_d_r_e_c_o_r_d);

        save_Button = findViewById(R.id.save_button);
        save_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distance_EditText = findViewById(R.id.distance_editTextNumber);
                duration_EditText = findViewById(R.id.duration_editTextNumber);
                String distance = distance_EditText.getText().toString();
                String time = duration_EditText.getText().toString();
                double speed_d = ((Double.parseDouble(distance)/Double.parseDouble(time))*3600)/1000;
                String result_text = String.format(Locale.getDefault(), "%.1f", speed_d);
                String distance_text = String.format(Locale.getDefault(), "%.1f", Double.parseDouble(distance));
                String time_text = String.format(Locale.getDefault(), "%.1f", Double.parseDouble(time));

                final User user = new User(0, result_text, distance_text,time_text);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(ADDRECORD.this);
                        db.userDao().addUser(user);
                        finish();
                    }
                });
            }
        });
    }
}