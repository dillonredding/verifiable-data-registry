package com.github.dillonredding

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.utils.io.charsets.Charsets
import kotlin.test.Test
import kotlin.test.assertEquals

class IssuerRoutesTests {
    @Test
    fun testGetIssuers() = testApplication {
        val response = client.get("/issuers")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(ContentType.Text.Plain.withCharset(Charsets.UTF_8), response.contentType())
        assertEquals("No issuers found", response.bodyAsText())
    }
}