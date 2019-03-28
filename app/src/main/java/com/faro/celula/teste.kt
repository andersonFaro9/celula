package com.faro.celula

import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton

class ScrollAwareFABBehavior : FloatingActionButton.Behavior() {
    // ...

    fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: FloatingActionButton,
        target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int
    ) {
        super.onNestedScroll(
            coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
            dyUnconsumed
        )

        if (dyConsumed > 0 && child.visibility == View.VISIBLE) {
            child.hide()
        } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {
            child.show()
        }
    }

    // ...
}