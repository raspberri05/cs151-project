import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CafeOnlineOrderSystemGUI extends JFrame{
	
	public CafeOnlineOrderSystemGUI(cafe myCafe) {

        setTitle("Naya's Diner");
        setSize(735, 446);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel background = new JLabel(new ImageIcon("resources/background.jpg"));
        setContentPane(background);
        background.setLayout(new GridBagLayout());


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Welcome to Naya's Diner!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        welcomePanel.add(welcomeLabel);

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        JButton exitButton = new JButton("Exit");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen(CafeOnlineOrderSystemGUI.this);
                loginScreen.setVisible(true);
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignupScreen signupScreen = new SignupScreen(CafeOnlineOrderSystemGUI.this);
                signupScreen.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.setOpaque(false);

        panel.add(welcomePanel, gbc);
        panel.add(loginButton, gbc);
        panel.add(signUpButton, gbc);
        panel.add(exitButton, gbc);

        background.add(panel, gbc);


        add(panel);

	}

        public static void main(String[] args) {
                cafe myCafe = cafe.DB;
                CafeOnlineOrderSystemGUI gui = new CafeOnlineOrderSystemGUI(myCafe);
                gui.setVisible(true);
        }
}
