package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerITest {
    @Autowired
    CustomerController  customerController;
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Rollback
    @Test
    void deleteById() {
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity responseEntity = customerController.deleteCustomerById(customer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(customerRepository.findById(customer.getId()).isEmpty());
    }

    @Test
    void deleteByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteCustomerById(UUID.randomUUID());
        });
    }

    @Test
    @Rollback
    @Transactional
    void testSaveCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder().name("Sebas").build();

        ResponseEntity responseEntity = customerController.handlePost(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] customerLocation = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(customerLocation[4]);

        Customer customer = customerRepository.findById(savedUUID).get();

        assertThat(customer).isNotNull();
    }

    @Test
    @Rollback
    @Transactional
    void testUpdateById() {
        CustomerDTO customerDTO = customerController.listAllCustomers().get(0);

        CustomerDTO newCustomer = CustomerDTO.builder()
                .name("Sebas").build();

        ResponseEntity responseEntity = customerController.updateCustomerByID(customerDTO.getId(), newCustomer);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Customer updatedCustomer = customerRepository.findById(customerDTO.getId()).get();

        assertThat(updatedCustomer.getName()).isEqualTo(newCustomer.getName());
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.updateCustomerByID(UUID.randomUUID(), CustomerDTO.builder().build());
        });
    }

    @Test
    @Rollback
    @Transactional
    void testEmptyList() {
        customerRepository.deleteAll();
        List<CustomerDTO> dtos =  customerController.listAllCustomers();
        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testListCustomers() {
        List<CustomerDTO> dtos =  customerController.listAllCustomers();
         assertThat(dtos.size()).isEqualTo(3);
    }

    @Test
    void testGetById() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO  customerDTO = customerController.getCustomerById(customer.getId());

        assertThat(customerDTO).isNotNull();
    }

    @Test
    void testGetByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }


}