import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;


public class SignupScreen extends JDialog {

	public SignupScreen(JFrame parent, Map<String, User> users) {
		super(parent, "Sign Up", true);
        UserManager userManager = new UserManager();
        setLayout(new BorderLayout());
        setSize(400, 550);
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

        String[] roles = {"Customer", "Administrator"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setPreferredSize(new Dimension(firstNameField.getPreferredSize()));

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);
	}
}
