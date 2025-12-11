package forward.growth.weatherForcaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherForcasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForcasterApplication.class, args);
	}

}
