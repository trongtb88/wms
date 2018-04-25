package wms.vog_app.common;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class Utils {

	private static final String WS_URL_LOGIN = "ws.url.checklogin";
	private static final String WS_URL_DETAIL_VOG = "ws.url.detailVog";
	private static final String WS_URL_UPDATE_VOG = "ws.url.updateVog";
	private static final String WS_URL_UPDATE_RS232_VOG = "ws.url.updateRS232";

	private static final String APP_VERSION = "project.version";
	private static final String APP_MAIN_FONT_STYLE= "project.main.fontStyle";
	private static final String APP_MAIN_FONT_SIZE= "project.main.fontSize";
	private static final String APP_TABLE_FONT_STYLE= "project.table.fontStyle";
	private static final String APP_TABLE_FONT_SIZE= "project.table.fontSize";
	private static final String APP_COM_PORT= "project.com.port";
	private static final String APP_COM_BAUDRATE= "project.com.baudrate";
	private static final String APP_COM_DATABITS= "project.com.databits";
	private static final String APP_COM_STOPBITS= "project.com.stopbits";
	private static final String APP_COM_PARITY= "project.com.parity";
	private static final String APP_COM_CHECK_DUPLICATE_BARCODE = "project.call.com.duplicate.barcode";


	public static final String getWSLoginURL() {
		return SystemConfig.getProperties().getProperty(WS_URL_LOGIN);
	}

	public static final String getWSDetailVogURL() {
		return SystemConfig.getProperties().getProperty(WS_URL_DETAIL_VOG);
	}

	public static final String getWSUpdateVogURL() {
		return SystemConfig.getProperties().getProperty(WS_URL_UPDATE_VOG);
	}
	
	public static final String getWSUpdateRS232URL() {
		return SystemConfig.getProperties().getProperty(WS_URL_UPDATE_RS232_VOG);
	}

	public static final String getAppVersion() {
		return SystemConfig.getProperties().getProperty(APP_VERSION);
	}

	public static final String getMainFontStyle() {
		return SystemConfig.getProperties().getProperty(APP_MAIN_FONT_STYLE);
	}

	public static final int getMainFontSize() {
		return Integer.valueOf(SystemConfig.getProperties().getProperty(APP_MAIN_FONT_SIZE));
	}

	public static final String getTableFontStyle() {
		return SystemConfig.getProperties().getProperty(APP_TABLE_FONT_STYLE);
	}

	public static final int getTableFontSize() {
		return Integer.valueOf(SystemConfig.getProperties().getProperty(APP_TABLE_FONT_SIZE));
	}

	public static String getCOMPort() {
		return SystemConfig.getProperties().getProperty(APP_COM_PORT);
	}
	public static int getCOMBaudRate() {
		return Integer.valueOf(SystemConfig.getProperties().getProperty(APP_COM_BAUDRATE));
	}
	public static int getCOMDataBits() {
		return Integer.valueOf(SystemConfig.getProperties().getProperty(APP_COM_DATABITS));
	}
	public static int getCOMSTopBits() {
		return Integer.valueOf(SystemConfig.getProperties().getProperty(APP_COM_STOPBITS));
	}
	public static int getCOMParity() {
		return Integer.valueOf(SystemConfig.getProperties().getProperty(APP_COM_PARITY));
	}
	public static boolean isCallCOMIfDuplicateBarcode() {
		return (Integer.valueOf(SystemConfig.getProperties().getProperty(APP_COM_CHECK_DUPLICATE_BARCODE)) == 1);
	}
	
	public static boolean isJSONValid(String jsonInString ) {
		JSONParser parser = new JSONParser();
	    try {
	    	JSONObject result = (JSONObject) parser.parse(jsonInString);
	    	return true;
	    } catch (Exception e) {
	    	return false;
	    }
	  }
	
}