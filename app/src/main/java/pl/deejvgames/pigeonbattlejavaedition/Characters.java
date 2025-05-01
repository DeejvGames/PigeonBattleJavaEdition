package pl.deejvgames.pigeonbattlejavaedition;

public enum Characters {

    PIGEON(R.string.default_pigeon, 1200, 5, 0, 0, 0, 0, R.drawable.pigeon), // NAME, HP, DMG, DMG PER SECOND, TAKES LESS DAMAGE, SPEED BOOST, PRICE
    RADIO_PIGEON(R.string.radio_pigeon, 1250, 5, 5, 0, 0, 60, R.drawable.radio_pigeon),
    PIGOBOMB(R.string.pigobomb, 1300, 25, 0, 0, 0, 180, R.drawable.pigobomb),
    FEATHERED_PIGEON(R.string.feathered_pigeon, 1500, 5, 0, 15, 0, 200, R.drawable.feathered_pigeon),
    MILK_PIGEON(R.string.milk_pigeon, 1200, 15, 0, 10, 0, 170, R.drawable.milk_pigeon),
    WHEEL_PIGEON(R.string.wheel_pigeon, 1250, 10, 0, 0, 25, 150, R.drawable.wheel_pigeon),
    NUCLEAR_PIGEON(R.string.nuclear_pigeon, 1400, 35, 0, 0, 0, 220, R.drawable.nuclear_pigeon);

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
}
