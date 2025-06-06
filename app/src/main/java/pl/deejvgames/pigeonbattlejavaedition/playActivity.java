package pl.deejvgames.pigeonbattlejavaedition;

import static android.view.View.GONE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.VISIBLE;

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
import java.util.Objects;
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
        Button attackButton = findViewById(R.id.controlAttack);
        attackButton.setOnTouchListener((v, event) -> {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(Objects.equals(saveToFile.loadData(this, saveToFile.isSpamAttackingEnabledFileName, "isSpamAttackingEnabled"), String.valueOf(true))){
                        isAttackButtonTouched = true;
                        createTouchDamage();
                    } else{
                        isAttackButtonTouched = true;
                        if(createTouchDamageThread == null || !createTouchDamageThread.isAlive()){
                            createTouchDamage();
                        }
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    isAttackButtonTouched = false;
                    return true;
            }
            return false;
        });
        playerImage.post(() -> {
            joystick.getPlayerImage(playerImage);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        killedOpponents = 0;
        setPlayerHP();
        ((TextView)findViewById(R.id.playerHp)).setText(getString(R.string.player, playerHP));
        ((ImageView)findViewById(R.id.playerImage)).setImageIcon(Icon.createWithResource(this, pigeonsActivity.selectedCharacter.getImage()));
        ((TextView)findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP));
        ((ImageView)findViewById(R.id.opponentImage)).setImageIcon(Icon.createWithResource(this, opponent.getImage()));
        setSelectedPowerUpsValue();
        if(pigeonsActivity.isPigeoninSelected){
            findViewById(R.id.pigeoninPowerUp).setVisibility(VISIBLE);
        } else{
            findViewById(R.id.pigeoninPowerUp).setVisibility(GONE);
        }
        characterSpeed();
        opponentSpeed();
        opponentMovement();
        attackPlayer();
        checkPlayerHp();
        checkOpponentHp();
        dealDamagePerSecond();
        dealOpponentDamagePerSecond();
        healPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.d("onDestroy", "onDestroy!");
        checkPlayerHpThread.interrupt();
        checkOpponentHpThread.interrupt();
        dealDamagePerSecondThread.interrupt();
        dealOpponentDamagePerSecondThread.interrupt();
        attackPlayerThread.interrupt();
        if(createTouchDamageThread != null){
            if(createTouchDamageThread.isAlive()){
                createTouchDamageThread.interrupt();
            }
        }
        if(pigeonsActivity.isPigeoninSelected){
            healPlayerThread.interrupt();
        }
        damagedDamages.clear();
    }

    public void setSelectedPowerUpsValue(){
        if(Objects.equals(saveToFile.loadData(this, saveToFile.selectedPowerUpsFileName, "isPigeoninSelected"), String.valueOf(true))){
            pigeonsActivity.isPigeoninSelected = true;
        } else{
            pigeonsActivity.isPigeoninSelected = false;
        }
    }

    int killedOpponents = 0;

    Opponents opponent = Opponents.OPPONENT_RADIO_PIGEON;
    ImageView gameOpponent;
    int playerHP = pigeonsActivity.selectedCharacter.getHP();
    int opponentHP = opponent.getHP();


    public void setPlayerHP(){
        if(pigeonsActivity.isPigeoninSelected){
            playerHP = pigeonsActivity.selectedCharacter.getHP() + PowerUps.PIGEONIN.getAdditonalHp();
        } else{
            playerHP = pigeonsActivity.selectedCharacter.getHP();
        }
    }

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
            double damageTextureSize = (48/2.625) * getResources().getDisplayMetrics().density;
            damageTexture = new ImageView(this);
            damageTexture.setImageResource(R.drawable.damage);
