package com.example.malavshah.registrationuidesign;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.malavshah.registrationuidesign.R.id.spinner;

public class MainActivity extends AppCompatActivity {

    Spinner mySpinner;
    Calendar myCalendar;
    EditText et_firstname, et_lastname, et_age, et_phone, et_address, et_date;
    String firstname, lastname, age, phone, address, date;
    int gender;
    Button btn;
    RadioGroup rg_gender;
    RadioButton male;
    RadioButton female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Keyboard is hidden
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Validations
        et_firstname = (EditText) findViewById(R.id.firstName);
        et_lastname = (EditText) findViewById(R.id.lastName);
        et_age = (EditText) findViewById(R.id.age);
        et_phone = (EditText) findViewById(R.id.phone);
        et_address = (EditText) findViewById(R.id.address);
        btn = (Button) findViewById(R.id.button);
        rg_gender = (RadioGroup) findViewById(R.id.radiogroup);
        male = (RadioButton) findViewById(R.id.radio_male);

        //spinner = (Spinner) findViewById(spinner);

        //TextView errorText = (TextView)spinner.getSelectedView();


        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                male.setError(null);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        //Spinner
        mySpinner = (Spinner) findViewById(spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.times));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        //Calendar
        myCalendar = Calendar.getInstance();
        et_date = (EditText) findViewById(R.id.Birthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        et_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_date.setText(sdf.format(myCalendar.getTime()));
    }

    public void register() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Signup has failed!", Toast.LENGTH_LONG).show();
        } else {
            showDialog();
        }
            //Toast.makeText(this, "Signup successful", Toast.LENGTH_LONG).show()
    }

    public boolean validate(){
        Drawable dr = getResources().getDrawable(R.drawable.ic_error_black_24dp);
        //add an error icon to yur drawable files
        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
        boolean valid = true;
        if(firstname.isEmpty()){
            //et_firstname.setError("Please enter valid first name!");
            et_firstname.setError("Please enter valid first name!", dr);
            valid = false;
        }
        if(lastname.isEmpty()){
            et_lastname.setError("Please enter valid last name!", dr);
            valid = false;
        }
        if(age.isEmpty()){
            et_age.setError("Please enter valid age!", dr);
            valid = false;
        }
        if(phone.isEmpty() || phone.length()<10){
            et_phone.setError("Please enter valid phone number!",dr);
            valid = false;
        }
        if(address.isEmpty()){
            et_address.setError("Please enter valid postal address!", dr);
            valid = false;
        }
        /*if(date.isEmpty()){
            et_date.setError("Please select date!", dr);
            valid = false;
        }*/
        if(rg_gender.getCheckedRadioButtonId() == -1){
            male.setError("Please select your gender!", dr);
            valid = false;
        }
        if(mySpinner.getSelectedItemId()<=0){
            TextView errorText = (TextView)mySpinner.getSelectedView();
            errorText.setError("Please select some value", dr);
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            //errorText.setText("my actual error text");
            valid = false;
        }
        return valid;
    }

    public void initialize(){
        firstname = et_firstname.getText().toString();
        lastname = et_lastname.getText().toString();
        age = et_age.getText().toString();
        phone = et_phone.getText().toString();
        address = et_address.getText().toString();
        gender = rg_gender.getCheckedRadioButtonId();
        date = et_date.getText().toString();
        //Log.i("Info", date);
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Get the layout inflater
        LayoutInflater inflater = (MainActivity.this).getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.alertdialog, null))
                // Add action buttons
                .setPositiveButton(R.string.register_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(R.string.register_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "No", Toast.LENGTH_LONG).show();
                    }
                });
        builder.create();
        builder.show();
    }

}
