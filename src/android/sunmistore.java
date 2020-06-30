package com.openmove.sunmistore;

import org.apache.cordova.*;
import org.json.JSONArray;

public class SunmiStore extends CordovaPlugin {
    String TAG = "SunmiStorePlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext){
        if (action.equals("openstore")) {

            if (args.length() < 1){
                return false;
            }

            String PACKAGE_NAME = args.getString(0);

            String uri = String.format("market://woyou.market/appDetail?packageName=%s", PACKAGE_NAME);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            PackageManager packageManager = getPackageManager();

            List activities = packageManager.queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);

            boolean isIntentSafe = activities.size() > 0;

            if (isIntentSafe) {
                callbackContext.success();
                startActivity(intent);
            } else {
                callbackContext.error("Intent not safe");
            }
        
            return true;
        } 
        
        return false;
    }
}
