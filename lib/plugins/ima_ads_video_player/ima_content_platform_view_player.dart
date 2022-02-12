import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class ImaContentPlatformViewPlayerWidget extends StatelessWidget {
  const ImaContentPlatformViewPlayerWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    const String viewType = 'ima_player_prototype.ima_content_platform_view_player';
    final Map<String, dynamic> creationParams = <String, dynamic>{};
    return AndroidView(
      viewType: viewType,
      layoutDirection: TextDirection.ltr,
      creationParams: creationParams,
      creationParamsCodec: const StandardMessageCodec(),
    );
  }
}
