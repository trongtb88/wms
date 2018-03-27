package wmg.vog_app.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import wmg.vog_app.common.Utils;
import wmg.vog_app.common.VogConstants;
import wmg.vog_app.controller.LoginController;

/**
 * Login screen to WS Input : userId, Password Output : userId
 *
 * @author tran-binh-trong
 *
 */

public class LoginDialog extends JDialog {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lblUserName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    private String userId;
    private String fullName;
    private ProductFrame parentFrame;
    private String version = "";

    public LoginDialog(JFrame parent) {
	super(parent, "Login", true);
	parentFrame = (ProductFrame) parent;
	//
	JPanel panel = new JPanel(new GridBagLayout());
	GridBagConstraints cs = new GridBagConstraints();

	cs.fill = GridBagConstraints.HORIZONTAL;
	version = Utils.getAppVersion();
	String strTitleLogin = "Welcome to WMS System" + "-v" + version;
	setTitle(strTitleLogin);

	lblUserName = new javax.swing.JLabel();
	txtUsername = new javax.swing.JTextField();
	lblPassword = new javax.swing.JLabel();
	txtPassword = new javax.swing.JPasswordField();
	btnLogin = new javax.swing.JButton();
	btnCancel = new javax.swing.JButton();

	txtUsername.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    authenticate();
		}
	    }
	});

	txtPassword.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    authenticate();
		}
	    }
	});

	btnLogin = new JButton("Login");

	btnLogin.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    authenticate();
		}
	    }
	});
	btnLogin.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		authenticate();
	    }
	});
	btnCancel = new JButton("Cancel");

	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	lblUserName.setText("UserName");

	lblPassword.setText("Password");

	btnLogin.setText("Login");

	btnCancel.setText("Cancel");

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
		getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
							layout.createSequentialGroup()
								.addGap(54, 54,
									54)
								.addGroup(
									layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(
											lblUserName,
											javax.swing.GroupLayout.PREFERRED_SIZE,
											75,
											javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
											lblPassword))
								.addPreferredGap(
									javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
									layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING,
										false)
										.addComponent(
											txtUsername,
											javax.swing.GroupLayout.DEFAULT_SIZE,
											242,
											Short.MAX_VALUE)
										.addComponent(
											txtPassword)))
						.addGroup(
							layout.createSequentialGroup()
								.addGap(153,
									153,
									153)
								.addComponent(
									btnLogin,
									javax.swing.GroupLayout.PREFERRED_SIZE,
									91,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
									javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(
									btnCancel,
									javax.swing.GroupLayout.PREFERRED_SIZE,
									88,
									javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(89, Short.MAX_VALUE)));
	layout.setVerticalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addGap(54, 54, 54)
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(
							txtUsername,
							javax.swing.GroupLayout.PREFERRED_SIZE,
							32,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(
							lblUserName,
							javax.swing.GroupLayout.PREFERRED_SIZE,
							25,
							javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(
					javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(
							txtPassword,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							32, Short.MAX_VALUE))
				.addPreferredGap(
					javax.swing.LayoutStyle.ComponentPlacement.RELATED,
					javax.swing.GroupLayout.DEFAULT_SIZE,
					Short.MAX_VALUE)
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnCancel))
				.addGap(56, 56, 56)));

	pack();

	btnCancel.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    if (JOptionPane.showConfirmDialog(parentFrame,
			    VogConstants.EXIT_MSG, "Really Closing?",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			System.exit(0);
		    }
		}
	    }
	});
	btnCancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (JOptionPane
			.showConfirmDialog(parentFrame, VogConstants.EXIT_MSG,
				"Really Closing?", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		    System.exit(0);
		}
	    }
	});
	this.addWindowListener(new java.awt.event.WindowAdapter() {
	    @Override
	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		System.exit(0);
	    }

	});

	pack();
	setResizable(false);
	setSize(466, 220);
	setLocationRelativeTo(parent);
    }

    public String getUsername() {
	return txtUsername.getText().trim();
    }

    public String getPassword() {
	return new String(txtPassword.getPassword());
    }

    public boolean isSucceeded() {
	return succeeded;
    }

    private void authenticate() {
	JSONObject loginOutputObj = LoginController.checkLogin(getUsername(),
		getPassword());
	if (loginOutputObj != null
		&& loginOutputObj.get("code").toString()
			.equals(VogConstants.LOGIN_OK_CODE)) {
	    succeeded = true;
	    setUserId(loginOutputObj.get("code").toString());
	    setFullName(loginOutputObj.get("name").toString());
	    setVisible(false);
	    parentFrame.setVisible(true);
	    parentFrame.setUserId(getUserId());
	    parentFrame.setFullName(getFullName());
	} else {
	    JOptionPane.showMessageDialog(LoginDialog.this,
		    "Username hoặc mật khẩu không hợp lệ", "Login",
		    JOptionPane.ERROR_MESSAGE);
	    txtUsername.requestFocus();
	    succeeded = false;
	}
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

}