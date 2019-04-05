//package com.example.mellov2;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.content.SharedPreferences;
//import android.graphics.drawable.ClipDrawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.NotificationManagerCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.Toast;
//import android.widget.EditText;
//import android.widget.ViewFlipper;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//public class CurrentStatusFragment extends Fragment {
//
//    //=============================================
//
//    //for notifications
//    private static String notification_title = "Bladder Fullness Status";
//    private String notification_content;
//    private final String CHANNEL_ID = "mello_notifications";
//    private final int NOTIFICATION_ID = 001;
//
//    //read in value from device
//    private int percentBladderFullness = 43;
//
//    //for the drop animation
//    private EditText etPercent;
//    private ClipDrawable mImageDrawable;
//
//    private int mLevel = 0;
//    private int fromLevel = 0;
//    private int toLevel = 0;
//    public static final int MAX_LEVEL = 100;  //******to be updated with maximum ADC value
//    public static final int LEVEL_DIFF = 20;   //minimum ADC value
//    public static final int DELAY = 30;
//
//
//    //=============================================
//
//    private void doTheUpAnimation(int fromLevel, int toLevel){
//        mLevel += LEVEL_DIFF;
//        mImageDrawable.setLevel(mLevel);
//        if(mLevel<=toLevel){
//            mUpHandler.postDelayed(animateUpImage, DELAY);
//        }else{
//            mUpHandler.removeCallbacks(animateUpImage);
//            CurrentStatusFragment.this.fromLevel = toLevel;
//        }
//    }
//
//    private void doTheDownAnimation(int fromLevel, int toLevel){
//        mLevel -= LEVEL_DIFF;
//        mImageDrawable.setLevel(mLevel);
//        if (mLevel >= toLevel){
//            mDownHandler.postDelayed(animateDownImage,DELAY);
//        }else{
//            mDownHandler.removeCallbacks(animateDownImage);
//            CurrentStatusFragment.this.fromLevel = toLevel;
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_current_status, container, false);
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//
//        boolean switch50Value = prefs.getBoolean("switch_status_50", true);
//        boolean switch80Value = prefs.getBoolean("switch_status_80", true);
//        boolean switch100Value = prefs.getBoolean("switch_status_100", true);
//
//        //initialize animation of drop
//        ImageView img = (ImageView) view.findViewById(R.id.imageView1);
//        mImageDrawable = (ClipDrawable) img.getDrawable();
//        mImageDrawable.setLevel(0);
//
//        //to move progress upwards
//        Handler mUpHandler = new Handler();
//        Runnable animateUpImage = new Runnable(){
//            @Override
//            public void run(){
//                doTheUpAnimation(fromLevel, toLevel);
//            }
//        };
//
//        //to move progress downwards
//        Handler mDownHandler = new Handler();
//        Runnable animateDownImage = new Runnable(){
//            @Override
//            public void run(){
//                doTheDownAnimation(fromLevel,toLevel);
//            }
//        };
//
//
//
//        while(true) {
//            //fetch percentage from bladder
//            //try{
//            //    TimeUnit.SECONDS.sleep(1);
//            //} catch (InterruptedException e) {
//            //}
//
//            //percentBladderFullness = (percentBladderFullness+10)%100;
//
//            ImageView currentStatusImage = (ImageView) view.findViewById(R.id.current_status_drop_image);
//
//            if (percentBladderFullness > 90) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_100);
//                if (switch100Value == true) {
//                    notification_content = "Your bladder is at 100% fullness";
//                    displayNotification(view);
//                }
//            } else if (percentBladderFullness > 80) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_90);
//            } else if (percentBladderFullness > 70) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_80);
//                if (switch80Value == true) {
//                    notification_content = "Your bladder is at 80% fullness";
//                    displayNotification(view);
//                }
//            } else if (percentBladderFullness > 60) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_70);
//            } else if (percentBladderFullness > 50) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_60);
//            } else if (percentBladderFullness > 40) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_50);
//                if (switch50Value == true) {
//                    notification_content = "Your bladder is at 50% fullness";
//                    displayNotification(view);
//                }
//            } else if (percentBladderFullness > 30) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_40);
//            } else if (percentBladderFullness > 20) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_30);
//            } else if (percentBladderFullness > 10) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_20);
//            } else if (percentBladderFullness > 5) {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_10);
//            } else {
//                currentStatusImage.setImageResource(R.drawable.current_status_drop_0);
//            }
//            return view;
//        }
//    }
//
//
//    public void displayNotification(View view) {
//        createNotificationChannel();
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID);
//
//        notificationBuilder.setDefaults(Notification.DEFAULT_ALL)
//                .setSmallIcon(R.drawable.dashboard_icon_current)
//                .setContentTitle(notification_title)
//                .setContentText(notification_content)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
//        notificationManagerCompat.notify(NOTIFICATION_ID, notificationBuilder.build());
//    }
//
//    private void createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "Mello Notifications";
//            String description = "Bladder Fullness Notifications";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//
//            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//
//            notificationChannel.setDescription(description);
//
//            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
//
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//    }
//}
