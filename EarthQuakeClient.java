import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData) {
            if (quake.getMagnitude() > magMin) {
                answer.add(quake);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData) {
            if (quake.getLocation().distanceTo(from) < distMax) {
                answer.add(quake);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry quake : quakeData) {
            double depth = quake.getDepth();
            if ( depth > minDepth && depth < maxDepth) {
                answer.add(quake);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry quake : quakeData) {
            String title = quake.getInfo();
            if (where.equals("any") && title.contains(phrase)) {
                answer.add(quake);
            } else if (where.equals("start") && title.substring(0, title.length()/2).contains(phrase)) {
                answer.add(quake);
            } else if (where.equals("end") && title.substring(title.length()/2).contains(phrase)) {
                answer.add(quake);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> results = filterByMagnitude(list, 5.0);
        for (QuakeEntry result : results) {
            System.out.println(result);
        }
        System.out.println("Found "+results.size()+" quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> quakes = filterByDistanceFrom(list,1000,city);
        for (QuakeEntry quake : quakes) {
            System.out.println(quake.getLocation().distanceTo(city)+" "+quake.getInfo());
        }
        System.out.println("Found "+quakes.size()+" quakes that match that criteria");

    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> quakes = filterByDepth(list, -10000, -5000);
        for (QuakeEntry quake : quakes) {
            System.out.println(quake);
        }
        System.out.println("Found "+quakes.size()+" quakes that match that criteria");
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> quakes = filterByPhrase(list, "end", "California");
        //ArrayList<QuakeEntry> quakes = filterByPhrase(list, "any", "Can"); //3
        //ArrayList<QuakeEntry> quakes = filterByPhrase(list, "start", "Explosion"); //2
        for (QuakeEntry quake : quakes) {
            System.out.println(quake);
        }
        System.out.println("Found "+quakes.size()+" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
