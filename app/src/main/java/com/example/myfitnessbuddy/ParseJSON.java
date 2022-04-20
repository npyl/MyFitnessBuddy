package com.example.myfitnessbuddy;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ParseJSON {
    String readJSON(String url, Context ctx) {
        // code taken from: https://stackoverflow.com/questions/9544737/read-file-from-assets

        String contents = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(ctx.getAssets().open(url), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                contents += mLine;
            }
        } catch (IOException e) {
            Log.d("LOGIN", "got exception" + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.d("LOGIN", "got exception" + e);
                }
            }
        }
        return contents;
    }

    public ParseJSON(String url, Context ctx) {
        String contents = readJSON(url, ctx);

        Log.d("LOGIN", "json contents: " + contents);
    }
}
