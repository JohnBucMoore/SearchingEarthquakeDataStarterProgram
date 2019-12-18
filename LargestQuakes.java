import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        for (QuakeEntry quake : list) {
            System.out.println(quake);
        }
        System.out.println("read data for "+list.size());

    }

    public void test() {
        findLargestQuakes();
    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.test();
    }
}
