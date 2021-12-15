import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Race implements Serializable {  //create race class

    private Date date;
    private  int participants;
    private  String [][] driverDetails;
    private int x=0;
    private ArrayList drivers;

    public Race(Date date, ArrayList<Formula1Driver> drivers) {  //race constructor
        this.date = date;
        this.drivers = drivers;
    }

    public void setDriverDetails(String name, String position) { //set values for the drivers details
        this.driverDetails [x][0] = name;
        this.driverDetails [x][1] = position;
        x++;
    }

    public void setParticipants(int participants) { //set participants
        this.participants = participants;
    }

    public ArrayList getDrivers() {
        return drivers;
    }

    public void setDriver(ArrayList drivers) {
        this.drivers = drivers;
    }

    public int getParticipants() {
        return participants;
    }

    public void setDate(Date date) { //set date
        this.date = date;
    }

    public String[][] getDriverDetails() {  //get drivers details

        return driverDetails;
    }

    public Date getDate() {  //get date

        return date;
    }


    public void setParticipants() {
        this.participants++;
    }

//    public void setDriverDetails(String[][] driverDetails) {
//        this.driverDetails = driverDetails;
//    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Race(Date date, int x){
        this.date=date;
        this.driverDetails= new String[x][2];


    }

    public String toString(){
        return "Date: "+getDate();
    }

    public void setParticipatingDrivers() {
    }
}
