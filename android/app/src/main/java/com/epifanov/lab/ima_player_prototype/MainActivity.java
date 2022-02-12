package com.epifanov.lab.ima_player_prototype;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

public class MainActivity extends FlutterActivity {
   @Override
   public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
      flutterEngine
        .getPlatformViewsController()
        .getRegistry()
        .registerViewFactory("ima_player_prototype.ima_content_platform_view_player", new NativeViewFactory());
   }
}
