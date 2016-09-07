package info.blockchain.api;

import info.blockchain.wallet.util.WebUtil;
import org.json.JSONObject;

public class PinStore implements BaseApi {

    private static final String PIN_STORE = "pin-store";
    public static final String PROD_PIN_STORE_URL = PROTOCOL + SERVER_ADDRESS + PIN_STORE;
    public static final String DEV_PIN_STORE_URL = PROTOCOL + WALLET_DEV_SUBDOMAIN + DEV_SERVER_ADDRESS + PIN_STORE;
    public static final String STAGING_PIN_STORE_URL = PROTOCOL + WALLET_STAGING_SUBDOMAIN + DEV_SERVER_ADDRESS + PIN_STORE;

    private String pinstoreUrl = PROD_PIN_STORE_URL;

    public PinStore() {
        pinstoreUrl = PersistentUrls.getInstance().getPinstoreUrl();
    }

    public JSONObject validateAccess(String key, String pin) throws Exception {

        StringBuilder args = new StringBuilder();

        args.append("key=" + key);
        args.append("&pin=" + pin);
        args.append("&method=get");
        args.append("&api_code=" + API_CODE);

        String response = WebUtil.getInstance().postURL(pinstoreUrl, args.toString(), 1);

        if (response == null || response.length() == 0)
            throw new Exception("Invalid Server Response");

        return new JSONObject(response);
    }

    public JSONObject setAccess(String key, String value, String pin) throws Exception {

        StringBuilder args = new StringBuilder();

        args.append("key=" + key);
        args.append("&value=" + value);
        args.append("&pin=" + pin);
        args.append("&method=put");
        args.append("&api_code=" + API_CODE);

        String response = WebUtil.getInstance().postURL(pinstoreUrl, args.toString());

        if (response == null || response.length() == 0)
            throw new Exception("Invalid Server Response");

        return new JSONObject(response);
    }
}