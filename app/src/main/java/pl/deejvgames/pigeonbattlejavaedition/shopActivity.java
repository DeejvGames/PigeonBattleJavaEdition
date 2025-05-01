package pl.deejvgames.pigeonbattlejavaedition;

import static pl.deejvgames.pigeonbattlejavaedition.MainActivity.userCoins;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isFeatheredPigeonUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isMilkPigeonUnlocked;
import static pl.deejvgames.pigeonbattlejavaedition.pigeonsActivity.isNuclearPigeonUnlocked;
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
                userCoins -= radiopigeon.getPrice();
                ((Button) findViewById(R.id.radioPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.radioPigeonBuy).setEnabled(false);
                isRadioPigeonUnlocked = true;
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
                userCoins -= pigobomb.getPrice();
                ((Button) findViewById(R.id.pigobombBuy)).setText(getString(R.string.bought));
                findViewById(R.id.pigobombBuy).setEnabled(false);
                isPigobombUnlocked = true;
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
                userCoins -= featheredpigeon.getPrice();
                ((Button) findViewById(R.id.featheredPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.featheredPigeonBuy).setEnabled(false);
                isFeatheredPigeonUnlocked = true;
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
                userCoins -= milkpiegeon.getPrice();
                ((Button) findViewById(R.id.milkPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.milkPigeonBuy).setEnabled(false);
                isMilkPigeonUnlocked = true;
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
                userCoins -= wheelpigeon.getPrice();
                ((Button) findViewById(R.id.wheelPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.wheelPigeonBuy).setEnabled(false);
                isWheelPigeonUnlocked = true;
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
                userCoins -= nuclearpigeon.getPrice();
                ((Button) findViewById(R.id.nuclearPigeonBuy)).setText(getString(R.string.bought));
                findViewById(R.id.nuclearPigeonBuy).setEnabled(false);
                isNuclearPigeonUnlocked = true;
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
                userCoins -= pigeonin.getPrice();
                ((Button) findViewById(R.id.pigeoninBuy)).setText(getString(R.string.bought));
                findViewById(R.id.pigeoninBuy).setEnabled(false);
                isPigeoninUnlocked = true;
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