package com.github.dillonredding.common

import java.util.*

fun String.toUUID(): UUID = UUID.fromString(this)