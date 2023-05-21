package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
import models.Reading;

/**
 * Updated original playlist lab to work with assignment set up for Station and readings including
 * the longitude and latitude from the yml file
 * plus the latest readings which was taken from a post by KGarvey to check for readings and
 * call the last entry in the array
 */

@Entity
public class Station extends Model {
    public String title;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    public Long code;
    public double latitude;
    public double longitude;

    public Station(String title, double latitude, double longitude) {
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public List<Reading> latestReading() {
        List<Reading> currentReading = null;
        if (readings.size() > 0) {
            currentReading = readings.subList(readings.size() - 1, readings.size());
        }

        return currentReading;
    }

}

