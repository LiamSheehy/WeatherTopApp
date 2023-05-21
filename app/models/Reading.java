package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Reading extends Model {
    public int code;
    public double temp;
    public double windSpeed;
    public double windDirection;
    public int pressure;
    public String weatherCode;
    public int beaufort;

    /**
     * Constructor method for the above Reading class
     * @param code
     * @param temp
     * @param windSpeed
     * @param windDirection
     * @param pressure
     */
    public Reading(int code, double temp, double windSpeed, double windDirection, int pressure) {
        this.code = code;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }

    /** Calculation to convert temp celcius to fahrenheit and display the output which is then called in the view Tag
     * latestreadings.html
     *
     * @param celcius
     * @return
     */
    public double convertToFahrenheit(double celcius) {
        return (celcius * 1.8) + 32;
    }

    public double displayFahrenheit() {
        double celcius = Math.round(temp * 100.0) / 100.0;
        return convertToFahrenheit(celcius);
    }


    /** Calculation to convert param code to string output representation of the conditions and display the output which is then called in the view Tag
     * latestreadings.html
     *
     * @param code
     * @return
     */
    public String convertToWeathercode(int code) {
        if (code == 100) {
            weatherCode = "Clear";
        } else if (code == 200) {
            weatherCode = "Partial clouds";
        } else if (code == 300) {
            weatherCode = "Cloudy";
        } else if (code == 400) {
            weatherCode = "Light Showers";
        } else if (code == 500) {
            weatherCode = "Heavy Showers";
        } else if (code == 600) {
            weatherCode = "Rain";
        } else if (code == 700) {
            weatherCode = "Snow";
        } else if (code == 800) {
            weatherCode = "Thunder";
        } else {
            weatherCode = "Unknown";
        }

        return weatherCode;
    }

    public String displayWeatherCode() {
        int number = code;
        String convertToWeathercode = convertToWeathercode(number);
        return convertToWeathercode;
    }

    /** Calculation to convert param windSpeed to beaufort scale output and display the output which is then called in the view Tag
     * latestreadings.html
     * @param windSpeed
     * @return
     */
    public int convertToBeaufort(double windSpeed) {
        if (windSpeed == 1) {
            beaufort = 0;
        } else if (windSpeed > 1 && windSpeed <= 5) {
            beaufort = 1;
        } else if (windSpeed >= 6 && windSpeed <= 11) {
            beaufort = 2;
        } else if (windSpeed >= 12 && windSpeed <= 19) {
            beaufort = 3;
        } else if (windSpeed >= 20 && windSpeed <= 28) {
            beaufort = 4;
        } else if (windSpeed >= 29 && windSpeed <= 38) {
            beaufort = 5;
        } else if (windSpeed >= 39 && windSpeed <= 49) {
            beaufort = 6;
        } else if (windSpeed >= 50 && windSpeed <= 61) {
            beaufort = 7;
        } else if (windSpeed >= 62 && windSpeed <= 74) {
            beaufort = 8;
        } else if (windSpeed >= 75 && windSpeed <= 88) {
            beaufort = 9;
        } else if (windSpeed >= 89 && windSpeed <= 102) {
            beaufort = 10;
        } else if (windSpeed >= 103 && windSpeed <= 117) {
            beaufort = 11;

        } else {
            beaufort = 0;
        }

        return beaufort;
    }

    public int displayBeaufort() {
        double beaufortScale = windSpeed;
        int beaufort1 = convertToBeaufort(beaufortScale);
        return beaufort1;
    }

/** Calculation to convert param windDirection to output compass direction and display the output which is then called
 *  the view Tag latestreadings.html
 * I was going to copy the Beaufort type code but knew it could be calculated by breaking up the 16 points and dividing
 * it to represent the direction but I didnt know how to array it so i Used chat Gpt to see if there was a way to write
 * it other than writing a long if else statement
 *  @param windDirection
 * @return
 */
    public static String convertToCompassDirection(double windDirection) {
        String[] directions = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
        int index = (int) ((windDirection / 22.5) + 0.5);
        return directions[index % 16];
    }

    public String displayCompassDirection() {
        double number = windDirection;
        String compassDirections = convertToCompassDirection(number);
        return compassDirections;
    }
/** Calculation to convert param temp & windSpeed to output windchill and display the output which is then called
 *  the view Tag latestreadings.html
 * @ param temp windSpeed
 * @return
 * */
    public double calculateWindChill(double temp, double windSpeed) {
        double windChill = 13.12 + 0.6215 * temp - 11.37 * Math.pow(windSpeed, 0.16) + 0.3965 * temp * Math.pow(windSpeed, 0.16);
        return Math.round(windChill * 100.0) / 100.0;
    }

    public double displayWindChill() {
        return calculateWindChill(temp,windSpeed);
    }


}
