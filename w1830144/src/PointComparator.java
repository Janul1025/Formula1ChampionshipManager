import java.util.Comparator;

public class PointComparator implements Comparator<Formula1Driver> {



    public int compare(Formula1Driver s1,Formula1Driver s2){  //compare s1 and s2 difference
        if(s1.getNumberofpoints()==s2.getNumberofpoints()){
            if(s1.getFirstposition()<s2.getFirstposition()) {
                return 1;
            }else
                return -1;

        }

        else if(s1.getNumberofpoints()<s2.getNumberofpoints()) //compare points difference
            return 1;
        else                 //check ascending order or descending order
            return -1;
    }


}
