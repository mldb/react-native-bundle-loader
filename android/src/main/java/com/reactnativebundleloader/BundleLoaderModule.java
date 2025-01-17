package com.reactnativebundleloader;


import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.facebook.react.ReactApplication;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import com.facebook.react.devsupport.DevInternalSettings;

public class BundleLoaderModule extends ReactContextBaseJavaModule {
  ReactInstanceManager instanceManager;

  BundleLoaderModule(ReactApplicationContext context) {
    super(context);
    ReactApplication reactApplication = (ReactApplication) getCurrentActivity().getApplication();
    instanceManager = reactApplication.getReactNativeHost().getReactInstanceManager();

  }

  @ReactMethod
  public void load(String host) {
      DevInternalSettings mDevSetting = new DevInternalSettings(getReactApplicationContext(), new DevInternalSettings.Listener() {
          @Override
          public void onInternalSettingsChanged() {
              instanceManager.recreateReactContextInBackground();
          }
      });
      mDevSetting.getPackagerConnectionSettings().setDebugServerHost(host);
      instanceManager.recreateReactContextInBackground();
  }

  @NonNull
  @Override
  public String getName() {
    return "BundleLoaderModule";
  }
}
