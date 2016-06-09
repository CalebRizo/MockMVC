package hello

import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
public class GreetingController {

    private static final String template = 'Hello, %s!'
    private final AtomicLong counter = new AtomicLong()

    @RequestMapping('/greeting')
    Greeting greeting(@RequestParam(value='name', defaultValue='World') String name) {
        
        new Greeting(counter.incrementAndGet(), String.format(template, name))
    }

    @RequestMapping(value = '/greeting',  method = POST)  
    Greeting greeting(@RequestBody Person person) {
        
        new Greeting(id: counter.incrementAndGet(), content: String.format(template, person.name))
    }
}