package com.rudolfhladik.tmobile.prototype.fragments

import android.support.v4.app.Fragment

/**
 *
 * Created by rd on 13/10/16.
 */

class FragmentTabInfo(cls: Class<out Fragment>, tabNameId: Int) {
    var cls: Class<out Fragment>
    var tabNameId: Int = 0

    init {
        this.cls = cls
        this.tabNameId = tabNameId
    }
}
