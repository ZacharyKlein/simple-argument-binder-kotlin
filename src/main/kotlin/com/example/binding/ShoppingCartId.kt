package com.example.binding

import io.micronaut.core.bind.annotation.Bindable
import kotlin.annotation.Retention

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Bindable
annotation class ShoppingCartId(val value: String = "")
