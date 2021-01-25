package id.collect.desk.masterservice.controller;

import id.collect.desk.masterservice.entity.Customer;
import id.collect.desk.masterservice.repo.CustomerRepository;
import id.collect.desk.masterservice.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Map<String, Object> objectMap = new HashMap<>();
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        objectMap.put("data", customerList);

        Response response = new Response();
        response.setMessage("Load List Customer Successfully");
        response.setService(nameofCurrMethod);
        response.setData(objectMap);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Response response = new Response();
        try {
            customerRepository.save(customer);
            response.setData(customer);
            response.setMessage("Create Customer Successfully");
            response.setService(nameofCurrMethod);
        } catch (Exception e) {
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setService(nameofCurrMethod);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable Long id) {
        return customerRepository.findById(id)
                .map(custmr -> {
                    custmr.setName(customer.getName());
                    custmr.setAddress(customer.getAddress());
                    custmr.setPhoneNumber(customer.getPhoneNumber());
                    custmr.setIsActive(customer.getIsActive());
                    return ResponseEntity.ok(custmr);
                })
                .orElseGet(() -> {
                    customer.setId(id);
                    customerRepository.save(customer);
                    return ResponseEntity.ok(customer);
                });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.deleteById(id);
                    return ResponseEntity.ok(customer);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
