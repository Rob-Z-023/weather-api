public class Coordinates{
    private final String logtidue, latidude;

    public Coordinates(String logtidue, String latidude) {
        this.logtidue = logtidue;
        this.latidude = latidude;
    }
    public String getLogtidue() {
        return logtidue;
    }
    public String getLatidude() {
        return latidude;
    }

    public String toString() {
        return "lat: " + latidude + "   lon: " + logtidue;
    }
}