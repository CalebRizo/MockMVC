package hello.rest.api.v1

import hello.rest.api.v1.model.Greeting
import hello.rest.api.v1.model.Person
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile

import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
@RequestMapping('/greeting')
class GreetingController {

    private static final String template = 'Hello, %s!'
    private final AtomicLong counter = new AtomicLong()

    @GetMapping
    Greeting greeting(@RequestParam(value='name', defaultValue='World') String name) {
        
        new Greeting(id: counter.incrementAndGet(), content: String.format(template, name))
    }

    @PostMapping
    Greeting greeting(@RequestBody Person person) {
        
        new Greeting(id: counter.incrementAndGet(), content: String.format(template, person.name))
    }

    @PostMapping(value='/multipart')
    ResponseEntity<Map> multipart(@RequestParam('multipart')MultipartFile multipartFile) {
        Map info = [
          contentType: multipartFile.contentType,
          name: multipartFile.name,
          original: multipartFile.originalFilename
        ]

//    ResponseEntity.ok(info)
        new ResponseEntity(info,HttpStatus.ACCEPTED)
    }
}