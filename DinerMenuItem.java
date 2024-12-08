public class DinerMenuItem implements MenuItem {

    private String title;
    private String itemID;
    private String description;
    private float price;
    private int count;
    private boolean current;

    public DinerMenuItem(String title, String itemID, String description, float price, int count, boolean current) {
        this.title = title;
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.count = count;
        this.current = current;
    }

    public String getTitle() {
        return title;
    }

    public String getItemID() {
        return itemID;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public boolean isAvailable() {
        return count > 0;
    }

    public String getMenuType() {
        return "Diner";
    }

    public boolean isActive() {
        return current;
    }

    public void setActive(boolean active) {
        current = active;
    }

    @Override
    public String toDataString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;", getMenuType(), title, itemID, description, price, count, current);
    }
}