package wms.vog_app.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import wms.vog_app.common.Utils;
import wms.vog_app.common.VogConstants;
import wms.vog_app.controller.ProductController;
import wms.vog_app.controller.WSController;
import wms.vog_app.model.TableModel;
import wms.vog_app.model.TableModel.MyModelTableRender;
import wms.vog_app.model.Vog;

/**
 * Main screen to view product detail Features : Login, Logout Menu and View
 * Product Detail From WS.
 *
 * @author tran-binh-trong
 */

public class ProductFrame extends JFrame {

	private static final long serialVersionUID = 99L;
	final static Logger logger = Logger.getLogger(WSController.class);

	private Map<String, Vog> historiesSearch = new LinkedHashMap<String, Vog>();
	private String userId = "";
	private String fullName = "";

	private final JMenuBar jmb = new JMenuBar();
	private final StatusBar statusBar = new StatusBar();
	private LoginDialog loginDlg = new LoginDialog(this);

	private final JPanel jMainPanel = new JPanel();
	private JTextField jTextProductId = null;
	private JTable jTableData = null;
	private JTextField txtProductCode = new JTextField();

	private JTextField txtComRS232 = new javax.swing.JTextField();
	private JTextField txtType = new javax.swing.JTextField();
	private JTextField txtLength = new javax.swing.JTextField();
	private JTextField txtWidth = new javax.swing.JTextField();
	private JTextField txtThickness = new javax.swing.JTextField();
	private JTextField txtInvoiceNumber = new javax.swing.JTextField();
	private JTextArea txtNote = new javax.swing.JTextArea();

	private String mainFontStyle = Utils.getMainFontStyle();
	private int mainFontSize = Utils.getMainFontSize();
	private String tableFontStyle = Utils.getTableFontStyle();
	private int tableFontSize = Utils.getTableFontSize();
	
	private String receivedDataCom = "";

	private ProductController productController = new ProductController(
			getUserId());

	public ProductFrame() {

		this.setTitle("VOG System");
		loginDlg.setVisible(true);

		createMenu(jmb);
		createMainPanel(jMainPanel);
		this.setJMenuBar(jmb);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize(600, 900);

		if (loginDlg.isSucceeded()) {
			this.setVisible(true);
		} else {
			this.setVisible(false);
		}
		this.pack();

		txtProductCode.requestFocus();
		setDataTable(historiesSearch);
		statusBar.setFont(new java.awt.Font(tableFontStyle, 0, tableFontSize));

	}

	/***
	 * Layoout the main panel
	 *
	 * @param jMainPanel
	 */
	private void createMainPanel(JPanel jMainPanel) {

		JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
		jTableData = new javax.swing.JTable();
		jMainPanel = new javax.swing.JPanel();
		JLabel lblProductCode = new javax.swing.JLabel();
		txtProductCode = new javax.swing.JTextField();
		JButton btnSearch = new javax.swing.JButton();
		JLabel jLabel2 = new javax.swing.JLabel();
		txtComRS232 = new javax.swing.JTextField();
		JLabel lblType = new javax.swing.JLabel();
		txtType = new javax.swing.JTextField();
		JLabel lblLength = new javax.swing.JLabel();
		txtLength = new javax.swing.JTextField();
		JLabel lblWidth = new javax.swing.JLabel();
		txtWidth = new javax.swing.JTextField();
		JLabel lblThickness = new javax.swing.JLabel();
		txtThickness = new javax.swing.JTextField();
		JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		txtNote = new javax.swing.JTextArea();
		JLabel lblNote = new javax.swing.JLabel();
		JSeparator jSeparator1 = new javax.swing.JSeparator();
		JLabel lblHistories = new javax.swing.JLabel();
		JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
		jTableData = new javax.swing.JTable();
		JLabel lblInvoice = new javax.swing.JLabel();
		txtInvoiceNumber = new javax.swing.JTextField();

		TableModel model = new TableModel();
		jTableData.setModel(model);
		jTableData.getTableHeader().setFont(
				new Font(tableFontStyle, 0, tableFontSize));

		jTableData.setDefaultRenderer(Object.class, new MyModelTableRender());
		jTableData.setColumnSelectionAllowed(false);
		jTableData.setRowSelectionAllowed(true);
		jTableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jScrollPane2.setViewportView(jTableData);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jMainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(
				javax.swing.border.BevelBorder.LOWERED, null,
				java.awt.Color.green, null, null));
		jMainPanel.setForeground(new java.awt.Color(51, 51, 255));

