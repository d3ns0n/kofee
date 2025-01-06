package de.d3ns0n.code.kofee.integration.clients

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration.ofSeconds

@Lazy
@Component
abstract class AbstractClient {
    @LocalServerPort
    var port: Int = 0

    protected val client: WebTestClient by lazy {
        WebTestClient
            .bindToServer()
            .responseTimeout(ofSeconds(30))
            .baseUrl("http://localhost:$port/")
            .build()
    }

    @Autowired lateinit var objectMapper: ObjectMapper
}
