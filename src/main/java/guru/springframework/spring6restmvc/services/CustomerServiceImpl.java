package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<Integer,Customer> customers;

    public CustomerServiceImpl() {
        this.customers = new HashMap<>();
        Customer customer1 = Customer.builder()
                .id(1)
                .firstName("Sebastian")
                .lastName("Velez")
                .build();
        customers.put(customer1.getId(), customer1);
    }

    @Override
    public void createCustomer(Customer customer) {
        try {
            Customer newCustomer = Customer.builder()
                    .id(customer.getId())
                    .lastName(customer.getLastName())
                    .firstName(customer.getFirstName())
                    .build();
            customers.put(customer.getId(), newCustomer);
        }catch (Exception e){
            log.error(e.toString());
        }
    }
}
