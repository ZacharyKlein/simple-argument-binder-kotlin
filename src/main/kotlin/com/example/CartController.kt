package com.example

import io.micronaut.http.annotation.Get
import com.example.binding.ShoppingCartId
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller

@Controller("/cart")
class CartController {
    @Get("/")
    fun loadCart(@ShoppingCartId id: Long): HttpResponse<String> {
        return HttpResponse.ok("Session:$id")
    }
}
