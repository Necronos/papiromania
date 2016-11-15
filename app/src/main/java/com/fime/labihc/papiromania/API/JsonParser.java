package com.fime.labihc.papiromania.API;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonParser {
    public static JSONObject OpenJsonFileFromAssets(Context context, String filename) {
        try {
            String json;
            InputStream is = context.getAssets().open(filename);
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            return new JSONObject(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
