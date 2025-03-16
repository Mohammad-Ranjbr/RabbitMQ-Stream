package com.example.rabbitmq.consumer.stream.config;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Configuration
public class RabbitmqStreamConfig {

    public static final String STREAM_NAME = "s.number";
    private static final String CONSUMER_OFFSET_ABSOLUTE_01 = "consumer-offset-absolute-01";
    private static final String CONSUMER_OFFSET_FIRST_01  = "consumer-offset-first-01";
    private static final String CONSUMER_OFFSET_LAST_01  = "consumer-offset-last-01";
    private static final String CONSUMER_OFFSET_NEXT_01  = "consumer-offset-next-01";
    private static final String CONSUMER_OFFSET_TIMESTAMP_01  = "consumer-offset-timestamp-01";

    private static final String CONSUMER_OFFSET_ABSOLUTE_02 = "consumer-offset-absolute-02";
    private static final String CONSUMER_OFFSET_FIRST_02  = "consumer-offset-first-02";
    private static final String CONSUMER_OFFSET_LAST_02  = "consumer-offset-last-02";
    private static final String CONSUMER_OFFSET_NEXT_02  = "consumer-offset-next-02";
    private static final String CONSUMER_OFFSET_TIMESTAMP_02  = "consumer-offset-timestamp-02";

    private static final String CONSUMER_OFFSET_FIRST_03  = "consumer-offset-first-03";
    private static final String CONSUMER_SINGLE_ACTIVE_01  = "consumer-single-active-01";

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> absoluteContainerFactoryOne(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true); // This Container Factory must be configured with the nativeListener = true property so that it can receive the Context (containing information such as Offset) along with the message.
        factory.setConsumerCustomizer((id, builder) -> {
            // .autoTrackingStrategy() makes RabbitMQ automatically manage the consumer state and its offset.
            builder.name(CONSUMER_OFFSET_ABSOLUTE_01).offset(OffsetSpecification.offset(3)).autoTrackingStrategy();
        });
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> firstContainerFactoryOne(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_FIRST_01).offset(OffsetSpecification.first()).autoTrackingStrategy());
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> lastContainerFactoryOne(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_LAST_01).offset(OffsetSpecification.last()).autoTrackingStrategy());
        return factory;
    }

    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> nextContainerFactoryOne(Environment env) {
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(env);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_NEXT_01).offset(OffsetSpecification.next()).autoTrackingStrategy());
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> timestampContainerFactoryOne(Environment environment){
        // UTC is a type of "universal time" that is the same everywhere in the world. Every region in the world uses UTC to set its local time, and its time difference from UTC is known.
        //.toEpochSecond():
        //This method converts the modified date and time to the number of seconds since January 1, 1970 (Epoch).
        // This is a time conversion to the epoch timestamp format, which is commonly used in computer systems to store time.
        long timestampForOffset = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(5).toEpochSecond() * 1000;
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_TIMESTAMP_01).offset(OffsetSpecification.timestamp(timestampForOffset)).autoTrackingStrategy());
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> absoluteContainerFactoryTwo(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true); // This Container Factory must be configured with the nativeListener = true property so that it can receive the Context (containing information such as Offset) along with the message.
        factory.setConsumerCustomizer((id, builder) -> {
            // .autoTrackingStrategy() makes RabbitMQ automatically manage the consumer state and its offset.
            builder.name(CONSUMER_OFFSET_ABSOLUTE_02).offset(OffsetSpecification.offset(3)).autoTrackingStrategy();
        });
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> firstContainerFactoryTwo(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_FIRST_02).offset(OffsetSpecification.first()).autoTrackingStrategy());
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> lastContainerFactoryTwo(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_LAST_02).offset(OffsetSpecification.last()).autoTrackingStrategy());
        return factory;
    }

    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> nextContainerFactoryTwo(Environment env) {
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(env);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_NEXT_02).offset(OffsetSpecification.next()).autoTrackingStrategy());
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> timestampContainerFactoryTwo(Environment environment){
        long timestampForOffset = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(5).toEpochSecond() * 1000;
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_TIMESTAMP_02).offset(OffsetSpecification.timestamp(timestampForOffset)).autoTrackingStrategy());
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> firstContainerFactoryThree(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_OFFSET_FIRST_03).offset(OffsetSpecification.first()).manualTrackingStrategy());
        return factory;
    }

    @Bean
    public RabbitListenerContainerFactory<StreamListenerContainer> singleActiveContainerFactory(Environment environment){
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.name(CONSUMER_SINGLE_ACTIVE_01).offset(OffsetSpecification.next())
                .singleActiveConsumer().autoTrackingStrategy());
        return factory;
    }

}