		lblProductCode
				.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		lblProductCode.setText("Mã sản phẩm");
		txtProductCode
				.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N

		btnSearch.setText("Tìm Kiếm");

		jLabel2.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		jLabel2.setText("Com RS32");
		jLabel2.setToolTipText("");

		txtComRS232.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		txtComRS232.setEditable(false);

		lblType.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		lblType.setText("Kiểu Màu");

		txtType.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		txtType.setEditable(false);

		lblLength.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		lblLength.setText("Dài");

		txtLength.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		txtLength.setEditable(false);

		lblWidth.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		lblWidth.setText("Rộng");

		txtWidth.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		txtWidth.setEditable(false);

		lblThickness.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		lblThickness.setText("Dày");

		txtThickness.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		txtThickness.setEditable(false);

		txtNote.setColumns(20);
		txtNote.setRows(5);
		txtNote.setPreferredSize(new java.awt.Dimension(245, 80));
		txtNote.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		txtNote.setEditable(false);
		jScrollPane1.setViewportView(txtNote);

		lblNote.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		lblNote.setText("Ghi chú");

		jSeparator1.setSize(new java.awt.Dimension(30, 10));

		lblHistories.setText("Lịch sử");
		jScrollPane3.setViewportView(jTableData);

		lblInvoice.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize)); // NOI18N
		lblInvoice.setText("Mã đơn");

		txtInvoiceNumber.setFont(new java.awt.Font(mainFontStyle, 0,
				mainFontSize)); // NOI18N
		txtInvoiceNumber.setEditable(false);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jMainPanel);
		jMainPanel.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(69, 69, 69)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lblHistories)
														.addComponent(
																jScrollPane3))
										.addGap(72, 72, 72))
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(220, 220, 220)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jSeparator1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				713,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lblProductCode)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								lblType)
																						.addComponent(
																								lblLength)
																						.addComponent(
																								lblWidth)
																						.addComponent(
																								lblThickness)
																						.addComponent(
																								lblNote)
																						.addComponent(
																								lblInvoice))
																		.addGap(65,
																				65,
																				65)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								300,
																								Short.MAX_VALUE)
																						.addComponent(
																								txtInvoiceNumber)
																						.addComponent(
																								txtProductCode)
																						.addComponent(
																								txtComRS232)
																						.addComponent(
																								txtType)
																						.addComponent(
																								txtLength)
																						.addComponent(
																								txtWidth)
																						.addComponent(
																								txtThickness))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnSearch)
																		.addGap(363,
																				363,
																				363))))
						.addComponent(statusBar,
								javax.swing.GroupLayout.Alignment.TRAILING));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(38, 38, 38)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																btnSearch,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																36,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel1Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				lblProductCode)
																		.addComponent(
																				txtProductCode,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(13, 13, 13)
										.addComponent(
												jSeparator1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												10,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel2)
														.addComponent(
																txtComRS232,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtType,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblType))
										.addGap(7, 7, 7)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtLength,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblLength))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtWidth,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblWidth))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtThickness,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblThickness))
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(166,
																				166,
																				166)
																		.addComponent(
																				lblHistories))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								txtInvoiceNumber,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblInvoice))
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jScrollPane1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGap(19,
																												19,
																												19)
																										.addComponent(
																												lblNote)))))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												324, Short.MAX_VALUE)
										.addGap(47, 47, 47)
										.addComponent(
												statusBar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												26,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jMainPanel, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jMainPanel, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jScrollPane3.setHorizontalScrollBarPolicy(
			   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane3.setVerticalScrollBarPolicy(
			   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 

		pack();

		// Search action event
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				search();
			}
		});
		btnSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					search();
				}
			}
		});
		txtProductCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					search();
				}
			}
		});

		// Jtable click event
		jTableData.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (jTableData.getSelectedRow() >= 0) {
							Vog vog = (Vog) historiesSearch.get(jTableData
									.getValueAt(jTableData.getSelectedRow(), 0)
									.toString());
							txtProductCode.setText(vog.getProductCode());
							txtComRS232.setText(vog.getComRS232());
							txtType.setText(vog.getType());
							txtLength.setText(vog.getLength());
							txtWidth.setText(vog.getWidth());
							txtThickness.setText(vog.getThickness());
							txtInvoiceNumber.setText(vog.getInvoiceNumber());
							txtNote.setText(vog.getNote());
						}
					}
				});

		// Window event
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, VogConstants.EXIT_MSG,
						"Really Closing?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}

	/**
	 * Search detail product after inputing product code Call 1 other thread to
	 * make sure app is responsible to user activities.
	 */
	private void search() {
		String s = txtProductCode.getText().trim();
		if (!s.equals("")) {
			logger.info("Search information for product code : " + s);
			txtProductCode.setText(s);
			Thread searchingThread = new Thread(new SearchingThread());
			searchingThread.start();

		} else {
			JOptionPane.showMessageDialog(null,
					VogConstants.REQUIRED_INPUT_MSG, "Input error",
					JOptionPane.ERROR_MESSAGE);
			txtProductCode.requestFocus();
			txtProductCode.selectAll();
		}
	}

	/**
	 * Create MenuBar for mainframe
	 *
	 * @param jmb
	 */
	private void createMenu(JMenuBar jmb) {

		JMenu jmFile = new JMenu("File");
		JMenuItem jmiLgout = new JMenuItem("Logout");
		JMenuItem jmiExit = new JMenuItem("Exit");
		jmFile.add(jmiLgout);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		jmb.add(jmFile, Box.LEFT_ALIGNMENT);

		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		jmHelp.add(jmiAbout);
		jmb.add(jmHelp, Box.LEFT_ALIGNMENT);

		jmb.add(Box.createHorizontalGlue());
		JLabel lblName = new JLabel("Xin Chào : " + getFullName());
		lblName.setFont(new java.awt.Font(mainFontStyle, 0, mainFontSize));
		lblName.setForeground(Color.BLUE);
		jmb.add(lblName);

		jmiLgout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane
						.showConfirmDialog(null, VogConstants.LOGOUT_MSG,
								"Really Logout?", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					setVisible(false);
					loginDlg.setVisible(true);
				}

			}

		});
		jmiExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, VogConstants.EXIT_MSG,
						"Really Closing?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});
		jmiAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog aboutDialog = new AboutDialog();
				aboutDialog.setVisible(true);
			}

		});

	}

	public JTextField getjTextProductId() {
		return jTextProductId;
	}


	/**
	 * Thread to search and process data return from WS
	 *
	 * @author tran-binh-trong
	 */
	private class SearchingThread implements Runnable {

		public SearchingThread() {
		}

		public void run() {
			productController.setUserId(getUserId());
			statusBar.setText("Searching...");
			JSONObject outputDetailVog = new JSONObject();
			Vog vog = new Vog();
			boolean isFailedWS = false;
			final String searchValue = txtProductCode.getText().trim();

			// 1. Get detail product from WS
			if (!historiesSearch.containsKey(txtProductCode.getText().trim())) {
				outputDetailVog = productController
						.getDetailVogFromWS(searchValue);
				if (outputDetailVog.containsKey("code")
						&& outputDetailVog.get("code").toString()
								.equals(VogConstants.WS_FAILED)) {
					statusBar.setText(outputDetailVog.get("msg").toString());
					isFailedWS = true;
				} else {
					vog = Vog.createFromJSon(outputDetailVog);
				}
			} else {
				vog = (Vog) historiesSearch.get(searchValue);
			}
			final Vog foundVog = vog;
			final boolean isFailedCallWS = isFailedWS;

			// 2. Update GUI
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							// Update value
							if (!isFailedCallWS) {
								if (StringUtils.isNotEmpty(foundVog
										.getProductCode())
										&& foundVog.getProductCode().toString() != "null") {
									historiesSearch.put(foundVog.getProductCode(), foundVog);
									txtComRS232.setText(foundVog.getComRS232());
									txtType.setText(foundVog.getType());
									txtLength.setText(foundVog.getLength());
									txtWidth.setText(foundVog.getWidth());
									txtThickness.setText(foundVog
											.getThickness());
									txtInvoiceNumber.setText(foundVog
											.getInvoiceNumber());
									txtNote.setText(foundVog.getNote());
									statusBar
											.setText("Search completed, Found 1 record");
									txtProductCode.requestFocus();
									txtProductCode.selectAll();
									// 3. Send Data to COM port
									if (!isFailedCallWS
											&& StringUtils.isNotEmpty(foundVog
													.getComRS232())) {
										sendDataToCOM(foundVog.getComRS232(),
												foundVog);
									}
									setDataTable(historiesSearch);
									receivedDataCom = txtProductCode.getText();

								} else {
									JOptionPane.showMessageDialog(null,
											"Không tìm thấy sản phẩm !"
													+ searchValue, "Lỗi",
											JOptionPane.ERROR_MESSAGE);
									txtProductCode.requestFocus();
									statusBar
											.setText("Search completed, Found 0 record");
								}
							}

						}

					});

				}
			});

		}

	}

	/***
	 * Method to send data to com
	 *
	 * @param data
	 * @param currentVog
	 */
	private void sendDataToCOM(String data, Vog currentVog) {
		String strSerialPort = Utils.getCOMPort();
		logger.info("Start sending data to COM...");
		statusBar.setText("Start sending data to COM...");
		final SerialPort serialPort = new SerialPort(strSerialPort);
		try {
			// Open serial port
			serialPort.openPort();
			serialPort.setParams(Utils.getCOMBaudRate(),
					Utils.getCOMDataBits(), Utils.getCOMSTopBits(),
					Utils.getCOMParity());
			// Write data to port
			serialPort.writeBytes(data.getBytes());
			statusBar.setText("Sending data to COM successfully!");
			logger.info("Sending data to COM successfully!");
			// Update value on jtable cell
			currentVog.setComSentStatus(VogConstants.COM_OK);

			JSONObject outputUpdateVog = productController.updateBarCodeWS(this.txtProductCode.getText().trim());
			
			// Add event to listen data from com port.
			serialPort.addEventListener(new SerialPortEventListener() {
			    @Override
                		public void serialEvent(SerialPortEvent serialPortEvent) {
                		    if (serialPortEvent.isRXCHAR()) { // if we receive data
                			if (serialPortEvent.getEventValue() > 0) { // if there
                								   // is some
                								   // existent
                								   // data
                			    try {
                				String receivedData = serialPort.readString();
                				byte[] bytes = serialPort.readBytes();
                				System.out.println("RECEIVED FROM COM in bytes " + new String(bytes));
                				logger.info("RECEIVED FROM COM" + receivedData);
                				// Change color of this rsr232
                				if (StringUtils.isNotEmpty(receivedData)) {
                				    //Call Update webservice RS232.
                				    updateColor(receivedData);
                				    logger.info("Calling update WS RS232 ");
                				    productController.updateRS232(txtProductCode.getText().trim());
                				    logger.info("Update WS RS232 successfully.");
                				}
                			    } catch (SerialPortException e) {
                				logger.error("Reading data from COM fail update WS RS232 ");
                				e.printStackTrace();
                			    }
                
                			}
                		    }
                		}
    
			});
			if (outputUpdateVog.containsKey("code")
					&& outputUpdateVog.get("code").toString()
							.equals(VogConstants.WS_OK)) {
				statusBar.setText("Update barcode again successfully");
				logger.info("Sending data to COM successfully!");
			} else {
				statusBar.setText("Update barcode again failed");
				logger.info("Update barcode again failed");
			}
		} catch (SerialPortException ex) {
			currentVog.setComSentStatus(VogConstants.COM_ERROR);
			statusBar.setText("Failed to send data to COM!" + ex.getMessage());
			logger.error("Failed to send data to COM!", ex);
		} finally {
			historiesSearch.put(currentVog.getProductCode(), currentVog);
		}
	}

	/**
	 * Update datatable if have any changes
	 *
	 * @param historiesSearch
	 */
	private void setDataTable(Map<String, Vog> historiesSearch) {

		if (!historiesSearch.isEmpty()) {
			List<Vog> vogs = new ArrayList<Vog>(historiesSearch.values());
			Collections.reverse(vogs);
			TableModel model = new TableModel(vogs);
			this.jTableData.setModel(model);
			this.jTableData.updateUI();
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
	
	public class MyModelTableRender extends DefaultTableCellRenderer{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
	        Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            if (isSelected || hasFocus) {
                this.setBackground(Color.BLUE);
                this.setForeground(Color.WHITE);

            } else {
            	setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            
            String productCode = (String) table.getValueAt(row, 0);
	        if (column == 6) {
		        System.out.println(getReceivedDataCom() + productCode);
	        	Vog vog = historiesSearch.get(productCode);
	        	if (vog != null) {
	        		if (getReceivedDataCom() != null 
	        				&& getReceivedDataCom().length() > 0 
	        				&& vog.getComRS232().toString().equals(getReceivedDataCom())) {
	        		    setBackground(Color.green);
	        		}
	        	}
	        }
            return tableCellRendererComponent;
	    }
	}
	
	public void updateColor(String receivedData) {
	    setReceivedDataCom(receivedData);
	    jTableData.repaint();
	}

	public String getReceivedDataCom() {
	    return receivedDataCom;
	}

	public void setReceivedDataCom(String receivedDataCom) {
	    this.receivedDataCom = receivedDataCom;
	}


}
