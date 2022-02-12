import 'package:ima_player_prototype/bloc/ads_viewer/ads_viewer_cubit.dart';
import 'package:ima_player_prototype/core/locator.dart';
import 'package:ima_player_prototype/plugins/ima_ads_video_player/ima_content_platform_view_player.dart';
import 'package:ima_player_prototype/widgets/back_button.dart';
import 'package:flutter/material.dart';
import 'package:ima_player_prototype/utils/screen_transitions.dart';

class AdsViewerScreen extends StatelessWidget {
  static const name = "AdsViewerScreen";
  const AdsViewerScreen({Key? key}) : super(key: key);

  static Route route() {
    return ScreenTransitions.getTransition(
        ScreenTransitionType.fadeIn,
        const RouteSettings(name: name),
        const AdsViewerScreen()
    );
  }

  void _onAdEnds() {
    locator.get<AdsViewerCubit>().onAdEnds();
  }

  void _onTapBack() {
    locator.get<AdsViewerCubit>().onTapBack();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: MediaQuery.of(context).size.width,
      height: MediaQuery.of(context).size.height,
      color: const Color(0xFF333333),
      child: Stack(
        children: [
          ImaContentPlatformViewPlayerWidget(
            onAdEnds: _onAdEnds,
          ),
          BackButtonWidget(
            onTap: _onTapBack,
          ),
        ],
      ),
    );
  }
}
