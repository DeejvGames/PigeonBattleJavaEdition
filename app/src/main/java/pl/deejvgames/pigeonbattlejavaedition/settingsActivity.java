package pl.deejvgames.pigeonbattlejavaedition;

import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.languageKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.oledModeEnabledKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.readData;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.writeData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.Locale;
import java.util.Objects;

public class settingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        setAppLanguage();
        setOledModeSwitchToggleState();
        findViewById(R.id.english_language).setOnClickListener(v -> onEnglishLanguageSelect(this));
        findViewById(R.id.polish_language).setOnClickListener(v -> onPolishLanguageSelect(this));
        ((MaterialSwitch) findViewById(R.id.oledSwitch)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            onOledModeSwitchToggle();
        });
        if(Boolean.parseBoolean(readData(this, oledModeEnabledKey))){
            findViewById(R.id.main).setBackgroundColor(Color.rgb(0, 0, 0));
        }
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onRestart(){
        super.onRestart();
        setAppLanguage();
        setOledModeSwitchToggleState();
        if(Boolean.parseBoolean(readData(this, oledModeEnabledKey))){
            findViewById(R.id.main).setBackgroundColor(Color.rgb(0, 0, 0));
        } else{
            findViewById(R.id.main).setBackgroundColor(Color.parseColor(getString(R.color.theme)));
        }
    }

    public void setAppLanguage(){
        if(getResources().getConfiguration().getLocales().get(0).getLanguage().equals("en")){
            ((RadioButton)findViewById(R.id.english_language)).setChecked(true);
        }
        if(getResources().getConfiguration().getLocales().get(0).getLanguage().equals("pl")){
            ((RadioButton)findViewById(R.id.polish_language)).setChecked(true);
        }
    }

    public void onEnglishLanguageSelect(Context context){
        ((RadioButton)findViewById(R.id.english_language)).setChecked(true);
        Locale.setDefault(Locale.forLanguageTag("en"));
        Configuration config = new Configuration();
        config.setLocale(Locale.forLanguageTag("en"));
        context.getApplicationContext().createConfigurationContext(config);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        saveToFile.writeData(this, languageKey, "en");
        recreate();
    }

    public void onPolishLanguageSelect(Context context){
        ((RadioButton)findViewById(R.id.polish_language)).setChecked(true);
        Locale.setDefault(Locale.forLanguageTag("pl"));
        Configuration config = new Configuration();
        config.setLocale(Locale.forLanguageTag("pl"));
        context.getApplicationContext().createConfigurationContext(config);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        saveToFile.writeData(this, languageKey, "pl");
        recreate();
    }

    public void onOledModeSwitchToggle(){
        if(!((MaterialSwitch) findViewById(R.id.oledSwitch)).isChecked()){
            writeData(this, oledModeEnabledKey, String.valueOf(false));
            onRestart();
        } else{
            writeData(this, oledModeEnabledKey, String.valueOf(true));
            onRestart();
        }
    }

    public void setOledModeSwitchToggleState(){
        if(Boolean.parseBoolean(readData(this, oledModeEnabledKey))){
            ((MaterialSwitch) findViewById(R.id.oledSwitch)).setChecked(true);
        } else{
            ((MaterialSwitch) findViewById(R.id.oledSwitch)).setChecked(false);
        }
    }
}