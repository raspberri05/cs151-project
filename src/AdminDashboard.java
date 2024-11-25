import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard(JFrame parent, User admin) {
        super("Admin Dashboard");
        setLayout(new BorderLayout());
        setSize(735, 446);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel firstNameLabel = new JLabel("Welcome, " + admin.getFirstName());
        JButton manageCustomersButton = new JButton("Manage Customers");
        JButton manageMenuButton = new JButton("Manage Menu");

        panel.add(firstNameLabel, gbc);
        panel.add(manageCustomersButton, gbc);
        panel.add(manageMenuButton, gbc);

        add(panel, BorderLayout.CENTER);


	}
}
