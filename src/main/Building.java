public abstract class Building {
    public String getName() {
        return name;
    }

    private String name;
    public boolean complete(){return daysWorkedOn >= daysToComplete;}

    private int woodCost, metalCost, daysWorkedOn, daysToComplete;

    public Building(String name, int woodCost, int metalCost, int daysToComplete) {
        this.woodCost = woodCost;
        this.metalCost = metalCost;
        this.daysToComplete = daysToComplete;
        this.name = name;
    }

    public void incrementDaysWorkedOn(){
        daysWorkedOn++;}


    public void impact(){}
}


