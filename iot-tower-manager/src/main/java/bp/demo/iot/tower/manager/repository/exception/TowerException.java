package bp.demo.iot.tower.manager.repository.exception;

public interface TowerException {

  public static final String TOWER_CARRIER_PLATFORM_SERVICE_DENIAL_EXCEPTION
          = "Tower Carrier Platform Not Supported";

  public static final String REGISTRATION_EXISTS_FOR_USER_EXCEPTION
          = "Registration exists for user";

  public static final String USER_REGISTRATION_INACTIVE
          = "Registration for user inactive";

  public static final String USER_NOT_FOUND_CONFLICT
          = "User not found";

  public static final String CONTENT_ACCESS_DENIAL_POLICY_RULE
          = "Content access denied due to age restriction policy rule";
}
