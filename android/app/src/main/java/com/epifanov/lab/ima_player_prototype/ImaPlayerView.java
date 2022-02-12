package com.epifanov.lab.ima_player_prototype;

import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.platform.PlatformView;

class ImaPlayerView implements PlatformView {
  private PlayerView playerView;
  private SimpleExoPlayer player;
  private ImaAdsLoader adsLoader;

  ImaPlayerView(@NonNull Context context, int id, @Nullable Map<String, Object> creationParams) {
    playerView = new PlayerView(context);
    adsLoader = new ImaAdsLoader.Builder(context).build();
    initializePlayer(context);
  }

  @NonNull
  @Override
  public View getView() {
    return playerView;
  }

  @Override
  public void dispose() {
    releasePlayer();
  }

  private void releasePlayer() {
    adsLoader.setPlayer(null);
    playerView.setPlayer(null);
    player.release();
    player = null;
  }

  private void initializePlayer(Context context) {
    DataSource.Factory dataSourceFactory =
      new DefaultDataSourceFactory(context, Util.getUserAgent(context, ImaPlayerViewPlugin.channelName));

    MediaSourceFactory mediaSourceFactory =
      new DefaultMediaSourceFactory(dataSourceFactory)
        .setAdsLoaderProvider(unusedAdTagUri -> adsLoader)
        .setAdViewProvider(playerView);

    player = new SimpleExoPlayer.Builder(context)
      .setMediaSourceFactory(mediaSourceFactory)
      .build();
    playerView.setPlayer(player);
    adsLoader.setPlayer(player);

    player.addListener(new Player.Listener() {
      @Override
      public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
        Player.Listener.super.onPositionDiscontinuity(oldPosition, newPosition, reason);
        final  boolean isPlayingAd = player.isPlayingAd();
        ImaPlayerViewPlugin.channel.invokeMethod("onPositionDiscontinuity",
          CommonUtils.hashMapOf(new Pair<>("isPlayingAd", isPlayingAd))
        );
      }
    });

    Uri contentUri = Uri.parse("https://storage.googleapis.com/gvabox/media/samples/stock.mp4");
    Uri adTagUriSnibble = Uri.parse("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/21822846450/Test_Preroll&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator=");
    MediaItem mediaItem = new MediaItem.Builder()
      .setUri(contentUri)
      .setAdsConfiguration(new MediaItem.AdsConfiguration.Builder(adTagUriSnibble).build())
      .build();

    player.setMediaItem(mediaItem);
    player.prepare();

    player.setPlayWhenReady(true);
  }
}
