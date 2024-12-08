import javax.swing.*;
import java.awt.*;

public class CustomerDashboard extends JFrame {

    public CustomerDashboard(JFrame parent, User curr) {
        super("Customer Dashboard");
        setLayout(new BorderLayout());
        setSize(400, 365);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel firstNameLabel = new JLabel("Welcome, " + curr.getFirstName());

        panel.add(firstNameLabel, gbc);

        add(panel, BorderLayout.CENTER);

    }
}
