package com.clipandbooks.sample.custumnoti;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button mBtn1, mBtn2, mBtn3, mBtn4, mBtn5, mBtn6, mBtn7;
    private TextView mInfomationView;
    private Context mContext;
    private Bitmap bigIcon1, bigIcon2;
    private Bitmap bigPicture;
    private final int mNotificationId             	= 10001;

    private Notification.Builder mBuilder;
    private NotificationManager notiManager;

    private NotificationCompat.Builder mBuilderCompat;
    private NotificationManagerCompat notiManagerCompat;
    private final int mNotificationCompatId 		= 10002;

    private Notification.BigPictureStyle mBigStyle;
    private Notification.BigTextStyle mBigTextStyle;
    private NotificationCompat.BigTextStyle mCompacBigTextStyle;

    private String mCustomTitle = "Title with <font color='#ff0000'>Color</font> and <strong>Bold</strong>";
    private String mCustomText = "Text with <font color='#ff0000'>Color</font> and <strong>Bold</strong>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;


        mInfomationView = (TextView)findViewById(R.id.info_view);
        bigIcon1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_big1);
        bigIcon2 = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_big2);
        bigPicture = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.expand_view_image);

        // Notification : Normal
        mBtn1 = (Button)findViewById(R.id.btn1);
        mBtn1.setOnClickListener(new BtnClickListener1());

        // Notification : Big Picture
        mBtn2 = (Button)findViewById(R.id.btn2);
        mBtn2.setOnClickListener(new BtnClickListener1());

        // Notification : Custom BigView 1
        mBtn3 = (Button)findViewById(R.id.btn3);
        mBtn3.setOnClickListener(new BtnClickListener1());

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


    }

    // System Default (API 19 Over)
    private class BtnClickListener1 implements View.OnClickListener {

        @TargetApi(19)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1 :
                    // Normal View (non-expand)
                    mBuilder = new Notification.Builder(mContext);
                    mBuilder.setContentTitle(getString(R.string.noti_title))                      // Notification 제목 (기본 View 에서)
                            .setContentText(getString(R.string.noti_context))           // Notification 내용
                            .setTicker(getString(R.string.noti_ticker))                 // Notify될때 Status bar에 노출되는 티커 문구
                            .setSmallIcon(R.drawable.ic_stat_name)                      // Status bar noti Icon. 일반 아이콘을 쓰면 Noti ticker 출력 시 Ref폰에서 아이콘 짤림 (24pixel)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setDefaults(Notification.DEFAULT_VIBRATE)                  // Noti 진동
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));    // 알람음.

                    // Notification Icon. 지정하지 않으면 small Icon이 노출
                    // 예제에서는 동일 이미지를 왜곡 없이 사용하기 위해서 drawable-nodpi에 복사해 둠.
                    mBuilder.setLargeIcon(bigIcon1);

                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId, mBuilder.build());

                    mInfomationView.setText(getString(R.string.noti_1_description));
                    break;

                case R.id.btn2 :
                    // Big Picture View (expand)

                    // BigPicture Style 정의
                    mBigStyle = new Notification.BigPictureStyle();
                    mBigStyle.setBigContentTitle(getString(R.string.noti_title_exp));
                    mBigStyle.setSummaryText(getString(R.string.noti_context_summary));
                    mBigStyle.bigPicture(bigPicture);

                    // 축소되어서 보일때의 View
                    mBuilder = new Notification.Builder(mContext);
                    mBuilder.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))
                            .setSmallIcon(R.drawable.ic_stat_name)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setLargeIcon(bigIcon1)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setStyle(mBigStyle);  //확장된 View에서 사용할 스타일 설정(Big Picture)
                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId, mBuilder.build());
                    mInfomationView.setText(getString(R.string.noti_2_description));
                    break;
                case R.id.btn3 :
                    // CustomView : Expand View에 이미지만 노출 (Expand View에 이미지만 노출)
                    mBuilder = new Notification.Builder(mContext);

                    /* 확장 커스텀 View
                     * Notification.bigContentView에 RemoteView를 붙이면 Expand  속성이 살아남. 이때 커스텀 뷰로 처리 가능
                     * Notification.Builder.setContent() 로는 축소된 화면에서 커스텀만 가능.
                     * RemoteView구성할때 확장되는 것을 고려해서 Expand View의 높이는 256dp로 잡을 것.
                     */

                    // Custom View를 생성
                    RemoteViews expandedView1 = new RemoteViews(mContext.getPackageName(), R.layout.notification_view1);
                    expandedView1.setTextViewText(R.id.noti_title, Html.fromHtml(mCustomTitle));
                    expandedView1.setTextViewText(R.id.noti_message, Html.fromHtml(mCustomText));

                    // 축소되어서 보일때의 View
                    mBuilder.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))
                            .setSmallIcon(R.drawable.ic_stat_name)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setLargeIcon(bigIcon2)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

                    Notification noti1 = mBuilder.build();

                    noti1.bigContentView = expandedView1;
                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId, noti1);
                    mInfomationView.setText(getString(R.string.noti_3_description));
                    break;

                case R.id.btn4 :
                    // CustomView : Expand View가 커스텀 된 듯한 느낌. (layout을 별도로 꾸밈)
                    mBuilder = new Notification.Builder(mContext);

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
                    notiManager.notify(mNotificationId, noti2);
                    mInfomationView.setText(getString(R.string.noti_4_description));
                    break;

                case R.id.btn5 :
                    // Big Picture View Custuom (expand)
                    mBuilder = new Notification.Builder(mContext);

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
                    notiManager.notify(mNotificationId, noti3);
                    mInfomationView.setText(getString(R.string.noti_5_description));
                    break;

                case R.id.btn6 :
                    mBigTextStyle = new Notification.BigTextStyle();
                    mBigTextStyle.setBigContentTitle(getString(R.string.noti_title_exp));
                    mBigTextStyle.setSummaryText(getString(R.string.noti_context_summary));
                    mBigTextStyle.bigText(getString(R.string.noti_context_exp));

                    mBuilder = new Notification.Builder(mContext);
                    mBuilder.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))
                            //.setSmallIcon(R.drawable.ic_stat_name)
                            .setSmallIcon(R.mipmap.ic_launcher)                           // Noti Icon을 launcher 아이콘으로 대체해도 무방 (Nexus6P 마쉬맬로우 단말 기준
                            .setTicker(getString(R.string.noti_ticker))
                            .setLargeIcon(bigIcon1)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setStyle(mBigTextStyle);  //확장된 View에서 사용할 스타일 설정(Big Picture)


                    notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notiManager.notify(mNotificationId, mBuilder.build());
                    mInfomationView.setText(getString(R.string.noti_6_description));
                    break;

            }
        }
    }


    // compact style Code
    private class BtnClickListener2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch(v.getId()) {
                case R.id.btn7 :

                    mCompacBigTextStyle = new NotificationCompat.BigTextStyle();
                    mCompacBigTextStyle.setBigContentTitle(getString(R.string.noti_title_exp));
                    mCompacBigTextStyle.setSummaryText(getString(R.string.noti_context_summary));
                    mCompacBigTextStyle.bigText(getString(R.string.noti_context_exp));


                    mBuilderCompat = new NotificationCompat.Builder(mContext);
                    mBuilderCompat.setContentTitle(getString(R.string.noti_title))
                            .setContentText(getString(R.string.noti_context))           // Notification 내용
                            .setTicker(getString(R.string.noti_ticker))                 // Notify될때 Status bar에 노출되는 티커 문구
                            .setSmallIcon(R.drawable.ic_stat_name)                      // Status bar noti Icon. 일반 아이콘을 쓰면 Noti ticker 출력 시 Ref폰에서 아이콘 짤림 (24pixel)
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

}
