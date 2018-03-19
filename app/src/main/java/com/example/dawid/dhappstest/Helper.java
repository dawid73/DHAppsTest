package com.example.dawid.dhappstest;

import android.content.Context;
import android.content.res.AssetManager;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by Dawid on 19.03.2018.
 */


public final class Helper {

        public static String getProperty(String key,Context context) throws IOException {
            Properties properties = new Properties();;
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
            return properties.getProperty(key);


        }
}
