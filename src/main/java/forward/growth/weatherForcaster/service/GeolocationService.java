package forward.growth.weatherForcaster.service;

import forward.growth.weatherForcaster.dto.WeatherForcastRequest;
import forward.growth.weatherForcaster.dto.WeatherForcastResponse;
import forward.growth.weatherForcaster.entity.GeoResults;
import forward.growth.weatherForcaster.entity.Geolocation;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class GeolocationService {
    private  WeatherForcastService weatherfortcastservice;
    private WebClient webclient;

    public GeolocationService(WeatherForcastService weatherfortcastservice,WebClient webclient) {
        this.weatherfortcastservice = weatherfortcastservice;
        this.webclient=webclient;
    }


    public Mono<WeatherForcastResponse> findCoordinates(WeatherForcastRequest wfreq) {

        return webclient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search")
                        .queryParam("name", wfreq.getCity().trim())
                        .queryParam("count", 1)
                        .build())
                .retrieve()
                .bodyToMono(GeoResults.class)
                .flatMap(georesults -> {
                    System.out.println(georesults.getResults().get(0).getLatitude());
                    return weatherfortcastservice.getWeatherdetails(georesults,wfreq.getCity(),wfreq.getCountry());
                });
    }



}
