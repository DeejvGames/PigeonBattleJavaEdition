package pl.deejvgames.pigeonbattlejavaedition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class drawJoystick extends View {

    public ImageView imageView;
    public drawJoystick(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        makeJoystick((int) ((280/2.625)*getResources().getDisplayMetrics().density), (int) ((280/2.625)*getResources().getDisplayMetrics().density), (int) ((200/2.625)*getResources().getDisplayMetrics().density), (int) ((75/2.625)*getResources().getDisplayMetrics().density));
    }

    public void getPlayerImage(ImageView imageView){
        this.imageView = imageView;
        this.characterPositionX = imageView.getX();
        this.characterPositionY = imageView.getY();
        this.characterWidth = imageView.getWidth();
    }

    private int outerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;
    private double velocityX;
    private double velocityY;

//    private int scaledOuterCircleRadius;
//    private int scaledInnerCircleRadius;

    public float characterPositionX = 0;
    public float characterPositionY = 0;

    private Thread movementThread;

    public void makeJoystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius){
        outerCircleCenterPositionX = centerPositionX;
        outerCircleCenterPositionY = centerPositionY;
        innerCircleCenterPositionX = centerPositionX;
        innerCircleCenterPositionY = centerPositionY;

        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.argb(80, 255, 255, 255));
        outerCirclePaint.setStyle(Paint.Style.FILL);
        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.rgb(255, 255, 255));
        innerCirclePaint.setStyle(Paint.Style.FILL);

        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas){
        super.onDraw(canvas);

        canvas.drawCircle(outerCircleCenterPositionX, outerCircleCenterPositionY, outerCircleRadius, outerCirclePaint);
        canvas.drawCircle(innerCircleCenterPositionX, innerCircleCenterPositionY, innerCircleRadius, innerCirclePaint);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        playerMaxXPos = screenWidth - characterWidth;
        playerMinYPos = (340/2.625) * getResources().getDisplayMetrics().density;
        playerMaxYPos = findViewById(R.id.joystick).getY() - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
    }

    int characterWidth;

    int screenWidth;
    int playerMaxXPos;
    double playerMinYPos;
    double playerMaxYPos;

    public void update(){
        this.post(() -> {
            updateInnerCirclePosition();
            velocityX = actuatorX*playActivity.movementSpeed;
            velocityY = actuatorY*playActivity.movementSpeed;
            characterPositionX += (float) velocityX;
            characterPositionY += (float) velocityY;
            if(characterPositionX < 0){
                characterPositionX = 0;
            }
            if(characterPositionX > playerMaxXPos){
                characterPositionX = playerMaxXPos;
            }
            if(characterPositionY < playerMinYPos){
                characterPositionY = (int) playerMinYPos;
//                Log.d("playerPosScale", String.valueOf(playerMinYPos));
            }
            if(characterPositionY > playerMaxYPos){
                characterPositionY = (int) playerMaxYPos;
//                Toast.makeText(this.getContext(), String.valueOf(playerMaxYPos), Toast.LENGTH_LONG).show();
//                Log.d("playerPosScale", String.valueOf(playerMaxYPos));
            }
            changePlayerImagePosition(characterPositionX, characterPositionY);
        });
    }

    private void updateInnerCirclePosition(){
//        Log.d("joystickUpdate", "Updating...");
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX*outerCircleRadius);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY*outerCircleRadius);
//        Log.d("joystickUpdateResults", String.valueOf(innerCircleCenterPositionX) + " " + String.valueOf(innerCircleCenterPositionY));
//        Log.d("playerPosUpdateResults", velocityX + " " + velocityY + " " + characterPositionX + " " + characterPositionY + " " + actuatorX + " " + actuatorY + " " + playActivity.movementSpeed);
        invalidate();
    }

    public boolean isPressed(double touchPositionX, double touchPositionY){
        double joystickCenterToTouchDistance = Math.sqrt(Math.pow(outerCircleCenterPositionX - touchPositionX, 2) + Math.pow(outerCircleCenterPositionY - touchPositionY, 2));
        return joystickCenterToTouchDistance < outerCircleRadius;
    }

    public void setIsPressed(boolean isPressed){
        this.isPressed = isPressed;
    }

    public boolean getIsPressed(){
        return isPressed;
    }

    public void setActuator(double touchPositionX, double touchPositionY){
        double deltaX = touchPositionX - outerCircleCenterPositionX;
        double deltaY = touchPositionY - outerCircleCenterPositionY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        if(deltaDistance < outerCircleRadius){
            actuatorX = deltaX/outerCircleRadius;
            actuatorY = deltaY/outerCircleRadius;
        } else{
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }
    }

    public void resetActuator(){
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                Log.d("joystickTouchEvent", "ACTION DOWN");
                update();
                if(isPressed(event.getX(), event.getY())){
                    setIsPressed(true);
                    startMovementLoop();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
//                Log.d("joystickTouchEvent", "ACTION MOVE");
                update();
                if(getIsPressed()){
                    setActuator(event.getX(), event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
//                Log.d("joystickTouchEvent", "ACTION UP");
                update();
                setIsPressed(false);
                resetActuator();
                innerCircleCenterPositionX = outerCircleCenterPositionX;
                innerCircleCenterPositionY = outerCircleCenterPositionY;
                stopMovementLoop();
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void changePlayerImagePosition(float x, float y){
        imageView.setX(x);
        imageView.setY(y);
    }

    public void startMovementLoop(){
        movementThread = new Thread(() -> {
            while(getIsPressed()){
                update();
//                Log.d("characterMovementSpeed", String.valueOf(playActivity.movementSpeed));
//                Log.d("characterPosition", "X = " + characterPositionX + " Y = " + characterPositionY);
                try {
                    try{
                        Thread.sleep(1000/(int) getDisplay().getRefreshRate());
                    } catch(NullPointerException exception){
                        exception.printStackTrace();
                        break;
                    }
//                    Log.d("test", String.valueOf(1000/(int) getDisplay().getRefreshRate()));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        movementThread.start();
    }

    public void stopMovementLoop(){
        if(movementThread != null){
            movementThread.interrupt();
            movementThread = null;
        }
    }

//    public void joystickScaling(int outerCircleRadius, int innerCircleRadius){
//        int screenWidth = getResources().getDisplayMetrics().widthPixels;
//        double scaledWidth = (double) screenWidth/1080;
//        scaledOuterCircleRadius = (int) (outerCircleRadius*scaledWidth);
//        scaledInnerCircleRadius = (int) (innerCircleRadius*scaledWidth);
//        Log.d("scaledJoystick", screenWidth + " " + scaledWidth + " " + scaledOuterCircleRadius + " " + outerCircleRadius);
//    }
}
