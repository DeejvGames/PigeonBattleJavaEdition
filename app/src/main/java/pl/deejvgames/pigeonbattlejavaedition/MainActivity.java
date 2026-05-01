package pl.deejvgames.pigeonbattlejavaedition;

import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.coinsKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.languageKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.oledModeEnabledKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.readData;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.scoreKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.selectedCharacterKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.selectedPowerUpKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.wasSpamAttackingEnabledKey;
import static pl.deejvgames.pigeonbattlejavaedition.settingsActivity.isOledModeEnabled;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        saveToFile.convertFiles(this);
        saveToFile.createFiles(this);
        saveToFile.loadValues(this);
        setLanguage();
        setContentView(R.layout.activity_main);
        initUserCoinsAndScore(this);
        setCoinsTextView();
        setScoreTextView();
        isOledModeEnabled = Boolean.parseBoolean(readData(this, oledModeEnabledKey));
        pigeonsActivity.selectedCharacter = Characters.valueOf(saveToFile.readData(this, selectedCharacterKey));
        if(!Objects.equals(saveToFile.readData(this, selectedPowerUpKey), "null")){
            pigeonsActivity.selectedPowerUp = PowerUps.valueOf(saveToFile.readData(this, selectedPowerUpKey));
        }
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onResume() {
        super.onResume();
        initUserCoinsAndScore(this);
        setCoinsTextView();
        setScoreTextView();
        if(isOledModeEnabled){
            findViewById(R.id.main).setBackgroundColor(Color.rgb(0, 0, 0));
        } else{
            findViewById(R.id.main).setBackgroundColor(Color.parseColor(getString(R.color.theme)));
        }
    }

    public static int userCoins;
    public static int userScore;

    public void initUserCoinsAndScore(Context context){
        userCoins = Integer.parseInt(saveToFile.readData(this, coinsKey));
        userScore = Integer.parseInt(saveToFile.readData(this, scoreKey));
    }

    public void setLanguage(){
        Locale.setDefault(Locale.forLanguageTag(saveToFile.readData(this, languageKey)));
        Configuration config = new Configuration();
        config.setLocale(Locale.forLanguageTag(saveToFile.readData(this, languageKey)));
        getApplicationContext().createConfigurationContext(config);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    public void openPlayActivity(View view){
        Intent intent = new Intent(MainActivity.this, playActivity.class);
        startActivity(intent);
    }

    public void openShopActivity(View view){
        Intent intent = new Intent(MainActivity.this, shopActivity.class);
        startActivity(intent);
    }

    public void openCharacterActivity(View view){
        Intent intent = new Intent(MainActivity.this, CharacterActivity.class);
        startActivity(intent);
    }

    public void openSettingsActivity(View view){
        Intent intent = new Intent(MainActivity.this, settingsActivity.class);
        startActivity(intent);
    }

    public void setCoinsTextView(){
        if(Objects.equals(saveToFile.readData(this, wasSpamAttackingEnabledKey), String.valueOf(true))){
            ((TextView)findViewById(R.id.coinsAmount)).setText(getString(R.string.coins_amount, userCoins)+"*");
        } else{
            ((TextView)findViewById(R.id.coinsAmount)).setText(getString(R.string.coins_amount, userCoins));
        }
    }

    public void setScoreTextView(){
        if(Objects.equals(saveToFile.readData(this, wasSpamAttackingEnabledKey), String.valueOf(true))){
            ((TextView)findViewById(R.id.score)).setText(getString(R.string.score, userScore)+"*");
        } else{
            ((TextView)findViewById(R.id.score)).setText(getString(R.string.score, userScore));
        }
    }
}