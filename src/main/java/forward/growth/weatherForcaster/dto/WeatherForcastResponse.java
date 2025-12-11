package forward.growth.weatherForcaster.dto;

import java.io.Serializable;

public class WeatherForcastResponse implements Serializable {
    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    private static final long serialVersionUID = 1L;
    private String city;
    private String country;

    private Daily daily;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    private String timezone;
}
