package pl.deejvgames.pigeonbattlejavaedition;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_character);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setPlayerImageAndCharacterName();
        setPowerUpVisibilityAndCharacterName();
        getCharacterProperties();
        ((TextView) findViewById(R.id.characterName)).setText(characterName);
        ((TextView) findViewById(R.id.characterProperties)).setText(characterProperties);
    }

    public void customizeCharacter(View view){
        Intent intent = new Intent(CharacterActivity.this, pigeonsActivity.class);
        startActivity(intent);
    }

    String characterName;
    String characterProperties;

    int totalDamage;
    int totalHp;

    public void setPlayerImageAndCharacterName(){
        if(pigeonsActivity.selectedCharacter == Characters.PIGEON){
            ((ImageView) findViewById(R.id.characterImageView)).setImageResource(R.drawable.pigeon);
            characterName = getString(R.string.default_pigeon);
        } else if(pigeonsActivity.selectedCharacter == Characters.RADIO_PIGEON){
            ((ImageView) findViewById(R.id.characterImageView)).setImageResource(R.drawable.radio_pigeon);
            characterName = getString(R.string.radio_pigeon);
        } else if(pigeonsActivity.selectedCharacter == Characters.PIGOBOMB){
            ((ImageView) findViewById(R.id.characterImageView)).setImageResource(R.drawable.pigobomb);
            characterName = getString(R.string.pigobomb);
        } else if(pigeonsActivity.selectedCharacter == Characters.FEATHERED_PIGEON){
            ((ImageView) findViewById(R.id.characterImageView)).setImageResource(R.drawable.feathered_pigeon);
            characterName = getString(R.string.feathered_pigeon);
        } else if(pigeonsActivity.selectedCharacter == Characters.MILK_PIGEON){
            ((ImageView) findViewById(R.id.characterImageView)).setImageResource(R.drawable.milk_pigeon);
            characterName = getString(R.string.milk_pigeon);
        } else if(pigeonsActivity.selectedCharacter == Characters.WHEEL_PIGEON){
            ((ImageView) findViewById(R.id.characterImageView)).setImageResource(R.drawable.wheel_pigeon);
            characterName = getString(R.string.wheel_pigeon);
        } else if(pigeonsActivity.selectedCharacter == Characters.NUCLEAR_PIGEON){
            ((ImageView) findViewById(R.id.characterImageView)).setImageResource(R.drawable.nuclear_pigeon);
            characterName = getString(R.string.nuclear_pigeon);
        }
    }

    public void setPowerUpVisibilityAndCharacterName(){
        if(pigeonsActivity.isPigeoninSelected){
            findViewById(R.id.powerUpImageView).setVisibility(VISIBLE);
            characterName = characterName+" "+getString(R.string.with)+" "+getString(R.string.pigeonin2);
        } else{
            findViewById(R.id.powerUpImageView).setVisibility(INVISIBLE);
        }
    }

    public void getTotalDamageAndHp(){
        if(pigeonsActivity.isPigeoninSelected){
            totalDamage = pigeonsActivity.selectedCharacter.getCharacterDamage()+PowerUps.PIGEONIN.getAdditonalDamage();
            totalHp = pigeonsActivity.selectedCharacter.getHP()+PowerUps.PIGEONIN.getAdditonalHp();
        } else{
            totalDamage = pigeonsActivity.selectedCharacter.getCharacterDamage();
            totalHp = pigeonsActivity.selectedCharacter.getHP();
        }
    }

    public void getCharacterProperties(){
        getTotalDamageAndHp();
        characterProperties = getString(R.string.hasHp, totalHp)+"\n"+getString(R.string.dealsDamage, totalDamage)+"\n";
        if(pigeonsActivity.selectedCharacter.getCharacterDamagePerSecond() != 0){
            characterProperties = characterProperties+getString(R.string.dealsDamagePerSecond, pigeonsActivity.selectedCharacter.getCharacterDamagePerSecond())+"\n";
        }
        if(pigeonsActivity.selectedCharacter.getCharacterLessDamage() != 0){
            characterProperties = characterProperties+getString(R.string.takesLessDamage, pigeonsActivity.selectedCharacter.getCharacterLessDamage()).replace(String.valueOf(pigeonsActivity.selectedCharacter.getCharacterLessDamage()), pigeonsActivity.selectedCharacter.getCharacterLessDamage()+"%")+"\n";
        }
        if(pigeonsActivity.selectedCharacter.getCharacterSpeedBoost() != 0){
            characterProperties = characterProperties+getString(R.string.isFaster, pigeonsActivity.selectedCharacter.getCharacterSpeedBoost()).replace(String.valueOf(pigeonsActivity.selectedCharacter.getCharacterSpeedBoost()), pigeonsActivity.selectedCharacter.getCharacterSpeedBoost()+"%");
        }
        if(pigeonsActivity.isPigeoninSelected){
            characterProperties = characterProperties+getString(R.string.getsHp, PowerUps.PIGEONIN.getHealingHp());
        }
    }
}