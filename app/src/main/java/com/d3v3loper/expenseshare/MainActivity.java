package com.d3v3loper.expenseshare;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Patterns;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class MainActivity extends AppCompatActivity {

    private EditText itemsInputField;
    private EditText amountInputField;
    private EditText nameInputField;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    // variable to track event time
    private long mLastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbar.setTitle("ExpenseShare");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemsInputField = (EditText) findViewById(R.id.items_input);
        amountInputField = (EditText) findViewById(R.id.amount_input);
        nameInputField = (EditText) findViewById(R.id.name_input);

        findViewById(R.id.submit_button).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    // Preventing multiple clicks, using threshold of 5 second
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 5000) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    // Handle button clicks
                        validateInput();
                    }
                }
        );
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            new MaterialStyledDialog.Builder(this)
                    .setTitle("About")
                    .setDescription(R.string.dialog_content)
                    .setStyle(Style.HEADER_WITH_TITLE)
                    .setHeaderColor(R.color.colorPrimary)
                    .setPositiveText("Expense Report")
                    .withDialogAnimation(true)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            String url = (getString(R.string.googlesheet_url));
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    })
                    .setNegativeText("Ok")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        }
                    })
                    .withDivider(true)
                    .setCancelable(false)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }




















    private void validateInput() { // Validate text input

        if (itemsInputField.getText().toString().trim().length() == 0)  {
            itemsInputField.setError("Enter item names!");
            Toast.makeText(MainActivity.this, "Please fill item names!", Toast.LENGTH_LONG).show();
        }

        else if(amountInputField.getText().toString().trim().length() == 0) {
            amountInputField.setError("Enter amount!");
            Toast.makeText(MainActivity.this, "Please fill amount!", Toast.LENGTH_LONG).show();
        }

        else if(nameInputField.getText().toString().trim().length() == 0) {
            nameInputField.setError("Enter your name!");
            Toast.makeText(MainActivity.this, "Please enter your name!", Toast.LENGTH_LONG).show();
        }

	else {
            sendData();
            // validateEmail();
         }
    }






    private void sendData() { // Send data to Google Spreadsheet if text input is valid

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/") // Your spreadsheet URL
                .build();
        final SpreadsheetWebService spreadsheetWebService = retrofit.create(SpreadsheetWebService.class);

        String itemsInput = itemsInputField.getText().toString();
        String amountInput = amountInputField.getText().toString();
        String nameInput = nameInputField.getText().toString();

        Call<Void> feedbackCall = spreadsheetWebService.feedbackSend(itemsInput, amountInput, nameInput);
        feedbackCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("XXX", "Submitted. " + response);
                Toast.makeText(MainActivity.this,"Your Expense was submitted!",Toast.LENGTH_LONG).show();
                // Clear all fields after submitting
                itemsInputField.setText("");
                amountInputField.setText("");
                nameInputField.setText("");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("XXX", "Failed", t);
                Toast.makeText(MainActivity.this,"There was an error! Check your Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

}
