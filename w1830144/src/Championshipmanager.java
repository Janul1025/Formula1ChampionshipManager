import java.io.IOException;
import java.text.ParseException;

public interface Championshipmanager  {

    public void createDriver() ;

    public void deleteDriver();


    public void changeDriver();

    public void statistics();

    public void table();
    public void addRace() throws ParseException;

    public void save();


    void load() throws IOException;
}
