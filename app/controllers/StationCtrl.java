package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;
//import utils.StationAnalytics;
import java.util.HashMap;

import java.time.LocalDateTime;

public class StationCtrl extends Controller
{

    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        render("station.html", station);
    }


    public static void deleteReading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing " + readingid);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/stations/" + id);
    }

    public static void addreading(Long id, int code, double temp, double windSpeed, double windDirection, int pressure)
    {
        Reading reading = new Reading(code, temp, windSpeed, windDirection, pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }


}

