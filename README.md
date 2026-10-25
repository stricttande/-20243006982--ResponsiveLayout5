# PracticalFive

Android application for the Practical 5 municipality exercise.

- Student: Xiezheng (20243006982)
- Application: Chinese Municipalities
- Language and UI: Java and XML

Enter Beijing, Shanghai, Chongqing, or Tianjin on the first screen. The detail screen shows the matching photograph and city summary, uses the municipality's colour scheme, and opens its government website with an implicit browser intent.

The four summaries are approximately 80-120 words and follow the required array order: Beijing, Shanghai, Chongqing, Tianjin. They were written from official municipality sources:

- [Beijing municipal facts](https://english.beijing.gov.cn/beijinginfo/facts/)
- [Shanghai overview](https://english.shanghai.gov.cn/en-Overview/index.html)
- [Chongqing overview](https://english.cq.gov.cn/aboutchongqing/overview/202606/t20260608_15735716.html)
- [Tianjin historical origins](https://en.tj.gov.cn/tianjininfo/profile/202312/t20231225_6489676.html)

The supplied map is a PNG in the resource archive; it is converted to `chinese_municipalities.jpg` in `res/drawable/` to match the required resource filename. The four supplied city photographs are included unchanged.

## Build and test

Open this directory in Android Studio, or use a terminal with the Android SDK configured:

```powershell
.\gradlew.bat :app:assembleDebug
.\gradlew.bat :app:testDebugUnitTest
```

The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`.
