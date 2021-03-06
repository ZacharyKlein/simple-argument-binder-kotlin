package com.example.binding

import io.micronaut.core.convert.ConversionService
import io.micronaut.jackson.serialize.JacksonObjectSerializer
import io.micronaut.http.bind.binders.AnnotatedRequestArgumentBinder
import io.micronaut.core.convert.ArgumentConversionContext
import io.micronaut.core.bind.ArgumentBinder.BindingResult
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import java.util.*
import javax.inject.Singleton

@Singleton
class ShoppingCartArgumentBinder(
        private val conversionService: ConversionService<*>,
        private val objectSerializer: JacksonObjectSerializer) : AnnotatedRequestArgumentBinder<ShoppingCartId, Any?> {

    override fun getAnnotationType(): Class<ShoppingCartId> {
        return ShoppingCartId::class.java
    }

    override fun bind(context: ArgumentConversionContext<Any?>?, source: HttpRequest<*>?): BindingResult<Any?> {
        val parameterName = context?.annotationMetadata?.stringValue(ShoppingCartId::class.java)?.orElse(context.argument?.name)
        val cookie = source?.cookies?.get("shoppingCart")
        if (cookie != null) {
            val cookieValue: Optional<Map<String, Any>> = objectSerializer.deserialize(
                    cookie.value.toByteArray(),
                    Argument.mapOf(String::class.java, Any::class.java))

            return BindingResult {
                cookieValue.flatMap { map: Map<String, Any> ->
                    conversionService.convert(map[parameterName], context)
                }
            }
        }
        return BindingResult.EMPTY
    }
}
