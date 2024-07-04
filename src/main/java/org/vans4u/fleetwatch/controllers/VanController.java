package org.vans4u.fleetwatch.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.vans4u.fleetwatch.domain.Van;
import org.vans4u.fleetwatch.services.VanService;
import org.vans4u.fleetwatch.services.BadVanServiceRequestException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vans")
public class VanController {

    private final VanService vanService;

    public VanController(VanService vanService) {
        this.vanService = vanService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Van> getVanById(@PathVariable("id") long id) {
        var van = vanService.getVanById(id);
        if(van.isPresent()) {
            return ResponseEntity.ok(van.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    ResponseEntity<List<Van>> getVans() {
        var vans = vanService.listAllVans();
        if (!vans.isEmpty()) {
            return ResponseEntity.ok(vans);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    ResponseEntity<Van> addVan(@Valid @RequestBody Van van) {
        return new ResponseEntity<>(vanService.addVan(van), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteVanById(@PathVariable("id") long id) {
        if(vanService.deleteVanById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/mileage/{mileage}")
    ResponseEntity<Void> updateVanMileage(@PathVariable("id") long id, @PathVariable("mileage") int mileage) {
        if(vanService.updateMileage(id, mileage)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadVanServiceRequestException.class)
    public String handleBadVanServiceRequestException(BadVanServiceRequestException ex) {
        return ex.getMessage();
    }


}
