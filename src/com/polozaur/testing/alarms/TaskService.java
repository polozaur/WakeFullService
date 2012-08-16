package com.polozaur.testing.alarms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class TaskService extends Service {
	
	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(getApplicationContext(), 
				"STARTED SERVICE",Toast.LENGTH_SHORT).show();
		stopSelf();
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(),
				"STOPED SERVICE",Toast.LENGTH_SHORT).show();;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
