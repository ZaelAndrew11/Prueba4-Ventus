package cl.aguzman.prueba4.models;

public class Countries {
    private String name, capital, region, subregion;
    private int population;
    private Float[] latlng;

    public Countries() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Float[] getLatlng() {
        return latlng;
    }

    public void setLatlng(Float[] latlng) {
        this.latlng = latlng;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
