package com.epifanov.lab.ima_player_prototype;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

class CommonUtils {

   public static Map<String, Object> hashMapOf(Pair<String, Object>... entries) {
      final HashMap<String, Object> result = new HashMap<>();
      for (Pair<String, Object> pair: entries) {
         result.put(pair.first, pair.second);
      }
      return result;
   }

}
