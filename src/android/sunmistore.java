package com.openmove.sunmistore;

import org.apache.cordova.*;
import org.json.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import java.util.List;

public class SunmiStore extends CordovaPlugin {
    String TAG = "SunmiStorePlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext){
        if (action.equals("openstore")) {

            Context context = this.cordova.getActivity().getApplicationContext();

            if (args.length() < 1){
                return false;
            }

            String PACKAGE_NAME = null;
            
            try {
                PACKAGE_NAME = args.getString(0);
            }catch(JSONException e) {

            }

            if (PACKAGE_NAME == null) {
                return false;
            }

            String uri = String.format("market://woyou.market/appDetail?packageName=%s", PACKAGE_NAME);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            PackageManager packageManager = context.getPackageManager();

            List activities = packageManager.queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);

            boolean isIntentSafe = activities.size() > 0;

            if (isIntentSafe) {
                callbackContext.success();
                context.startActivity(intent);
            } else {
                callbackContext.error("Intent not safe");
            }
        
            return true;
        } 
        
        return false;
    }
}
