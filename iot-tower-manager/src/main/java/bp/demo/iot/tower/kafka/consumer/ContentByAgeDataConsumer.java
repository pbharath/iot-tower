package bp.demo.iot.tower.kafka.consumer;

import bp.demo.iot.tower.manager.service.PolicyService;
import bp.demo.iot.tower.model.ContentByAgePolicyRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class ContentByAgeDataConsumer {

  private CountDownLatch contentByAgeDataLatch = new CountDownLatch(1);

  private Logger logger =
          LoggerFactory.getLogger(ContentByAgeDataConsumer.class);

  @Autowired
  private PolicyService policyService;

  public CountDownLatch getContentByAgeDataLatch() {
    return this.contentByAgeDataLatch;
  }

  @KafkaListener(
          topics = "${bp.demo.iot.policy.kafka.data.age.content.topic}",
          containerFactory = "contentByAgeDataKafkaListenerContainerFactory")
  public void carrierListener(ContentByAgePolicyRule contentByAgePolicyRule) {

    logger.info("Received content by age data message: " + contentByAgePolicyRule);

    ContentByAgePolicyRule policyRule =
            policyService.createNewContentByAgePolicyRule(contentByAgePolicyRule);

    logger.info("Persisted content by age policy rule: " + policyRule);

    this.getContentByAgeDataLatch().countDown();
  }
}
