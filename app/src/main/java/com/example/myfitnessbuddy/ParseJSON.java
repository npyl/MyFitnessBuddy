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

    private String readJSON(String url, Context ctx) throws IOException {
        // code taken from: https://stackoverflow.com/questions/9544737/read-file-from-assets

        InputStream is = ctx.getAssets().open(url);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String myJson = new String(buffer, "UTF-8");
        return myJson;
    }

    public ParseJSON(String url, Context ctx) throws JSONException, IOException {
        this.contents = readJSON(url, ctx);
        this.jsonObject = new JSONObject(this.contents);
    }

    public JSONArray getJSONArray(String string) throws JSONException {
        return this.jsonObject.getJSONArray(string);
    }
}
