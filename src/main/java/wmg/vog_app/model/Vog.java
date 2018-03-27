package wmg.vog_app.model;

import org.json.simple.JSONObject;

import wmg.vog_app.common.VogConstants;


/***
 * Entity map with json output
 * @author tran-binh-trong
 *
 */
public class Vog {

	private String productCode = "";
	private String comRS232 = "";
	private String type = "";
	private String length = "";
	private String width = "";
	private String thickness = "";
	private String invoiceNumber = "";
	private String note = "";
	private String comSentStatus = "ERROR";

	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getComRS232() {
		return comRS232;
	}
	public void setComRS232(String comRS232) {
		this.comRS232 = comRS232;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getThickness() {
		return thickness;
	}
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public static Vog createFromJSon(JSONObject outputDetailVog) {
		Vog vog = new Vog();
		vog.setProductCode(outputDetailVog.containsKey(VogConstants.PRODUCT_CODE) ? outputDetailVog.get(VogConstants.PRODUCT_CODE).toString() : "");
		vog.setComRS232(outputDetailVog.containsKey(VogConstants.RS232) ? outputDetailVog.get(VogConstants.RS232).toString() : "");
		vog.setType(outputDetailVog.containsKey(VogConstants.TYPE) ? outputDetailVog.get(VogConstants.TYPE).toString() : "");
		vog.setLength(outputDetailVog.containsKey(VogConstants.LENGTH) ? outputDetailVog.get(VogConstants.LENGTH).toString() : "");
		vog.setWidth(outputDetailVog.containsKey(VogConstants.WIDTH) ? outputDetailVog.get(VogConstants.WIDTH).toString() : "");
		vog.setThickness(outputDetailVog.containsKey(VogConstants.THICKNESS) ? outputDetailVog.get(VogConstants.THICKNESS).toString() : "");
		vog.setInvoiceNumber(outputDetailVog.containsKey(VogConstants.INVOICE_NUMBER) ? outputDetailVog.get(VogConstants.INVOICE_NUMBER).toString() : "");
		vog.setNote(outputDetailVog.containsKey(VogConstants.NOTE) ? outputDetailVog.get(VogConstants.NOTE).toString() : "");
		return vog;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getComSentStatus() {
		return comSentStatus;
	}
	public void setComSentStatus(String comSentStatus) {
		this.comSentStatus = comSentStatus;
	}
}
