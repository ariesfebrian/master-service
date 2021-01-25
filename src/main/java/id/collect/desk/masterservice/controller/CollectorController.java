package id.collect.desk.masterservice.controller;

import id.collect.desk.masterservice.entity.Collector;
import id.collect.desk.masterservice.repo.CollectorRepository;
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
@RequestMapping("/api/collector")
public class CollectorController {

    @Autowired
    private CollectorRepository collectorRepository;

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Map<String, Object> objectMap = new HashMap<>();
        List<Collector> collectorList = (List<Collector>) collectorRepository.findAll();
        objectMap.put("data", collectorList);

        Response response = new Response();
        response.setMessage("Load List Collector Successfully");
        response.setService(nameofCurrMethod);
        response.setData(objectMap);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Collector collector) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        if (collector == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Response response = new Response();
        try {
            collectorRepository.save(collector);
            response.setData(collector);
            response.setMessage("Create Collector Successfully");
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
    public ResponseEntity<Collector> update(@RequestBody Collector collector, @PathVariable Long id) {
        return collectorRepository.findById(id)
                .map(collector1 -> {
                    collector1.setName(collector.getName());
                    return ResponseEntity.ok(collector1);
                })
                .orElseGet(() -> {
                    collector.setId(id);
                    collectorRepository.save(collector);
                    return ResponseEntity.ok(collector);
                });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Collector> delete(@PathVariable Long id) {
        return collectorRepository.findById(id)
                .map(customer -> {
                    collectorRepository.deleteById(id);
                    return ResponseEntity.ok(customer);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
