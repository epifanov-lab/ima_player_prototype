package com.epifanov.lab.ima_player_prototype;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class ImaPlayerViewPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {
   public static MethodChannel channel;
   public static final String channelName = "ima_player_prototype.ima_content_platform_view_player";

   @Override
   public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
      channel = new MethodChannel(binding.getBinaryMessenger(), channelName);
      channel.setMethodCallHandler(this);

      binding
        .getPlatformViewRegistry()
        .registerViewFactory(channelName, new ImaPlayerViewFactory());
   }

   @Override
   public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {}

   @Override
   public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {

   }
}
