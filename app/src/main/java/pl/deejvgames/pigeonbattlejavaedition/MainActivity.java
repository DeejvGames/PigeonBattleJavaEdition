package pl.deejvgames.pigeonbattlejavaedition;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // GAME IS AT EARLY DEVELOPMENT STAGE. FEATURES MAY BE UNBALANCED, NOT WORKING AND NOT FULLY IMPLEMENTED!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setCoinsTextView();
        pigeonsActivity.selectedCharacter = Characters.PIGEON;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCoinsTextView();
    }

    public static int userCoins = 1200; // CHANGED TO 1200 DUE TO TESTING SHOP & PIGEONS CATEGORIES

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
}