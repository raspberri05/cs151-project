import javax.swing.*;
import java.awt.*;

public class UserManagerScreen extends JFrame {

    public UserManagerScreen(JFrame parent) {
        super("Manage Users");
        setLayout(new BorderLayout());
        setSize(735, 446);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel("User Manager Screen");

        panel.add(label, gbc);

        add(panel, BorderLayout.CENTER);


	}
}
