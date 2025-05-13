package pl.deejvgames.pigeonbattlejavaedition;


import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class pigeonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pigeons);
        setUnlockedCharactersAndPowerUpsValue();
        loadPigeons();
        checkForSelectedCharacter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPigeons();
        checkForSelectedCharacter();
    }

    public static Characters selectedCharacter = Characters.PIGEON; // SELECTED CHARACTER VARIABLE

    public void createUnlockedPigeonsAndPowerUpsFileValues(){
        StringBuilder stringBuilder = new StringBuilder();
        String value;
        if(isPigeonUnlocked){
            stringBuilder.append("isPigeonUnlocked=true\n");
        } else{
            stringBuilder.append("isPigeonUnlocked=false\n");
        }
        if(isRadioPigeonUnlocked){
            stringBuilder.append("isRadioPigeonUnlocked=true\n");
        } else{
            stringBuilder.append("isRadioPigeonUnlocked=false\n");
        }
        if(isPigobombUnlocked){
            stringBuilder.append("isPigobombUnlocked=true\n");
        } else{
            stringBuilder.append("isPigobombUnlocked=false\n");
        }
        if(isFeatheredPigeonUnlocked){
            stringBuilder.append("isFeatheredPigeonUnlocked=true\n");
        } else{
            stringBuilder.append("isFeatheredPigeonUnlocked=false\n");
        }
        if(isMilkPigeonUnlocked){
            stringBuilder.append("isMilkPigeonUnlocked=true\n");
        } else{
            stringBuilder.append("isMilkPigeonUnlocked=false\n");
        }
        if(isWheelPigeonUnlocked){
            stringBuilder.append("isWheelPigeonUnlocked=true\n");
        } else{
            stringBuilder.append("isWheelPigeonUnlocked=false\n");
        }
        if(isNuclearPigeonUnlocked){
            stringBuilder.append("isNuclearPigeonUnlocked=true\n");
        } else{
            stringBuilder.append("isNuclearPigeonUnlocked=false\n");
        }
        if(isPigeoninUnlocked){
            stringBuilder.append("isPigeoninUnlocked=true\n");
        } else{
            stringBuilder.append("isPigeoninUnlocked=false\n");
        }
        value = stringBuilder.toString();
        saveToFile.saveData(this, saveToFile.unlockedPigeonsAndPowerUpsFileName, value);
    }

    public void setUnlockedCharactersAndPowerUpsValue(){
        if(Objects.equals(saveToFile.loadData(this, saveToFile.unlockedPigeonsAndPowerUpsFileName, "isRadioPigeonUnlocked"), String.valueOf(true))){
            isRadioPigeonUnlocked = true;
        } else{
            isRadioPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.loadData(this, saveToFile.unlockedPigeonsAndPowerUpsFileName, "isPigobombUnlocked"), String.valueOf(true))){
            isPigobombUnlocked = true;
        } else{
            isPigobombUnlocked = false;
        }
        if(Objects.equals(saveToFile.loadData(this, saveToFile.unlockedPigeonsAndPowerUpsFileName, "isFeatheredPigeonUnlocked"), String.valueOf(true))){
            isFeatheredPigeonUnlocked = true;
        } else{
            isFeatheredPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.loadData(this, saveToFile.unlockedPigeonsAndPowerUpsFileName, "isMilkPigeonUnlocked"), String.valueOf(true))){
            isMilkPigeonUnlocked = true;
        } else{
            isMilkPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.loadData(this, saveToFile.unlockedPigeonsAndPowerUpsFileName, "isWheelPigeonUnlocked"), String.valueOf(true))){
            isWheelPigeonUnlocked = true;
        } else{
            isWheelPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.loadData(this, saveToFile.unlockedPigeonsAndPowerUpsFileName, "isNuclearPigeonUnlocked"), String.valueOf(true))){
            isNuclearPigeonUnlocked = true;
        } else{
            isNuclearPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.loadData(this, saveToFile.unlockedPigeonsAndPowerUpsFileName, "isPigeoninUnlocked"), String.valueOf(true))){
            isPigeoninUnlocked = true;
        } else{
            isPigeoninUnlocked = false;
        }
    }

    public static boolean isPigeonUnlocked = true;
    public static boolean isRadioPigeonUnlocked;
    public static boolean isPigobombUnlocked;
    public static boolean isFeatheredPigeonUnlocked;
    public static boolean isMilkPigeonUnlocked;
    public static boolean isWheelPigeonUnlocked;
    public static boolean isNuclearPigeonUnlocked;
    public static boolean isPigeoninUnlocked;

    public static boolean isPigeoninSelected;

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
        if(isPigeoninUnlocked){
            findViewById(R.id.powerUpsTitle).setVisibility(VISIBLE);
            findViewById(R.id.pigeoninImageView).setVisibility(VISIBLE);
            findViewById(R.id.pigeoninTitle).setVisibility(VISIBLE);
            findViewById(R.id.pigeoninBenefits).setVisibility(VISIBLE);
            findViewById(R.id.pigeoninSelect).setVisibility(VISIBLE);
        }
    }

    Characters pigeon = Characters.PIGEON;
    Characters radioPigeon = Characters.RADIO_PIGEON;
    Characters pigobomb = Characters.PIGOBOMB;
    Characters featheredPigeon = Characters.FEATHERED_PIGEON;
    Characters milkPigeon = Characters.MILK_PIGEON;
    Characters wheelPigeon = Characters.WHEEL_PIGEON;
    Characters nuclearPigeon = Characters.NUCLEAR_PIGEON;
    PowerUps pigeonin = PowerUps.PIGEONIN;

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
        findViewById(R.id.pigeoninSelect).setEnabled(true);
        ((Button)findViewById(R.id.pigeoninSelect)).setText(R.string.select);
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
        saveToFile.saveData(this, saveToFile.selectedCharacterFileName, "selectedCharacter="+Characters.PIGEON);
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
        saveToFile.saveData(this, saveToFile.selectedCharacterFileName, "selectedCharacter="+Characters.RADIO_PIGEON);
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
        saveToFile.saveData(this, saveToFile.selectedCharacterFileName, "selectedCharacter="+Characters.PIGOBOMB);
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
        saveToFile.saveData(this, saveToFile.selectedCharacterFileName, "selectedCharacter="+Characters.FEATHERED_PIGEON);
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
        saveToFile.saveData(this, saveToFile.selectedCharacterFileName, "selectedCharacter="+Characters.MILK_PIGEON);
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
        saveToFile.saveData(this, saveToFile.selectedCharacterFileName, "selectedCharacter="+Characters.WHEEL_PIGEON);
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
        saveToFile.saveData(this, saveToFile.selectedCharacterFileName, "selectedCharacter="+Characters.NUCLEAR_PIGEON);
    }

    public void onPigeoninSelect(View view){
        if(!isPigeoninSelected){
            ((Button)findViewById(R.id.pigeoninSelect)).setText(R.string.unselect);
            isPigeoninSelected = true;
        } else{
            ((Button)findViewById(R.id.pigeoninSelect)).setText(R.string.select);
            isPigeoninSelected = false;
        }
    }

    public void checkForSelectedCharacter(){
        if(selectedCharacter == Characters.PIGEON){
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
        if(selectedCharacter == Characters.RADIO_PIGEON){
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
        }
        if(selectedCharacter == Characters.PIGOBOMB){
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
        }
        if(selectedCharacter == Characters.FEATHERED_PIGEON){
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
        }
        if(selectedCharacter == Characters.MILK_PIGEON){
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
        }
        if(selectedCharacter == Characters.WHEEL_PIGEON){
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
        }
        if(selectedCharacter == Characters.NUCLEAR_PIGEON){
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
        }
        if(isPigeoninSelected){
            ((Button)findViewById(R.id.pigeoninSelect)).setText(R.string.unselect);
        }
    }
}