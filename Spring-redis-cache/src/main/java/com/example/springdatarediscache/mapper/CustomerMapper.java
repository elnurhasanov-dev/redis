package com.example.springdatarediscache.mapper;

import com.example.springdatarediscache.dao.entity.CustomerEntity;
import com.example.springdatarediscache.modlel.request.CustomerRequestDto;
import com.example.springdatarediscache.modlel.response.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity requestDtoToCustomer(CustomerRequestDto customerRequestDto);

    CustomerResponseDto customerToResponseDto(CustomerEntity customer);

    void updateCustomerEntity(@MappingTarget CustomerEntity entity, CustomerRequestDto request);

}
