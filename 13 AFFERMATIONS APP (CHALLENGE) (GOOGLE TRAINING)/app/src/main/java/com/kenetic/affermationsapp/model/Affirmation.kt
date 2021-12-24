package com.kenetic.affermationsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(@StringRes val stringResourceID1: Int,
                       @DrawableRes val imageResourceID1: Int,
                       @StringRes val stringResourceID2: Int,
                       @DrawableRes val imageResourceID2: Int)