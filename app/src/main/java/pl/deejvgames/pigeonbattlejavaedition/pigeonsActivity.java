package pl.deejvgames.pigeonbattlejavaedition;


import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class pigeonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pigeons);
        loadPigeons();
        defaultSettings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPigeons();
    }

    public static Characters selectedCharacter = Characters.PIGEON; // SELECTED CHARACTER VARIABLE

    public static boolean isPigeonUnclocked = true;
    public static boolean isRadioPigeonUnlocked = false;
    public static boolean isPigobombUnlocked = false;
    public static boolean isFeatheredPigeonUnlocked = false;
    public static boolean isMilkPigeonUnlocked = false;
    public static boolean isWheelPigeonUnlocked = false;
    public static boolean isNuclearPigeonUnlocked = false;

    public static boolean isPigeoninUnlocked = false;

    public void loadPigeons(){
        if(isRadioPigeonUnlocked){
            findViewById(R.id.radioPigeonImageView).setVisibility(VISIBLE);
            findViewById(R.id.radioPigeonTitle).setVisibility(VISIBLE);
            findViewById(R.id.radioPigeonBenefits).setVisibility(VISIBLE);
            findViewById(R.id.radioPigeonSelect).setVisibility(VISIBLE);
        }
        if(isPigobombUnlocked){
            findViewById(R.id.pigobombImageView).setVisibility(VISIBLE);
            findViewById(R.id.pigobombTitle).setVisibility(VISIBLE);
            findViewById(R.id.pigobombBenefits).setVisibility(VISIBLE);
            findViewById(R.id.pigobombSelect).setVisibility(VISIBLE);
        }
        if(isFeatheredPigeonUnlocked){
            findViewById(R.id.featheredPigeonImageView).setVisibility(VISIBLE);
            findViewById(R.id.featheredPigeonTitle).setVisibility(VISIBLE);
            findViewById(R.id.featheredPigeonBenefits).setVisibility(VISIBLE);
            findViewById(R.id.featheredPigeonSelect).setVisibility(VISIBLE);
        }
        if(isMilkPigeonUnlocked){
            findViewById(R.id.milkPigeonImageView).setVisibility(VISIBLE);
            findViewById(R.id.milkPigeonTitle).setVisibility(VISIBLE);
            findViewById(R.id.milkPigeonBenefits).setVisibility(VISIBLE);
            findViewById(R.id.milkPigeonSelect).setVisibility(VISIBLE);
        }
        if(isWheelPigeonUnlocked){
            findViewById(R.id.wheelPigeonImageView).setVisibility(VISIBLE);
            findViewById(R.id.wheelPigeonTitle).setVisibility(VISIBLE);
            findViewById(R.id.wheelPigeonBenefits).setVisibility(VISIBLE);
            findViewById(R.id.wheelPigeonSelect).setVisibility(VISIBLE);
        }
        if(isNuclearPigeonUnlocked){
            findViewById(R.id.nuclearPigeonImageView).setVisibility(VISIBLE);
            findViewById(R.id.nuclearPigeonTitle).setVisibility(VISIBLE);
            findViewById(R.id.nuclearPigeonBenefits).setVisibility(VISIBLE);
            findViewById(R.id.nuclearPigeonSelect).setVisibility(VISIBLE);
        }
    }

    Characters pigeon = Characters.PIGEON;
    Characters radioPigeon = Characters.RADIO_PIGEON;
    Characters pigobomb = Characters.PIGOBOMB;
    Characters featheredPigeon = Characters.FEATHERED_PIGEON;
    Characters milkPigeon = Characters.MILK_PIGEON;
    Characters wheelPigeon = Characters.WHEEL_PIGEON;
    Characters nuclearPigeon = Characters.NUCLEAR_PIGEON;

    public void defaultSettings(){
        findViewById(R.id.pigeonSelect).setEnabled(false);
        ((Button)findViewById(R.id.pigeonSelect)).setText(R.string.selected);
        findViewById(R.id.radioPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.radioPigeonSelect)).setText(R.string.select);
        findViewById(R.id.pigobombSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigobombSelect)).setText(R.string.select);
        findViewById(R.id.featheredPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.featheredPigeonSelect)).setText(R.string.select);
        findViewById(R.id.milkPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.milkPigeonSelect)).setText(R.string.select);
        findViewById(R.id.wheelPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.wheelPigeonSelect)).setText(R.string.select);
        findViewById(R.id.nuclearPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.nuclearPigeonSelect)).setText(R.string.select);
    }

    public void onPigeonSelect(View view){
        findViewById(R.id.pigeonSelect).setEnabled(false);
        ((Button)findViewById(R.id.pigeonSelect)).setText(R.string.selected);
        findViewById(R.id.radioPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.radioPigeonSelect)).setText(R.string.select);
        findViewById(R.id.pigobombSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigobombSelect)).setText(R.string.select);
        findViewById(R.id.featheredPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.featheredPigeonSelect)).setText(R.string.select);
        findViewById(R.id.milkPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.milkPigeonSelect)).setText(R.string.select);
        findViewById(R.id.wheelPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.wheelPigeonSelect)).setText(R.string.select);
        findViewById(R.id.nuclearPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.nuclearPigeonSelect)).setText(R.string.select);
        selectedCharacter = Characters.PIGEON;
    }

    public void onRadioPigeonSelect(View view){
        findViewById(R.id.pigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigeonSelect)).setText(R.string.select);
        findViewById(R.id.radioPigeonSelect).setEnabled(false);
        ((Button)findViewById(R.id.radioPigeonSelect)).setText(R.string.selected);
        findViewById(R.id.pigobombSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigobombSelect)).setText(R.string.select);
        findViewById(R.id.featheredPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.featheredPigeonSelect)).setText(R.string.select);
        findViewById(R.id.milkPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.milkPigeonSelect)).setText(R.string.select);
        findViewById(R.id.wheelPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.wheelPigeonSelect)).setText(R.string.select);
        findViewById(R.id.nuclearPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.nuclearPigeonSelect)).setText(R.string.select);
        selectedCharacter = Characters.RADIO_PIGEON;
    }

    public void onPigobombSelect(View view){
        findViewById(R.id.pigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigeonSelect)).setText(R.string.select);
        findViewById(R.id.radioPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.radioPigeonSelect)).setText(R.string.select);
        findViewById(R.id.pigobombSelect).setEnabled(false);
        ((Button)findViewById(R.id.pigobombSelect)).setText(R.string.selected);
        findViewById(R.id.featheredPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.featheredPigeonSelect)).setText(R.string.select);
        findViewById(R.id.milkPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.milkPigeonSelect)).setText(R.string.select);
        findViewById(R.id.wheelPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.wheelPigeonSelect)).setText(R.string.select);
        findViewById(R.id.nuclearPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.nuclearPigeonSelect)).setText(R.string.select);
        selectedCharacter = Characters.PIGOBOMB;
    }

    public void onFeatheredPigeonSelect(View view){
        findViewById(R.id.pigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigeonSelect)).setText(R.string.select);
        findViewById(R.id.radioPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.radioPigeonSelect)).setText(R.string.select);
        findViewById(R.id.pigobombSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigobombSelect)).setText(R.string.select);
        findViewById(R.id.featheredPigeonSelect).setEnabled(false);
        ((Button)findViewById(R.id.featheredPigeonSelect)).setText(R.string.selected);
        findViewById(R.id.milkPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.milkPigeonSelect)).setText(R.string.select);
        findViewById(R.id.wheelPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.wheelPigeonSelect)).setText(R.string.select);
        findViewById(R.id.nuclearPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.nuclearPigeonSelect)).setText(R.string.select);
        selectedCharacter = Characters.FEATHERED_PIGEON;
    }

    public void onMilkPigeonSelect(View view){
        findViewById(R.id.pigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigeonSelect)).setText(R.string.select);
        findViewById(R.id.radioPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.radioPigeonSelect)).setText(R.string.select);
        findViewById(R.id.pigobombSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigobombSelect)).setText(R.string.select);
        findViewById(R.id.featheredPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.featheredPigeonSelect)).setText(R.string.select);
        findViewById(R.id.milkPigeonSelect).setEnabled(false);
        ((Button)findViewById(R.id.milkPigeonSelect)).setText(R.string.selected);
        findViewById(R.id.wheelPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.wheelPigeonSelect)).setText(R.string.select);
        findViewById(R.id.nuclearPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.nuclearPigeonSelect)).setText(R.string.select);
        selectedCharacter = Characters.MILK_PIGEON;
    }

    public void onWheelPigeonSelect(View view){
        findViewById(R.id.pigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigeonSelect)).setText(R.string.select);
        findViewById(R.id.radioPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.radioPigeonSelect)).setText(R.string.select);
        findViewById(R.id.pigobombSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigobombSelect)).setText(R.string.select);
        findViewById(R.id.featheredPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.featheredPigeonSelect)).setText(R.string.select);
        findViewById(R.id.milkPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.milkPigeonSelect)).setText(R.string.select);
        findViewById(R.id.wheelPigeonSelect).setEnabled(false);
        ((Button)findViewById(R.id.wheelPigeonSelect)).setText(R.string.selected);
        findViewById(R.id.nuclearPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.nuclearPigeonSelect)).setText(R.string.select);
        selectedCharacter = Characters.WHEEL_PIGEON;
    }

    public void onNuclearPigeonSelect(View view){
        findViewById(R.id.pigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigeonSelect)).setText(R.string.select);
        findViewById(R.id.radioPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.radioPigeonSelect)).setText(R.string.select);
        findViewById(R.id.pigobombSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigobombSelect)).setText(R.string.select);
        findViewById(R.id.featheredPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.featheredPigeonSelect)).setText(R.string.select);
        findViewById(R.id.milkPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.milkPigeonSelect)).setText(R.string.select);
        findViewById(R.id.wheelPigeonSelect).setEnabled(true);
        ((Button)findViewById(R.id.wheelPigeonSelect)).setText(R.string.select);
        findViewById(R.id.nuclearPigeonSelect).setEnabled(false);
        ((Button)findViewById(R.id.nuclearPigeonSelect)).setText(R.string.selected);
        selectedCharacter = Characters.NUCLEAR_PIGEON;
    }

}