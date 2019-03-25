package bp.demo.iot.tower.manager.service.helper;

import bp.demo.iot.tower.manager.repository.dao.RegisteredUserDAO;
import bp.demo.iot.tower.model.RegisteredUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceUserServiceHelper {

  @Autowired
  private ModelMapper modelMapper;

  public RegisteredUserDAO convertRegisteredUserDataToDAO(RegisteredUser registeredUser) {

    PropertyMap<RegisteredUser, RegisteredUserDAO> duMap =
            new PropertyMap<RegisteredUser, RegisteredUserDAO>() {
              protected void configure() {
                map().getKeyDAO().setCarrierName(source.getCarrierName());
                map().getKeyDAO().setEmail(source.getEmail());
                map().setTowerName(source.getTowerName());
                map().setUserId(source.getUserId());
                map().setDob(source.getDob());
                map().setActive(source.isActive());
                map().setTimeStamp(source.getTimeStamp());
              }
            };

    TypeMap<RegisteredUser, RegisteredUserDAO> typeMap
            = modelMapper.getTypeMap(RegisteredUser.class, RegisteredUserDAO.class);

    if (typeMap == null) {
      modelMapper.addMappings(duMap);
    }

    return modelMapper.map(registeredUser, RegisteredUserDAO.class);
  }
}
