import java.util.ArrayList;

public class Route {

    ArrayList<City> routeCities;
    double totalDistance;

    // Default Konstruktor
    private Route() {
        this.routeCities = new ArrayList<City>();
        this.totalDistance = 0;
    }

    // Copy Konstruktor
    private Route(Route route) {
        this.routeCities = new ArrayList<City>(route.routeCities);
        this.totalDistance = route.totalDistance;
    }

    public void appendCity(City city, Connection connection) {
        if (connection != null && !routeCities.isEmpty()) {
            totalDistance += connection.getDistanceInKm(routeCities.get(routeCities.size() - 2), city);
        }
        if (city == null) {
            System.out.println("City ist null in appendCity");
        } else {
            routeCities.add(city);
        }
    }

    public boolean containsCity(City city) {
        return routeCities.contains(city);
    }

    @Override
    public String toString() {
        return routeCities.toString() + " Distanz: " + totalDistance;
    }

    private static void addAllRoutes(ArrayList<Route> allPossibleRoutes, Route currentRoute, City currentCity, City destination, Connection connection) {
        currentRoute.appendCity(currentCity, connection);

        if (currentCity.equals(destination)) {
            Route finalRoute = new Route(currentRoute);
            allPossibleRoutes.add(finalRoute);
            System.out.println("Route gefunden: " + finalRoute);
            return;
        }

        ArrayList<Connection> possibleNextCities = new ArrayList<Connection>(currentCity.getConnections());

        for (Connection ati : possibleNextCities) {
            City otherCity = ati.getOtherCity(currentCity);

            if (!currentRoute.containsCity(otherCity)) {
                Route continuedRoute = new Route(currentRoute);
                System.out.println("Aktuelle Route: " + continuedRoute + " mit möglicher nächster Stadt: " + otherCity);
                addAllRoutes(allPossibleRoutes, continuedRoute, otherCity, destination, ati);
            } else {
                System.out.println("Stadt " + otherCity + " bereits besucht, überspringen.");
            }
        }
    }

    public static Route getShortestRoute(City origin, City destination) {
        Route neueRoute = new Route();
        ArrayList<Route> allPossibleRoutes = new ArrayList<Route>();
        addAllRoutes(allPossibleRoutes, neueRoute, origin, destination, null);

        System.out.println("Alle möglichen Routen:");
        for (Route route : allPossibleRoutes) {
            System.out.println(route);
        }

        ArrayList<Route> routesOrderedByDistance = new ArrayList<Route>();
        for (Route tempi : allPossibleRoutes) {
            int j;
            for (j = 0; j < routesOrderedByDistance.size(); j++) {
                Route tempj = routesOrderedByDistance.get(j);
                if (tempi.totalDistance < tempj.totalDistance) {
                    break;
                }
            }
            routesOrderedByDistance.add(j, tempi);
        }

        for (Route route : routesOrderedByDistance) {
            System.out.println("In for Schleife");
            System.out.println(route);
        }

        if (routesOrderedByDistance.isEmpty()) {
            System.out.println("Keine Route gefunden");
            return null;
        } else {
            return routesOrderedByDistance.get(0);
        }
    }
}