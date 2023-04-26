package ServiceGrid;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class SGExample {

    public static void main(String[] args) {
        try (Ignite ignite = Ignition.start()) {

            ignite.services().deployClusterSingleton("WeatherService", new WeatherServiceImpl());

            WeatherService service = ignite.services().service("WeatherService");

            String forecast = service.getCurrentTemperature("Boston", "US");
            System.out.println("Forecast in Boston " + forecast);

            forecast = service.getCurrentTemperature("Seattle", "US");
            System.out.println("Forecast In Seattle " + forecast);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
