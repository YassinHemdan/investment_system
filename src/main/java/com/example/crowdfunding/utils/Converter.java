package com.example.crowdfunding.utils;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class Converter {

    private final ModelMapper modelMapper;
    public Converter(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    public <S, T> List<T> toList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> T convert(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public <S, T> void update(S source, T target) {
        modelMapper.map(source, target);
    }

}