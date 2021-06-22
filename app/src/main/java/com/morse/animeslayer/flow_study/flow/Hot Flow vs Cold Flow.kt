package com.morse.animeslayer.flow_study.flow

/*

    Cold Flow >>>> upstream emit items to downstream but the upstream emit items only when downstream subscribe only to upstream , the upstream will emit items

    Hot Flow >>>> upstream emit items to downstream and it will be active always Until if no flow to listen on it , and new comer down stream subscribe .

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    Always Hot Flow >> cache more space , because it emit last item when any new subscriber subscribe on it

    shareIn and stateIn are both convert cold flow as default of flow to hot flow but shareIn have a unique behaviour

    shareIn >> only one reference of flow with mutiple Subscriper else new source as flow generated if new subscriber scbsribe

    on it

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^



*/