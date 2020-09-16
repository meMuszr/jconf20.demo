package me.muszr.config;

import javax.annotation.Nullable;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.jackson.JacksonConfiguration;
import io.micronaut.jackson.ObjectMapperFactory;

@Factory
@Replaces(ObjectMapperFactory.class)
public class CustomObjectMapperFactory extends ObjectMapperFactory  {
        @Override
        @Singleton
        @Replaces(ObjectMapper.class)
    public ObjectMapper objectMapper(@Nullable JacksonConfiguration jacksonConfiguration,
                                     @Nullable JsonFactory jsonFactory) {
            final ObjectMapper mapper = super.objectMapper(jacksonConfiguration, jsonFactory);
            mapper.registerModule(new ParameterNamesModule());
            return mapper;
        }
    
}
