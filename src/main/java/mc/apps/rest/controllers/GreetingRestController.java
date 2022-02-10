package mc.apps.rest.controllers;

import mc.apps.rest.modele.Greeting;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class GreetingRestController {

    @GetMapping("/rest/greeting")
    public HttpEntity<Greeting> getGreeting(@RequestParam(value = "name", defaultValue = "World") String name){

        Greeting greeting = new Greeting(String.format("Hello %s!",name));

        greeting.add(linkTo(methodOn(GreetingRestController.class).getGreeting(name)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
