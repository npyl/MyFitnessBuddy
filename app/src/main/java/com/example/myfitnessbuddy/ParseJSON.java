package com.example.myfitnessbuddy;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParseJSON {
    private String      contents;
    private JSONObject  jsonObject;

    private String readJSON(String url, Context ctx) {
        // code taken from: https://stackoverflow.com/questions/9544737/read-file-from-assets

        String contents = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(ctx.getAssets().open(url), "UTF-8"));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                contents += mLine;
            }
        } catch (IOException e) {
            Log.d("ParseJSON", "got exception" + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.d("ParseJSON", "got exception" + e);
                }
            }
        }
        return contents;
    }

    public ParseJSON(String url, Context ctx) throws JSONException {
        this.contents = readJSON(url, ctx);
        this.jsonObject = new JSONObject(this.contents);
    }

    public JSONArray getListOfUsers() throws JSONException
    {
        return this.jsonObject.getJSONArray("users");
    }
}
