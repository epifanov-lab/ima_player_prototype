import 'package:ima_player_prototype/core/global_navigator.dart';
import 'package:ima_player_prototype/core/locator.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:ima_player_prototype/bloc/ads_viewer/ads_viewer_state.dart';


class AdsViewerCubit extends Cubit<AdsViewerState> {

  AdsViewerCubit() : super(const AdsViewerState.initial());

  void onTapBack() {
    locator.get<GlobalNavigator>().pop();
  }

}
