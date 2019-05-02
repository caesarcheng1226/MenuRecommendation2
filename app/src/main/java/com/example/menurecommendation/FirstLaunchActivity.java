package com.example.menurecommendation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FirstLaunchActivity extends Activity {

    public static final String sharedPrefFile = "com.example.menurecommendation.sharedprefs";
    public static final String COME_FROM_FRAGMENT_FLAG = "isComeFromProfileFragment";

    public static final String WELCOME_Reply = "Welcome";
    public static final String Name_Key = "Name";

    public static final String District_KEY = "District";
    //public static final String DISLIKE_KEY = "Dislike";
    public static final String NOP_KEY = "NOP";

    public static final String NOM_KEY = "nom";
    public static final String NOV_KEY = "nov";
    public static final String NOS_KEY = "nos";

    public static final String BEEF_KEY = "beef_key";
    public static final String PORK_KEY = "pork_key";
    public static final String CHICKEN_KEY = "chicken_key";
    public static final String LETTUCE_KEY = "lettuce_key";
    public static final String BROCCOLI_KEY = "broccoli_key";
    public static final String SPINACH_KEY = "spinach_key";
    public static final String WS_KEY = "ws_key";
    public static final String CS_KEY = "cs_key";

    private Spinner NOPSpinner;
    private Spinner NOMSpinner;
    private Spinner NOVSpinner;
    private Spinner NOSSpinner;
    private Spinner DistrictSpinner;
    private EditText welcomeMessage;
    private CheckBox checkBoxBeef;
    private CheckBox checkBoxPork;
    private CheckBox checkBoxChicken;
    private CheckBox checkBoxLettuce;
    private CheckBox checkBoxBroccoli;
    private CheckBox checkBoxSpinach;
    private CheckBox checkBoxWS;
    private CheckBox checkBoxCS;

    private SharedPreferences mPreferences;
    private Intent welcomeIntent;

    private boolean isComeFromProfileFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_landing_page);

        isComeFromProfileFragment = getIntent().getBooleanExtra(COME_FROM_FRAGMENT_FLAG,false);

        // Set NOPSpinner
        NOPSpinner = findViewById(R.id.noOfPplSpinner);
        final ArrayAdapter<CharSequence> NOPAdapter = ArrayAdapter.createFromResource(this,
                R.array.no_of_people_array, android.R.layout.simple_spinner_item);
        NOPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NOPSpinner.setAdapter(NOPAdapter);

        // Set NOMSpinner
        NOMSpinner = findViewById(R.id.noOfMeatSpinner);
        final ArrayAdapter<CharSequence> NOMAdapter = ArrayAdapter.createFromResource(this,
                R.array.no_of_meat_array, android.R.layout.simple_spinner_item);
        NOMAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NOMSpinner.setAdapter(NOMAdapter);

        // Set NOVSpinner
        NOVSpinner = findViewById(R.id.noOfViggieSpinner);
        final ArrayAdapter<CharSequence> NOVAdapter = ArrayAdapter.createFromResource(this,
                R.array.no_of_viggie_array, android.R.layout.simple_spinner_item);
        NOVAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NOVSpinner.setAdapter(NOVAdapter);

        // Set NOSSpinner
        NOSSpinner = findViewById(R.id.noOfSoupSpinner);
        final ArrayAdapter<CharSequence> NOSAdapter = ArrayAdapter.createFromResource(this,
                R.array.no_of_soup_array, android.R.layout.simple_spinner_item);
        NOSAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NOSSpinner.setAdapter(NOSAdapter);



        // Set DistrictSpinner
        DistrictSpinner = findViewById(R.id.districtSpinner);
        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,
                R.array.district_array, android.R.layout.simple_spinner_item);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DistrictSpinner.setAdapter(districtAdapter);

        // Get Welcome Message
        welcomeMessage = findViewById(R.id.user_name);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        Button submitButton = findViewById(R.id.submitButton);

        checkBoxBeef = findViewById(R.id.checkBox2);
        checkBoxPork = findViewById(R.id.checkBox3);
        checkBoxChicken = findViewById(R.id.checkBox4);
        checkBoxLettuce = findViewById(R.id.checkBox6);
        checkBoxBroccoli = findViewById(R.id.checkBox7);
        checkBoxSpinach = findViewById(R.id.checkBox8);
        checkBoxWS = findViewById(R.id.checkBox10);
        checkBoxCS = findViewById(R.id.checkBox11);

        setupInfo();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor prefEditor = mPreferences.edit();
                welcomeIntent = new Intent();
                prefEditor.putString(WELCOME_Reply, welcomeMessage.getText().toString());
                prefEditor.putInt(District_KEY, DistrictSpinner.getSelectedItemPosition());

                prefEditor.putString(Name_Key, welcomeMessage.getText().toString());
                prefEditor.putInt(NOP_KEY, NOPSpinner.getSelectedItemPosition());
                prefEditor.putInt(NOM_KEY, NOMSpinner.getSelectedItemPosition());
                prefEditor.putInt(NOV_KEY, NOVSpinner.getSelectedItemPosition());
                prefEditor.putInt(NOS_KEY, NOSSpinner.getSelectedItemPosition());

                prefEditor.putBoolean(BEEF_KEY, checkBoxBeef.isChecked());
                prefEditor.putBoolean(PORK_KEY, checkBoxPork.isChecked());
                prefEditor.putBoolean(CHICKEN_KEY, checkBoxChicken.isChecked());
                prefEditor.putBoolean(LETTUCE_KEY, checkBoxLettuce.isChecked());
                prefEditor.putBoolean(BROCCOLI_KEY, checkBoxBroccoli.isChecked());
                prefEditor.putBoolean(SPINACH_KEY, checkBoxSpinach.isChecked());
                prefEditor.putBoolean(WS_KEY, checkBoxWS.isChecked());
                prefEditor.putBoolean(CS_KEY, checkBoxCS.isChecked());

                prefEditor.apply();
                prefEditor.commit();
                setContentView(R.layout.profile_fragment);
                //Log.d("clicktag", "onClick: Testtttt");

                if(!isComeFromProfileFragment){
                    welcomeIntent.setClass(FirstLaunchActivity.this, MainActivity.class);
                    FirstLaunchActivity.this.startActivity(welcomeIntent);
                }else{
                    setResult(RESULT_OK, welcomeIntent);
                }

                finish();

            }
        });

    }

    private void setupInfo(){
        welcomeMessage.setText(mPreferences.getString(FirstLaunchActivity.WELCOME_Reply,""));
        DistrictSpinner.setSelection(mPreferences.getInt(FirstLaunchActivity.District_KEY,0));
        NOPSpinner.setSelection(mPreferences.getInt(FirstLaunchActivity.NOP_KEY,0));
        NOMSpinner.setSelection(mPreferences.getInt(FirstLaunchActivity.NOM_KEY,0));
        NOVSpinner.setSelection(mPreferences.getInt(FirstLaunchActivity.NOV_KEY,0));
        NOSSpinner.setSelection(mPreferences.getInt(FirstLaunchActivity.NOS_KEY,0));

        checkBoxBeef.setChecked(mPreferences.getBoolean(BEEF_KEY, false));
        checkBoxPork.setChecked(mPreferences.getBoolean(PORK_KEY, false));
        checkBoxChicken.setChecked(mPreferences.getBoolean(CHICKEN_KEY, false));
        checkBoxLettuce.setChecked(mPreferences.getBoolean(LETTUCE_KEY, false));
        checkBoxBroccoli.setChecked(mPreferences.getBoolean(BROCCOLI_KEY, false));
        checkBoxSpinach.setChecked(mPreferences.getBoolean(SPINACH_KEY, false));
        checkBoxWS.setChecked(mPreferences.getBoolean(WS_KEY, false));
        checkBoxCS.setChecked(mPreferences.getBoolean(CS_KEY, false));


    }

}