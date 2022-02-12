import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const String _channelName = 'ima_player_prototype.ima_content_platform_view_player';

class ImaContentPlatformViewPlayerWidget extends StatefulWidget {

  final Function? onAdEnds;

  const ImaContentPlatformViewPlayerWidget({
    Key? key,
    this.onAdEnds,
  }) : super(key: key);

  @override
  State<ImaContentPlatformViewPlayerWidget> createState() {
    return _ImaContentPlatformViewPlayerWidgetState();
  }
}

class _ImaContentPlatformViewPlayerWidgetState extends State<ImaContentPlatformViewPlayerWidget> {

  final MethodChannel _channel = const MethodChannel(_channelName);

  @override
  void initState() {
    super.initState();
    _channel.setMethodCallHandler((call) => _handleNativeMessages(call));
  }

  Future _handleNativeMessages(MethodCall call) async {
    switch (call.method) {
      case "onPositionDiscontinuity":
        if (call.arguments['isPlayingAd'] == false) {
          widget.onAdEnds?.call();
        }
        break;
    }
    return true;
  }

  @override
  Widget build(BuildContext context) {
    final Map<String, dynamic> creationParams = <String, dynamic>{};
    return AndroidView(
      viewType: _channelName,
      layoutDirection: TextDirection.ltr,
      creationParams: creationParams,
      creationParamsCodec: const StandardMessageCodec(),
    );
  }

}
