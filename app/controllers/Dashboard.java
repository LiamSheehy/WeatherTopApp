package controllers;

import java.util.List;

import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Admin");

    List<Station> stations = Station.findAll();
    render("dashboard.html", stations);
  }

  public static void deleteStation(Long id) {
    Station station = Station.findById(id);
    Logger.info("Removing" + station.title);
    station.delete();
    redirect("/dashboard");
  }

  public static void addStation(String title, double latitude, double longitude) {
    Station station = new Station(title, latitude, longitude);
    Logger.info("Adding a new station called " + title);
    station.save();
    redirect("/dashboard");
  }
}

