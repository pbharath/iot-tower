package bp.demo.iot.tower.manager.service;

import bp.demo.iot.tower.kafka.publisher.DeviceUserPublisher;
import bp.demo.iot.tower.manager.repository.ContentByAgePolicyRuleRepository;
import bp.demo.iot.tower.manager.repository.RegisteredUserRepository;
import bp.demo.iot.tower.manager.repository.TowerCarrierPlatformPolicyRuleRepository;
import bp.demo.iot.tower.manager.repository.dao.ContentByAgePolicyRuleDAO;
import bp.demo.iot.tower.manager.repository.dao.RegisteredUserDAO;
import bp.demo.iot.tower.manager.repository.dao.TowerCarrierPlatformPolicyRuleDAO;
import bp.demo.iot.tower.manager.repository.dao.pk.RegisteredUserKeyDAO;
import bp.demo.iot.tower.manager.repository.dao.pk.TowerCarrierPlatformPolicyRuleKeyDAO;
import bp.demo.iot.tower.manager.repository.exception.TowerException;
import bp.demo.iot.tower.manager.repository.exception.TowerManagerException;
import bp.demo.iot.tower.manager.service.helper.DeviceUserServiceHelper;
import bp.demo.iot.tower.model.DeviceUser;
import bp.demo.iot.tower.model.RegisteredUser;
import bp.demo.iot.tower.model.UserContentActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceUserService {

  private Logger logger = LoggerFactory.getLogger(DeviceUserService.class);

  @Autowired
  private DeviceUserPublisher deviceUserPublisher;

  @Autowired
  private DeviceUserServiceHelper deviceUserServiceHelper;

  @Autowired
  private RegisteredUserRepository registeredUserRepository;

  @Autowired
  private TowerCarrierPlatformPolicyRuleRepository towerCarrierPlatformPolicyRuleRepository;

  @Autowired
  private ContentByAgePolicyRuleRepository contentByAgePolicyRuleRepository;

  public boolean processNewDeviceUser(DeviceUser deviceUser)
          throws TowerManagerException {

    String carrierName = deviceUser.getCarrierName();
    String platformName = deviceUser.getPlatformName();

    if(registrationExistsForTowerCarrierPlatform(deviceUser)) {
      String exceptionMessage =
              TowerException.TOWER_CARRIER_PLATFORM_SERVICE_DENIAL_EXCEPTION +
                      " for Carrier: " + carrierName + ", Platform: " + platformName;
      logger.error(exceptionMessage);
      throw new TowerManagerException(exceptionMessage);
    }

    if(isDeviceUserRegistered(deviceUser.getCarrierName(), deviceUser.getEmail())) {
      String exceptionMessage =
              TowerException.REGISTRATION_EXISTS_FOR_USER_EXCEPTION +
                      " for Carrier: " + carrierName + ", Email: " + deviceUser.getEmail();
      logger.error(exceptionMessage);
      throw new TowerManagerException(exceptionMessage);
    }

    deviceUser.setActive(true);

    Message<DeviceUser> message =
            MessageBuilder.withPayload(deviceUser)
                    .setHeader(
                            KafkaHeaders.MESSAGE_KEY,
                            deviceUser.getCarrierName())
                    .setHeader(KafkaHeaders.TOPIC,
                            deviceUserPublisher.getTopic())
                    .build();
    deviceUserPublisher.sendMessage(message);

    return true;

  }

  private boolean registrationExistsForTowerCarrierPlatform(DeviceUser deviceUser) {

    String towerName = deviceUser.getTowerName();
    String carrierName = deviceUser.getCarrierName();
    String platformName = deviceUser.getPlatformName();

    TowerCarrierPlatformPolicyRuleKeyDAO tcpPolicyRuleDAO =
            new TowerCarrierPlatformPolicyRuleKeyDAO(towerName, carrierName,
                    platformName);

    Optional<TowerCarrierPlatformPolicyRuleDAO> optionalTcpPolicyDAO =
            towerCarrierPlatformPolicyRuleRepository.findById(tcpPolicyRuleDAO);

    return optionalTcpPolicyDAO.isPresent();
  }

  private boolean isDeviceUserRegistered(String carrierName, String email) {

    RegisteredUserKeyDAO key =
            new RegisteredUserKeyDAO(carrierName, email);
    Optional<RegisteredUserDAO> optionalRegisteredUser =
            registeredUserRepository.findById(key);

    return optionalRegisteredUser.isPresent();
  }

  private boolean isDeviceUserRegisteredValid(String carrierName,
                                              String email) {

    RegisteredUserKeyDAO key =
            new RegisteredUserKeyDAO(carrierName, email);
    Optional<RegisteredUserDAO> optionalRegisteredUser =
            registeredUserRepository.findById(key);

    return optionalRegisteredUser.isPresent() && optionalRegisteredUser.get().isActive();
  }

  public boolean processUserContentAccess(UserContentActivity userContentActivity)
          throws TowerManagerException {

    if(validateUsingContentByAgePolicyRule(userContentActivity)) {
      return true;
    }
    else {
      String exceptionMessage = TowerException.CONTENT_ACCESS_DENIAL_POLICY_RULE +
                      " for Carrier: " + userContentActivity.getCarrierName() +
              ", User:" + " " + userContentActivity.getUserId();
      logger.error(exceptionMessage);
      throw new TowerManagerException(exceptionMessage);
    }

  }


  private boolean validateUsingContentByAgePolicyRule(UserContentActivity userContentActivity)
    throws  TowerManagerException{

    List<RegisteredUserDAO> optionalRegisteredUser =
            registeredUserRepository.findByCarrierUser(userContentActivity.getCarrierName(),
                    userContentActivity.getUserId());

    if (optionalRegisteredUser.size() == 0) {
      String exceptionMessage = TowerException.USER_NOT_FOUND_CONFLICT +
              " for Carrier: " + userContentActivity.getCarrierName() + ", " +
              "UserID: " + userContentActivity.getUserId();
      logger.error(exceptionMessage);

      throw new TowerManagerException(exceptionMessage);
    }

    RegisteredUserDAO registeredUserDAO = optionalRegisteredUser.iterator().next();

    int ageInYears =
            Period.between(convertToLocalDateViaMilisecond(registeredUserDAO.getDob()),
                    convertToLocalDateViaMilisecond(new Date())).getYears();

    List<ContentByAgePolicyRuleDAO> optionalPolicyRule =
            contentByAgePolicyRuleRepository.findByAgeRange(ageInYears, ageInYears);

    if (optionalPolicyRule.iterator().hasNext()){

      ContentByAgePolicyRuleDAO contentByAgePolicyRuleDAO =
              optionalPolicyRule.iterator().next();

      if (contentByAgePolicyRuleDAO.getContentSet().contains(userContentActivity.getContent_type()))
        return true;
    }

    return false;

  }

  private LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
  }


  public boolean persistRegisteredUser(RegisteredUser registeredUser) {

    RegisteredUserDAO dao =
            deviceUserServiceHelper.convertRegisteredUserDataToDAO(registeredUser);

    registeredUserRepository.insert(dao);

    return true;
  }

}
