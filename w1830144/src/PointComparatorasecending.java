import java.util.Comparator;
import java.util.function.Function;

public class PointComparatorasecending implements Comparator<Formula1Driver> {



        public int compare(Formula1Driver s1,Formula1Driver s2){
            if(s1.getNumberofpoints()==s2.getNumberofpoints()){
                if(s1.getFirstposition()>s2.getFirstposition()) {
                    return 1;
                }else
                    return -1;

            }

            else if(s1.getNumberofpoints()>s2.getNumberofpoints())
                return 1;
            else                 //check ascending order or descending order
                return -1;
        }

    @Override
    public Comparator<Formula1Driver> reversed() {

            return Comparator.super.reversed();
    }



}


