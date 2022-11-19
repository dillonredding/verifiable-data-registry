package com.github.dillonredding.routes

import com.github.dillonredding.common.toUUID
import com.github.dillonredding.models.RegisterIssuerDto
import com.github.dillonredding.services.IssuerService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.configureIssuerRoutes() {
    route("/issuers") {
        post {
            val issuer = call.receive<RegisterIssuerDto>()
            IssuerService.register(issuer)
            call.respondText("Successfully registered issuer", status = HttpStatusCode.Created)
        }

        get {
            val issuers = IssuerService.findAll()
            call.respond(issuers)
        }

        get("{id}") {
            val id = call.parameters.getOrFail("id").toUUID()
            val issuer = IssuerService.findById(id) ?: return@get call.respondText(
                "No issuer found with ID $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(issuer)
        }

        get("{id}/did.json") {
            
        }
    }
}