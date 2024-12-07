import java.util.ArrayList;

public class MenuManager {
    private cafe DB;
	private ArrayList<MenuItem> menu;

    public MenuManager() {
        DB = cafe.getInstance();
        menu = DB.getMenu();
    }

    public String getMenuInfo(String itemID) {
        for (MenuItem m : menu) {
            if (m.getItemID().equals(itemID)) {
                return "Name: " + m.getTitle() + "\nPrice: " + m.getPrice() + "\nCount: " + m.getCount() + "\nActive: " + (m.isActive() ? "Yes" : "No");
            }
        }
        return null;
    }

    public void addMenuItem(MenuItem item) {
        DB.addMenuItem(item);
    }

    public void deleteMenuItem(String itemID) {
        DB.deleteMenuItem(itemID);
    }

    public  ArrayList<MenuItem> getMenu() {
        return DB.getMenu();
    }
}
