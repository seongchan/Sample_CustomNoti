# Sample_CustomNoti #
안드로이드 Notification 샘플입니다. 

TargetSdkVersion값이 21이상이면, 실제 smallIcon 이미지가 컬러이더라도 마쉬멜로우기반의 단말에서는 흰색으로 Noti아이콘이 변경됩니다. 

## 2018.03.26 ##
서버에 있는 이미지를 Notification에 노출되도록 샘플 예제 추가했습니다. 
(참고 URL : [http://roemilk.tistory.com/m/10](http://roemilk.tistory.com/m/10) )

## 2018.03.27 ##
Android 8에 Notification.Builder API 형식이 변경됨에 따라서 이에 대한 API Level 구분 반영추가되었습니다.
(targetSDKVersion 26로 된 경우에 Notification에 나오지 않음)

## 2022.01.20 ##
간만에 코드(?) 업데이트 했습니다.
Support 라이브러리에서 AndroidX로 마이그레이션(?)을 했고, 최신 gradle 버전에 맞춰서 빌드환경 수정했습니다.
알림 우선순위를 높인 경우에 플로팅 알림이 뜨는 예제 코드 추가했습니다.
별다른 작업은 아닌데, 작업할때 참고하시길!!
