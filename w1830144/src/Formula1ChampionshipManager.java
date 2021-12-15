import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public  class Formula1ChampionshipManager implements Championshipmanager {
    int position;

    Scanner input = new Scanner(System.in);

    public ArrayList<Formula1Driver> drivers = new ArrayList<>();
    public ArrayList<Race> races = new ArrayList<>();

    public ArrayList<Formula1Driver> getDrivers() { //get drivers from arraylist

        return drivers;
    }

//    public ArrayList<Race> getRaces() {  //get racec from arraylist
//
//        return races;
//    }

    @Override
    public void createDriver() {   //create driver for get drivers data


        System.out.println("Enter the driver's Name  : ");
        String name = input.next();
        System.out.println("Enter location  :");
        String location = input.next();
        System.out.println("Enter team   :");
        String team = input.next();
        boolean isUnique = true;
        for (Formula1Driver x : drivers) {
            if (x.getTeam().equals(team)) {
                isUnique = false;
            }
        }
        if (isUnique) {



            Formula1Driver f1 = new Formula1Driver(name, location, team);
            drivers.add(f1);


        }


    }

    @Override
    public void deleteDriver() {   //delete driver is unique

        System.out.println("Enter the drivers name");
        String name = input.next();
        System.out.println("Enter the team name");
        String team = input.next();
        Formula1Driver d1 = null;
        for (Formula1Driver x : drivers) {
            if (x.getName().equals(name) && x.getTeam().equals(team)) {

                d1 = x;
            }
        }
        drivers.remove(d1);
        System.out.println("Driver" + " " + name + "successfully removed..");
    }

    @Override
    public void changeDriver() {  //change driver methodd for change the driver frm the list

        System.out.println("Enter team name ");
        String team = input.next();
        boolean isName = true;
        for (Formula1Driver x : drivers) {
            if (x.getTeam().equals(team)) {
                System.out.println("Enter the name");
                String name = input.next();
                x.setName(name);
          }
        }
    }


    @Override
    public void statistics() {  //create statistic method to get statistics of the races

        System.out.println("Enter name");
        String name = input.next();


        for (Formula1Driver x : drivers) {
            if (x.getName().equals(name)) {
                System.out.println("Driver name  :" + x.getName() + " ");

                System.out.println("Team name :" + x.getTeam());
                System.out.println("Location : " + x.getLocation());
                System.out.println("First " + x.getFirstposition());
                System.out.println("Second "+ x.getSecondpositions());
                System.out.println("Third  "+ x.getThirdposition());

            }
        }
    }


    @Override
    public void table() {  //create table to view display point table

        Collections.sort(drivers, new PointComparator());
        System.out.println("-----------------------Drivers static table----------------------------");
        System.out.println("+--------------+------------+------------+----------------+-----------------+----------------+------------------+---------------------------+");
        System.out.println("| Drivers name |  Team name |   Location | First posotion | Second position |  Third position| Number of points | Numberofracecparticipated |");
        System.out.println("+--------------+------------+------------+----------------+-----------------+----------------+------------------+---------------------------+");



        for (Formula1Driver x : drivers) {

            String name = x.getName();
            String location = x.getLocation();
            String team = x.getTeam();

            int firstposition = x.getFirstposition();
            int secondpositions = x.getSecondpositions();
            int thirdposition = x.getThirdposition();
            int numberofpoints = x.calcpoints(position);
            int numberofracecparticipated = x.setNumberofracecparticipated();




//            System.out.println(name + "\t\t\t" + location + "\t\t\t\t\t\t\t" + team + "\t\t\t\t" + firstposition + "\t\t\t\t\t\t\t\t"
//                    + secondpositions + "\t\t\t\t\t" + thirdposition + "\t\t\t\t\t" + numberofpoints + "\t\t\t\t\t \t\t\t\t" + numberofracecparticipated);


            System.out.printf("| %13s" , "" +name);
            System.out.printf("| %11s" , "" +team);
            System.out.printf("| %11s" , "" +location);
            System.out.printf("| %15s" , "" +firstposition);
            System.out.printf("| %15s" , "" +secondpositions);
            System.out.printf("| %16s" , "" +thirdposition);
            System.out.printf("| %17s" , "" +numberofpoints);
            System.out.printf("| %22s" , "" +numberofracecparticipated+"|");

            System.out.println("+------------+------------+------------+----------------+----------------+----------------+------------------+---------------------------+");



        }
    }

    @Override
    public void addRace() throws ParseException {  //this method is add race method

        System.out.println("Enter date dd/MM/yyyy"); //get date


        String dates = input.next();
        SimpleDateFormat foemattee= new SimpleDateFormat("dd/MM/yyyy");
        Date date= foemattee.parse(dates);

        Race race = new Race(date,drivers.size());


        for (Formula1Driver x : drivers) {
            System.out.println("Is" + x.getName() + " present ");


            System.out.println("y/n");
            String y = input.next();


            if (y.equals("y")) {
                System.out.println("Position ");
                position = input.nextInt();

                x.setPosition(10);
                if (position == 1) {
                    x.setFirstposition();
                } else if (position == 2) {
                    x.setSecondpositions();
                } else if (position == 3) {
                    x.setThirdposition();
                    x.calcpoints(position);

                    race.setParticipants();
                    race.setDriverDetails(x.getName(),String.valueOf(position));


                }
            }
//            race.setDriverDetails(x.getName(), Integer.toString(position));
        }
        races.add(race);



    }

    @Override

    public void save() { //save all the data for this method
        try {

            File file = new File("test.txt");
            file.createNewFile();

            FileOutputStream f1 = new FileOutputStream(file);
            ObjectOutputStream obj = new ObjectOutputStream(f1);

            obj.writeObject(drivers);
            obj.writeObject(races);
            obj.close();
            f1.close();
        } catch (IOException ignored) {

        }

    }

    @Override
    public void load() throws IOException {   //load all the infor to this method
        FileInputStream file = new FileInputStream("test.txt");
        ObjectInputStream obj = new ObjectInputStream(file);

        for (int i = 0; i < 2; i++) {
            try {
                if (i == 0) {
                    drivers = (ArrayList<Formula1Driver>) obj.readObject();

                } else {
                    races = (ArrayList<Race>) obj.readObject();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws ParseException, IOException {  //this is main class. all the classes handle by main class

        Formula1ChampionshipManager f1 = new Formula1ChampionshipManager();  //create Formula1ChampionshipManager  object

        boolean exit = false;

        Scanner input = new Scanner(System.in); //scanner to get inputs

        while (!exit) {
            System.out.println("...............MENU..................");
            System.out.println("...... Select your Choise......");
            System.out.println();


            System.out.println("Press A to Create a driver ");
            System.out.println("Press B to Delete a driver");
            System.out.println("Press C to Change the driver for an existing constructor team");
            System.out.println("Press D  to Display various statictics a selecting driver");
            System.out.println("Press E Display point table");
            System.out.println("Press F to Add race to press");
            System.out.println("Press G to Save information ");
            System.out.println("Press H to Load infor");
            System.out.println("Press I to View GUI");
            System.out.println("...................................");

            System.out.println("Enter your choise  :");

            String Choise = input.next().toUpperCase();

            if (Choise.equals("A")) {
                f1.createDriver();
            } else if (Choise.equals("B")) {
                f1.deleteDriver();
            } else if (Choise.equals("C")) {
                f1.changeDriver();
            } else if (Choise.equals("D")) {
                f1.statistics();
            } else if (Choise.equals("E")) {
                f1.table();
            } else if (Choise.equals("F")) {
                f1.addRace();
            }else if(Choise.equals("G")) {
                f1.save();
            }else if(Choise.equals("H")){
                f1.load();
            }else if(Choise.equals("I")){
               F1GUI g= new F1GUI(f1);
            } else {
                System.out.println("Invalid input");


            }
        }

        while (exit) {
            System.out.println("\nDo you want to exit (y/n): ");
            String choice1 = input.next();
            choice1.toLowerCase();
            if (choice1.equals("y")) {
                exit = true;
                break;

            } else if (choice1.equals("n")) {
                break;
            }

        }

    }
}
//references
//https://beginnersbook.com/2013/04/oops-concepts/
//https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
//https://www.w3schools.com/java/java_interface.asp






































































