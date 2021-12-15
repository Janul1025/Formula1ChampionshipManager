import java.io.Serializable;

public abstract class Driver implements Serializable {

    private String name;
    private String location;
    private String team;





    public Driver(String name, String location, String team){  //create driver constructor
        this.name=name;
        this.location=location;
        this.team=team;

    }

    public abstract int calcpoints(int position); //create abstract class for calc points

    public String getName() { //get name of the driver
        return name;
    }

    public String getLocation() { //get location of the driver

        return location;
    }

    public String getTeam() { //create team for driver
        return team;
    }



    public void setName(String name) {

        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTeam(String team) {
        this.team = team;
    }


    public abstract int setNumberofracecparticipated();  //set number of parisipated for the race
}
