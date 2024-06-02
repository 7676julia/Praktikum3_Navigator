import java.lang.reflect.Array;
import java.util.ArrayList;

public class Route {
    
    ArrayList<City> routeCities = new ArrayList<City>();
    int totalDistance;

    //default Konstruktor
    private Route() {
    }
    
    //copy Konstruktor
    private Route (ArrayList<City> routeCities, int totalDistance) {
        ArrayList<City> routeCitiesCopy = new ArrayList<City>(routeCities);
        this.totalDistance = totalDistance;
    }

    public void appendCity(City city, Connection connection) {
        if (connection != null){
            totalDistance += connection.getDistanceInKm(routeCities.getLast(), city);
        }
        routeCities.add(city);
    }

    public boolean containsCity(City city){
        return routeCities.contains(city);
    }

    //Richtig?
    @Override
    public String toString(){
        return routeCities.toString() + " Distanz:" + totalDistance;
    }


    private static void addAllRoutes (ArrayList<Route> allPossibleRoutes, Route currentRoute, City currentCity, City destination, Connection connection){
        currentRoute.appendCity(currentCity, connection);
        if (currentCity == destination){
            allPossibleRoutes.add(currentRoute);
            //beendet void methoden
            return; 
        }
        else {
            ArrayList<Connection> possibleNextCities = new ArrayList<>(currentCity.getConnections()); 
            for (int j=0; j < possibleNextCities.size(); j++){
                //jeweils andere city finden und überprüfen ob route schon in der stadt war
                if (currentRoute.containsCity(connection.getOtherCity(currentCity))){
                    //eigene Ergänzung
                    System.out.println("Route enthält Stadt bereits");
                    break;
                }
                else{
                    Route continuedRoute = new Route(currentRoute.routeCities, currentRoute.totalDistance);
                    //rekursiver Aufruf
                    addAllRoutes(allPossibleRoutes, continuedRoute, connection.getOtherCity(currentCity), destination, possibleNextCities.get(j));
                }
            }
        }
    }

    //public static getShortestRoute
    public static Route getShortestRoute(City origin, City destination){
        //leere Route anlegen und über addAllRoutes alle möglichen Routen in arraylist hinzufügen
        Route neueRoute = new Route(); 
        ArrayList<Route> allPossibleRoutes = new ArrayList(); 
        addAllRoutes(allPossibleRoutes, neueRoute,origin, destination,null);
        ArrayList<Route> routesOrderedByDistance = new ArrayList(); 
        //Algorithmus der alle Routen aus allPossibleRoutes in routesOrderedByDistance sortiert
        for (int i = 0; i<allPossibleRoutes.size(); i++){
            //Hilfsvariable allpossibleRoutes
            Route tempi = allPossibleRoutes.get(i);
            int j;
            for (j=0; j<routesOrderedByDistance.size();j++){
                //Hilfsvariable routesOrderedByDistance
                Route tempj = routesOrderedByDistance.get(j);
                if (tempi.totalDistance < tempj.totalDistance){
                    break;
                }
            }
            routesOrderedByDistance.add(j, tempi);
        }
    for (int i = 0; i<routesOrderedByDistance.size(); i++){
        System.out.println(routesOrderedByDistance.get(i));
    }
    return routesOrderedByDistance.get(0);
}
   

}
