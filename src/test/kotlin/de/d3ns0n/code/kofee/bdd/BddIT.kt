package de.d3ns0n.code.kofee.bdd

import io.cucumber.spring.CucumberContextConfiguration
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.kafka.ConfluentKafkaContainer
import java.util.function.Supplier

const val INTEGRATION_TEST_PROFILE = "integration-test"

@ActiveProfiles(INTEGRATION_TEST_PROFILE)
@SpringBootTest
@Suite
@IncludeEngines("cucumber")
@CucumberContextConfiguration
@SelectClasspathResource("de/d3ns0n/code/kofee")
class BddIT {
    companion object {
        val postgresContainer: PostgreSQLContainer<*> =
            PostgreSQLContainer("postgres:17")
                .withUsername("postgres")
                .withPassword("postgres")

        val kafkaContainer = ConfluentKafkaContainer("confluentinc/cp-kafka:7.5.5")

        init {
            postgresContainer.start()
            kafkaContainer.start()
        }

        @Suppress("unused")
        @JvmStatic
        @DynamicPropertySource
        fun registerContainerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.liquibase.url", Supplier { postgresContainer.jdbcUrl })
            registry.add("spring.r2dbc.url", Supplier { postgresContainer.r2dbcUrl })
            registry.add("spring.kafka.bootstrap-servers", Supplier { kafkaContainer.bootstrapServers })
            registry.add("spring.kafka.properties.schema.registry.url", Supplier { "mock://localhost" })
        }
    }
}

private val PostgreSQLContainer<*>.r2dbcUrl: String
    get() = this.jdbcUrl.replace("jdbc", "r2dbc")
