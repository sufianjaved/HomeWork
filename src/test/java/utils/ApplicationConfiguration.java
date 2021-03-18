package utils;

public class ApplicationConfiguration {

    private static String hostBaseURL = DefaultConfiguration.getProperty("host");
    private static String localURL = DefaultConfiguration.getProperty("local");


    public static String getHostBaseUrl() {
        return hostBaseURL;
    }

    public static String getLocalhostBaseUrl() {
        return localURL;
    }
}
