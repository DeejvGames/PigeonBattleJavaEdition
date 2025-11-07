package pl.deejvgames.pigeonbattlejavaedition;

public enum Characters {

    PIGEON(R.string.default_pigeon, 1200, 50, 0, 0, 0, 0, R.drawable.player_pigeon), // NAME, HP, DMG, DMG PER SECOND, TAKES LESS DAMAGE, SPEED BOOST, PRICE
    RADIO_PIGEON(R.string.radio_pigeon, 1250, 50, 5, 0, 0, 150, R.drawable.player_radio_pigeon),
    PIGOBOMB(R.string.pigobomb, 1350, 60, 0, 0, 0, 450, R.drawable.player_pigobomb),
    FEATHERED_PIGEON(R.string.feathered_pigeon, 1600, 55, 0, 15, 0, 1000, R.drawable.player_feathered_pigeon),
    MILK_PIGEON(R.string.milk_pigeon, 1250, 70, 0, 10, 0, 600, R.drawable.player_milk_pigeon),
    WHEEL_PIGEON(R.string.wheel_pigeon, 1400, 65, 0, 0, 25, 800, R.drawable.player_wheel_pigeon),
    NUCLEAR_PIGEON(R.string.nuclear_pigeon, 1450, 90, 10, 0, 0, 1200, R.drawable.player_nuclear_pigeon);

    private final int characterNameId;
    private final int characterHP;
    private final int characterDamage;
    private final int characterDamagePerSecond;
    private final int characterLessDamage;
    private final int characterSpeedBoost;
    private final int characterPrice;
    private final int characterImage;

    Characters(int characterNameId, int characterHP, int characterDamage, int characterDamagePerSecond, int characterLessDamage, int characterSpeedBoost, int characterPrice, int characterImage){
        this.characterNameId = characterNameId;
        this.characterHP = characterHP;
        this.characterDamage = characterDamage;
        this.characterDamagePerSecond = characterDamagePerSecond;
        this.characterLessDamage = characterLessDamage;
        this.characterSpeedBoost = characterSpeedBoost;
        this.characterPrice = characterPrice;
        this.characterImage = characterImage;
    }

    public int getPrice(){
        return characterPrice;
    }
    public int getHP(){
        return characterHP;
    }
    public int getImage(){
        return characterImage;
    }
    public int getCharacterSpeedBoost(){
        return characterSpeedBoost;
    }
    public int getCharacterDamage(){
        return characterDamage;
    }
    public int getCharacterDamagePerSecond(){
        return characterDamagePerSecond;
    }
    public int getCharacterLessDamage(){
        return characterLessDamage;
    }
}
