package ServiceGrid;

import org.apache.ignite.services.Service;

public interface WeatherService extends Service {

    String getCurrentTemperature(String countryCode, String cityName) throws Exception;
}
