package com.lab.indvasg;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class About extends AppCompatActivity {

    TextView textAppName, textCopyright, textDetails, textInfo, textURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_about);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textAppName = findViewById(R.id.textAppName);
        textCopyright = findViewById(R.id.textCopyright);
        textDetails = findViewById(R.id.textDetails);
        textInfo = findViewById(R.id.textInfo);
        textURL = findViewById(R.id.textURL);
        textURL.setMovementMethod(LinkMovementMethod.getInstance());
    }
}