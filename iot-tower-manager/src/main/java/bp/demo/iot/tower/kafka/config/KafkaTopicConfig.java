package bp.demo.iot.tower.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:iot-tower-kafka.properties"})
public class KafkaTopicConfig {

  @Value(value = "${bp.demo.iot.tower.kafka.brokerlist}")
  private String bootstrapAddress;

  @Value(value = "${bp.demo.iot.operation.kafka.data.registered.user.topic}")
  private String registeredDeviceUserTopicName;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic registeredDeviceUserTopic() {
    return new NewTopic(registeredDeviceUserTopicName, 1, (short) 1);
  }

}
