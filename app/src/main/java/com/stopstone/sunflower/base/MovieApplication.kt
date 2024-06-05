package com.stopstone.sunflower.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()
/*

* @HiltAndroidApp
* Hilt를 사용하는 모든 Applicaton은 @HiltAndroidApp을 추가해줘야 합니다.
* Manifest에도 Application를 명시해줘야 합니다.
* 생성된 이 Hilt 구성요소는 Application 객체의 수명 주기에 연결되며 이와 관련한 종속 항목을 제공합니다.
*
* */