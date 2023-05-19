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


    public Reading(int code, double temp, double windSpeed, double windDirection, int pressure) {
        this.code = code;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }

    /** Calculation to convert temp celcius to fahrenheit and dispaly the output which is then called in the view Tag
     * latestreadings.html
     *
     * @param celcius
     * @return
     */
    public double convertToFahrenheit(double celcius) {
        return (celcius * 1.8) + 32;
    }

    public double displayFahrenheit() {
        double celcius = temp;
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

    // Used chat Gpt to see if there was a way to calculate the table for direction rather than writing a long if else statement
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

    public double calculateWindChill(double temp, double windSpeed) {
        double windChill = 13.12 + 0.6215 * temp - 11.37 * Math.pow(windSpeed, 0.16) + 0.3965 * temp * Math.pow(windSpeed, 0.16);
        return Math.round(windChill * 100.0) / 100.0;
    }

    public double displayWindChill() {
        return calculateWindChill(temp,windSpeed);
    }


}
