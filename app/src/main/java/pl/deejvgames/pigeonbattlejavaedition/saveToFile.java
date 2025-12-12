package pl.deejvgames.pigeonbattlejavaedition;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class saveToFile {

    public static String coinsFileName = "coins.txt";
    public static String scoreFileName = "score.txt";
    public static String selectedCharacterFileName = "selectedCharacter.txt";
    public static String unlockedPigeonsAndPowerUpsFileName = "unlockedPigeonsAndPowerUps.txt";
    public static String selectedPowerUpsFileName = "selectedPowerUps.txt";
    public static String selectedLanguageFileName = "selectedLanguage.txt";
    public static String isSpamAttackingEnabledFileName = "isSpamAttackingEnabled.txt";
    public static String wasSpamAttackingEnabledFileName = "wasSpamAttackingEnabled.txt";

    public static void saveData(Context context, String file, String data){
        if(file.equals(coinsFileName)){
            try(FileOutputStream fileOutputStream = context.openFileOutput(file, MODE_PRIVATE)){
                fileOutputStream.write(data.getBytes());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(selectedCharacterFileName)){
            try(FileOutputStream fileOutputStream = context.openFileOutput(file, MODE_PRIVATE)){
                fileOutputStream.write(data.getBytes());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(unlockedPigeonsAndPowerUpsFileName)){
            try(FileOutputStream fileOutputStream = context.openFileOutput(file, MODE_PRIVATE)){
                fileOutputStream.write(data.getBytes());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(selectedPowerUpsFileName)){
            try(FileOutputStream fileOutputStream = context.openFileOutput(file, MODE_PRIVATE)){
                fileOutputStream.write(data.getBytes());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(scoreFileName)){
            try(FileOutputStream fileOutputStream = context.openFileOutput(file, MODE_PRIVATE)){
                fileOutputStream.write(data.getBytes());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(selectedLanguageFileName)){
            try(FileOutputStream fileOutputStream = context.openFileOutput(file, MODE_PRIVATE)){
                fileOutputStream.write(data.getBytes());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(isSpamAttackingEnabledFileName)){
            try(FileOutputStream fileOutputStream = context.openFileOutput(file, MODE_PRIVATE)){
                fileOutputStream.write(data.getBytes());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(wasSpamAttackingEnabledFileName)){
            try(FileOutputStream fileOutputStream = context.openFileOutput(file, MODE_PRIVATE)){
                fileOutputStream.write(data.getBytes());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static String loadData(Context context, String file, String section){
        String line;

        if(file.equals(coinsFileName)){
            try(FileInputStream fileInputStream = context.openFileInput(coinsFileName)){
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line = reader.readLine()) != null){
                    if(section.equals("userCoins")){
                        if(line.startsWith("coins=")){
                            return line.substring(6);
                        }
                    }
                }

            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(selectedCharacterFileName)){
            try(FileInputStream fileInputStream = context.openFileInput(selectedCharacterFileName)){
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line = reader.readLine()) != null){
                    if(section.equals("selectedCharacter")){
                        if(line.startsWith("selectedCharacter=")){
                            return line.substring(18);
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(unlockedPigeonsAndPowerUpsFileName)){
            try(FileInputStream fileInputStream = context.openFileInput(unlockedPigeonsAndPowerUpsFileName)){
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line = reader.readLine()) != null){
                    if(section.equals("isPigeonUnlocked")){
                        if(line.startsWith("isPigeonUnlocked=")){
                            return line.substring(17);
                        }
                    }
                    if(section.equals("isRadioPigeonUnlocked")){
                        if(line.startsWith("isRadioPigeonUnlocked=")){
                            return line.substring(22);
                        }
                    }
                    if(section.equals("isPigobombUnlocked")){
                        if(line.startsWith("isPigobombUnlocked=")){
                            return line.substring(19);
                        }
                    }
                    if(section.equals("isFeatheredPigeonUnlocked")){
                        if(line.startsWith("isFeatheredPigeonUnlocked=")){
                            return line.substring(26);
                        }
                    }
                    if(section.equals("isMilkPigeonUnlocked")){
                        if(line.startsWith("isMilkPigeonUnlocked=")){
                            return line.substring(21);
                        }
                    }
                    if(section.equals("isWheelPigeonUnlocked")){
                        if(line.startsWith("isWheelPigeonUnlocked=")){
                            return line.substring(22);
                        }
                    }
                    if(section.equals("isNuclearPigeonUnlocked")){
                        if(line.startsWith("isNuclearPigeonUnlocked=")){
                            return line.substring(24);
                        }
                    }
                    if(section.equals("isPigeoninUnlocked")){
                        if(line.startsWith("isPigeoninUnlocked=")){
                            return line.substring(19);
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(selectedPowerUpsFileName)){
            try(FileInputStream fileInputStream = context.openFileInput(selectedPowerUpsFileName)){
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line = reader.readLine()) != null){
                    if(section.equals("isPigeoninSelected")){
                        if(line.startsWith("isPigeoninSelected=")){
                            return line.substring(19);
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(scoreFileName)){
            try(FileInputStream fileInputStream = context.openFileInput(scoreFileName)){
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line = reader.readLine()) != null){
                    if(section.equals("score")){
                        if(line.startsWith("score=")){
                            return line.substring(6);
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(selectedLanguageFileName)){
            try(FileInputStream fileInputStream = context.openFileInput(selectedLanguageFileName)){
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line = reader.readLine()) != null){
                    if(section.equals("language")){
                        if(line.startsWith("language=")){
                            return line.substring(9);
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(isSpamAttackingEnabledFileName)){
            try(FileInputStream fileInputStream = context.openFileInput(isSpamAttackingEnabledFileName)){
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line = reader.readLine()) != null){
                    if(section.equals("isSpamAttackingEnabled")){
                        if(line.startsWith("isSpamAttackingEnabled=")){
                            return line.substring(23);
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(file.equals(wasSpamAttackingEnabledFileName)){
            try(FileInputStream fileInputStream = context.openFileInput(wasSpamAttackingEnabledFileName)){
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line = reader.readLine()) != null){
                    if(section.equals("wasSpamAttackingEnabled")){
                        if(line.startsWith("wasSpamAttackingEnabled=")){
                            return line.substring(24);
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void createFiles(Context context){
        File coinsFile = new File(context.getFilesDir(), coinsFileName);
        File selectedCharacterFile = new File(context.getFilesDir(), selectedCharacterFileName);
        File unlockedPigeonsAndPowerUpsFile = new File(context.getFilesDir(), unlockedPigeonsAndPowerUpsFileName);
        File selectedPowerUpsFile = new File(context.getFilesDir(), selectedPowerUpsFileName);
        File scoreFile = new File(context.getFilesDir(), scoreFileName);
        File selectedLanguageFile = new File(context.getFilesDir(), selectedLanguageFileName);
        File wasSpamAttackingEnabledFile = new File(context.getFilesDir(), wasSpamAttackingEnabledFileName);
        if(!coinsFile.exists()){
            saveData(context, coinsFileName, "coins=0");
        }
        if(!selectedCharacterFile.exists()){
            saveData(context, selectedCharacterFileName, "selectedCharacter="+Characters.PIGEON);
        }
        if(!unlockedPigeonsAndPowerUpsFile.exists()){
            saveToFile.saveData(context, unlockedPigeonsAndPowerUpsFileName, "isPigeonUnlocked=true\nisRadioPigeonUnlocked=false\nisPigobombUnlocked=false\nisFeatheredPigeonUnlocked=false\nisMilkPigeonUnlocked=false\nisWheelPigeonUnlocked=false\nisNuclearPigeonUnlocked=false\nisPigeoninUnlocked=false\n");
        }
        if(!selectedPowerUpsFile.exists()){
            saveToFile.saveData(context, selectedPowerUpsFileName, "isPigeoninSelected=false\n");
        }
        if(!scoreFile.exists()){
            saveToFile.saveData(context, scoreFileName, "score=0");
        }
        if(!selectedLanguageFile.exists()){
            saveToFile.saveData(context, selectedLanguageFileName, "language=" + Locale.getDefault().getLanguage());
        }
        if(!wasSpamAttackingEnabledFile.exists()){
            saveToFile.saveData(context, wasSpamAttackingEnabledFileName, "wasSpamAttackingEnabled=false");
        }
    }

    public static void deleteFiles(Context context){
        context.deleteFile(coinsFileName);
        context.deleteFile(selectedCharacterFileName);
        context.deleteFile(unlockedPigeonsAndPowerUpsFileName);
        context.deleteFile(scoreFileName);
        context.deleteFile(isSpamAttackingEnabledFileName);
        context.deleteFile(wasSpamAttackingEnabledFileName);
    }

    public static void deleteSpamAttackingFile(Context context){
        File isSpamAttackingEnabledFile = new File(context.getFilesDir(), isSpamAttackingEnabledFileName);
        if(isSpamAttackingEnabledFile.exists()){
            context.deleteFile(isSpamAttackingEnabledFileName);
        }
    }
}
