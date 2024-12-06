import java.util.ArrayList;

public class MenuManager {
    private cafe DB;
	private ArrayList<MenuItem> menu;

    public MenuManager() {
        DB = cafe.getInstance();
        menu = DB.getMenu();
    }

    public void addMenuItem(MenuItem item) {
        DB.addMenuItem(item);
    }
    public  ArrayList<MenuItem> getMenu() {
        return DB.getMenu();
    }
}
