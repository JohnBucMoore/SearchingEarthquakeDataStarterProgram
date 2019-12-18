import java.util.*;
public class LargestQuakes {
    public ArrayList<QuakeEntry> getData() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        return list;
    }

    public void findLargestQuakes() {
        ArrayList<QuakeEntry> list = getData();
        /*
        for (QuakeEntry quake : list) {
            System.out.println(quake);
        }
         */
        System.out.println("read data for "+list.size());

    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int dex = 0;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getMagnitude() > data.get(dex).getMagnitude()) {
                dex = i;
            }
        }
        return dex;
    }

    public void test() {
        findLargestQuakes();
        //System.out.println(indexOfLargest(getData()));
    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.test();
    }
}
