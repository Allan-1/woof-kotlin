package com.example.woof.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(@DrawableRes val imageResourceId: Int,
               @StringRes val stringResourceId: Int,
               val age: Int, @StringRes
               val hobbiesResourceId: Int)
