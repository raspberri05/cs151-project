import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class DinerMenuItem implements MenuItem, Serializable  {
	
	private static final long serialVersionUID = 1L;
	
    private String title;
    private String itemID;
    private String description;
    private float price;
    private int count;
    private boolean available;
    private boolean current;
    
    private ArrayList<MenuItem> menuItems = new ArrayList<>(); // Store menu items

   	// xxx your codes
}
