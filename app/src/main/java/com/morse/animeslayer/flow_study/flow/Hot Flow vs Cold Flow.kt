package com.morse.animeslayer.flow_study.flow

/*

    Always Hot Flow >> cache more space , because it emit last item when any new subscriber subscribe on it

    shareIn and stateIn are both convert cold flow as default of flow to hot flow but shareIn have a unique behaviour

    shareIn >> only one reference of flow with mutiple Subscriper else new source as flow generated if new subscriber scbsribe

    on it

*/