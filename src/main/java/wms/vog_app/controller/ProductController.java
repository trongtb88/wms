package wms.vog_app.controller;

import org.json.simple.JSONObject;

import wms.vog_app.common.Utils;


/**
 * Product
 * @author tran-binh-trong
 *
 */
public class ProductController {

	private String userId = "";
	private WSController wSController ;
	String WS_URL_DETAIL_VOG = Utils.getWSDetailVogURL();
	String WS_URL_UPDATE_VOG = Utils.getWSUpdateVogURL();

    public ProductController(String userId) {
    	this.userId = userId;
    	this.wSController = new WSController();
    }
    public JSONObject getDetailVogFromWS(String productCode) {
    	JSONObject inputWS = new JSONObject();
    	inputWS.put("userId", userId);
    	inputWS.put("barcode", productCode);
    	return wSController.processData(WS_URL_DETAIL_VOG, inputWS, "GET");
    }
    public JSONObject updateBarCodeWS(String barcode) {
    	JSONObject inputWS = new JSONObject();
    	inputWS.put("userId", userId);
    	inputWS.put("barcode", barcode);
    	return wSController.processData(WS_URL_UPDATE_VOG, inputWS, "POST");
    }
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
