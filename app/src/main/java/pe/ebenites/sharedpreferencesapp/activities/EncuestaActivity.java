package pe.ebenites.sharedpreferencesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import pe.ebenites.sharedpreferencesapp.R;
import pe.ebenites.sharedpreferencesapp.models.User;
import pe.ebenites.sharedpreferencesapp.repositories.UserRepository;

public class EncuestaActivity extends AppCompatActivity {

    private TextView fullname1Text;
    private SharedPreferences sp;
    private EditText fullnameInput;
    private RadioGroup radioGroup;
    private CheckBox acepCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        fullname1Text = findViewById(R.id.fullname1_text);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("username", null);
        User user = UserRepository.findByUsername(username);

        //verificamos que haya datos
        if(user != null){
            fullname1Text.setText(user.getFullname());

        }

        fullnameInput = findViewById(R.id.fullname2_text);
        fullnameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String fullname = s.toString();
                sp.edit().putString("fullname", fullname).commit();

            }
        });
        //Recuperando el valor de la SharedPreferences

        String fullname = sp.getString("fullname", null);
        if(fullname != null){
            fullnameInput.setText(fullname);
        }

        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male_radio:
                        sp.edit().putString("gender","M").commit();
                        break;
                    case R.id.famale_radio:
                        sp.edit().putString("gender", "F").commit();
                        break;
                }
            }
        });

        //Recuperando el valor de la SharedPreferences
        String gender  = sp.getString("gender", null);
        if (gender != null){
            if("M".equals(gender)){
                radioGroup.check(R.id.male_radio);
            } else if ("F".equals(gender)) {
                radioGroup.check(R.id.famale_radio);
            }
            }


        }

    }



