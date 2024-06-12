public class Connection {
    
    City city1;
    City city2;
    private final double distanceInKm;
    
    //Konstruktor (klappt das?)
    public Connection (City city1, City city2) {
        this.city1 = city1;
        this.city2 = city2;
        this.distanceInKm = getDistanceInKm(city1, city2);
    }

    double getDistanceInKm ( City stadt1 , City stadt2 ) {
        //Latitud und Longitud Koordinaten der Städte holen
        double lat1 = stadt1.getLatitude ();
        double lon1 = stadt1.getLongitude ();
        double lat2 = stadt2.getLatitude ();
        double lon2 = stadt2.getLongitude ();
        //variable für den Erdradius
        double earthRadiusKm = 6371;
        //Differenz der Latitud und Longitud Koordinaten berechnen in Winkel im Bogenmaß  (degrees to radians Methode s.u.)
        double  dLat = degreesToRadians(lat2-lat1 ); 
        double dLon = degreesToRadians(lon2-lon1);
        lat1 = degreesToRadians(lat1); 
        lat2 = degreesToRadians(lat2);
        //Haversine Formel kürzeste Entfernung zwischen zwei Punkten auf der Oberfläche einer Kugel, 
        //wie zum Beispiel der Erde, zu berechnen, basierend auf ihren Längen- und Breitengraden
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        //atan = Arkustangens (auch inverse Tangens genannt)
        double c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a)); 
        return earthRadiusKm * c;
        }   
        
        //degrees to radians Methode
        private double degreesToRadians (double degrees ) { 
            return degrees * Math.PI / 180;
        }

        public City getOtherCity (City city) {
            //Wenn die Stadt1 die gleiche Stadt ist wie die übergebene Stadt, dann gib Stadt2 zurück
            if (city.equals(city1)) {
                return city2;
            }
            //Ansonsten gib Stadt1 zurück
            else {
                return city1;
            }
        }
        
}
