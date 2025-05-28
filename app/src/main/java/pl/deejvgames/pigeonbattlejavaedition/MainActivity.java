package pl.deejvgames.pigeonbattlejavaedition;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // GAME IS AT EARLY BETA DEVELOPMENT STAGE. FEATURES MAY BE UNBALANCED, NOT WORKING AND NOT FULLY IMPLEMENTED!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        saveToFile.createFiles(this);
        setLanguage();
        setContentView(R.layout.activity_main);
        initUserCoinsAndScore(this);
        setCoinsTextView();
        setScoreTextView();
        pigeonsActivity.selectedCharacter = Characters.valueOf(saveToFile.loadData(this, saveToFile.selectedCharacterFileName, "selectedCharacter"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUserCoinsAndScore(this);
        setCoinsTextView();
        setScoreTextView();
    }

    public static int userCoins;
    public static int userScore;

    public void initUserCoinsAndScore(Context context){
        userCoins = Integer.parseInt(saveToFile.loadData(context, saveToFile.coinsFileName, "userCoins"));
        userScore = Integer.parseInt(saveToFile.loadData(context, saveToFile.scoreFileName, "score"));
    }

    public void setLanguage(){
        Locale.setDefault(Locale.forLanguageTag(saveToFile.loadData(this, saveToFile.selectedLanguageFileName, "language")));
        Configuration config = new Configuration();
        config.setLocale(Locale.forLanguageTag(saveToFile.loadData(this, saveToFile.selectedLanguageFileName, "language")));
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

    public void openPigeonsActivity(View view){
        Intent intent = new Intent(MainActivity.this, pigeonsActivity.class);
        startActivity(intent);
    }

    public void openSettingsActivity(View view){
        Intent intent = new Intent(MainActivity.this, settingsActivity.class);
        startActivity(intent);
    }

    public void setCoinsTextView(){
        if(Objects.equals(saveToFile.loadData(this, saveToFile.wasSpamAttackingEnabledFileName, "wasSpamAttackingEnabled"), String.valueOf(true))){
            ((TextView)findViewById(R.id.coinsAmount)).setText(getString(R.string.coins_amount, userCoins)+"*");
        } else{
            ((TextView)findViewById(R.id.coinsAmount)).setText(getString(R.string.coins_amount, userCoins));
        }
    }

    public void setScoreTextView(){
        if(Objects.equals(saveToFile.loadData(this, saveToFile.wasSpamAttackingEnabledFileName, "wasSpamAttackingEnabled"), String.valueOf(true))){
            ((TextView)findViewById(R.id.score)).setText(getString(R.string.score, userScore)+"*");
        } else{
            ((TextView)findViewById(R.id.score)).setText(getString(R.string.score, userScore));
        }
    }
}