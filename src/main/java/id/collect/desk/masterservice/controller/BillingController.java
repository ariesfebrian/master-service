package id.collect.desk.masterservice.controller;

import id.collect.desk.masterservice.entity.Billing;
import id.collect.desk.masterservice.entity.dto.BillingDto;
import id.collect.desk.masterservice.repo.BillingRepository;
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
@RequestMapping("/api/v1/billing")
public class BillingController {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Map<String, Object> objectMap = new HashMap<>();
        List<Billing> billingList = (List<Billing>) billingRepository.findAll();
        objectMap.put("data", billingList);

        Response response = new Response();
        response.setMessage("Load List Billing Transaction Successfully");
        response.setService(nameofCurrMethod);
        response.setData(objectMap);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BillingDto billingDto) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        if (billingDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Response response = new Response();
        try {
            Billing billing = new Billing();
            billing.setCustomer(customerRepository.findById(billingDto.getIdCustomer()).get());
            billing.setBillingAmount(billingDto.getBillingAmount());
            billing.setPaymentDate(billingDto.getPaymentDate());
            billing.setIsClose(billingDto.getIsClose());
            billingRepository.save(billing);

            response.setData(billing);
            response.setMessage("Create Billing Successfully");
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
    public ResponseEntity<Billing> update(@RequestBody BillingDto billingDto, @PathVariable Long id) {
        return billingRepository.findById(id)
                .map(bil -> {
                    bil.setBillingAmount(billingDto.getBillingAmount());
                    bil.setIsClose(billingDto.getIsClose());
                    bil.setPaymentDate(billingDto.getPaymentDate());
                    return ResponseEntity.ok(bil);
                })
                .orElseGet(() -> {
                    Billing billing = new Billing();
                    billing.setId(id);
                    billingRepository.save(billing);
                    return ResponseEntity.ok(billing);
                });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Billing> delete(@PathVariable Long id) {
        return billingRepository.findById(id)
                .map(customer -> {
                    billingRepository.deleteById(id);
                    return ResponseEntity.ok(customer);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
