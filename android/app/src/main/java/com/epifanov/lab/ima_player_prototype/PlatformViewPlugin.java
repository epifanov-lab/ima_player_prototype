package com.epifanov.lab.ima_player_prototype;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;

public class PlatformViewPlugin implements FlutterPlugin {
   @Override
   public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
      binding
        .getPlatformViewRegistry()
        .registerViewFactory("ima_player_prototype.ima_content_platform_view_player", new NativeViewFactory());
   }

   @Override
   public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {}
}
