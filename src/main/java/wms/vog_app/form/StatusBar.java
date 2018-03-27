package wms.vog_app.form;

import java.awt.Dimension;

import javax.swing.JTextField;

/**
 * For display status of application.
 *
 * @author tran-binh-trong
 */
public class StatusBar extends JTextField {

	private static final long serialVersionUID = 88;

	/** Creates a new instance of StatusBar */
	public StatusBar() {
		super();
		super.setPreferredSize(new Dimension(100, 20));
		setMessage("Ready");
		this.setEditable(false);
	}

	public void setMessage(String message) {
		setText(" " + message);
	}

}