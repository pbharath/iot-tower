package bp.demo.iot.tower.manager.service;

import bp.demo.iot.tower.manager.repository.ContentByAgePolicyRuleRepository;
import bp.demo.iot.tower.manager.repository.TowerCarrierPlatformPolicyRuleRepository;
import bp.demo.iot.tower.manager.repository.dao.ContentByAgePolicyRuleDAO;
import bp.demo.iot.tower.manager.repository.dao.TowerCarrierPlatformPolicyRuleDAO;
import bp.demo.iot.tower.manager.service.helper.PolicyServiceHelper;
import bp.demo.iot.tower.model.ContentByAgePolicyRule;
import bp.demo.iot.tower.model.TowerCarrierPlatformPolicyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

  @Autowired
  private PolicyServiceHelper policyServiceHelper;

  @Autowired
  private ContentByAgePolicyRuleRepository contentByAgePolicyRuleRepository;

  @Autowired
  private TowerCarrierPlatformPolicyRuleRepository towerCarrierPlatformPolicyRuleRepository;

  public ContentByAgePolicyRule createNewContentByAgePolicyRule(ContentByAgePolicyRule cbapr) {

    ContentByAgePolicyRuleDAO dao =
            policyServiceHelper.convertToDAO(cbapr);

    ContentByAgePolicyRuleDAO persistedDAO =
            contentByAgePolicyRuleRepository.insert(dao);

    return policyServiceHelper.convertToEntity(persistedDAO);

  }

  public TowerCarrierPlatformPolicyRule createNewTowerCarrierPlatformPolicyRule(TowerCarrierPlatformPolicyRule tcppr) {

    TowerCarrierPlatformPolicyRuleDAO dao =
            policyServiceHelper.convertTowerCarrierPlatformToDAO(tcppr);

    TowerCarrierPlatformPolicyRuleDAO persistedDAO =
            towerCarrierPlatformPolicyRuleRepository.insert(dao);

    return policyServiceHelper.convertTowerContentPlatformToEntity(persistedDAO);

  }

}
