import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class PancakeMenuItem implements MenuItem, Serializable {
	private static final long serialVersionUID = 1L;
	
    private String title;
    private String itemID;
    private String description;
    private float price;
    private int count;
    private boolean available;
    private boolean current;
    
    private ArrayList<MenuItem> menuItems = new ArrayList<>(); // Store menu items

    public PancakeMenuItem(String title, String itemID, String description, float price, int count, boolean current) {
        this.title = title;
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.count = count;
        this.current = current;
        this.available = count > 0 && current;
    }
   		// xxx your codes
}
