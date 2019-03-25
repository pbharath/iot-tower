package bp.demo.iot.tower.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value(value = "${bp.demo.iot.tower.kafka.brokerlist}")
  private String bootstrapAddress;

  public ConsumerFactory<String, String> contentByAgeDataConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "Tower - Content By Age");
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
            new StringDeserializer());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> contentByAgeDataKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setMessageConverter(new StringJsonMessageConverter());
    factory.setConsumerFactory(contentByAgeDataConsumerFactory());
    return factory;
  }

  public ConsumerFactory<String, String> towerCarrierPlatformDataConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "Tower - Tower Carrier Platform Data");
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
            new StringDeserializer());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> towerCarrierPlatformDataKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setMessageConverter(new StringJsonMessageConverter());
    factory.setConsumerFactory(towerCarrierPlatformDataConsumerFactory());
    return factory;
  }

  public ConsumerFactory<String, String> registeredUserDataConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "Tower - Registered User");
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
            new StringDeserializer());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> registeredUserDataKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setMessageConverter(new StringJsonMessageConverter());
    factory.setConsumerFactory(registeredUserDataConsumerFactory());
    return factory;
  }

}
