package com.github.dillonredding.plugins

import com.github.dillonredding.models.RegisterIssuerDto
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validate<RegisterIssuerDto> {
            // TODO validate it.publicKey
            return@validate ValidationResult.Valid
        }
    }
}