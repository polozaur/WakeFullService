package com.polozaur.testing.alarms;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class TaskService extends Service {

	WakeLock wakeLock;
	KeyguardLock keyguardLock;

	@Override
	public void onStart(Intent intent, int startId) {
		PowerManager pm = (PowerManager) getApplicationContext()
				.getSystemService(Context.POWER_SERVICE);
		wakeLock = pm
				.newWakeLock(
						(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
								| PowerManager.FULL_WAKE_LOCK 
								| PowerManager.ACQUIRE_CAUSES_WAKEUP),
						"WAKEUP");
		wakeLock.acquire();
		KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext()
				.getSystemService(Context.KEYGUARD_SERVICE);
		keyguardLock = keyguardManager.newKeyguardLock("KEYBOARD DISABLE");
		keyguardLock.disableKeyguard();
		Log.e("SERVCE","STARTED");
		Toast.makeText(getApplicationContext(), "STARTED SERVICE",
				Toast.LENGTH_SHORT).show();
		SystemClock.sleep(5000);
		stopSelf();
	}

	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "STOPED SERVICE",
				Toast.LENGTH_SHORT).show();
		Log.e("SERVCE","STOPED");
		keyguardLock.reenableKeyguard();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
