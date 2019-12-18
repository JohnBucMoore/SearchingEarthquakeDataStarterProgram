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
        ArrayList<QuakeEntry> quakeData = getLargest(list, 5);
        for (QuakeEntry quake : quakeData) {
            System.out.println(quake);
        }
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

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> largest = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            int index = indexOfLargest(copy);
            largest.add(copy.get(index));
            copy.remove(copy.get(index));
        }
        return largest;
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
