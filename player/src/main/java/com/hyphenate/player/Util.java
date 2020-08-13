package com.hyphenate.player;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;


import java.util.Locale;
import java.util.concurrent.TimeUnit;

class Util {

  static String getDurationString(long durationMs, boolean negativePrefix) {
    return String.format(
        Locale.getDefault(),
        "%s%02d:%02d",
        negativePrefix ? "-" : "",
        TimeUnit.MILLISECONDS.toMinutes(durationMs),
        TimeUnit.MILLISECONDS.toSeconds(durationMs)
            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(durationMs)));
  }

  static boolean isColorDark(int color) {
    double darkness =
        1
            - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color))
                / 255;
    return darkness >= 0.5;
  }

  static int adjustAlpha(int color, @SuppressWarnings("SameParameterValue") float factor) {
    int alpha = Math.round(Color.alpha(color) * factor);
    int red = Color.red(color);
    int green = Color.green(color);
    int blue = Color.blue(color);
    return Color.argb(alpha, red, green, blue);
  }

  static int resolveColor(Context context, @AttrRes int attr) {
    return resolveColor(context, attr, 0);
  }

  private static int resolveColor(Context context, @AttrRes int attr, int fallback) {
    TypedArray a = context.getTheme().obtainStyledAttributes(new int[] {attr});
    try {
      return a.getColor(0, fallback);
    } finally {
      a.recycle();
    }
  }
}
