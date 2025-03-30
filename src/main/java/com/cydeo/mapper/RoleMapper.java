package com.cydeo.mapper;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper  {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoleDTO convertToDto(Role entity){
        return modelMapper.map(entity, RoleDTO.class);
    }

    public Role convertToEntity(RoleDTO roleDTO){
        return modelMapper.map(roleDTO, Role.class);
    }
}
