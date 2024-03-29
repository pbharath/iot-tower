package bp.demo.iot.tower.kafka.config;

import bp.demo.iot.tower.model.DeviceUser;
import bp.demo.iot.tower.model.UserContentActivity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

  @Value(value = "${bp.demo.iot.tower.kafka.brokerlist}")
  private String bootstrapAddress;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return configProps;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public Map<String, Object> stringKeyJsonValueProducerConfigs() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return configProps;
  }


  @Bean
  public ProducerFactory<String, DeviceUser> deviceUserEventProducerFactory() {
    return new DefaultKafkaProducerFactory<>(stringKeyJsonValueProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, DeviceUser> deviceUserEventKafkaTemplate() {
    return new KafkaTemplate<>(deviceUserEventProducerFactory());
  }

  @Bean
  public ProducerFactory<String, UserContentActivity> userContentActivityEventProducerFactory() {
    return new DefaultKafkaProducerFactory<>(stringKeyJsonValueProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, UserContentActivity> userContentActivityEventKafkaTemplate() {
    return new KafkaTemplate<>(userContentActivityEventProducerFactory());
  }

}
