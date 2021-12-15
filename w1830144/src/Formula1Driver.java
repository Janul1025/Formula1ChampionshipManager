public class Formula1Driver extends Driver{

    private int firstposition=0;
    private int secondpositions=0;
    private int thirdposition=0;
    private int numberofpoints;
    private int numberofracecparticipated;
    private int position;




    public Formula1Driver(String name, String location, String team) {  //create formula1driver method and contractor
        super(name, location, team);
    }

      @Override
        public int calcpoints(int possition) {  //create calcpoints of the driver


        if(possition==1){
        setNumberofpoints(getNumberofpoints()+25);

        }
        else if(possition==2){
            setNumberofpoints(getNumberofpoints()+18);
        }
        else if(possition==3){
            setNumberofpoints(getNumberofpoints()+15);
        }
      else if(possition==4){
            setNumberofpoints(getNumberofpoints()+12);
        }
      else if(possition==5){
            setNumberofpoints(getNumberofpoints()+10);
        }
      else if(possition==6){
            setNumberofpoints(getNumberofpoints()+8);
        }
      else if(possition==7){
            setNumberofpoints(getNumberofpoints()+6);
        }
      else if(possition==8){
            setNumberofpoints(getNumberofpoints()+4);
        }
      else if(possition==9){
            setNumberofpoints(getNumberofpoints()+2);
        }
      else if(possition==10){
            setNumberofpoints(getNumberofpoints()+1);
        }
      return getNumberofpoints();
        }

    @Override
    public int setNumberofracecparticipated() {  //set number of points from drivers
        return  setNumberofracecparticipated(getNumberofracecparticipated()+1);

    }


    {

    }


    public int getPosition() {
        return position;
    }

    public int getFirstposition() {
        return firstposition;
    }

    public int getSecondpositions() {
        return secondpositions;
    }

    public int getThirdposition() {
        return thirdposition;
    }

    public int getNumberofpoints() {
        return numberofpoints;
    }

    public int getNumberofracecparticipated() {
        return numberofracecparticipated;
    }

    public void setFirstposition() {
        this.firstposition ++;
    }

    public void setSecondpositions() {
        this.secondpositions ++;
    }

    public void setThirdposition() {
        this.thirdposition ++;}


    public int setNumberofpoints(int numberofpoints) {


        this.numberofpoints += numberofpoints;
        return numberofpoints;
    }

    public int setNumberofracecparticipated(int numberofracecparticipated) {
        this.numberofracecparticipated = numberofracecparticipated;


        return numberofracecparticipated;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
