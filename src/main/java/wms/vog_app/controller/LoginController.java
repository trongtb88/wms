package wms.vog_app.controller;

import org.json.simple.JSONObject;

import wms.vog_app.common.Utils;



/**
 * Processing authentication to API
 * @author tran-binh-trong
 *
 */
public class LoginController {

    public static JSONObject checkLogin(String username, String password) {
		String WS_URL_LOGIN = Utils.getWSLoginURL();
		JSONObject inputLogin = new JSONObject();

		inputLogin.put("username", username);
		inputLogin.put("password", password);

		WSController wsController = new WSController();
		return wsController.processData(WS_URL_LOGIN, inputLogin, "POST");
    }
}
