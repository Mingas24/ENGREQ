package com.backend_happibee.model.valueObjects;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Localizacao implements Serializable {
    double lat;
    double longi;

    String freg;

    boolean zonaProtegida;

    public boolean isZonaProtegida() {
        return zonaProtegida;
    }

    public void setZonaProtegida(boolean zonaProtegida) {
        this.zonaProtegida = zonaProtegida;
    }

    public Localizacao(double lat, double longi, String freg , boolean zonaProtegida){
        this.lat = lat;
        this.freg = freg;
        this.longi = longi;
        this.zonaProtegida = zonaProtegida;
    }

    public Localizacao() {

    }

    @Override
    public String toString() {
        return "lat=" + lat +" "+ "longi=" + longi;
    }

    public void setFreg(String freg) {
        this.freg = freg;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public String getFreg() {
        return freg;
    }


}
