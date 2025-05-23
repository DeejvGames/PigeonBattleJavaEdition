package pl.deejvgames.pigeonbattlejavaedition;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // GAME IS AT EARLY BETA DEVELOPMENT STAGE. FEATURES MAY BE UNBALANCED, NOT WORKING AND NOT FULLY IMPLEMENTED!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        saveToFile.createFiles(this);
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

    public void infoButtonEvent(View view){
        AlertDialog.Builder infoButtonDialogBuilder = new AlertDialog.Builder(this);
        infoButtonDialogBuilder.setTitle(R.string.info_button);
        infoButtonDialogBuilder.setMessage(getString(R.string.author_name) + " DeejvGames\n" + getString(R.string.source_code) + " https://github.com/DeejvGames/PigeonBattleJavaEdition\n" + getString(R.string.original_project_author_name) + " zntsproj\n" + getString(R.string.original_project_source_code) + " https://github.com/zntsproj/pigeonbattle"); // btw you must use getString()
        infoButtonDialogBuilder.setPositiveButton(R.string.info_button_close_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });
        infoButtonDialogBuilder.show();
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

    public void setCoinsTextView(){
        ((TextView)findViewById(R.id.coinsAmount)).setText(getString(R.string.coins_amount, userCoins));
    }

    public void setScoreTextView(){
        ((TextView)findViewById(R.id.score)).setText(getString(R.string.score, userScore));
    }
}