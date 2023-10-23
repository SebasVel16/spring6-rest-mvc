package guru.springframework.spring6restmvc.controllers;


import guru.springframework.spring6restmvc.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
