package forward.growth.weatherForcaster.controller;

import forward.growth.weatherForcaster.dto.WeatherForcastRequest;
import forward.growth.weatherForcaster.dto.WeatherForcastResponse;
import forward.growth.weatherForcaster.service.GeolocationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
@RestController
public class WeatherApiController {

    private GeolocationService geolocation;

    public WeatherApiController(GeolocationService geolocation){
      this.geolocation = geolocation;
    }

    @RequestMapping("/weather")
    @PostMapping
    public Mono<WeatherForcastResponse> geolocator(@RequestBody WeatherForcastRequest wfr) {
        System.out.println(wfr.getCountry());
        return geolocation.findCoordinates(wfr);   // now correct: returns Mono
    }

}
