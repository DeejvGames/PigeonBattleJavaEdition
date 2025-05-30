package pl.deejvgames.pigeonbattlejavaedition;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        checkSpamAttackingState();
        findViewById(R.id.english_language).setOnClickListener(v -> onEnglishLanguageSelect(this));
        findViewById(R.id.polish_language).setOnClickListener(v -> onPolishLanguageSelect(this));
        ((MaterialSwitch) findViewById(R.id.spamAttacking)).setOnCheckedChangeListener((buttonView, isChecked) -> setSpamAttackingState());
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        setAppLanguage();
        checkSpamAttackingState();
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
        saveToFile.saveData(context, saveToFile.selectedLanguageFileName, "language=en");
        recreate();
    }

    public void onPolishLanguageSelect(Context context){
        ((RadioButton)findViewById(R.id.polish_language)).setChecked(true);
        Locale.setDefault(Locale.forLanguageTag("pl"));
        Configuration config = new Configuration();
        config.setLocale(Locale.forLanguageTag("pl"));
        context.getApplicationContext().createConfigurationContext(config);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        saveToFile.saveData(context, saveToFile.selectedLanguageFileName, "language=pl");
        recreate();
    }

    public void setSpamAttackingState(){
        if(Objects.equals(saveToFile.loadData(this, saveToFile.isSpamAttackingEnabledFileName, "isSpamAttackingEnabled"), String.valueOf(false))){
            spamAttackingWarning();
            ((MaterialSwitch) findViewById(R.id.spamAttacking)).setChecked(true);
        } else{
            saveToFile.saveData(this, saveToFile.isSpamAttackingEnabledFileName, "isSpamAttackingEnabled=false");
            ((MaterialSwitch) findViewById(R.id.spamAttacking)).setChecked(false);
        }
    }

    public void checkSpamAttackingState(){
        if(Objects.equals(saveToFile.loadData(this, saveToFile.isSpamAttackingEnabledFileName, "isSpamAttackingEnabled"), String.valueOf(true))){
            ((MaterialSwitch) findViewById(R.id.spamAttacking)).setChecked(true);
        } else{
            ((MaterialSwitch) findViewById(R.id.spamAttacking)).setChecked(false);
        }
    }

    public void spamAttackingWarning(){
        AlertDialog.Builder spamAttackingWarningDialogBuilder = new AlertDialog.Builder(this);
        spamAttackingWarningDialogBuilder.setTitle(R.string.spam_attacking_warning);
        spamAttackingWarningDialogBuilder.setPositiveButton(R.string.enable, (dialog, which) -> {
            saveToFile.saveData(this, saveToFile.isSpamAttackingEnabledFileName, "isSpamAttackingEnabled=true");
            saveToFile.saveData(this, saveToFile.wasSpamAttackingEnabledFileName, "wasSpamAttackingEnabled=true");
        });
        spamAttackingWarningDialogBuilder.setNegativeButton(R.string.cancel, (dialog, which) -> {
            dialog.dismiss();
            ((MaterialSwitch) findViewById(R.id.spamAttacking)).setOnCheckedChangeListener(null);
            ((MaterialSwitch) findViewById(R.id.spamAttacking)).setChecked(false);
            ((MaterialSwitch) findViewById(R.id.spamAttacking)).setOnCheckedChangeListener((buttonView, isChecked) -> setSpamAttackingState());
        });
        spamAttackingWarningDialogBuilder.show();
    }
}