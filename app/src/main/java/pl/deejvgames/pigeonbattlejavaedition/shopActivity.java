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
        checkForBoughtItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForBoughtItems();
    }

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
                saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(userCoins-radiopigeon.getPrice()));
                userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
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
                saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(userCoins-pigobomb.getPrice()));
                userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
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
                saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(userCoins-featheredpigeon.getPrice()));
                userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
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
                saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(userCoins-milkpiegeon.getPrice()));
                userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
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
                saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(userCoins-wheelpigeon.getPrice()));
                userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
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
                saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(userCoins-nuclearpigeon.getPrice()));
                userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
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
                saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(userCoins-pigeonin.getPrice()));
                userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
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