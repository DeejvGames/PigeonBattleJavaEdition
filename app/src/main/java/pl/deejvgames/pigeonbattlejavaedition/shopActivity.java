package pl.deejvgames.pigeonbattlejavaedition;

import static pl.deejvgames.pigeonbattlejavaedition.MainActivity.userCoins;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isFeatheredPigeonUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isMilkPigeonUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isNuclearPigeonUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isPigeonUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isPigeoninUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isPigobombUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isRadioPigeonUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isWheelPigeonUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.coinsKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.featheredPigeonUnlockedKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.milkPigeonUnlockedKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.nuclearPigeonUnlockedKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.oledModeEnabledKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.pigeonUnlockedKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.pigeoninUnlockedKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.pigobombUnlockedKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.radioPigeonUnlockedKey;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.readData;
import static pl.deejvgames.pigeonbattlejavaedition.saveToFile.wheelPigeonUnlockedKey;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class shopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);
        if(Boolean.parseBoolean(readData(this, oledModeEnabledKey))){
            findViewById(R.id.main).setBackgroundColor(Color.rgb(0, 0, 0));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUnlockedCharactersAndPowerUpsValue();
        checkForBoughtItems();
    }

    public void createUnlockedPigeonsAndPowerUpsFileValues(){
        if(isPigeonUnlocked){
            saveToFile.writeData(this, pigeonUnlockedKey, String.valueOf(true));
        } else{
            saveToFile.writeData(this, pigeonUnlockedKey, String.valueOf(false));
        }
        if(isRadioPigeonUnlocked){
            saveToFile.writeData(this, radioPigeonUnlockedKey, String.valueOf(true));
        } else{
            saveToFile.writeData(this, radioPigeonUnlockedKey, String.valueOf(false));
        }
        if(isPigobombUnlocked){
            saveToFile.writeData(this, pigobombUnlockedKey, String.valueOf(true));
        } else{
            saveToFile.writeData(this, pigobombUnlockedKey, String.valueOf(false));
        }
        if(isFeatheredPigeonUnlocked){
            saveToFile.writeData(this, featheredPigeonUnlockedKey, String.valueOf(true));
        } else{
            saveToFile.writeData(this, featheredPigeonUnlockedKey, String.valueOf(false));
        }
        if(isMilkPigeonUnlocked){
            saveToFile.writeData(this, milkPigeonUnlockedKey, String.valueOf(true));
        } else{
            saveToFile.writeData(this, milkPigeonUnlockedKey, String.valueOf(false));
        }
        if(isWheelPigeonUnlocked){
            saveToFile.writeData(this, wheelPigeonUnlockedKey, String.valueOf(true));
        } else{
            saveToFile.writeData(this, wheelPigeonUnlockedKey, String.valueOf(false));
        }
        if(isNuclearPigeonUnlocked){
            saveToFile.writeData(this, nuclearPigeonUnlockedKey, String.valueOf(true));
        } else{
            saveToFile.writeData(this, nuclearPigeonUnlockedKey, String.valueOf(false));
        }
        if(isPigeoninUnlocked){
            saveToFile.writeData(this, pigeoninUnlockedKey, String.valueOf(true));
        } else{
            saveToFile.writeData(this, pigeoninUnlockedKey, String.valueOf(false));
        }
    }

    public void setUnlockedCharactersAndPowerUpsValue(){
        if(Objects.equals(saveToFile.readData(this, radioPigeonUnlockedKey), String.valueOf(true))){
            isRadioPigeonUnlocked = true;
        } else{
            isRadioPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.readData(this, pigobombUnlockedKey), String.valueOf(true))){
            isPigobombUnlocked = true;
        } else{
            isPigobombUnlocked = false;
        }
        if(Objects.equals(saveToFile.readData(this, featheredPigeonUnlockedKey), String.valueOf(true))){
            isFeatheredPigeonUnlocked = true;
        } else{
            isFeatheredPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.readData(this, milkPigeonUnlockedKey), String.valueOf(true))){
            isMilkPigeonUnlocked = true;
        } else{
            isMilkPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.readData(this, wheelPigeonUnlockedKey), String.valueOf(true))){
            isWheelPigeonUnlocked = true;
        } else{
            isWheelPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.readData(this, nuclearPigeonUnlockedKey), String.valueOf(true))){
            isNuclearPigeonUnlocked = true;
        } else{
            isNuclearPigeonUnlocked = false;
        }
        if(Objects.equals(saveToFile.readData(this, pigeoninUnlockedKey), String.valueOf(true))){
            isPigeoninUnlocked = true;
        } else{
            isPigeoninUnlocked = false;
        }
    }

    Characters pigeon = Characters.PIGEON;
    Characters radiopigeon = Characters.RADIO_PIGEON;
    Characters pigobomb = Characters.PIGOBOMB;
    Characters featheredpigeon = Characters.FEATHERED_PIGEON;
    Characters milkpiegeon = Characters.MILK_PIGEON;
    Characters wheelpigeon = Characters.WHEEL_PIGEON;
    Characters nuclearpigeon = Characters.NUCLEAR_PIGEON;
    PowerUps pigeonin = PowerUps.PIGEONIN;

    public void buyRadioPigeon(View view){
        if(!isRadioPigeonUnlocked){
            if(userCoins >= radiopigeon.getPrice()){
                userCoins-=radiopigeon.getPrice();
                saveToFile.writeData(this, coinsKey, String.valueOf(userCoins));
                ((Button) findViewById(R.id.radioPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.radioPigeonBuy).setEnabled(false);
                isRadioPigeonUnlocked = true;
                createUnlockedPigeonsAndPowerUpsFileValues();
                setUnlockedCharactersAndPowerUpsValue();
                Toast.makeText(this, getString(R.string.successfully_bought), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, getString(R.string.not_enough_coins), Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, getString(R.string.already_bought), Toast.LENGTH_SHORT).show();
        }
    }

    public void buyPigobomb(View view){
        if(!isPigobombUnlocked){
            if(userCoins >= pigobomb.getPrice()){
                userCoins-=pigobomb.getPrice();
                saveToFile.writeData(this, coinsKey, String.valueOf(userCoins));
                ((Button) findViewById(R.id.pigobombBuy)).setText(getString(R.string.bought));
                findViewById(R.id.pigobombBuy).setEnabled(false);
                isPigobombUnlocked = true;
                createUnlockedPigeonsAndPowerUpsFileValues();
                setUnlockedCharactersAndPowerUpsValue();
                Toast.makeText(this, getString(R.string.successfully_bought), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, getString(R.string.not_enough_coins), Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, getString(R.string.already_bought), Toast.LENGTH_SHORT).show();
        }
    }

    public void buyFeatheredPigeon(View view){
        if(!isFeatheredPigeonUnlocked){
            if(userCoins >= featheredpigeon.getPrice()){
                userCoins-=featheredpigeon.getPrice();
                saveToFile.writeData(this, coinsKey, String.valueOf(userCoins));
                ((Button) findViewById(R.id.featheredPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.featheredPigeonBuy).setEnabled(false);
                isFeatheredPigeonUnlocked = true;
                createUnlockedPigeonsAndPowerUpsFileValues();
                setUnlockedCharactersAndPowerUpsValue();
                Toast.makeText(this, getString(R.string.successfully_bought), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, getString(R.string.not_enough_coins), Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, getString(R.string.already_bought), Toast.LENGTH_SHORT).show();
        }
    }

    public void buyMilkPigeon(View view){
        if(!isMilkPigeonUnlocked){
            if(userCoins >= milkpiegeon.getPrice()){
                userCoins-=milkpiegeon.getPrice();
                saveToFile.writeData(this, coinsKey, String.valueOf(userCoins));
                ((Button) findViewById(R.id.milkPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.milkPigeonBuy).setEnabled(false);
                isMilkPigeonUnlocked = true;
                createUnlockedPigeonsAndPowerUpsFileValues();
                setUnlockedCharactersAndPowerUpsValue();
                Toast.makeText(this, getString(R.string.successfully_bought), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, getString(R.string.not_enough_coins), Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, getString(R.string.already_bought), Toast.LENGTH_SHORT).show();
        }
    }

    public void buyWheelPigeon(View view){
        if(!isWheelPigeonUnlocked){
            if(userCoins >= wheelpigeon.getPrice()){
                userCoins-=wheelpigeon.getPrice();
                saveToFile.writeData(this, coinsKey, String.valueOf(userCoins));
                ((Button) findViewById(R.id.wheelPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.wheelPigeonBuy).setEnabled(false);
                isWheelPigeonUnlocked = true;
                createUnlockedPigeonsAndPowerUpsFileValues();
                setUnlockedCharactersAndPowerUpsValue();
                Toast.makeText(this, getString(R.string.successfully_bought), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, getString(R.string.not_enough_coins), Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, getString(R.string.already_bought), Toast.LENGTH_SHORT).show();
        }
    }

    public void buyNuclearPigeon(View view){
        if(!isNuclearPigeonUnlocked){
            if(userCoins >= nuclearpigeon.getPrice()){
                userCoins-=nuclearpigeon.getPrice();
                saveToFile.writeData(this, coinsKey, String.valueOf(userCoins));
                ((Button) findViewById(R.id.nuclearPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.nuclearPigeonBuy).setEnabled(false);
                isNuclearPigeonUnlocked = true;
                createUnlockedPigeonsAndPowerUpsFileValues();
                setUnlockedCharactersAndPowerUpsValue();
                Toast.makeText(this, getString(R.string.successfully_bought), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, getString(R.string.not_enough_coins), Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, getString(R.string.already_bought), Toast.LENGTH_SHORT).show();
        }
    }

    public void buyPigeonin(View view){
        if(!isPigeoninUnlocked){
            if(userCoins >= pigeonin.getPrice()){
                userCoins-=pigeonin.getPrice();
                saveToFile.writeData(this, coinsKey, String.valueOf(userCoins));
                ((Button) findViewById(R.id.pigeoninBuy)).setText(getString(R.string.bought));
                findViewById(R.id.pigeoninBuy).setEnabled(false);
                isPigeoninUnlocked = true;
                createUnlockedPigeonsAndPowerUpsFileValues();
                setUnlockedCharactersAndPowerUpsValue();
                Toast.makeText(this, getString(R.string.successfully_bought), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, getString(R.string.not_enough_coins), Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, getString(R.string.already_bought), Toast.LENGTH_SHORT).show();
        }
    }

    public void checkForBoughtItems(){
        if(isRadioPigeonUnlocked){
            ((Button) findViewById(R.id.radioPigeonBuy)).setText(getString(R.string.bought));
            findViewById(R.id.radioPigeonBuy).setEnabled(false);
        }
        if(isPigobombUnlocked){
            ((Button) findViewById(R.id.pigobombBuy)).setText(getString(R.string.bought));
            findViewById(R.id.pigobombBuy).setEnabled(false);
        }
        if(isFeatheredPigeonUnlocked){
            ((Button) findViewById(R.id.featheredPigeonBuy)).setText(getString(R.string.bought));
            findViewById(R.id.featheredPigeonBuy).setEnabled(false);
        }
        if(isMilkPigeonUnlocked){
            ((Button) findViewById(R.id.milkPigeonBuy)).setText(getString(R.string.bought));
            findViewById(R.id.milkPigeonBuy).setEnabled(false);
        }
        if(isWheelPigeonUnlocked){
            ((Button) findViewById(R.id.wheelPigeonBuy)).setText(getString(R.string.bought));
            findViewById(R.id.wheelPigeonBuy).setEnabled(false);
        }
        if(isNuclearPigeonUnlocked){
            ((Button) findViewById(R.id.nuclearPigeonBuy)).setText(getString(R.string.bought));
            findViewById(R.id.nuclearPigeonBuy).setEnabled(false);
        }
        if(isPigeoninUnlocked){
            ((Button) findViewById(R.id.pigeoninBuy)).setText(getString(R.string.bought));
            findViewById(R.id.pigeoninBuy).setEnabled(false);
        }
    }
}