import java.lang.reflect.Array;
import java.util.ArrayList;

public class Route {
    
    ArrayList<City> routeCities = new ArrayList<City>();
    int totalDistance;

    //default Konstruktor
    private Route() {
    }

    //copy Konstruktor
    private Route (Route route){ 
        this.routeCities = new ArrayList<City>(route.routeCities);
        this.totalDistance = route.totalDistance;
    }

    
    public void appendCity(City city, Connection connection) {
        if (connection != null){
            totalDistance += connection.getDistanceInKm(routeCities.get(routeCities.size() - 1), city);
        }
        if (city == null){
            System.out.println("city ist null in append city");
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
         //city und connection hinzufügen
        currentRoute.appendCity(currentCity, connection);

        if (currentCity.equals(destination)){
            allPossibleRoutes.add(currentRoute);
            //beendet void methoden
            return; 
        }
        
        ArrayList<Connection> possibleNextCities = new ArrayList<Connection>(currentCity.getConnections()); 
            
        //for (int i=0; i< possibleNextCities.size(); i++){
        for (Connection ati : possibleNextCities){
            //jeweils andere city finden und überprüfen ob route schon in der stadt war
            //Connection ati = possibleNextCities.get(i);
            City otherCity = ati.getOtherCity(currentCity);
               
            if (!currentRoute.containsCity(otherCity)){
                //mit copy Konstruktor in neue Route duplizierne
                Route continuedRoute = new Route(currentRoute);
                System.out.println(continuedRoute.toString());
                //rekursiver Aufruf
                addAllRoutes(allPossibleRoutes, continuedRoute, otherCity, destination, ati);
            }
                
        }
    
    }

    //public static getShortestRoute
    public static Route getShortestRoute(City origin, City destination){
       
        //leere Route anlegen und über addAllRoutes alle möglichen Routen in arraylist hinzufügen
        Route neueRoute = new Route(); 
        ArrayList<Route> allPossibleRoutes = new ArrayList<Route>(); 
        addAllRoutes(allPossibleRoutes, neueRoute,origin, destination,null);
        
        // Prüfen, ob keine Routen gefunden wurden
        if (allPossibleRoutes.isEmpty()) {
            System.out.println("Keine Route gefunden");
            return null;
        }
        
        ArrayList<Route> routesOrderedByDistance = new ArrayList<Route>(); 
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
        System.out.println("in for schleife");
        System.out.println(routesOrderedByDistance.get(i));
    }
    
    if (routesOrderedByDistance.isEmpty()) {
        System.out.println("Keine Route gefundenunten");
        return null;
    } else {
        return routesOrderedByDistance.get(0);
    }
}

}
   


