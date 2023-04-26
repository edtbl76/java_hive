package ServiceGrid;

import org.apache.ignite.services.ServiceContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherServiceImpl implements WeatherService {


    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?";

    /** Sample app ID. */
    private static final String appId = "ca7345b4a1ef8c037f7749c09fcbf808";

    /** {@inheritDoc} */
    @Override
    public void init(ServiceContext serviceContext) throws Exception {
        System.out.println("Weather Service is initialized!");
    }

    /** {@inheritDoc} */
    @Override
    public void execute(ServiceContext serviceContext) throws Exception {
        System.out.println("Weather service is started.");
    }

    /** {@inheritDoc} */
    @Override
    public void cancel(ServiceContext serviceContext) {
        System.out.println("Weather service is stopped");
    }

    @Override
    public String getCurrentTemperature(String countryCode, String cityName) throws Exception {
        System.out.println(">>> Requested weather forecast [=" + cityName + ",countryCode=" + countryCode + "]");

        String connStr = WEATHER_URL + "q=" + cityName + "," + countryCode + "&appid=" + appId;

        URL url = new URL(connStr);
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            )) {
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                return builder.toString();
            }
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
