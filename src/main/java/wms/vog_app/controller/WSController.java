package wms.vog_app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import wms.vog_app.common.Utils;
import wms.vog_app.common.VogConstants;


public class WSController {

	final static Logger logger = Logger.getLogger(WSController.class);

	public JSONObject processData(final String httpURL, final JSONObject jSONObject, final String methodType) {
		JSONObject result = new  JSONObject();
		HttpURLConnection conn = null;
		try {
			logger.info("Calling WS URL " + httpURL);
			URL url = new URL(httpURL);
			conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod(methodType);
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(jSONObject.toJSONString().getBytes());
			os.flush();
			System.out.println("WS Response HTTP Code " + conn.getResponseCode());
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.info("WS Response HTTP Code : " + conn.getResponseCode());
				result.put("code", VogConstants.WS_FAILED);
				result.put("msg", conn.getResponseCode() + " " + conn.getResponseMessage());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output;
				JSONParser parser = new JSONParser();
				
				while ((output = br.readLine()) != null) {
					try {
						logger.info("WS Response Data : " + output);
						result = (JSONObject) parser.parse(output);
						
					} catch (org.json.simple.parser.ParseException e) {
						e.printStackTrace();
					}
					return result;
				}
			}

		} catch (MalformedURLException e) {
			logger.error("WS Response Failed : ", e);
			result.put("code", VogConstants.WS_FAILED);
			result.put("msg", e.getMessage());
			e.printStackTrace();

		} catch (IOException e) {
			logger.error("WS Response Failed : ", e);
			result.put("code", VogConstants.WS_FAILED);
			result.put("msg", e.getMessage());
			e.printStackTrace();

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result;

	}

}
