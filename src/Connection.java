public class Connection {
    public Connection (City city1, City city2) {
       
    }

    double getDistanceInKm ( City stadt1 , City stadt2 ) {
        double lat1 = stadt1.getLatitude ();
        double lon1 = stadt1.getLongitude ();
        double lat2 = stadt2.getLatitude ();
        double lon2 = stadt2.getLongitude ();
        double earthRadiusKm = 6371;
        double  dLat = degreesToRadians(lat2-lat1 ); 
        double dLon = degreesToRadians(lon2-lon1);
        lat1 = degreesToRadians(lat1); 
        lat2 = degreesToRadians(lat2);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a)); 
        return earthRadiusKm * c;
        }   
        private double degreesToRadians (double degrees ) { 
            return degrees * Math.PI / 180;
        }
        
}
