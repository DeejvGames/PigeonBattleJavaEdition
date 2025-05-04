package pl.deejvgames.pigeonbattlejavaedition;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class playActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);
        drawJoystick joystick = findViewById(R.id.joystick);
        ImageView playerImage = findViewById(R.id.playerImage);
        joystick.getPlayerImage(playerImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView)findViewById(R.id.playerHp)).setText(getString(R.string.player, pigeonsActivity.selectedCharacter.getHP()));
        ((ImageView)findViewById(R.id.playerImage)).setImageIcon(Icon.createWithResource(this, pigeonsActivity.selectedCharacter.getImage()));
        ((TextView)findViewById(R.id.opponentHp)).setText(getString(R.string.opponent, opponent.getHP()));
        ((ImageView)findViewById(R.id.opponentImage)).setImageIcon(Icon.createWithResource(this, opponent.getImage()));
        characterSpeed();
    }

    Opponents opponent = Opponents.OPPONENT_RADIO_PIGEON;

    public boolean isRadioPigeonOpponent = true;
    public boolean isPigobombOpponent = false;
    public boolean isFeatheredPigeonOpponent = false;
    public boolean isMilkPigeonOpponent = false;
    public boolean isWheelPigeonOpponent = false;
    public boolean isNuclearPigeonOpponent = false;

    public int defaultMovementSpeed = 15;
    public static double movementSpeed;

    public static void characterSpeed(){
        if(pigeonsActivity.selectedCharacter.getCharacterSpeedBoost() > 0){
            int speedBoost = 100 + pigeonsActivity.selectedCharacter.getCharacterSpeedBoost();
            movementSpeed = (double) (10 * speedBoost) / 100;
        } else{
            movementSpeed = 15;
        }
    }
}