package com.toptal.githubrepo.domain.extensions

import com.github.type.Repository

fun Int?.orDefault(default: Int = 0) = this ?: default

fun Boolean?.orDefault(default: Boolean = false) = this ?: default

fun String?.orDefault(default: String = "") = this ?: default

fun Throwable?.messageOrDefault(default: String = "UNKNOWN ERROR") = this?.message ?: default
