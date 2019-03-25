package bp.demo.iot.tower.manager.controller;

import bp.demo.iot.tower.manager.repository.exception.TowerManagerException;
import bp.demo.iot.tower.manager.service.DeviceUserService;
import bp.demo.iot.tower.model.DeviceUser;
import bp.demo.iot.tower.model.UserContentActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private Logger logger =
          LoggerFactory.getLogger(UserController.class);

  @Autowired
  private DeviceUserService deviceUserService;

  @PostMapping("/device/user")
  public ResponseEntity processNewDeviceUser(@RequestBody DeviceUser deviceUser) {

    try {

    deviceUserService.processNewDeviceUser(deviceUser);
      logger.info("Device User " + deviceUser + " captured");
      return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    catch(TowerManagerException tcpe) {
      logger.error("Device user " + deviceUser + " could not be processed");

        return new ResponseEntity(tcpe.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/device/user/activity")
  public ResponseEntity processUserContentActivity(@RequestBody UserContentActivity userContentActivity) {
    try {
      if(deviceUserService.processUserContentAccess(userContentActivity)) {
        logger.info("Device User " + userContentActivity + " captured");
        return new ResponseEntity(HttpStatus.ACCEPTED);
      }
      else
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
    catch (TowerManagerException tme) {
      logger.error(tme.getMessage());
      return new ResponseEntity(tme.getMessage(), HttpStatus.FORBIDDEN);
    }
  }

}
