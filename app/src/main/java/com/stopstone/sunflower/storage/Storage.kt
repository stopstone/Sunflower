package com.stopstone.sunflower.storage

import com.stopstone.sunflower.data.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

/*
    싱글톤패턴이란?
    해당 코드는 왜 싱글톤으로 구현했는가?
    싱글톤의 종류
 */


@Singleton
class Storage @Inject constructor() {
    var movieList: MutableList<Movie> = mutableListOf()

    val localMovieList = listOf(
        Movie(
            1,
            "범죄도시3",
            "마약범죄조직을 잡기 위해 베트남 호치민까지 진출한 마석도와 긴급대응팀의 활약을 그린 범죄액션 영화.",
            "https://github.com/stopstone/Sunflower/assets/77120604/c6efb8c6-b2c7-4fb8-be5f-8e29449f249a",
            "2023-05-17",
            7.8,
            false,
            0
        ),
        Movie(
            2,
            "가디언즈 오브 갤럭시: Volume 3",
            "갤럭시를 구하기 위해 다시 한번 모인 가디언즈 팀의 모험을 그린 마블 영화.",
            "https://github.com/stopstone/Sunflower/assets/77120604/0c82e416-ae4c-4297-ac76-8b6dbe385156",
            "2023-05-03",
            8.2,
            false,
            0
        ),
        Movie(
            3,
            "스즈메의 문단속",
            "스즈메가 운명적인 만남을 하게 되면서 벌어지는 이야기를 그린 신카이 마코토 감독의 애니메이션 영화.",
            "https://i.namu.wiki/i/g5o_pXalAW9QIS6PkgTKQsJJXpw01qxJtAARUV1xqiOqaG7nRwEHaSazoRu9WQWS-XYVSM2UVbpemqwwGpL1TA.webp",
            "2023-03-16",
            8.6,
            false,
            0
        ),
        Movie(
            4,
            "존 윅 4",
            "전설적인 무법자 존 윅이 연합 최고회의에 맞서 마지막 사투를 벌이는 액션 영화.",
            "https://i.namu.wiki/i/NdikfqD9ep_gcMtGFO8Yn1aDyW17YTS5a85qrYiKsnGD_cOudNNV34xJTuVYXvG3ci6eD0Bko8m1Qmep0VWuOg.webp",
            "2023-04-19",
            7.5,
            false,
            0
        ),
        Movie(
            5,
            "드림",
            "유년 시절 꿈을 접어야 했던 두 형제가 다시 꿈을 향해 나아가는 이야기를 그린 드라마 영화.",
            "https://i.namu.wiki/i/TEG4qwDzZ1PNvmO_MuG0jZzmkElBrx9FEkDrzOrlbsUg8QgvZImIZKSDSRCpA_4FvPQGfuBOKVpaA4Yg1siCCw.webp",
            "2023-04-26",
            8.9,
            false,
            0
        ),
        Movie(
            6,
            "옥수역 귀신",
            "귀신이 나타나는 옥수역 지하철 역사에서 벌어지는 미스터리 공포 영화.",
            "https://biz.chosun.com/resizer/0slsdWYgv5uYgCk68ZMfa1wjs9A=/530x759/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/7T3WTBBWFSCF4JQL2QWWPNTUE4.jpg",
            "2023-04-19",
            7.1,
            false,
            0
        ),
        Movie(
            7,
            "슈퍼 마리오 브라더스",
            "마리오와 친구들이 버섯 킹덤에서 모험을 겪는 이야기를 그린 애니메이션 영화.",
            "https://i.namu.wiki/i/V8OrHDR9K6QRYR8lkR7tcYDZEbqksuHQRwyeDuwQXhMS2kj7Ksln6Jj-LTtrVTreJ-Vn78q6d9PDz3W146QKQA.webp",
            "2023-04-26",
            8.4,
            false,
            0
        ),
        Movie(
            8,
            "리바운드",
            "농구 선수의 꿈을 접어야 했던 한 남자가 농구를 통해 인생의 기회를 잡게 되는 이야기.",
            "https://i.namu.wiki/i/X3MgO_kgxuxMvnenaOeeDIjayn2zs21EWvF6Z3Dg9HS797y-7X7GE0t24qJAp3be63FgvpVaTM4TUrZI-5BxVA.webp",
            "2023-04-12",
            7.7,
            false,
            0
        ),
        Movie(
            9,
            "렌필드",
            "드라큘라 백작의 충실한 하인이었던 렌필드가 자유를 얻게 되면서 벌어지는 이야기.",
            "https://i.namu.wiki/i/WU0crLeaokpswKBLDh5-8PuiwdP3LnR7Kq1h77ACq5jUbzntgczEuVe86a3p-zLA5WjBHebtg_fJcIZmxOHnBQ.webp",
            "2023-04-19",
            6.8,
            false,
            0
        ),
    )
}

/**
 * 개선사항
 * 현재 코드는 좋아요가 눌린 순서대로 리스트를 보여주고 있다.
 * plantList(식물 리스트)와 같은 순서로 정렬하여 보여주고 싶으면 어떻게 해야할까?
 *
 * -> Plant data class에 id를 추가하여 gardenList의 호출이 있을때 정렬을 하여 리스트에 보여준다.
 * gardenList의 변경이 있을때보다 호출빈도가 적을 수 있을 것 같아, 성능면에서 호출 시 정렬하는 것이 좋을 것 같다.
 */
