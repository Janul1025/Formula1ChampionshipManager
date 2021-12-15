import java.util.Comparator;

public class DateComparator implements Comparator<Race> {
    @Override
    public int compare(Race o1, Race o2) { //compare o1 and o2 difference
        if(o1.getDate().compareTo(o2.getDate()) == 0) {
            return 0;
        }else if(o1.getDate().compareTo(o2.getDate()) > 0) {
            return 1;
        }else {
            return -1;
        }
    }
}
