package bp.demo.iot.tower.kafka.consumer;

import bp.demo.iot.tower.manager.service.DeviceUserService;
import bp.demo.iot.tower.model.RegisteredUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class RegisteredUserDataConsumer {

  private CountDownLatch registeredUserDataLatch = new CountDownLatch(1);

  private Logger logger =
          LoggerFactory.getLogger(RegisteredUser.class);

  @Autowired
  private DeviceUserService deviceUserService;

  public CountDownLatch getRegisteredUserDataLatch() {
    return this.registeredUserDataLatch;
  }

  @KafkaListener(
          topics = "${bp.demo.iot.operation.kafka.data.registered.user.topic}",
          containerFactory = "registeredUserDataKafkaListenerContainerFactory")
  public void carrierListener(RegisteredUser registeredUser) {

    logger.info("Received registered user data message: " + registeredUser);

    boolean result =
            deviceUserService.persistRegisteredUser(registeredUser);

    logger.info("Persisted registered user with id: " + result);

    this.getRegisteredUserDataLatch().countDown();
  }

}
