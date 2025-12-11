package forward.growth.weatherForcaster.entity;

import java.util.List;

public class GeoResults {
   public List<Geolocation> getResults() {
      return results;
   }

   public void setResults(List<Geolocation> results) {
      this.results = results;
   }

   private List<Geolocation> results;
}
