package de.d3ns0n.code.kofee.infrastructure.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.kafka.topics")
data class TopicConfiguration(val items: String)
