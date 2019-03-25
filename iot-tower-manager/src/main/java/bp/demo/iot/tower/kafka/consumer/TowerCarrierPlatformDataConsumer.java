package bp.demo.iot.tower.kafka.consumer;

import bp.demo.iot.tower.manager.service.PolicyService;
import bp.demo.iot.tower.model.TowerCarrierPlatformPolicyRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class TowerCarrierPlatformDataConsumer {

  private CountDownLatch towerCarrierPlatformDataLatch = new CountDownLatch(1);

  private Logger logger =
          LoggerFactory.getLogger(TowerCarrierPlatformDataConsumer.class);

  @Autowired
  private PolicyService policyService;

  public CountDownLatch getTowerCarrierPlatformDataLatch() {
    return this.towerCarrierPlatformDataLatch;
  }

  @KafkaListener(
          topics = "${bp.demo.iot.policy.kafka.data.tower.carrier.platform.topic}",
          containerFactory = "towerCarrierPlatformDataKafkaListenerContainerFactory")
  public void carrierListener(TowerCarrierPlatformPolicyRule towerCarrierPlatformPolicyRule) {

    logger.info("Received tower carrier platform data message: " + towerCarrierPlatformPolicyRule);

    TowerCarrierPlatformPolicyRule policyRule =
      policyService.createNewTowerCarrierPlatformPolicyRule(towerCarrierPlatformPolicyRule);

    logger.info("Persisted tower carrier platform policy rule: " + policyRule);

    this.getTowerCarrierPlatformDataLatch().countDown();
  }

}
