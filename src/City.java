public class City {
    String stadtname; 
    double latitudKoordinate;
    double longitudKoordinate;


    //Konstruktor
    public City(String stadtname, double latitudKoordinate, double longitudKoordinate) {
        this.stadtname = stadtname;
        this.latitudKoordinate = latitudKoordinate;
        this.longitudKoordinate = longitudKoordinate;
    }
    
    //to String Methode
    @Override
    public String toString() {
        return stadtname + " " + latitudKoordinate + " " + longitudKoordinate;
    }

    
    // Getter und Setter
    public String getStadtname() {
        return stadtname;
    }

    public void setStadtname(String stadtname) {
        this.stadtname = stadtname;
    }

    public double getLatitude() {
        return latitudKoordinate;
    }

    public void setLatitudKoordinate(int latitudKoordinate) {
        this.latitudKoordinate = latitudKoordinate;
    }

    public double getLongitude() {
        return longitudKoordinate;
    }

    public void setLongitudKoordinate(int longitudKoordinate) {
        this.longitudKoordinate = longitudKoordinate;
    }

    
}
