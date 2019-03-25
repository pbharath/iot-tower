package bp.demo.iot.tower.kafka.publisher;

import bp.demo.iot.tower.model.UserContentActivity;
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
public class UserContentActivityPublisher {

  private Logger logger =
          LoggerFactory.getLogger(UserContentActivityPublisher.class);

  @Autowired
  private KafkaTemplate<String, UserContentActivity> userContentActivityEventKafkaTemplate;

  @Value(value = "${bp.demo.iot.tower.kafka.event.user.content.activity.topic}")
  private String userContentActivityEventTopic;

  public void sendCarrierTowerEventMessage(UserContentActivity userContentActivity) {
    userContentActivityEventKafkaTemplate.send(userContentActivityEventTopic, userContentActivity);
  }

  public String getTopic() {
    return this.userContentActivityEventTopic;
  }

  public void sendMessage(Message message) {

    ListenableFuture<SendResult<String, UserContentActivity>> future =
            userContentActivityEventKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, UserContentActivity>>() {

      @Override
      public void onSuccess(SendResult<String, UserContentActivity> result) {
        logger.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
      }

    });
  }
}
