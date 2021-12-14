package com.example.restservice;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
    This application uses the Jackson JSON library to automatically marshal instances of type Greeting into JSON.
    Jackson is included by default by the web starter.
    In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller. These
    components are identified by the @RestController annotation.
    This code handles GET requests for /greeting by returning a new instance of the Greeting class.
    This controller is concise and simple, but there is plenty going on under the hood. We break it down step by step.
    a. The @GetMapping annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.
    b. The @RequestParam annotation binds the value of the query string parameter name into the name parameter of the
    greeting() method. If the name parameter is absent in the request, the defaultValue of "World" is used.
    c. The implementation of the method body creates and returns a new Greeting object with id and content attributes
    based on the next value from the counter and formats the given name by using the greeting template.
    d. A key difference between a traditional MVC controller and the RESTful web service controller (which is shown
    in the other Greeting standalone class) is the way that the HTTP response body is created. Rather than relying on a
    view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service
    controller populates and returns a Greeting object. The object data will be written directly to the HTTP response
     as JSON.
    e. This code uses Spring @RestController annotation, which marks the class as a controller where every method
    returns a domain object instead of a view. It is shorthand for including both @Controller and @ResponseBody.
    f. The Greeting object must be converted to JSON. Thanks to Spring’s HTTP message converter support, you need not
     do this conversion manually. Because Jackson 2 is on the classpath, Spring’s MappingJackson2HttpMessageConverter
      is automatically chosen to convert the Greeting instance to JSON.

 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
