package de.d3ns0n.code.kofee.bdd

import io.cucumber.spring.CucumberContextConfiguration
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@Suite
@IncludeEngines("cucumber")
@CucumberContextConfiguration
@SelectClasspathResource("de/d3ns0n/code/kofee")
class BddIT
