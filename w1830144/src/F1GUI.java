import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class F1GUI extends JFrame{
    //Swing components needed
    JTable driverStatTableView;
    JScrollPane sp;
    JButton ascendingOrderButton;
    JButton descendingByWinsButton;
    JButton createRaceButton;
    JLabel dateOfRace;
    JLabel raceDateWithProb;
    JTable randomRaceTable;
    JScrollPane sp2;
    JButton createRaceButtonWithProb;
    JButton viewAllRaces;
    JButton search;
    JTextField inputName;
    JTable racesTable;
    JScrollPane sp3;
    JTable results;
    JScrollPane sp4;


    //Constructor
    public F1GUI(Formula1ChampionshipManager manager){
        //title of the GUI
        setTitle("Championship Manager GUI");

        //Table with driver stats
        String[] columnsStatTable = {"Driver Name","Team Name","Location","First Positions","Second Positions","Third Positions","Participated","Total Points"};
        String[][] rowsStatTable = new String[manager.drivers.size()][8]; //Holds data of the table
        guiTableDataPointsDescending(rowsStatTable,manager);
        driverStatTableView = new JTable(rowsStatTable,columnsStatTable);
        driverStatTableView.setDefaultEditor(Object.class, null); //get an uneditable table
        sp=new JScrollPane(driverStatTableView); //make the table scrollable
        sp.setBounds(25,25,750,300);

        //Button to sort stat table in ascending order
        ascendingOrderButton = new JButton("Ascending Order");
        ascendingOrderButton.setBounds(175,360,200,50);
        ascendingOrderButton.addActionListener(new  ActionListener() { //functionality
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guiTableDataPointsAscending(rowsStatTable,manager);
                driverStatTableView.repaint(); //refresh the table
            }
        });

        //Button to sort the above table in descending order regarding number of first positions won
        descendingByWinsButton = new JButton("Sort By Wins");
        descendingByWinsButton.setBounds(385,360,200,50);
        descendingByWinsButton.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guiTableDataWinsDescending(rowsStatTable,manager);
                driverStatTableView.repaint(); //Refresh the table
            }
        });

        //Generates a random race with proof table
        String [] columnGenerateRandom = {"Name","Position"};
        String [][] randomRaceRow = new String[manager.drivers.size()][2];
        dateOfRace = new JLabel();
        createRaceButton = new JButton("Random Race");
        createRaceButton.setBounds(175,420,200,50);
        createRaceButton.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String date = randomRace(manager,randomRaceRow);
                dateOfRace.setBounds(175, 480, 250, 20);
                dateOfRace.setText("Date  : " + date);
                guiTableDataPointsDescending(rowsStatTable, manager); //update values inside the table
                driverStatTableView.repaint(); //Refresh the table
                randomRaceTable = new JTable(randomRaceRow, columnGenerateRandom);
                randomRaceTable.setDefaultEditor(Object.class, null);
                sp2 = new JScrollPane(randomRaceTable);
                sp2.setBounds(165, 500, 210, 100);
                add(sp2);
            }
        });

        //Generates a random race according to probability with proof table
        createRaceButtonWithProb = new JButton("Probability Random Race");
        createRaceButtonWithProb.setBounds(385,420,200,50);
        //adds functionality to the button
        createRaceButtonWithProb.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                String date = randomRaceWithProbability(manager);
                guiTableDataPointsDescending(rowsStatTable,manager);
                driverStatTableView.repaint(); //Refresh the table
            }
        });

        //Display all the finished races
        viewAllRaces = new JButton("View Races");
        viewAllRaces.setBounds(175,620,200,50);
        viewAllRaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] raceTableColumn = {"Date","Participant Count"};
                String[][] raceTableData = new String[manager.races.size()][2];
                raceTableData(manager,raceTableData);
                racesTable = new JTable(raceTableData,raceTableColumn);
                racesTable.setDefaultEditor(Object.class, null);
                sp3 = new JScrollPane(racesTable);
                sp3.setBounds(800,25,350,400);
                add(sp3);
            }
        });

        //Display search results
        inputName = new JTextField(); //Gets search name
        inputName.setBounds(385,680,200,50);
        search = new JButton("Search");
        search.setBounds(175,680,200,50);
        search.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String [] resultTableColumn = {"Date","FinishedPosition"};
                String [][] resultTableData = new String[manager.races.size()][2];

                resultTableData(inputName.getText(),manager,resultTableData);
                results = new JTable(resultTableData,resultTableColumn);
                results.setDefaultEditor(Object.class,null);
                sp4 = new JScrollPane(results);
                sp4.setBounds(800,450,350,300);
                add(sp4);
            }
        });

        //Adds swing components to the interface
        add(sp);
        add(ascendingOrderButton);
        add(createRaceButton);
        add(createRaceButtonWithProb);
        add(descendingByWinsButton);
        add(dateOfRace);
        add(viewAllRaces);
        add(inputName);
        add(search);
        setSize(1175,820);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Programme ends after pressing close button of the GUI
    }

    public void guiTableDataPointsDescending(String[][] rows,Formula1ChampionshipManager manager) {
        //Sort drivers in the "driverList" in descending order
        Collections.sort(manager.drivers, new PointComparator()); //Descending order / Points
        int i=0;
        for (Formula1Driver temp : manager.drivers){
            rows [i][0] = temp.getName();
            rows [i][1] = temp.getTeam();
            rows [i][2] = temp.getLocation();
            rows [i][3] = Integer.toString(temp.getFirstposition());
            rows [i][4] = Integer.toString(temp.getSecondpositions());
            rows [i][5] = Integer.toString(temp.getThirdposition());
            rows [i][6] = Integer.toString(temp.getNumberofracecparticipated());
            rows [i][7] = Integer.toString(temp.getNumberofpoints());
            i++;
        }
    }

    public void guiTableDataPointsAscending(String[][] rows,Formula1ChampionshipManager manager) {
        //Sort drivers in the "driverList" in ascending order
        Collections.sort(manager.drivers,new PointComparatorasecending());
        int i=0;
        for (Formula1Driver temp : manager.drivers){
            rows [i][0] = temp.getName();
            rows [i][1] = temp.getTeam();
            rows [i][2] = temp.getLocation();
            rows [i][3] = Integer.toString(temp.getFirstposition());
            rows [i][4] = Integer.toString(temp.getSecondpositions());
            rows [i][5] = Integer.toString(temp.getThirdposition());
            rows [i][6] = Integer.toString(temp.getNumberofracecparticipated());
            rows [i][7] = Integer.toString(temp.getNumberofpoints());
            i++;
        }
    }

    public void guiTableDataWinsDescending(String[][] rows,Formula1ChampionshipManager manager) {
        //Sort drivers in the "driverList" in descending order considering wins
        Collections.sort(manager.drivers, new PointComparator());
        int i=0;
        for (Formula1Driver temp : manager.drivers){
            rows [i][0] = temp.getName();
            rows [i][1] = temp.getTeam();
            rows [i][2] = temp.getLocation();
            rows [i][3] = Integer.toString(temp.getFirstposition());
            rows [i][4] = Integer.toString(temp.getSecondpositions());
            rows [i][5] = Integer.toString(temp.getThirdposition());
            rows [i][6] = Integer.toString(temp.getNumberofracecparticipated());
            rows [i][7] = Integer.toString(temp.getNumberofpoints());
            i++;
        }
    }

    //creates a random race
    public String randomRace(Formula1ChampionshipManager manager,String[][] randomRaceRow){
        try {
            ArrayList<Integer> validPosition = new ArrayList<>(); //Store generated valid positions
            int position;
            int j = 0;
            int month = (int) (Math.random() * 12) + 1; //Generates a random month
            Calendar calender = Calendar.getInstance();
            calender.set(2021, month, 0);
            int daysOfMonth = calender.get(Calendar.DAY_OF_MONTH);  //Gets num of days in the month
            int day = (int) (Math.random() * daysOfMonth + 1); //Generates a random day
            String dateS = day + "/" + month + "/" + 2021;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
            Date date = dateFormat.parse(dateS);
            Race race = new Race(date, manager.drivers);

            manager.races.add(race);
            //Selecting drivers one by one
            for (Formula1Driver temp : manager.drivers) {
                //Generates a random position and check for validity
                while (true) {
                    position = (int) (Math.random() * manager.drivers.size()) + 1;
                    if (validPosition.isEmpty()) {
                        validPosition.add(position);
                        break;
                    } else if (!validPosition.contains(position)) {
                        validPosition.add(position);
                        break;
                    }
                }
                //Add values to the table
                randomRaceRow[j][0] = temp.getName();
                randomRaceRow[j][1] = Integer.toString(position);
                //Add points and position
                temp.calcpoints(position);
                if (position == 1) {
                    temp.setFirstposition();
                } else if (position == 2) {
                    temp.setSecondpositions();
                } else if (position == 3) {
                    temp.setThirdposition();
                }
                race.setParticipants();
                temp.setNumberofracecparticipated();
                //Store name and position of each driver
               // race.setDriverDetails(temp.getName(), Integer.toString(position));
                j++;
            }
            return dateS;
        }catch (ParseException ignored){
            return null;
        }
    }

    //Generates a random race where random driver win the race according to starting position (specific probability for each starting position)
    public String randomRaceWithProbability(Formula1ChampionshipManager manager){
        try {
            ArrayList<Integer> validPositionProb = new ArrayList<>(); //Holds randomly created valid positions
            boolean won;
            int positionOfDriver;
            int randomNum;
            boolean gotFirstPosition = false;
            //Since this programme is made for one season of the championship year was not generated as a random.

            int month = (int) (Math.random() * 12) + 1; //Generates a random month
            Calendar calender = Calendar.getInstance();
            calender.set(2021, month, 0);
            int daysOfMonth = calender.get(Calendar.DAY_OF_MONTH);  //Gets num of days in the month
            int day = (int) (Math.random() * daysOfMonth + 1); //Generates a random day
            String dateS = day + "/" + month + "/" + 2021;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
            Date date = dateFormat.parse(dateS);
            Race race = new Race(date, manager.drivers);
            manager.races.add(race);
            Collections.shuffle(manager.drivers);//Getting random starting positions by shuffling the list
            for (Formula1Driver temp : manager.drivers) {
                int starting = manager.drivers.indexOf(temp)+1; //starting position = index of the shuffled list
                //Generates a valid finished position
                while (true) {
                    //Wins the race considering starting position and its probability
                    randomNum = (int) (Math.random() * 100 + 1);
                    won = false;
                    if (starting == 1) {
                        if (randomNum >= 1 && randomNum <= 40) {
                            won = true;
                        }
                    } else if (starting == 2) {
                        if (randomNum >= 40 && randomNum <= 70) {
                            won = true;
                        }
                    } else if (starting == 3 || starting == 4) {
                        if (randomNum >= 70 && randomNum <= 80) {
                            won = true;
                        }
                    } else if (starting >= 5 && starting <= 8) {
                        if (randomNum == 100 || randomNum == 99) {
                            won = true;
                        }
                    }else if(starting == 9){
                        won = true;
                    }
                    if (won && !gotFirstPosition || (manager.drivers.size() < 9 && starting == manager.drivers.size()-1 && !validPositionProb.contains(1))) {
                        positionOfDriver = 1;
                        gotFirstPosition = true;
                    } else {
                        positionOfDriver = (int) (Math.random() * manager.drivers.size() - 1) + 2;
                    }
                    if (validPositionProb.isEmpty()) {
                        validPositionProb.add(positionOfDriver);
                        break;
                    } else if (!validPositionProb.contains(positionOfDriver)) {
                        validPositionProb.add(positionOfDriver);
                        break;
                    }
                }

                temp.calcpoints(positionOfDriver);
                //Add points and position
                if (positionOfDriver == 1) {
                    temp.setFirstposition();
                } else if (positionOfDriver == 2) {
                    temp.setSecondpositions();
                } else if (positionOfDriver == 3) {
                    temp.setThirdposition();
                }
                race.setParticipants();
                temp.setNumberofracecparticipated();
                //Store name and position of each driver
                //race.setDriverDetails(temp.getName(), Integer.toString(positionOfDriver));
            }

            return dateS;
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    //Set values in races table
    public void raceTableData(Formula1ChampionshipManager manager,String[][] raceTableData){
        //Sort the races according to date
        int i =0;
        //Date and number of participants gets added to table for each race
        for(Race temp : manager.races){
            raceTableData[i][0] = temp.getDate().toString();
            raceTableData[i][1] = Integer.toString(temp.getParticipants());
            i++;
        }
    }

    //Set values in results table
    public void resultTableData(String name,Formula1ChampionshipManager manager,String[][] resultTableData){
        int i = 0;
        //checks for driver name in races
        for(Race temp : manager.races){
            for(String[] driverTemp : temp.getDriverDetails()) {
                //Checks whether selected array element is null
                if(driverTemp[0] != null){
                    //date and position is of the race gets added to the table if a participated race is found
                    if (driverTemp[0].equals(name)) {
                        resultTableData[i][0] = temp.getDate().toString();
                        resultTableData[i][1] = driverTemp[1];
                        break;
                    }
                }
            }
            i++;
        }
    }
}
