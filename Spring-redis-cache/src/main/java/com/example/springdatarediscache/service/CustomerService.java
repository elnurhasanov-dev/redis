package com.example.springdatarediscache.service;

import com.example.springdatarediscache.dao.entity.CustomerEntity;
import com.example.springdatarediscache.dao.repository.CustomerRepository;
import com.example.springdatarediscache.modlel.request.CustomerRequestDto;
import com.example.springdatarediscache.modlel.response.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.springdatarediscache.mapper.CustomerMapper.CUSTOMER_MAPPER;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @CacheEvict(cacheNames = "customers", allEntries = true)
    public CustomerResponseDto createCustomer(CustomerRequestDto customerDto) {
        var customer = CUSTOMER_MAPPER.requestDtoToCustomer(customerDto);
        var savedCustomer = customerRepository.save(customer);

        return CUSTOMER_MAPPER.customerToResponseDto(savedCustomer);
    }

    @CacheEvict(cacheNames = "customers", allEntries = true)
    @CachePut(cacheNames = "customer_id", key = "'getCustomerById' + #id", unless = "#result == null")
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerDto) {
        var customer = fetchCustomerEntityIfExist(id);
        CUSTOMER_MAPPER.updateCustomerEntity(customer, customerDto);
        var updatedCustomer = customerRepository.save(customer);

        return CUSTOMER_MAPPER.customerToResponseDto(updatedCustomer);
    }

    @Cacheable(cacheNames = "customer_id", key = "#root.methodName + #id", unless = "#result == null")
    public CustomerResponseDto getCustomerById(Long id) {
        var customer = fetchCustomerEntityIfExist(id);
        return CUSTOMER_MAPPER.customerToResponseDto(customer);
    }

    @Cacheable(value = "customers", key = "#root.methodName", unless = "#result == null")
    public List<CustomerResponseDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(CUSTOMER_MAPPER::customerToResponseDto)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = {"customers", "customer_id"}, allEntries = true)
    public void deleteCustomer(Long id) {
        var customer = fetchCustomerEntityIfExist(id);
        customerRepository.delete(customer);
    }

    private CustomerEntity fetchCustomerEntityIfExist(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Id " + id + "not found"));
    }
}
