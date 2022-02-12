package com.epifanov.lab.ima_player_prototype;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.Map;

class ImaPlayerViewFactory extends PlatformViewFactory {

   ImaPlayerViewFactory() {
      super(StandardMessageCodec.INSTANCE);
   }

   @NonNull
   @Override
   public PlatformView create(@NonNull Context context, int id, @Nullable Object args) {
      final Map<String, Object> creationParams = (Map<String, Object>) args;
      return new ImaPlayerView(context, id, creationParams);
   }
}
