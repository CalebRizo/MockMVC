package gus.rest

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = [Application])
@SpringBootTest(webEnvironment = RANDOM_PORT, value = ['management.port=0'])
class TestIntegrationBase extends Specification {
}
