public class PancakeMenuItem implements MenuItem {
        
    private String title;
    private String itemID;
    private String description;
    private float price;
    private int count;
    private boolean available;
    private boolean current;

    public PancakeMenuItem(String title, String itemID, String description, float price, int count, boolean available, boolean current) {
        this.title = title;
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.count = count;
        this.available = available;
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
        return available;
    }

    public String getMenuType() {
        return "Pancake";
    }

    public String toDataString() {
        return String.format("Title: %s, ItemID: %s, Description: %s, Price: %.2f, Count: %d, Available: %b, Current: %b",
                title, itemID, description, price, count, available, current);
    }
}