package com.example.guardiancycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class PowerButtonReceiver extends BroadcastReceiver {
    private static int powerButtonPressCount = 0;
    private static long lastPressTime = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastPressTime < 1000) {
                powerButtonPressCount++;
            } else {
                powerButtonPressCount = 1; // Reset count
            }
            lastPressTime = currentTime;

            if (powerButtonPressCount >= 5) {
                // Trigger fake call
                Intent fakeCallIntent = new Intent(context, FakeCallActivity.class);
                fakeCallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(fakeCallIntent);
                powerButtonPressCount = 0; // Reset count after triggering
            }
        }
    }
}
