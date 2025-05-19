package pl.deejvgames.pigeonbattlejavaedition;

public enum PowerUps {
    PIGEONIN(R.string.pigeonin, R.drawable.pigeonin, 10, 50, 200, 120); // ADDITIONAL DMG, HEALING HP, ADDITIONAL HP, PRICE

    private final int powerUpNameId;
    private final int powerUpImage;
    private final int additonalDamage;
    private final int healingHp;
    private final int additonalHp;
    private final int powerUpPrice;

    PowerUps(int powerUpNameId, int powerUpImage, int additonalDamage, int healingHp, int additonalHp, int powerUpPrice){
        this.powerUpNameId = powerUpNameId;
        this.powerUpImage = powerUpImage;
        this.additonalDamage = additonalDamage;
        this.healingHp = healingHp;
        this.additonalHp = additonalHp;
        this.powerUpPrice = powerUpPrice;
    }

    public int getPrice(){
        return powerUpPrice;
    }
    public int getAdditonalDamage(){
        return additonalDamage;
    }
    public int getAdditonalHp(){
        return additonalHp;
    }
    public int getHealingHp(){
        return healingHp;
    }
}
