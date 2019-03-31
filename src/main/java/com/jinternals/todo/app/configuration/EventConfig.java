package com.jinternals.todo.app.configuration;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static org.axonframework.eventhandling.TrackingEventProcessorConfiguration.forParallelProcessing;

@Configuration
public class EventConfig {


    @Autowired
    public void config(EventProcessingConfigurer configurer) {
        configurer.registerTrackingEventProcessorConfiguration(
                c -> forParallelProcessing(2)
        );
    }

}