//            Log.d("screenDensity", String.valueOf(getResources().getDisplayMetrics().density));
            ConstraintLayout.LayoutParams damageParams = new ConstraintLayout.LayoutParams((int) damageTextureSize, (int) damageTextureSize);
            Log.d("damageScale", String.valueOf(damageTextureSize));
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

    public void updateDamage(){
        for(ImageView damageView:damages){
            new Thread(() -> {
                while(damageView.getX() < getResources().getDisplayMetrics().widthPixels){
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
//                    Log.d("damageScale", "STOPPED!");
                    container.removeView(damageView);
                    damages.remove(damageView);
                });
            }).start();
        }
    }

    public boolean isAttackButtonTouched;

    Thread createTouchDamageThread;

    public void createTouchDamage(){
        createTouchDamageThread = new Thread(() -> {
            while(isAttackButtonTouched){
                runOnUiThread(this::createDamage);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        createTouchDamageThread.start();
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
                int damageToDeal;
                if(opponent.getCharacterLessDamage() > 0){
                    int x = pigeonsActivity.selectedCharacter.getCharacterDamage() * (100-opponent.getCharacterLessDamage());
                    damageToDeal = x/100;
                } else{
                    damageToDeal = pigeonsActivity.selectedCharacter.getCharacterDamage();
                }
                if(pigeonsActivity.isPigeoninSelected){
                    damageToDeal += PowerUps.PIGEONIN.getAdditonalDamage();
                }
                opponentHP -= damageToDeal;
//                Log.d("reductDamage", "given damage: " + pigeonsActivity.selectedCharacter.getCharacterDamage());
            }
//            Log.d("opponentPos", "OpponentPos: X: " + opponentX + " Y: " + opponentY);
//            Log.d("damagePos", "DamagePos: X: " + damageView.getX() + " Y: " + damageView.getY());
        }
    }

    List<ImageView> damagedDamages = new ArrayList<>();

    public void opponentMovement(){
        Random random = new Random();
        int option = random.nextInt(3);
        switch(option){
            case 0: randomizeMovement();
//                Log.d("opponentMovement", "Option 0");
                break;
            case 1: randomizeMovement();
//                Log.d("opponentMovement", "Option 1");
                break;
            case 2: randomizeMovement(); // TODO: CHANGE TO goToRandomPos() AFTER FIXING IT
//                Log.d("opponentMovement", "Option 2");
                break;
        }
    }

    public void randomizeMovement(){
        Random random = new Random();
        int option = random.nextInt(2);
//        Log.d("opponentMovement", "randomizingMovement...");
        switch(option){
            case 0: moveOpponentXandY();
//                Log.d("randomizeMovement", "Option 0");
                break;
            case 1: moveOpponentY();
//                Log.d("randomizeMovement", "Option 0");
                break;
        }
    }

    public void goToRandomPos(){
        ImageView gameOpponent = findViewById(R.id.opponentImage);
        Random random = new Random();
//        Log.d("opponentMovement", "going to random pos...");
        int randomX = random.nextInt(1009);
        int randomY = random.nextInt(891);
        while(randomY < 340){
            randomY = random.nextInt(891);
        }
        int finalRandomY = randomY;
        new Thread(() -> {
            if(gameOpponent.getX() > randomX){
                new Thread(() -> {
                    while(gameOpponent.getX() > randomX){
                        if(gameOpponent.getX()-randomX < opponentMovementSpeed){
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
            if(gameOpponent.getX() < randomX){
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
            if(gameOpponent.getY() > finalRandomY){
                new Thread(() -> {
                    while(gameOpponent.getY() > finalRandomY){
                        if(gameOpponent.getY()-finalRandomY < opponentMovementSpeed){
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
                    while(gameOpponent.getY() < finalRandomY){
                        if(finalRandomY-gameOpponent.getY() < opponentMovementSpeed){
                            runOnUiThread(() -> gameOpponent.setY(finalRandomY));
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
        }).start();
        opponentMovementLoop();
    }

    public void moveOpponentXandY(){
        View gameOpponent = findViewById(R.id.opponentImage);
        View player = findViewById(R.id.playerImage);
//        Log.d("opponentMovement", "moving x and y");
        new Thread(() -> {
            float CharacterPosX = player.getX();
            float CharacterPosY = player.getY();
//            Log.d("POSs", "Player: X: " + CharacterPosX + " Y: " + CharacterPosY + " Opponent: X: " + gameOpponent.getX() + " Y: " + gameOpponent.getY());
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
//        Log.d("opponentMovement", "moving opponent y");
        new Thread(() -> {
            float CharacterPosY = player.getY();
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

    Thread attackPlayerThread;

    public void attackPlayer(){
        ImageView gameOpponent = findViewById(R.id.opponentImage);
        ImageView player = findViewById(R.id.playerImage);
        attackPlayerThread = new Thread(() -> {
            while(true){
                if(gameOpponent.getY() < player.getY()+72 && gameOpponent.getY() >= player.getY()){
//                    Log.d("opponentAttack", "Attacking player!");
                    createOpponentDamage();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        attackPlayerThread.start();
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
            double opponentDamageTextureSize = (48/2.625) * getResources().getDisplayMetrics().density;
            opponentDamageTexture = new ImageView(this);
            opponentDamageTexture.setImageResource(R.drawable.opponent_damage);
            ConstraintLayout.LayoutParams damageParams = new ConstraintLayout.LayoutParams((int) opponentDamageTextureSize, (int) opponentDamageTextureSize);
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
                        runOnUiThread(() -> {
                            container.removeView(damageView);
                            opponentDamages.remove(damageView);
                        });
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
                int damageToDeal;
                if(pigeonsActivity.selectedCharacter.getCharacterLessDamage() > 0){
                    int x = opponent.getCharacterDamage() * (100-pigeonsActivity.selectedCharacter.getCharacterLessDamage());
                    damageToDeal = x/100;
                } else{
                    damageToDeal = opponent.getCharacterDamage();
                }
                playerHP -= damageToDeal;
//                Log.d("reductDamage", "received damage:" + damageToDeal + " was about to get: " + opponent.getCharacterDamage());
            }
//            Log.d("opponentPos", "OpponentPos: X: " + playerX + " Y: " + playerY);
//            Log.d("damagePos", "DamagePos: X: " + damageView.getX() + " Y: " + damageView.getY());
//            Log.d("opponentDMG", String.valueOf(opponent.getCharacterDamage()));
        }
    }

    int newCoins = 0;
    int newScore = 0;

    Thread checkPlayerHpThread;
    Thread checkOpponentHpThread;

    @SuppressLint("StringFormatInvalid")
    public void checkPlayerHp(){
        checkPlayerHpThread = new Thread(() -> {
            while(true){
                if(playerHP <= 0){
                    newCoins = killedOpponents*10;
                    newScore = killedOpponents*10;
                    saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(MainActivity.userCoins+newCoins));
                    saveToFile.saveData(this, saveToFile.scoreFileName, "score="+(MainActivity.userScore+newScore));
                    MainActivity.userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
                    MainActivity.userScore = Integer.parseInt(saveToFile.loadData(this, saveToFile.scoreFileName, "score"));
                    Intent intent = new Intent(playActivity.this, MainActivity.class);
                    startActivity(intent);
                    runOnUiThread(() -> Toast.makeText(this, getString(R.string.you_lost, newCoins), Toast.LENGTH_SHORT).show());
                    finish();
                    break;
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        checkPlayerHpThread.start();
    }

    @SuppressLint("StringFormatInvalid")
    public void checkOpponentHp(){
        checkOpponentHpThread = new Thread(() -> {
            while(true){
                if(opponentHP <= 0){
                    switch(opponent){
                        case OPPONENT_RADIO_PIGEON:
                            killedOpponents += 1;
                            opponent = Opponents.OPPONENT_PIGOBOMB;
                            opponentHP = opponent.getHP();
                            runOnUiThread(() -> {
                                ((TextView)findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP));
                                ((ImageView)findViewById(R.id.opponentImage)).setImageIcon(Icon.createWithResource(this, opponent.getImage()));
                            });
                            break;
                        case OPPONENT_PIGOBOMB:
                            killedOpponents += 1;
                            opponent = Opponents.OPPONENT_FEATHERED_PIGEON;
                            opponentHP = opponent.getHP();
                            runOnUiThread(() -> {
                                ((TextView)findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP));
                                ((ImageView)findViewById(R.id.opponentImage)).setImageIcon(Icon.createWithResource(this, opponent.getImage()));
                            });
                            break;
                        case OPPONENT_FEATHERED_PIGEON:
                            killedOpponents += 1;
                            opponent = Opponents.OPPONENT_MILK_PIGEON;
                            opponentHP = opponent.getHP();
                            runOnUiThread(() -> {
                                ((TextView)findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP));
                                ((ImageView)findViewById(R.id.opponentImage)).setImageIcon(Icon.createWithResource(this, opponent.getImage()));
                            });
                            break;
                        case OPPONENT_MILK_PIGEON:
                            killedOpponents += 1;
                            opponent = Opponents.OPPONENT_WHEEL_PIGEON;
                            opponentHP = opponent.getHP();
                            runOnUiThread(() -> {
                                ((TextView)findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP));
                                ((ImageView)findViewById(R.id.opponentImage)).setImageIcon(Icon.createWithResource(this, opponent.getImage()));
                            });
                            break;
                        case OPPONENT_WHEEL_PIGEON:
                            killedOpponents += 1;
                            opponent = Opponents.OPPONENT_NUCLEAR_PIGEON;
                            opponentHP = opponent.getHP();
                            runOnUiThread(() -> {
                                ((TextView)findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP));
                                ((ImageView)findViewById(R.id.opponentImage)).setImageIcon(Icon.createWithResource(this, opponent.getImage()));
                            });
                            break;
                        case OPPONENT_NUCLEAR_PIGEON:
                            killedOpponents += 1;
                            newCoins = killedOpponents*10;
                            newScore = killedOpponents*10;
                            saveToFile.saveData(this, saveToFile.coinsFileName, "coins="+(MainActivity.userCoins+newCoins));
                            saveToFile.saveData(this, saveToFile.scoreFileName, "score="+(MainActivity.userScore+newScore));
                            MainActivity.userCoins = Integer.parseInt(saveToFile.loadData(this, saveToFile.coinsFileName, "userCoins"));
                            MainActivity.userScore = Integer.parseInt(saveToFile.loadData(this, saveToFile.scoreFileName, "score"));
                            opponent = Opponents.OPPONENT_RADIO_PIGEON;
                            opponentHP = opponent.getHP();
                            Intent intent = new Intent(playActivity.this, MainActivity.class);
                            startActivity(intent);
                            runOnUiThread(() -> Toast.makeText(this, getString(R.string.you_win, newCoins), Toast.LENGTH_SHORT).show());
                            finish();
                            break;
                    }
//                    Log.d("userCoins", "new coins: " + newCoins);
//                    Log.d("userCoins", "coins: " + MainActivity.userCoins);
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        checkOpponentHpThread.start();
    }

    Thread dealDamagePerSecondThread;

    public void dealDamagePerSecond(){
        dealDamagePerSecondThread = new Thread(() -> {
            while(true){
                if(pigeonsActivity.selectedCharacter.getCharacterDamagePerSecond() > 0){
                    int damageToDeal;
                    if(opponent.getCharacterLessDamage() > 0){
                        int x = pigeonsActivity.selectedCharacter.getCharacterDamagePerSecond() * (100-opponent.getCharacterLessDamage());
                        damageToDeal = x/100;
                    } else{
                        damageToDeal = pigeonsActivity.selectedCharacter.getCharacterDamagePerSecond();
                    }
                    opponentHP -= damageToDeal;
                    runOnUiThread(() -> ((TextView) findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponentHP)));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        dealDamagePerSecondThread.start();
    }

    Thread dealOpponentDamagePerSecondThread;

    public void dealOpponentDamagePerSecond(){
        dealOpponentDamagePerSecondThread = new Thread(() -> {
            while(true){
                if(opponent.getCharacterDamagePerSecond() > 0){
                    int damageToDeal;
                    if(pigeonsActivity.selectedCharacter.getCharacterLessDamage() > 0){
                        int x = opponent.getCharacterDamagePerSecond() * (100-pigeonsActivity.selectedCharacter.getCharacterLessDamage());
                        damageToDeal = x/100;
                    } else{
                        damageToDeal = opponent.getCharacterDamagePerSecond();
                    }
                    playerHP -= damageToDeal;
                    runOnUiThread(() -> ((TextView)findViewById(R.id.playerHp)).setText(getString(R.string.player, playerHP)));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        dealOpponentDamagePerSecondThread.start();
    }

    Thread healPlayerThread;

    public void healPlayer(){
        if(pigeonsActivity.isPigeoninSelected){
            healPlayerThread = new Thread(() -> {
                while(true){
                    runOnUiThread(() -> {
                        playerHP += PowerUps.PIGEONIN.getHealingHp();
                        ((TextView)findViewById(R.id.playerHp)).setText(getString(R.string.player, playerHP));
                    });
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            healPlayerThread.start();
        }
    }
}