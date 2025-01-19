package de.d3ns0n.code.kofee.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Flux

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfiguration {
    companion object {
        fun authoritiesFromJwtRealmRoles(jwt: Jwt): Collection<GrantedAuthority> =
            jwt.claims["realm_access"]
                ?.let { (it as? Map<*, *>)?.get("roles") }
                ?.let { (it as? List<*>) }
                ?.mapNotNull { (it as? String)?.let { SimpleGrantedAuthority("ROLE_$it") } }
                ?: emptyList()
    }

    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http {
            authorizeExchange {
                authorize(anyExchange, authenticated)
            }

            oauth2ResourceServer {
                jwt {
                    jwtAuthenticationConverter =
                        reactiveJwtAuthenticationConverter()
                }
            }
        }

    private fun reactiveJwtAuthenticationConverter() =
        ReactiveJwtAuthenticationConverter()
            .apply {
                this.setJwtGrantedAuthoritiesConverter(
                    Converter<Jwt, Flux<GrantedAuthority>> {
                        Flux.fromIterable(authoritiesFromJwtRealmRoles(it))
                    },
                )
            }
}

class Roles {
    companion object {
        const val COFFEE_FARMER = "coffee_farmer"
    }
}
