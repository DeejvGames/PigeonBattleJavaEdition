package pl.deejvgames.pigeonbattlejavaedition;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class saveToFile {

    public static String appStorageFileName = "appStorage.txt";
    public static List<String> values = new ArrayList<>();

    public static void writeData(Context context, String key, String value){
        for(String line:values){
            if(line.startsWith(key)){
                values.set(values.indexOf(line), (key+value));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(String line:values){
            stringBuilder.append(line+"\n");
        }
        try(FileOutputStream fileOutputStream = context.openFileOutput(appStorageFileName, MODE_PRIVATE)){
            fileOutputStream.write(stringBuilder.toString().getBytes());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String readData(Context context, String key){
        try{
            FileInputStream fileInputStream = context.openFileInput(appStorageFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line!=null){
                if(line.startsWith(key)){
                    return line.substring(key.length());
                } else {
                    line = reader.readLine();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void loadValues(Context context){
        values.add(coinsKey+readData(context, coinsKey));
        values.add(scoreKey+readData(context, scoreKey));
        values.add(languageKey+readData(context, languageKey));
        values.add(selectedCharacterKey+readData(context, selectedCharacterKey));
        values.add(pigeoninSelectedKey+readData(context, pigeoninSelectedKey));
        values.add(pigeonUnlockedKey+readData(context, pigeonUnlockedKey));
        values.add(radioPigeonUnlockedKey+readData(context, radioPigeonUnlockedKey));
        values.add(pigobombUnlockedKey+readData(context, pigobombUnlockedKey));
        values.add(featheredPigeonUnlockedKey+readData(context, featheredPigeonUnlockedKey));
        values.add(milkPigeonUnlockedKey+readData(context, milkPigeonUnlockedKey));
        values.add(wheelPigeonUnlockedKey+readData(context, wheelPigeonUnlockedKey));
        values.add(nuclearPigeonUnlockedKey+readData(context, nuclearPigeonUnlockedKey));
        values.add(pigeoninUnlockedKey+readData(context, pigeoninUnlockedKey));
        values.add(wasSpamAttackingEnabledKey+readData(context, wasSpamAttackingEnabledKey));
        values.add(fileFormatKey+readData(context, fileFormatKey));
    }

    public static String coinsKey = "coins=";
    public static String scoreKey = "score=";
    public static String languageKey = "language=";
    public static String selectedCharacterKey = "selectedCharacter=";
    public static String pigeoninSelectedKey = "isPigeoninSelected=";
    public static String pigeonUnlockedKey = "isPigeonUnlocked=";
    public static String radioPigeonUnlockedKey = "isRadioPigeonUnlocked=";
    public static String pigobombUnlockedKey = "isPigobombUnlocked=";
    public static String featheredPigeonUnlockedKey = "isFeatheredPigeonUnlocked=";
    public static String milkPigeonUnlockedKey = "isMilkPigeonUnlocked=";
    public static String wheelPigeonUnlockedKey = "isWheelPigeonUnlocked=";
    public static String nuclearPigeonUnlockedKey = "isNuclearPigeonUnlocked=";
    public static String pigeoninUnlockedKey = "isPigeoninUnlocked=";
    public static String wasSpamAttackingEnabledKey = "wasSpamAttackingEnabled=";
    public static String fileFormatKey = "fileFormat=";

    public static void createFiles(Context context){
        File appStorageFile = new File(context.getFilesDir(), appStorageFileName);

        if(!appStorageFile.exists()){
            values.clear();
            values.add(coinsKey+"0");
            values.add(scoreKey+"0");
            values.add(languageKey+Locale.getDefault().getLanguage());
            values.add(selectedCharacterKey+Characters.PIGEON);
            values.add(pigeoninSelectedKey+"false");
            values.add(pigeonUnlockedKey+"true");
            values.add(radioPigeonUnlockedKey+"false");
            values.add(pigobombUnlockedKey+"false");
            values.add(featheredPigeonUnlockedKey+"false");
            values.add(milkPigeonUnlockedKey+"false");
            values.add(wheelPigeonUnlockedKey+"false");
            values.add(nuclearPigeonUnlockedKey+"false");
            values.add(pigeoninUnlockedKey+"false");
            values.add(wasSpamAttackingEnabledKey+"false");
            values.add(fileFormatKey+"2");
            writeData(context, coinsKey, "0");
            writeData(context, scoreKey, "0");
            writeData(context, languageKey, Locale.getDefault().getLanguage());
            writeData(context, selectedCharacterKey, String.valueOf(Characters.PIGEON));
            writeData(context, pigeoninSelectedKey, "false");
            writeData(context, pigeonUnlockedKey, "true");
            writeData(context, radioPigeonUnlockedKey, "false");
            writeData(context, pigobombUnlockedKey, "false");
            writeData(context, featheredPigeonUnlockedKey, "false");
            writeData(context, milkPigeonUnlockedKey, "false");
            writeData(context, wheelPigeonUnlockedKey, "false");
            writeData(context, nuclearPigeonUnlockedKey, "false");
            writeData(context, pigeoninUnlockedKey, "false");
            writeData(context, wasSpamAttackingEnabledKey, "false");
            writeData(context, fileFormatKey, "2");
        }
    }

    public static void deleteFiles(Context context){
        File appStorageFile = new File(context.getFilesDir(), appStorageFileName);
        if(appStorageFile.exists()){
            appStorageFile.delete();
        }
    }

    public static void convertFiles(Context context){
        if(new File(context.getFilesDir(), "coins.txt").exists() && new File(context.getFilesDir(), "score.txt").exists() && new File(context.getFilesDir(), "selectedCharacter.txt").exists()){
            try{
                FileInputStream coinsFileInputStream = context.openFileInput("coins.txt");
                InputStreamReader coinsInputStreamReader = new InputStreamReader(coinsFileInputStream, StandardCharsets.UTF_8);
                BufferedReader coinsReader = new BufferedReader(coinsInputStreamReader);
                String coinsLine = coinsReader.readLine();
                writeData(context, "", coinsLine);

                FileInputStream scoreFileInputStream = context.openFileInput("score.txt");
                InputStreamReader scoreInputStreamReader = new InputStreamReader(scoreFileInputStream, StandardCharsets.UTF_8);
                BufferedReader scoreReader = new BufferedReader(scoreInputStreamReader);
                String scoreLine = scoreReader.readLine();
                writeData(context, "", scoreLine);

                FileInputStream selectedCharacterFileInputStream = context.openFileInput("selectedCharacter.txt");
                InputStreamReader selectedCharacterInputStreamReader = new InputStreamReader(selectedCharacterFileInputStream, StandardCharsets.UTF_8);
                BufferedReader selectedCharacterReader = new BufferedReader(selectedCharacterInputStreamReader);
                String selectedCharacterLine = selectedCharacterReader.readLine();
                writeData(context, "", selectedCharacterLine);

                FileInputStream unlockedPigeonsAndPowerUpsFileInputStream = context.openFileInput("unlockedPigeonsAndPowerUps.txt");
                InputStreamReader unlockedPigeonsAndPowerUpsInputStreamReader = new InputStreamReader(unlockedPigeonsAndPowerUpsFileInputStream, StandardCharsets.UTF_8);
                BufferedReader unlockedPigeonsAndPowerUpsReader = new BufferedReader(unlockedPigeonsAndPowerUpsInputStreamReader);
                String unlockedPigeonsAndPowerUpsLine = unlockedPigeonsAndPowerUpsReader.readLine();
                while(unlockedPigeonsAndPowerUpsLine!=null){
                    writeData(context, "", unlockedPigeonsAndPowerUpsLine);
                    unlockedPigeonsAndPowerUpsLine = unlockedPigeonsAndPowerUpsReader.readLine();
                }

                FileInputStream selectedPowerUpsFileInputStream = context.openFileInput("selectedPowerUps.txt");
                InputStreamReader selectedPowerUpsInputStreamReader = new InputStreamReader(selectedPowerUpsFileInputStream, StandardCharsets.UTF_8);
                BufferedReader selectedPowerUpsReader = new BufferedReader(selectedPowerUpsInputStreamReader);
                String selectedPowerUpsLine = selectedPowerUpsReader.readLine();
                writeData(context, "", selectedPowerUpsLine);

                FileInputStream selectedLanguageFileInputStream = context.openFileInput("selectedLanguage.txt");
                InputStreamReader selectedLanguageInputStreamReader = new InputStreamReader(selectedLanguageFileInputStream, StandardCharsets.UTF_8);
                BufferedReader selectedLanguageReader = new BufferedReader(selectedLanguageInputStreamReader);
                String selectedLanguageLine = selectedLanguageReader.readLine();
                writeData(context, "", selectedLanguageLine);

                FileInputStream wasSpamAttackingEnabledFileInputStream = context.openFileInput("wasSpamAttackingEnabled.txt");
                InputStreamReader wasSpamAttackingEnabledInputStreamReader = new InputStreamReader(wasSpamAttackingEnabledFileInputStream, StandardCharsets.UTF_8);
                BufferedReader wasSpamAttackingEnabledReader = new BufferedReader(wasSpamAttackingEnabledInputStreamReader);
                String wasSpamAttackingEnabledLine = wasSpamAttackingEnabledReader.readLine();
                writeData(context, "", wasSpamAttackingEnabledLine);

                writeData(context, fileFormatKey, "2");

                new File(context.getFilesDir(), "coins.txt").delete();
                new File(context.getFilesDir(), "score.txt").delete();
                new File(context.getFilesDir(), "selectedCharacter.txt").delete();
                new File(context.getFilesDir(), "unlockedPigeonsAndPowerUps.txt").delete();
                new File(context.getFilesDir(), "selectedPowerUps.txt").delete();
                new File(context.getFilesDir(), "selectedLanguage.txt").delete();
                new File(context.getFilesDir(), "wasSpamAttackingEnabled.txt").delete();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
