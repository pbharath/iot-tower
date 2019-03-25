package bp.demo.iot.tower.manager.service.helper;

import bp.demo.iot.tower.manager.repository.dao.ContentByAgePolicyRuleDAO;
import bp.demo.iot.tower.manager.repository.dao.TowerCarrierPlatformPolicyRuleDAO;
import bp.demo.iot.tower.model.ContentByAgePolicyRule;
import bp.demo.iot.tower.model.TowerCarrierPlatformPolicyRule;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PolicyServiceHelper {

    @Autowired
    private ModelMapper modelMapper;

    public ContentByAgePolicyRule
    convertToEntity(ContentByAgePolicyRuleDAO dao) {

      PropertyMap<ContentByAgePolicyRuleDAO,
              ContentByAgePolicyRule> prMap =
              new PropertyMap<ContentByAgePolicyRuleDAO, ContentByAgePolicyRule>() {
                protected void configure() {
                  map().setId(source.getId());
                  map().setStartAge(source.getStartAge());
                  map().setEndAge(source.getEndAge());
                  map().setContentSet(source.getContentSet());
                }
              };

      TypeMap<ContentByAgePolicyRuleDAO, ContentByAgePolicyRule> typeMap
              = modelMapper.getTypeMap(ContentByAgePolicyRuleDAO.class, ContentByAgePolicyRule.class);
      if (typeMap == null) {
        modelMapper.addMappings(prMap);
      }

      return modelMapper.map(dao, ContentByAgePolicyRule.class);

    }

    public ContentByAgePolicyRuleDAO
    convertToDAO(ContentByAgePolicyRule policyRule) {

      PropertyMap<ContentByAgePolicyRule,
              ContentByAgePolicyRuleDAO> prMap =
              new PropertyMap<ContentByAgePolicyRule,
                      ContentByAgePolicyRuleDAO>() {
                protected void configure() {
                  map().setId(source.getId());
                  map().setStartAge(source.getStartAge());
                  map().setEndAge(source.getEndAge());
                  map().setContentSet(source.getContentSet());
                }
              };

      TypeMap<ContentByAgePolicyRule, ContentByAgePolicyRuleDAO> typeMap
              = modelMapper.getTypeMap(ContentByAgePolicyRule.class, ContentByAgePolicyRuleDAO.class);
      if (typeMap == null) {
        modelMapper.addMappings(prMap);
      }

      return modelMapper.map(policyRule, ContentByAgePolicyRuleDAO.class);
    }

    public TowerCarrierPlatformPolicyRule
    convertTowerContentPlatformToEntity(TowerCarrierPlatformPolicyRuleDAO dao) {

      PropertyMap<TowerCarrierPlatformPolicyRuleDAO,
              TowerCarrierPlatformPolicyRule> prMap =
              new PropertyMap<TowerCarrierPlatformPolicyRuleDAO, TowerCarrierPlatformPolicyRule>() {
                protected void configure() {
                  map().setTowerName(source.getKeyDAO().getTowerName());
                  map().setCarrierName(source.getKeyDAO().getCarrierName());
                  map().setPlatformName(source.getKeyDAO().getPlatformName());
                  map().setActive(source.isActive());
                }
              };

      TypeMap<TowerCarrierPlatformPolicyRuleDAO, TowerCarrierPlatformPolicyRule> typeMap
              = modelMapper.getTypeMap(TowerCarrierPlatformPolicyRuleDAO.class, TowerCarrierPlatformPolicyRule.class);
      if (typeMap == null) {
        modelMapper.addMappings(prMap);
      }

      return modelMapper.map(dao, TowerCarrierPlatformPolicyRule.class);

    }

    public TowerCarrierPlatformPolicyRuleDAO
    convertTowerCarrierPlatformToDAO(TowerCarrierPlatformPolicyRule policyRule) {

      PropertyMap<TowerCarrierPlatformPolicyRule,
              TowerCarrierPlatformPolicyRuleDAO> prMap =
              new PropertyMap<TowerCarrierPlatformPolicyRule,
                      TowerCarrierPlatformPolicyRuleDAO>() {
                protected void configure() {
                  map().getKeyDAO().setTowerName(source.getTowerName());
                  map().getKeyDAO().setCarrierName(source.getCarrierName());
                  map().getKeyDAO().setPlatformName(source.getPlatformName());
                  map().setActive(source.getActive());
                }
              };

      TypeMap<TowerCarrierPlatformPolicyRule, TowerCarrierPlatformPolicyRuleDAO> typeMap
              = modelMapper.getTypeMap(TowerCarrierPlatformPolicyRule.class, TowerCarrierPlatformPolicyRuleDAO.class);
      if (typeMap == null) {
        modelMapper.addMappings(prMap);
      }

      return modelMapper.map(policyRule, TowerCarrierPlatformPolicyRuleDAO.class);
    }

}
