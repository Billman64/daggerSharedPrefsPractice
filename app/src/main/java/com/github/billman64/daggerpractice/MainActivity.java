package com.github.billman64.daggerpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inUsername, inNumber;
    Button btnSave, btnGet;
    private MyComponent myComponent;
    private String TAG = "Dagger practice demo";


    @Inject
    SharedPreferences sharedPreferences;    // SharedPreferences injected. No need to initialize.

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);    // Approx. alternative without Dagger.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        myComponent = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        myComponent.inject(this);

    }

    private void initViews(){
        btnGet = findViewById(R.id.btnGet);
        btnSave = findViewById(R.id.btnSave);
        inUsername = findViewById(R.id.inUsername);
        inNumber = findViewById(R.id.inNumber);
        btnSave.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.btnGet:
                Log.d(TAG, "btnGet");
                inUsername.setText(sharedPreferences.getString("username","default"));  // Get shared preferences.
                inNumber.setText(sharedPreferences.getString("number","12345"));
                break;

            case R.id.btnSave:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",inUsername.getText().toString().trim());
                editor.putString("number", inNumber.getText().toString().trim());
                editor.apply();                                                                     // Save shared preferences.
                Log.d(TAG, "btnSave");
                break;
        }
    }

}
