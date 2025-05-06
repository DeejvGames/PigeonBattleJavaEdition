package pl.deejvgames.pigeonbattlejavaedition;

import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class playActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);
        container = findViewById(R.id.main);
        drawJoystick joystick = findViewById(R.id.joystick);
        ImageView playerImage = findViewById(R.id.playerImage);
        joystick.getPlayerImage(playerImage);
        Button attackButton = findViewById(R.id.controlAttack);
        attackButton.setOnTouchListener((v, event) -> {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    isAttackButtonTouched = true;
                    createTouchDamage();
                    return true;
                case MotionEvent.ACTION_UP:
                    isAttackButtonTouched = false;
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView)findViewById(R.id.playerHp)).setText(getString(R.string.player, playerHP));
        ((ImageView)findViewById(R.id.playerImage)).setImageIcon(Icon.createWithResource(this, pigeonsActivity.selectedCharacter.getImage()));
        ((TextView)findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP));
        ((ImageView)findViewById(R.id.opponentImage)).setImageIcon(Icon.createWithResource(this, opponent.getImage()));
        characterSpeed();
        opponentSpeed();
    }

    Opponents opponent = Opponents.OPPONENT_RADIO_PIGEON;
    ImageView gameOpponent;
    int playerHP = pigeonsActivity.selectedCharacter.getHP();
    int opponentHP = opponent.getHP();

    public boolean isRadioPigeonOpponent = true;
    public boolean isPigobombOpponent = false;
    public boolean isFeatheredPigeonOpponent = false;
    public boolean isMilkPigeonOpponent = false;
    public boolean isWheelPigeonOpponent = false;
    public boolean isNuclearPigeonOpponent = false;

    public int defaultMovementSpeed = 15;
    public static double movementSpeed;
    public static double opponentMovementSpeed;

    public static void characterSpeed(){
        if(pigeonsActivity.selectedCharacter.getCharacterSpeedBoost() > 0){
            int speedBoost = 100 + pigeonsActivity.selectedCharacter.getCharacterSpeedBoost();
            movementSpeed = (double) (10 * speedBoost) / 100;
        } else{
            movementSpeed = 10;
        }
    }

    public void opponentSpeed(){
        if(opponent.getCharacterSpeedBoost() > 0){
            int speedBoost = 100 + opponent.getCharacterSpeedBoost();
            opponentMovementSpeed = (double) (10 * speedBoost) / 100;
        } else{
            opponentMovementSpeed = 10;
        }
    }

    public float damagePosX;
    public float damagePosY;

    public ImageView damageTexture;

    ConstraintLayout container;

    List<ImageView> damages = new ArrayList<>();

    public void createDamage(){
        View playerImage = findViewById(R.id.playerImage);
        float characterPosX = playerImage.getX();
        float characterPosY = playerImage.getY();
        float characterCenterX = playerImage.getHeight()/2;
        float characterCenterY = playerImage.getWidth()/2;
        float actualCharacterX = characterPosX+characterCenterX-24;
        float actualCharacterY = characterPosY+characterCenterY-24;
        runOnUiThread(() -> {
            damageTexture = new ImageView(this);
            damageTexture.setImageResource(R.drawable.damage);
            ConstraintLayout.LayoutParams damageParams = new ConstraintLayout.LayoutParams(48, 48);
            damageTexture.setX(actualCharacterX);
            damageTexture.setY(actualCharacterY);
            damagePosX = actualCharacterX;
            damagePosY = actualCharacterY;
            damages.add(damageTexture);
            container.addView(damageTexture, damageParams);
        });
        updateDamage();
    }

    public void onAttack(View view){
        createDamage();
    }

    Thread updateDamageThread;

    public void updateDamage(){
        for(ImageView damageView:damages){
            new Thread(() -> {
                while(damageView.getX() < 1080){
                    runOnUiThread(() -> {
                        damageView.setX(damageView.getX()+10);
                        dealDamage();
                        ((TextView) findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP));
                    });
                    try{
                        Thread.sleep(16);
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                runOnUiThread(() -> {
                    container.removeView(damageView);
                    damages.remove(damageView);
                });
            }).start();
        }
    }

    public void updateDamageInterrupt(){
        updateDamageThread.interrupt();
    }

    public boolean isAttackButtonTouched;

    public void createTouchDamage(){
        new Thread(() -> {
            while(isAttackButtonTouched){
                runOnUiThread(this::createDamage);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    public void dealDamage(){
        gameOpponent = findViewById(R.id.opponentImage);
        for(ImageView damageView:damages){
            float damageWidth = damageTexture.getWidth();
            float damageHeight = damageTexture.getHeight();
            float damageRight = damageView.getX()+damageWidth;
            float damageBottom = damageView.getY()+damageHeight;
            float opponentX = gameOpponent.getX();
            float opponentY = gameOpponent.getY();
            float opponentRight = opponentX + gameOpponent.getWidth();
            float opponentBottom = opponentY + gameOpponent.getHeight();
            if(damageView.getX() < opponentRight && damageRight > opponentX && damageView.getY() < opponentBottom && damageBottom > opponentY && !damagedDamages.contains(damageView)){
                damagedDamages.add(damageView);
                damageView.setVisibility(GONE);
                container.removeView(damageView);
                opponentHP -= pigeonsActivity.selectedCharacter.getCharacterDamage();
            }
            Log.d("opponentPos", "OpponentPos: X: " + opponentX + " Y: " + opponentY);
            Log.d("damagePos", "DamagePos: X: " + damageView.getX() + " Y: " + damageView.getY());
        }
    }

    List<ImageView> damagedDamages = new ArrayList<>();

    public void goToPlayerPosition(){
        View gameOpponent = findViewById(R.id.opponentImage);
        View player = findViewById(R.id.playerImage);
        float CharacterPosX = player.getX();
        float CharacterPosY = player.getY();
        Log.d("POSs", "Player: X: " + CharacterPosX + " Y: " + CharacterPosY + " Opponent: X: " + gameOpponent.getX() + " Y: " + gameOpponent.getY());
        if(CharacterPosX > gameOpponent.getX()){
            new Thread(() -> {
                while(gameOpponent.getX() < CharacterPosX){
                    if(gameOpponent.getX() >= CharacterPosX){
                        break;
                    }
                    runOnUiThread(() -> gameOpponent.setX((float) (gameOpponent.getX()+opponentMovementSpeed)));
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }).start();
        }
        if(CharacterPosX < gameOpponent.getX()){
            new Thread(() -> {
                while(gameOpponent.getX() > CharacterPosX){
                    if(gameOpponent.getX() <= CharacterPosX){
                        break;
                    }
                    runOnUiThread(() -> gameOpponent.setX((float) (gameOpponent.getX()-opponentMovementSpeed)));
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }).start();
        }
//        if(CharacterPosY > gameOpponent.getY()){
//            new Thread(() -> {
//                while(gameOpponent.getY() != CharacterPosY){
//                    gameOpponent.setY((float) (gameOpponent.getY()+opponentMovementSpeed));
//                }
//            }).start();
//        }
//        if(CharacterPosY < gameOpponent.getY()){
//            new Thread(() -> {
//                while(gameOpponent.getY() != CharacterPosY){
//                    gameOpponent.setY((float) (gameOpponent.getY()-opponentMovementSpeed));
//                }
//            });
//        }
    }
}