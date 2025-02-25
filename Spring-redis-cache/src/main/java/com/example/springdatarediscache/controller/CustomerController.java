package com.example.springdatarediscache.controller;

import com.example.springdatarediscache.modlel.request.CustomerRequestDto;
import com.example.springdatarediscache.modlel.response.CustomerResponseDto;
import com.example.springdatarediscache.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerDto) {
        CustomerResponseDto createdCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdCustomer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDto customerDto) {
        CustomerResponseDto updateCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateCustomer);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto getCustomer = customerService.getCustomerById(id);
        return new ResponseEntity<>(getCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomer() {
        List<CustomerResponseDto> customerDto = customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @DeleteMapping("/{id}")
    public void deletedCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

}
