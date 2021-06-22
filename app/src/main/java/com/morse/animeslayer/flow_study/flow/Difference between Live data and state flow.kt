package com.morse.animeslayer.flow_study.flow

/*


StateFlow and LiveData have similarities. Both are observable data holder classes,
and both follow a similar pattern when used in your app architecture.

Note, however, that StateFlow and LiveData do behave differently:

StateFlow requires an initial state to be passed in to the constructor, while LiveData does not.

LiveData.observe() automatically unregisters the consumer when the view goes to the STOPPED state,
 whereas collecting from a StateFlow or any other flow does not stop collecting automatically.
  To achieve the same behavior,you need to collect the flow from a Lifecycle.repeatOnLifecycle block.



*/