package com.clipandbooks.sample.custumnoti;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class MainActivity extends Activity {

    private Button mBtn1, mBtn2, mBtn3, mBtn3_1, mBtn4, mBtn5, mBtn6, mBtn7;
    private Button mBtn8, mBtn9, mBtn10, mBtn3_2, mBtn11, mBtn12, mBtn13, mBtn14;
    private TextView mInfomationView;
    private Context mContext;
    private Bitmap bigIcon1, bigIcon2;
    private Bitmap bigPicture;
    private final int mNotificationId_1     = 10001;
    private final int mNotificationId_2     = 10002;
    private final int mNotificationId_3     = 10003;
    private final int mNotificationId_4     = 10004;
    private final int mNotificationId_5     = 10005;
    private final int mNotificationId_6     = 10006;

    private final int mNotificationCompatId = 20002;
    private final String mChannelId_defalut      = "default";
    private final String mChannelId_high         = "high";
    //private final String mChannelName       = "kd;vs";


    private Notification.Builder mBuilder;
    private NotificationManager notiManager;

    private NotificationCompat.Builder mBuilderCompat;
    private NotificationManagerCompat notiManagerCompat;


    Notification.BigPictureStyle mBigStyle;
    Notification.BigTextStyle mBigTextStyle;
    NotificationCompat.BigTextStyle mCompacBigTextStyle;

    String mCustomTitle = "Title with <font color='#ff0000'>Color</font> and <strong>Bold</strong>";
    String mCustomText = "Text with <font color='#ff0000'>Color</font> and <strong>Bold</strong>";
    String TestBigImageUrl = "https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"; //External BigStyleImage

    String TAG = "SampleCustionNoti";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        bigIcon1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_big1);
        bigIcon2 = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_big2);
        bigPicture = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.expand_view_image);

        mInfomationView = (TextView)findViewById(R.id.info_view);

        // Notification : Normal
        mBtn1 = (Button)findViewById(R.id.btn1);
        mBtn1.setOnClickListener(new BtnClickListener1());

        // Notification : BigTextStyle
        mBtn2 = (Button)findViewById(R.id.btn2);
        mBtn2.setOnClickListener(new BtnClickListener1());

        // Notification : BigPicture (local)
        mBtn3 = (Button)findViewById(R.id.btn3);
        mBtn3.setOnClickListener(new BtnClickListener1());

        // Notification : BigPicture (external)
        mBtn3_1 = (Button)findViewById(R.id.btn3_1);
        mBtn3_1.setOnClickListener(new BtnClickListener1());

        // Notification : Custom BigView 2
        mBtn4 = (Button)findViewById(R.id.btn4);
        mBtn4.setOnClickListener(new BtnClickListener1());

        // Notification : Custom BigView 2
        mBtn5 = (Button)findViewById(R.id.btn5);
        mBtn5.setOnClickListener(new BtnClickListener1());

        mBtn6 = (Button)findViewById(R.id.btn6);
        mBtn6.setOnClickListener(new BtnClickListener1());

        mBtn7 = (Button)findViewById(R.id.btn7);
        mBtn7.setOnClickListener(new BtnClickListener2());

        ////

        mBtn8 = (Button)findViewById(R.id.btn8);
        mBtn8.setOnClickListener(new BtnClickListener1());

        // Notification : BigTextStyle
        mBtn9 = (Button)findViewById(R.id.btn9);
        mBtn9.setOnClickListener(new BtnClickListener1());

        // Notification : BigPicture (local)
        mBtn10 = (Button)findViewById(R.id.btn10);
        mBtn10.setOnClickListener(new BtnClickListener1());

        // Notification : BigPicture (external)
        mBtn3_2 = (Button)findViewById(R.id.btn3_12);
        mBtn3_2.setOnClickListener(new BtnClickListener1());

        // Notification : Custom BigView 2
        mBtn11 = (Button)findViewById(R.id.btn11);
        mBtn11.setOnClickListener(new BtnClickListener1());

        // Notification : Custom BigView 2
        mBtn12 = (Button)findViewById(R.id.btn12);
        mBtn12.setOnClickListener(new BtnClickListener1());

        mBtn13 = (Button)findViewById(R.id.btn13);
        mBtn6.setOnClickListener(new BtnClickListener1());

        mBtn14 = (Button)findViewById(R.id.btn14);
        mBtn14.setOnClickListener(new BtnClickListener2());

        initChannels(this);

    }

    private class BtnClickListener1 implements View.OnClickListener {

        @SuppressLint("WrongConstant")
        @TargetApi(19)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1 :
                case R.id.btn8 :
                    // Normal View (non-expand)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        mBuilder = new Notification.Builder(mContext);
                    } else {
                        if (v.getId() == R.id.btn1) {
                            mBuilder = new Notification.Builder(mContext, mChannelId_defalut);
                        } else {
                            mBuilder = new Notification.Builder(mContext, mChannelId_high);
                        }
                    }
                    mBuilder.setContentTitle(getString(R.string.noti_title))        // Notification 제목 (기본 View 에서)
                            .setContentText(getString(R.string.noti_context))         // Notification 내용
                            .setTicker(getString(R.string.noti_ticker))               // Notify될때 Status bar에 노출되는 티커 문구
                                    //.setSmallIcon(R.drawable.ic_stat_name)    // Status bar noti Icon. 일반 아이콘을 쓰면 Noti ticker 출력 시 Ref폰에서 아이콘 짤림 (24pixel)
                            .setSmallIcon(R.mipmap.ic_launcher)                 // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setDefaults(Notification.DEFAULT_VIBRATE)          // Noti 진동
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));    // 알람음.

                    // Notification Icon. 지정하지 않으면 small Icon이 노출
                    // 예제에서는 동일 이미지를 왜곡 없이 사용하기 위해서 drawable-nodpi에 복사해 둠.
                    mBuilder.setLargeIcon(bigIcon1);

                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId_1, mBuilder.build());

                    mInfomationView.setText(getString(R.string.noti_1_description));
                    break;

                case R.id.btn2 :
                case R.id.btn9 :
                    // Expand Text View
                    // BigTextStyle 정의
                    mBigTextStyle = new Notification.BigTextStyle();
                    mBigTextStyle.setBigContentTitle(getString(R.string.noti_title_big));
                    mBigTextStyle.setSummaryText(getString(R.string.noti_context));
                    mBigTextStyle.bigText(getString(R.string.noti_context_big));

                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        mBuilder = new Notification.Builder(mContext);
                    } else {
                        if (v.getId() == R.id.btn2) {
                            mBuilder = new Notification.Builder(mContext, mChannelId_defalut);
                        } else {
                            mBuilder = new Notification.Builder(mContext, mChannelId_high);
                        }
                    }
                    mBuilder.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))
                            //.setSmallIcon(R.drawable.ic_stat_name)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setLargeIcon(bigIcon1)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setStyle(mBigTextStyle);  //확장된 View에서 사용할 스타일 설정(BigTextStyle : Expand)

                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId_2, mBuilder.build());

                    mInfomationView.setText(getString(R.string.noti_1_1_description));
                    break;

                case R.id.btn3 :
                case R.id.btn10:
                    // Expand Big Picture View (local)
                    // BigPicture Style 정의
                    mBigStyle = new Notification.BigPictureStyle();
                    mBigStyle.setBigContentTitle(getString(R.string.noti_title_big));
                    mBigStyle.setSummaryText(getString(R.string.noti_context_big));
                    mBigStyle.bigPicture(bigPicture);

                    // 축소되어서 보일때의 View
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        mBuilder = new Notification.Builder(mContext);
                    } else {
                        if (v.getId() == R.id.btn3) {
                            mBuilder = new Notification.Builder(mContext, mChannelId_defalut);
                        } else {
                            mBuilder = new Notification.Builder(mContext, mChannelId_high);
                        }
                    }
                    mBuilder.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))
                            //.setSmallIcon(R.drawable.ic_stat_name)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setLargeIcon(bigIcon1)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setStyle(mBigStyle);  //확장된 View에서 사용할 스타일 설정(Big Picture)


                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId_3, mBuilder.build());
                    mInfomationView.setText(getString(R.string.noti_2_description));
                    break;

                case R.id.btn3_1 :
                case R.id.btn3_12 :
                    // 축소되어서 보일때의 View
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        mBuilder = new Notification.Builder(mContext);
                    } else {
                        if (v.getId() == R.id.btn3_1) {
                            mBuilder = new Notification.Builder(mContext, mChannelId_defalut);
                        } else {
                            mBuilder = new Notification.Builder(mContext, mChannelId_high);
                        }
                    }
                    mBuilder.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))
                            //.setSmallIcon(R.drawable.ic_stat_name)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setLargeIcon(bigIcon1)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setStyle(mBigStyle);  //확장된 View에서 사용할 스타일 설정(Big Picture)


                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                    //NotificationManager
                    final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    if(TestBigImageUrl != null) {
                        if (!TestBigImageUrl.equals("")) {
                            notificationBigStyle(notificationManager, mBuilder, TestBigImageUrl, getString(R.string.noti_title_big), getString(R.string.noti_context_big));
                            return;
                        }
                    }

                    mInfomationView.setText(getString(R.string.noti_2_description));
                    break;

                case R.id.btn4 :
                case R.id.btn11 :
                    // CustomView : Expand View에 이미지만 노출 (Expand View에 이미지만 노출)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        mBuilder = new Notification.Builder(mContext);
                    } else {
                        if (v.getId() == R.id.btn4) {
                            mBuilder = new Notification.Builder(mContext, mChannelId_defalut);
                        } else {
                            mBuilder = new Notification.Builder(mContext, mChannelId_high);
                        }
                    }

                    /* 확장 커스텀 View
                     * Notification.bigContentView에 RemoteView를 붙이면 Expand  속성이 살아남. 이때 커스텀 뷰로 처리 가능
                     * Notification.Builder.setContent() 로는 축소된 화면에서 커스텀만 가능.
                     * RemoteView구성할때 확장되는 것을 고려해서 Expand View의 높이는 256dp로 잡을 것.
                     */

                    // Custom View를 생성
                    RemoteViews expandedView1 = new RemoteViews(mContext.getPackageName(), R.layout.notification_view1);

                    // 축소되어서 보일때의 View
                    mBuilder.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))
                            //.setSmallIcon(R.drawable.ic_stat_name)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setLargeIcon(bigIcon2)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

                    Notification noti1 = mBuilder.build();

                    noti1.bigContentView = expandedView1;
                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId_4, noti1);
                    mInfomationView.setText(getString(R.string.noti_3_description));
                    break;

                case R.id.btn5 :
                case R.id.btn12 :
                    // CustomView : Expand View가 커스텀 된 듯한 느낌. (layout을 별도로 꾸밈)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        mBuilder = new Notification.Builder(mContext);
                    } else {
                        if (v.getId() == R.id.btn5) {
                            mBuilder = new Notification.Builder(mContext, mChannelId_defalut);
                        } else {
                            mBuilder = new Notification.Builder(mContext, mChannelId_high);
                        }
                    }

                    /* 확장 커스텀 View
                     * Notification.bigContentView에 RemoteView를 붙이면 Expand  속성이 살아남. 이때 커스텀 뷰로 처리 가능
                     * Notification.Builder.setContent() 로는 축소된 화면에서 커스텀만 가능.
                     * RemoteView구성할때 확장되는 것을 고려해서 Expand View의 높이는 256dp로 잡을 것.
                     */

                    // Custom View를 생성
                    RemoteViews expandedView2 = new RemoteViews(mContext.getPackageName(), R.layout.notification_view2);
                    expandedView2.setTextViewText(R.id.noti_title, Html.fromHtml(mCustomTitle));
                    expandedView2.setTextViewText(R.id.noti_message, Html.fromHtml(mCustomText));
                    expandedView2.setImageViewResource(R.id.noti_icon, R.drawable.ic_big2);
                    // 축소되어서 보일때의 View
                    mBuilder.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))
                            //.setSmallIcon(R.drawable.ic_stat_name)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setLargeIcon(bigIcon2)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                    Notification noti2 = mBuilder.build();

                    noti2.bigContentView = expandedView2;
                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId_5, noti2);
                    mInfomationView.setText(getString(R.string.noti_4_description));
                    break;

                case R.id.btn6 :
                case R.id.btn13 :
                    // Big Picture View Custuom (expand)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        mBuilder = new Notification.Builder(mContext);
                    } else {
                        if (v.getId() == R.id.btn6) {
                            mBuilder = new Notification.Builder(mContext, mChannelId_defalut);
                        } else {
                            mBuilder = new Notification.Builder(mContext, mChannelId_high);
                        }
                    }

                    /* 확장 커스텀 View
                     * Notification.bigContentView에 RemoteView를 붙이면 Expand  속성이 살아남. 이때 커스텀 뷰로 처리 가능
                     * Notification.Builder.setContent() 로는 축소된 화면에서 커스텀만 가능.
                     * RemoteView구성할때 확장되는 것을 고려해서 Expand View의 높이는 256dp로 잡을 것.
                     */
                    // Custom View를 생성
                    RemoteViews smallView3 = new RemoteViews(mContext.getPackageName(), R.layout.notification_view1_small);
                    RemoteViews expandedView3 = new RemoteViews(mContext.getPackageName(), R.layout.notification_view2);
                    smallView3.setTextViewText(R.id.noti_title, Html.fromHtml(mCustomTitle));
                    smallView3.setTextViewText(R.id.noti_message, Html.fromHtml(mCustomText));
                    expandedView3.setTextViewText(R.id.noti_title, Html.fromHtml(mCustomTitle));
                    expandedView3.setTextViewText(R.id.noti_message, Html.fromHtml(mCustomText));
                    expandedView3.setImageViewResource(R.id.noti_icon, R.drawable.ic_big2);

                    // 축소되어서 보일때의 View
                    // small View도 커스텀 View 를 사용하기 때문에 Status 노출되는 아이콘과 티커만 설정
                    //mBuilder.setSmallIcon(R.drawable.ic_stat_name)
                    mBigStyle = new Notification.BigPictureStyle();
                    mBuilder.setStyle(mBigStyle);
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setContent(smallView3);

                    Notification noti3 = mBuilder.build();

                    noti3.bigContentView = expandedView3;
                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId_6, noti3);
                    mInfomationView.setText(getString(R.string.noti_5_description));
                    break;
            }
        }
    }

    private class BtnClickListener2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch(v.getId()) {
                case R.id.btn7 :
                case R.id.btn14 :

                    mCompacBigTextStyle = new NotificationCompat.BigTextStyle();
                    mCompacBigTextStyle.setBigContentTitle(getString(R.string.noti_title_big));
                    mCompacBigTextStyle.setSummaryText(getString(R.string.noti_context));
                    mCompacBigTextStyle.bigText(getString(R.string.noti_context_big));

                    if (v.getId() == R.id.btn7) {
                        mBuilderCompat = new NotificationCompat.Builder(mContext, mChannelId_defalut);  //targetSDKVersion 26. appCompat-V7-26...
                    } else {
                        mBuilderCompat = new NotificationCompat.Builder(mContext,mChannelId_high);  //targetSDKVersion 26. appCompat-V7-26...
                    }


                    //mBuilderCompat = new NotificationCompat.Builder(mContext);  //targetSDKVersion 26 under. appCompat-V7-25...

                    mBuilderCompat.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))           // Notification 내용
                            .setTicker(getString(R.string.noti_ticker))                 // Notify될때 Status bar에 노출되는 티커 문구
                            //.setSmallIcon(R.drawable.ic_stat_name)                      // Status bar noti Icon. 일반 아이콘을 쓰면 Noti ticker 출력 시 Ref폰에서 아이콘 짤림 (24pixel)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setDefaults(Notification.DEFAULT_VIBRATE)                  // Noti 진동
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));    // 알람음.
                    mBuilderCompat.setLargeIcon(bigIcon1);
                    mBuilderCompat.setStyle(mCompacBigTextStyle);

                    notiManagerCompat = NotificationManagerCompat.from(mContext);
                    notiManagerCompat.notify(mNotificationCompatId, mBuilderCompat.build());

                    mInfomationView.setText(getString(R.string.noti_1_description));
                    break;
            }
        }
    }


    /**
     * BigStyle의 Notification을 Notify합니다. (http://roemilk.tistory.com/m/10)
     * @param notificationManager
     * @param notificationBuilder
     * @param bigImagePath 빅피쳐스타일 표시될 이미지 주소
     * @param bigTitle 빅피쳐스타일 표시될 타이틀
     * @param bigSubTitle 빅피쳐스타일 표시될 내용
     *
     */
    private void notificationBigStyle(final NotificationManager notificationManager, final Notification.Builder notificationBuilder,
                                      String bigImagePath, final String bigTitle, final String bigSubTitle){
        //BigPicture Style
        Log.d(TAG, "BigPicture Style init");
        final Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(notificationBuilder);
        Glide.with(getApplicationContext())
                .load(bigImagePath)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(720,384) {
                    @Override
                    public void onResourceReady(final Bitmap resource, GlideAnimation glideAnimation) {
                        Log.d(TAG, "Glide onResourceReady.. Bitmap resource");
                        if(resource != null){
                            Log.d(TAG, "resource is null..");

                        }

                        bigPictureStyle.setBigContentTitle(bigTitle);
                        bigPictureStyle.setSummaryText(bigSubTitle);
                        bigPictureStyle.bigPicture(resource);
                        notificationBuilder.setStyle(bigPictureStyle);
                        notificationManager.notify(mNotificationId_3, notificationBuilder.build());
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        Log.d(TAG, "onLoadFailed..");

                    }
                });
    }


    // AndroidX
    public void initChannels(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel1 = new NotificationChannel(mChannelId_defalut, getString(R.string.channel_default_name), NotificationManager.IMPORTANCE_DEFAULT);
        NotificationChannel channel2 = new NotificationChannel(mChannelId_high, getString(R.string.channel_high_name), NotificationManager.IMPORTANCE_HIGH);
        channel1.setDescription(getString(R.string.app_name));
        channel2.setDescription(getString(R.string.app_name));
        notificationManager.createNotificationChannel(channel1);
        notificationManager.createNotificationChannel(channel2);
    }

}
