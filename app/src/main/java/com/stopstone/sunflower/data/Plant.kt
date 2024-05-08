package com.stopstone.sunflower.data
/*
*  Plant class가 있는 위치를 명시한다.
* 코틀린의 패키지는 관련 클래스, 함수, 인터페이스를 그룹화하는 네임스페이스로 사용된다.
* */

import android.os.Parcelable
import kotlinx.parcelize.Parcelize // 직렬화를 위한 Parcelize 임포트

@Parcelize
data class Plant(
    val name: String, // 식물의 이름을 저장하는 변수
    val image: Int, // 이미지 resource를 저장하는 변수
    val description: String, // 식물에 관한 설명을 저장하는 변수
    val favorite: Boolean, // 실물 아이템의 좋아요 여부를 저장하는 변수
    val viewType: Int,
) : Parcelable
/*
* Serializable이란?
*   - 객체를 직렬화 할 수 있도록 지원하 인터페이스
*   - 직렬화는 객체를 바이트 스트림으로 변환하는 과정을 말한다.
*   - 바이트 스트림으로 변환하는 이유는 객체를 파일에 저장하거나 네트워크를 통해 전소하기 위함이다.
*
* 해당 클래스는 어떤 클래스인가?
*   - 해당 클래스는 식물 정보를 저장하는 객체이다.
*   - Serializable을 상속받아 직렬화하여 데이터를 전송한다.

* Serializable vs Parcelable에 대해 비교해보고 리팩토링 할 수 있도록 하자
* - Parcelable은 데이터를 안전하게 전달하고 전송하기 위해 사용되는 직렬화 인터페이스이다.
* - Serializable과 비슷한 기능을 제공하지만 직렬화, 역직렬화 과정에서 발생하는 오버헤드를 줄이고 성능을 향상시키는데 중점을 둔다.
* - Parcelable을 사용하여 Intent를 통해 데이터를 전달하거나 Bundle에 객체를 저장할 때 사용됩니다.
* - 플러그인을 추가해 자동으로 직렬화 할 수 있도록 제공받았다.
* */