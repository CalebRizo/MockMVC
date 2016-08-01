package gus.rest.api.v1

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.JsonFieldType.STRING
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import groovy.json.JsonOutput
import gus.rest.BaseControllerSpec
import gus.rest.api.v1.model.NotificationList
import gus.rest.api.v1.model.SmsRequest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Issue

import java.lang.Void as Should

class GreetingControllerIntegrationSpec extends BaseControllerSpec {

  Should 'receive a multipartFile'() {
    given:
      NotificationList notificationList = new NotificationList(emails: 'caleb@holagus.com')
      String json = JsonOutput.toJson(notificationList)
      FileInputStream file = new FileInputStream('./src/test/resources/badNumberDemo.csv')
      MockMultipartFile multipartFile = new MockMultipartFile('src/test/resources/badNumberDemo.csv', file)
      HashMap<String, String> contentTypeParams = new HashMap();
      contentTypeParams.put('boundary', '265001916915724');
      MediaType mediaType = new MediaType('multipart', 'form-data', contentTypeParams);
    when:
      ResultActions result = mockMvc.perform(
        fileUpload('/v1/sms/_blast')
          .file(multipartFile)
      )
    then:
      result
        .andExpect(status().isBadRequest())
        .andDo(document('v1-sms-blast/good'))
  }
}
