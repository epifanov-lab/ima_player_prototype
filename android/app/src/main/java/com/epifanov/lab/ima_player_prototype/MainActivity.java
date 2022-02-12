package com.epifanov.lab.ima_player_prototype;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

public class MainActivity extends FlutterActivity {

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      MultiDex.install(this);
   }

   @Override
   public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
      flutterEngine
        .getPlatformViewsController()
        .getRegistry()
        .registerViewFactory("ima_player_prototype.ima_content_platform_view_player", new NativeViewFactory());
   }
}
