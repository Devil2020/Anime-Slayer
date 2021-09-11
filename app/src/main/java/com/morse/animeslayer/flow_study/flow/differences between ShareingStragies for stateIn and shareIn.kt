package com.morse.animeslayer.flow_study.flow

/*
*   Why we use it ?
*
*       for controlling when the Producer start emiting
*
*   Sharing Stratigy have 3 Types okay ?
*
*       1- SharingStrategy.Earling -> it make the Producer Active Immediatly .
*
*       2- SharingStrategy.Lazily -> it make the the producer not Active Until Consumer Subscribe on it .
*
*           In Both the  Sharing of Coroutine Stop Immediatly when last Consumer disappear .
*
*       3- SharingStrategy.WhileSubscribed -> it is the same as Lazily with the Start but different with End
*
*
*
*   Sharing Strategy Type                        Start                          End
*
*
*
*           Earling                            Sharing start immediatly          never stop
*
*           Lazily                          sharing start after apearance for    never stop
*                                                   first Consumer
*
*           WhileSubscribed                       Like Lazily in Start          Stop when last Consumer Disappear okay
*                                                                               but we can customize how many milisecond after disappear by default),
*                                                                               keeping the replay cache forever (by default)
*                                                                               it have 2 Variables
*                                                                               stopTimeoutMillis: Long -> كام ثانيه ما بين اخر كونسيومر ظاهر و اوقف الشير
*                                                                               replayExpirationMillis: Long -> اد اى وقت يحصل فيه اكسبير لل كاش
*
* */