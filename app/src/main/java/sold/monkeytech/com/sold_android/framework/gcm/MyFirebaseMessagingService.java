//package sold.monkeytech.com.sold_android.framework.gcm;
//
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.support.v4.app.NotificationCompat;
//import android.util.Log;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//import com.mid.mid.R;
//import com.mid.mid.ui.activities.MainActivity;
//
//import java.util.Map;
//
//import io.intercom.android.sdk.push.IntercomPushClient;
//
///**
// * Created by MonkeyFather on 31/01/2018.
// */
//
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    private static final String TAG = "wowGCM";
//    private final IntercomPushClient intercomPushClient = new IntercomPushClient();
//
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//        Map message = remoteMessage.getData();
//        if (intercomPushClient.isIntercomPush(message)) {
//            Log.d("wowIntercom", "push notification recevied From: " + message);
//            intercomPushClient.handlePush(getApplication(), message);
//        } else {
//            //DO HOST LOGIC HERE
//        }
//
////        Log.d(TAG, "Notification Click Action: " + remoteMessage.getNotification().getClickAction());
//
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//            String action = remoteMessage.getData().get("action");
//
//
//        }
//
//
//    }
//
//    private void sendNotification(String messageBody, String action, String msg, int searchId) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("action", action);
//        intent.putExtra("msg", msg);
//        intent.putExtra("searchId", searchId);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
////        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.intercom_chat_icon)
//                        .setContentTitle("eMe")
//                        .setContentText(messageBody)
//                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//    }
//}
