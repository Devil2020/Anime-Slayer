package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.runBlocking

/*

للأسف هنا هكتب عربي عشان فيها تركايه حلوه و احتمال اتلغبط او انسي شرحها , عمتا هيا بتعمل فلو جديد تاني حلو ؟ , طول بقا
ما الأصلي بيبعت اسرع من الوقت الى حاطه فى الديباونس مش هيوصل حاجه للفلو الجديد , يعني من الأخر طول ما البطأ يكون اكبر عن الوقت الى
حطينه فى الديباونس هبعت للفلو الجديد و هكذا السايكل ز

 */

fun main (){

    runBlocking {

        flowF()
            .debounce(160000000000000000)
            .collect {
                printMessage(0 , "The Item After Debounce callled is ${it}")
            }

    }

}