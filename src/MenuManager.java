import java.util.List;

public class MenuManager {
    private cafe DB;
	private List<MenuItem> menu;

    public MenuManager() {
        DB = cafe.getInstance();
        menu = DB.getMenu();
    }

    public void addMenuItem(MenuItem item) {
        DB.addMenuItem(item);
    }
    public List<MenuItem> getMenu() {
        return DB.getMenu();
    }
}
