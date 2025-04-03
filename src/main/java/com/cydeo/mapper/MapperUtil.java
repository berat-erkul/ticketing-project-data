package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method is used to convert an object of one type to another type using ModelMapper.
     *
     * @param objectToBeConverted the object to be converted
     * @param convertedObject the class of the converted object
     * @param <T> the type of the converted object
     * @return the converted object
     */
    public <T> T conver(Object objectToBeConverted, Class <T> convertedObject) {
        return modelMapper.map(objectToBeConverted, convertedObject);
    }
}
