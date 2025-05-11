package pl.deejvgames.pigeonbattlejavaedition;

import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        opponentMovement();
        attackPlayer();
//        checkPlayerHp();
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
            opponentMovementSpeed = (double) (7 * speedBoost) / 100;
        } else{
            opponentMovementSpeed = 7;
        }
    }

    public float damagePosX;
    public float damagePosY;

    public float opponentDamagePosX;
    public float opponentDamagePosY;

    public ImageView damageTexture;
    public ImageView opponentDamageTexture;

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

    public void opponentMovement(){
        Random random = new Random();
        int option = random.nextInt(3);
        switch(option){
            case 0: randomizeMovement();
                Log.d("opponentMovement", "Option 0");
                break;
            case 1: randomizeMovement();
                Log.d("opponentMovement", "Option 1");
                break;
            case 2: goToRandomPos();
                Log.d("opponentMovement", "Option 2");
                break;
        }
    }

    public void randomizeMovement(){
        Random random = new Random();
        int option = random.nextInt(2);
        Log.d("opponentMovement", "randomizingMovement...");
        switch(option){
            case 0: moveOpponentXandY();
                Log.d("randomizeMovement", "Option 0");
                break;
            case 1: moveOpponentY();
                Log.d("randomizeMovement", "Option 0");
                break;
        }
    }

    public void goToRandomPos(){
        ImageView gameOpponent = findViewById(R.id.opponentImage);
        Random random = new Random();
        Log.d("opponentMovement", "going to random pos...");
        int randomX = random.nextInt(1009);
        int randomY = random.nextInt(891);
        while(randomY < 340){
            randomY = random.nextInt(891);
        }
        int finalRandomY = randomY;
        new Thread(() -> {
            if(gameOpponent.getX() > randomX){
                new Thread(() -> {
                    while(gameOpponent.getX() < randomX){
                        if(randomX-gameOpponent.getX() < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setX(randomX));
                        } else{
                            runOnUiThread(() -> gameOpponent.setX((float) (gameOpponent.getX()+opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
            if(gameOpponent.getX() < randomX){
                new Thread(() -> {
                    while(gameOpponent.getX() > randomX){
                        if(randomX-gameOpponent.getX() < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setX(randomX));
                        } else{
                            runOnUiThread(() -> gameOpponent.setX((float) (gameOpponent.getX()-opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
            if(gameOpponent.getY() > finalRandomY){
                new Thread(() -> {
                    while(gameOpponent.getY() < finalRandomY){
                        if(gameOpponent.getY()- finalRandomY < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setY(finalRandomY));
                        } else{
                            runOnUiThread(() -> gameOpponent.setY((float) (gameOpponent.getY()-opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
            if(gameOpponent.getY() < finalRandomY){
                new Thread(() -> {
                    while(gameOpponent.getY() > finalRandomY){
                        if(gameOpponent.getY()- finalRandomY < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setY(finalRandomY));
                        } else{
                            runOnUiThread(() -> gameOpponent.setY((float) (gameOpponent.getY()-opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
        }).start();
        opponentMovementLoop();
    }

    public void moveOpponentXandY(){
        View gameOpponent = findViewById(R.id.opponentImage);
        View player = findViewById(R.id.playerImage);
        Log.d("opponentMovement", "moving x and y");
        new Thread(() -> {
            float CharacterPosX = player.getX();
            float CharacterPosY = player.getY();
            Log.d("POSs", "Player: X: " + CharacterPosX + " Y: " + CharacterPosY + " Opponent: X: " + gameOpponent.getX() + " Y: " + gameOpponent.getY());
            if(CharacterPosX > gameOpponent.getX()){
                new Thread(() -> {
                    while(gameOpponent.getX() < CharacterPosX){
                        if(CharacterPosX-gameOpponent.getX() < opponentMovementSpeed){
                                runOnUiThread(() -> gameOpponent.setX(CharacterPosX));
                        } else{
                                runOnUiThread(() -> gameOpponent.setX((float) (gameOpponent.getX()+opponentMovementSpeed)));
                        }
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
                        if(gameOpponent.getX()-CharacterPosX < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setX(CharacterPosX));
                        } else{
                                runOnUiThread(() -> gameOpponent.setX((float) (gameOpponent.getX()-opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
            if(CharacterPosY > gameOpponent.getY()){
                new Thread(() -> {
                    while(gameOpponent.getY() < CharacterPosY){
                        if(CharacterPosY-gameOpponent.getY() < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setY(CharacterPosY));
                        } else{
                            runOnUiThread(() -> gameOpponent.setY((float) (gameOpponent.getY()+opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
            if(CharacterPosY < gameOpponent.getY()){
                new Thread(() -> {
                    while(gameOpponent.getY() > CharacterPosY){
                        if(gameOpponent.getY()-CharacterPosY < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setY(CharacterPosY));
                        } else{
                            runOnUiThread(() -> gameOpponent.setY((float) (gameOpponent.getY()-opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
        }).start();
        opponentMovementLoop();
    }

    public void moveOpponentY(){
        View gameOpponent = findViewById(R.id.opponentImage);
        View player = findViewById(R.id.playerImage);
        Log.d("opponentMovement", "moving opponent y");
        new Thread(() -> {
            float CharacterPosY = player.getY();
            if(CharacterPosY > gameOpponent.getY()){
                new Thread(() -> {
                    while(gameOpponent.getY() != CharacterPosY){
                        if(CharacterPosY-gameOpponent.getY() < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setY(CharacterPosY));
                        } else{
                            runOnUiThread(() -> gameOpponent.setY((float) (gameOpponent.getY()+opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
            if(CharacterPosY < gameOpponent.getY()){
                new Thread(() -> {
                    while(gameOpponent.getY() != CharacterPosY){
                        if(gameOpponent.getY()-CharacterPosY < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setY(CharacterPosY));
                        } else{
                            runOnUiThread(() -> gameOpponent.setY((float) (gameOpponent.getY()-opponentMovementSpeed)));
                        }
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
        }).start();
        opponentMovementLoop();
    }

    public void opponentMovementLoop(){
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            runOnUiThread(this::opponentMovement);
        }).start();
    }

    public void attackPlayer(){
        ImageView gameOpponent = findViewById(R.id.opponentImage);
        ImageView player = findViewById(R.id.playerImage);
        new Thread(() -> {
            while(true){
                if(gameOpponent.getY() < player.getY()+72 && gameOpponent.getY() >= player.getY()){
                    Log.d("opponentAttack", "Attacking player!");
                    createOpponentDamage();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    List<ImageView> opponentDamages = new ArrayList<>();
    List<ImageView> opponentDamagedDamages = new ArrayList<>();

    public void createOpponentDamage(){
        View opponentImage = findViewById(R.id.opponentImage);
        float opponentPosX = opponentImage.getX();
        float opponentPosY = opponentImage.getY();
        float opponentCenterX = opponentImage.getHeight()/2;
        float opponentCenterY = opponentImage.getWidth()/2;
        float actualOpponentX = opponentPosX + opponentCenterX -24;
        float actualOpponentY = opponentPosY + opponentCenterY -24;
        runOnUiThread(() -> {
            opponentDamageTexture = new ImageView(this);
            opponentDamageTexture.setImageResource(R.drawable.damage);
            ConstraintLayout.LayoutParams damageParams = new ConstraintLayout.LayoutParams(48, 48);
            opponentDamageTexture.setX(actualOpponentX);
            opponentDamageTexture.setY(actualOpponentY);
            opponentDamagePosX = actualOpponentX;
            opponentDamagePosY = actualOpponentY;
            opponentDamages.add(opponentDamageTexture);
            container.addView(opponentDamageTexture, damageParams);
            updateOpponentDamage();
        });
    }

    public void updateOpponentDamage(){
        for(ImageView damageView:opponentDamages){
            new Thread(() -> {
                while(damageView.getX() > 0){
                    runOnUiThread(() -> {
                        damageView.setX(damageView.getX()-10);
                        dealOpponentDamage();
                        ((TextView) findViewById(R.id.playerHp)).setText(getString(R.string.player, playerHP));
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
                    opponentDamages.remove(damageView);
                });
            }).start();
        }
    }

    public void dealOpponentDamage(){
        ImageView playerImage = findViewById(R.id.playerImage);
        for(ImageView damageView:opponentDamages){
            float damageWidth = opponentDamageTexture.getWidth();
            float damageHeight = opponentDamageTexture.getHeight();
            float damageRight = damageView.getX()+damageWidth;
            float damageBottom = damageView.getY()+damageHeight;
            float playerX = playerImage.getX();
            float playerY = playerImage.getY();
            float playerRight = playerX + playerImage.getWidth();
            float playerBottom = playerY + playerImage.getHeight();
            if(damageView.getX() < playerRight && damageRight > playerX && damageView.getY() < playerBottom && damageBottom > playerY && !opponentDamagedDamages.contains(damageView)){
                opponentDamagedDamages.add(damageView);
                damageView.setVisibility(GONE);
                container.removeView(damageView);
                playerHP -= opponent.getCharacterDamage();
            }
//            Log.d("opponentPos", "OpponentPos: X: " + playerX + " Y: " + playerY);
//            Log.d("damagePos", "DamagePos: X: " + damageView.getX() + " Y: " + damageView.getY());
//            Log.d("opponentDMG", String.valueOf(opponent.getCharacterDamage()));
        }
    }

    public void checkPlayerHp(){
        new Thread(() -> {
            while(true){
                if(playerHP <= 1185){
                    Intent intent = new Intent(playActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, getString(R.string.you_lost), Toast.LENGTH_SHORT).show();
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}