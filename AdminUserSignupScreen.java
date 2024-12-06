import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AdminUserSignupScreen extends JDialog {

	public AdminUserSignupScreen(JFrame parent) {
		super(parent, "Sign Up", true);
        UserManager manageUsers = new UserManager();
        setLayout(new BorderLayout());
        setSize(600, 700);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel firstNameLabel = new JLabel("First Name");
        JLabel lastNameLabel = new JLabel("Last Name");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        JLabel roleLabel = new JLabel("Role");

        JTextField firstNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        String[] roles = {"Customer", "Admin"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setPreferredSize(new Dimension(firstNameField.getPreferredSize()));

        JCheckBox activeCheckBox = new JCheckBox("Active");

        JButton submitButton = new JButton("Add User");
        JButton cancelButton = new JButton("Cancel");

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String role = roleComboBox.getSelectedItem().toString();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String username = createUsername(firstName);
                boolean isActive = activeCheckBox.isSelected(); // Retrieve the checkbox value
                if (role.equals("Admin")) {
                    Admin admin = new Admin(firstName, lastName, email, username, password, isActive);
                    manageUsers.addUser(admin);
                }
                else {
                    Customer customer = new Customer(firstName, lastName, email, username, password, isActive, new ArrayList<>());
                    manageUsers.addUser(customer);
                }
                JOptionPane.showMessageDialog(null, "This User's username is " + username, "User Added Successfully!", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        panel.add(firstNameLabel, gbc);
        panel.add(firstNameField, gbc);
        panel.add(lastNameLabel, gbc);
        panel.add(lastNameField, gbc);
        panel.add(emailLabel, gbc);
        panel.add(emailField, gbc);
        panel.add(passwordLabel, gbc);
        panel.add(passwordField, gbc);
        panel.add(roleLabel, gbc);
        panel.add(roleComboBox, gbc);
        panel.add(activeCheckBox, gbc);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);
	}

    private String createUsername(String username) {
        int random = (int) (Math.random() * 9000) + 1000;
        return username + random;
    }
}
