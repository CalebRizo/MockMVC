package gus.rest

import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration

class BaseControllerSpec extends TestIntegrationBase {
  @Autowired
  ConfigurableApplicationContext context

  @Rule
  JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation('src/docs/generated-snippets')

  MockMvc mockMvc

  void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
      .apply(documentationConfiguration(restDocumentation))
      .build()
  }
}
