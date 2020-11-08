package com.example.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.speedrecords.adapter.UserAdapter;
import com.example.speedrecords.db.AppDatabase;
import com.example.speedrecords.model.User;
import com.example.speedrecords.util.AppExecutors;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button button;
    private RecyclerView mRecyclerView;
    private TextView totalTextView,overTextView;


    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final User[] users = db.userDao().getAllUsers();

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        UserAdapter adapter = new UserAdapter(MainActivity.this, users);
                        mRecyclerView.setAdapter(adapter);
                    }
                });


        int total = 0;
        int over = 0;
        String msg = "";
        for (User u : users) {
            if(Double.parseDouble(u.result) > 80.00){
                over+=1;
                msg = Integer.toString(over);
            }
            total+=1;
        }

        final String overText = msg;
        final String totalText = Integer.toString(total);
        executors.mainThread().execute(new Runnable() {
          @Override
          public void run() { // main thread
            totalTextView = findViewById(R.id.total_textView);
            totalTextView.setText("TOTAL: "+totalText);
            overTextView = findViewById(R.id.over_limit_textView);
            overTextView.setText("OVER LIMIT: "+overText);
          }
        });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.user_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        
        button = findViewById(R.id.add_record_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ADDRECORD.class);
                startActivity(intent);
            }
        });
    }
}
