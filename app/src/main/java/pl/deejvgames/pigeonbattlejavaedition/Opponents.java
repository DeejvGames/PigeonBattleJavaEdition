package pl.deejvgames.pigeonbattlejavaedition;

public enum Opponents {
    OPPONENT_PIGEON(R.string.default_pigeon, 1200, 50, 0, 0, 0, 0, R.drawable.opponent_pigeon), // NAME, HP, DMG, DMG PER SECOND, TAKES LESS DAMAGE, SPEED BOOST, PRICE
    OPPONENT_RADIO_PIGEON(R.string.radio_pigeon, 1250, 50, 5, 0, 0, 200, R.drawable.opponent_radio_pigeon),
    OPPONENT_PIGOBOMB(R.string.pigobomb, 1350, 60, 0, 0, 0, 450, R.drawable.opponent_pigobomb),
    OPPONENT_FEATHERED_PIGEON(R.string.feathered_pigeon, 1600, 55, 0, 15, 0, 1400, R.drawable.opponent_feathered_pigeon),
    OPPONENT_MILK_PIGEON(R.string.milk_pigeon, 1250, 70, 0, 10, 0, 800, R.drawable.opponent_milk_pigeon),
    OPPONENT_WHEEL_PIGEON(R.string.wheel_pigeon, 1400, 65, 0, 0, 25, 1000, R.drawable.opponent_wheel_pigeon),
    OPPONENT_NUCLEAR_PIGEON(R.string.nuclear_pigeon, 1450, 90, 10, 0, 0, 1500, R.drawable.opponent_nuclear_pigeon);

    private final int characterNameId;
    private final int characterHP;
    private final int characterDamage;
    private final int characterDamagePerSecond;
    private final int characterLessDamage;
    private final int characterSpeedBoost;
    private final int characterPrice;
    private final int characterImage;

    Opponents(int characterNameId, int characterHP, int characterDamage, int characterDamagePerSecond, int characterLessDamage, int characterSpeedBoost, int characterPrice, int characterImage){
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
