package com.lab.indvasg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCalculate, btnClear;

    EditText etUnit, etRebate;

    TextView textUnit, textRebate, textTotal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        etUnit = findViewById(R.id.etUnit);
        etRebate = findViewById(R.id.etRebate);
        textUnit= findViewById(R.id.textUnit);
        textRebate= findViewById(R.id.textRebate);
        textTotal= findViewById(R.id.textTotal);

        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();

        if (selected == R.id.menuAbout) {

            //Toast.makeText(this, "This is the About page", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == btnCalculate) {
            String strUnit = etUnit.getText().toString();
            String strRebate = etRebate.getText().toString();
            DecimalFormat df = new DecimalFormat("0.00");
            double unit, rebate;
            double bill = 0;
            double total = 0;
            try {
                unit = Double.parseDouble(strUnit);
                rebate = Double.parseDouble(strRebate);

                if (unit <= 200) {
                    bill = unit * 0.218;
                } else if (unit <= 300) {
                    bill = (200 * 0.218) + (unit - 200) * 0.334;
                } else if (unit <= 600) {
                    bill = (200 * 0.218) + (100 * 0.334) + (unit - 300) * 0.516;
                } else {
                    bill = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + (unit - 600) * 0.546;
                }

                total = bill - (bill * rebate / 100);

            } catch (NumberFormatException nfe) {
                Toast.makeText(this, "Please enter numbers in the input field", Toast.LENGTH_SHORT).show();
            } catch (Exception exception) {
                Toast.makeText(this,"Please enter numbers in the input field - unspecific error", Toast.LENGTH_SHORT).show();
            }

            textTotal.setText("Total Bills: " + df.format(total));

        } else if (view == btnClear) {
            etUnit.setText("");
            etRebate.setText("");
            textTotal.setText("Total Bills:");

        }
    }
}