public class Weather {
    private Double temperature;
    private String description;

    public Weather(Double temperature, String description) {
        this.temperature = temperature;
        this.description = description;
    }

    public Double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
