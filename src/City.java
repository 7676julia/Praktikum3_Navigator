import java.util.ArrayList;

public class City {
    
    String stadtname; 
    double latitudKoordinate;
    double longitudKoordinate;
    ArrayList<Connection> connections = new ArrayList<Connection>();


    //Konstruktor
    public City(String stadtname, double latitudKoordinate, double longitudKoordinate) {
        this.stadtname = stadtname;
        this.latitudKoordinate = latitudKoordinate;
        this.longitudKoordinate = longitudKoordinate;
    }
    
    public void addConnection (City cityToConnect){
        if (this.stadtname == cityToConnect.stadtname){
           System.out.println("Eine Stadt kann nicht mit sich selbst verknüpft werden");
        }
        else {
            connections.add(new Connection(this, cityToConnect));
        }
    }

    public String getRouteToCity (City destination){
        return null; 
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

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    
}
