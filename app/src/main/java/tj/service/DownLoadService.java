package tj.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import tj.com.androidservice.MainActivity;

/**
 * Created by tjhan on 2017/9/11.
 */

public class DownLoadService extends Service {
    private static final String TAG = "DownLoadService";
    private DownLoadBinder downLoadBinder = new DownLoadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return downLoadBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: 服务开始执行啦");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: 服务开启啦");
        super.onCreate();
        Intent intent= new Intent(this, MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("下载服务的标题")
                .setContentText("下载服务的详情介绍")
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: 服务销毁啦");
        super.onDestroy();
    }

    public class DownLoadBinder extends Binder{
        public void startDownLoad()
        {
            Log.d(TAG, "startDownLoad: 开始下载");
        }
        public int getProgress()
        {
            Log.d(TAG, "getProgress: 获取进度");
            return 0;
        }
    }
}
