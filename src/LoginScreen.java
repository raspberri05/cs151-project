import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JDialog {
	

    public LoginScreen(JFrame parent) {
        super(parent, "Login", true);
        setLayout(new BorderLayout());
        setSize(400, 365);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;


        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                UserManager manageUsers = new UserManager();
                User loggedInUser = manageUsers.login(username, password);
                String role = loggedInUser.getRole();
                if (role.equals("Admin")) {
                    AdminDashboard adminDashboard = new AdminDashboard(parent, loggedInUser);
                    adminDashboard.setVisible(true);
                    dispose();

                }
            }
        });

        panel.add(usernameLabel, gbc);
        panel.add(usernameField, gbc);
        panel.add(passwordLabel, gbc);
        panel.add(passwordField, gbc);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);
    }
}
