package com.morse.animeslayer.flow_study.flow

/*
*  Replay = Int >>  the int is be the number of values to save integers when
*  sending some items and new subscriber will subscribe si it will get last
*  Int Values Returned
*
* Buffer = Int and Strategy >> is used for tryEmit and it returned false okay , it will cach it in the buffer
* if and only if i put a value in the configration else it will be 0 ,
*
*
*
* >>> When tryEmit will return False ?
*       |
*       |
*        >  Tries to emit a value to this shared flow without suspending.
*           It returns true if the value was emitted successfully. When
*           this function returns false, it means that the call to a plain emit
*           function will suspend until there is a buffer space available.
* */