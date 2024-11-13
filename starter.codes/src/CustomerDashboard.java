import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDashboard extends JFrame {

	private UserManager userManager;
	private User currentUser; // The user who is currently logged in

	private JTextPane cartPane;
	private JTextPane billPane;
	private JTextPane menuPane;
	private StyledDocument cartDoc;
	private StyledDocument billDoc;
	private StyledDocument menuDoc;

	private JCheckBox breakfastCheckbox;
	private JCheckBox dinnerCheckbox;

	private JPanel tipPanel;
	private ButtonGroup tipGroup;
	private JRadioButton noTipButton;
	private JRadioButton tenPercentButton;
	private JRadioButton fifteenPercentButton;
	private JRadioButton twentyPercentButton;

   	// xxx your codes
}
