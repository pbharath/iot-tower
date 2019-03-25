package bp.demo.iot.tower.manager;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "bp.demo.iot.tower.manager",
        "bp.demo.iot.tower.kafka"})
public class TowerManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TowerManagerApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper(){
    return  new ModelMapper();
  }

}
