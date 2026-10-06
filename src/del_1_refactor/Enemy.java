package del_1_refactor;

public class Enemy {
    private String shortname;
    private String longname;
    private String description;
    private int health;
    private Weapon weapon;
    private Room currentroom;

    public Enemy(String shortname, String longname, String description,
                 int health, Weapon weapon, Room currentroom) {
        this.shortname = shortname;
        this.longname = longname;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.currentroom = currentroom;
    }

    public String getShortName() {
        return shortname;
    }

    public String getLongName() {
        return longname;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void hit(Weapon weapon) {
        health -= weapon.getDamage();
        if (isDead()) {
            currentroom.addItem(this.weapon);
            currentroom.removeEnemy(this);
        }
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int attack() {
        if (!weapon.canUse()) {
            return 0;
        }
        weapon.use();
        return weapon.getDamage();
    }
}
