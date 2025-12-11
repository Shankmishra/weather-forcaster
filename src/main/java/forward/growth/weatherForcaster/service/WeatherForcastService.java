package forward.growth.weatherForcaster.service;

import forward.growth.weatherForcaster.dto.WeatherForcastResponse;
import forward.growth.weatherForcaster.entity.GeoResults;
import forward.growth.weatherForcaster.entity.Geolocation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class WeatherForcastService {

    private WebClient webclient;

    public WeatherForcastService(WebClient webclient) {
        this.webclient = webclient;
    }

    @Cacheable(value = "Weather", key = "#geoResults.getResults().get(0).getLatitude() + '_' + #geoResults.getResults().get(0).getLongitude()")
    public Mono<WeatherForcastResponse> getWeatherdetails(GeoResults geoResults,String city,String country) {

        return webclient.get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&daily=temperature_2m_max,temperature_2m_min",
                        geoResults.getResults().get(0).getLatitude(),
                        geoResults.getResults().get(0).getLongitude())
                .retrieve()
                .bodyToMono(WeatherForcastResponse.class)
                .map(r->{
                    r.setCity(city);
                    r.setCountry(country);
                    return r;
                })
                .doOnNext(resp -> System.out.println("----Called---"));
    }

}
