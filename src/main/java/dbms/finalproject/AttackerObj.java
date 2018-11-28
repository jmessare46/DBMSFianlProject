package dbms.finalproject;

public class AttackerObj {
    private String name;
    private Double id;
    private String weaponType;
    private String weapon;
    private Integer nWounded;
    private Integer damageCost;
    private String motive;

    public AttackerObj(String name, Double id, String weaponType, String weapon, Integer nWounded, Integer damageCost, String motive) {
        this.name = name;
        this.id = id;
        this.weaponType = weaponType;
        this.weapon = weapon;
        this.nWounded = nWounded;
        this.damageCost = damageCost;
        this.motive = motive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getId() {
        return id;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public Integer getnWounded() {
        return nWounded;
    }

    public void setnWounded(Integer nWounded) {
        this.nWounded = nWounded;
    }

    public Integer getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(Integer damageCost) {
        this.damageCost = damageCost;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }
}
