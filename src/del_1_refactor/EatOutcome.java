package del_1_refactor;

public class EatOutcome {

    private EatResult result;
    private String itemName;
    private int healthChange;

    public EatOutcome(EatResult result, String itemName, int healthChange) {
        this.result = result;
        this.itemName = itemName;
        this.healthChange = healthChange;
    }

    public EatResult getResult() {
        return result;
    }

    public String getItemName() {
        return itemName;
    }

    public int getHealthChange() {
        return healthChange;
    }
}