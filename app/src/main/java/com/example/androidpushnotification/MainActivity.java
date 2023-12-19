package com.example.androidpushnotification;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                            if (task.isSuccessful() && task.getResult() != null) {
                                String token = task.getResult();
                                Toast.makeText(MainActivity.this, "Current token [" + token + "]", Toast.LENGTH_LONG).show();
                                Log.d("App", "Token [" + token + "]");
                            } else {
                                // Handle error
                                Toast.makeText(MainActivity.this, "Token retrieval failed", Toast.LENGTH_LONG).show();
                                Log.e("App", "Token retrieval failed", task.getException());
                            }
                        });
            }
        });
    }

    }
//
