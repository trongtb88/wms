package wms.vog_app.model;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/***
 * Table model for jtable, define columns and rows
 * @author tran-binh-trong
 *
 */
public class TableModel extends AbstractTableModel {

	private static final long serialVersionUID = 109L;
	private List<Vog> vogs;
	
	private String receiveDataCom;

    
	String headerList[] = new String[] { "Mã sản phẩm", "Kiểu Màu", "Dài", "Rộng", "Dày", "Mã đơn", "Gửi tới COM" };

	public TableModel(List<Vog> vogs) {
		this.vogs = vogs;
	}

	public TableModel() {
	}

	public int getColumnCount() {
		return 7;
	}

	public int getRowCount() {
		return (vogs != null ? vogs.size() : 0);
	}
	public Object getValueAt(int row, int column) {
		Vog entity = null;
		entity= (Vog) vogs.get(row);

		switch (column) {

		case 0:
			return entity.getProductCode();
		case 1:
			return entity.getType();
		case 2:
			return entity.getLength();
		case 3:
			return entity.getWidth();
		case 4:
			return entity.getThickness();
		case 5:
			return entity.getInvoiceNumber();
		case 6:
			return entity.getComSentStatus();
		default :
			return "";
		}

	}
	public String getColumnName(int col) {
		return headerList[col];
	}
	public class MyModelTableRender extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (vogs.get(row).getComRS232() == receiveDataCom) c.setBackground(Color.orange);
            else c.setBackground(table.getBackground());
            table.repaint();
            return c;
        }
    }
	public String getReceiveDataCom() {
		return receiveDataCom;
	}

	public void setReceiveDataCom(String receiveDataCom) {
		this.receiveDataCom = receiveDataCom;
	}
}