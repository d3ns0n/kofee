package de.d3ns0n.code.kofee

import de.d3ns0n.code.kofee.infrastructure.configuration.TopicConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(TopicConfiguration::class)
@SpringBootApplication
class KoFeeApplication

fun main(args: Array<String>) {
    runApplication<KoFeeApplication>(*args)
}
