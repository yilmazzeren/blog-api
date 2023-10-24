package com.example.blog.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(emptyListConverter());
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    private AbstractConverter<List<?>, List<?>> emptyListConverter() {
        return new AbstractConverter<List<?>, List<?>>() {
            @Override
            protected List<?> convert(List<?> sourceList) {
                return sourceList != null ? sourceList : List.of();
            }
        };
    }
}
