package bp.demo.iot.tower.kafka.publisher;

import bp.demo.iot.tower.model.DeviceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class DeviceUserPublisher {

  private Logger logger =
          LoggerFactory.getLogger(DeviceUserPublisher.class);

  @Autowired
  private KafkaTemplate<String, DeviceUser> deviceUserEventKafkaTemplate;

  @Value(value = "${bp.demo.iot.operation.kafka.event.device.user.topic}")
  private String deviceUserEventTopic;

  public void sendCarrierTowerEventMessage(DeviceUser deviceUser) {
    deviceUserEventKafkaTemplate.send(deviceUserEventTopic, deviceUser);
  }

  public String getTopic() {
    return this.deviceUserEventTopic;
  }

  public void sendMessage(Message message) {

    ListenableFuture<SendResult<String, DeviceUser>> future =
            deviceUserEventKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, DeviceUser>>() {

      @Override
      public void onSuccess(SendResult<String, DeviceUser> result) {
        logger.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
      }

    });
  }

}
