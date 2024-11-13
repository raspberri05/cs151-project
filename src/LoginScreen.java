import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class LoginScreen extends JDialog {
	
	private Map<String, User> users;

    public LoginScreen(JFrame parent, Map<String, User> users) {
        super(parent, "Login", true);
        UserManager userManager = new UserManager();
        setLayout(new BorderLayout());
        setSize(300, 300);
        setLocationRelativeTo(parent);

	// xxx your codes
}
