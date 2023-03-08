public class Building{
    private String name;
    private int woodCost ;
    private int metalCost;
    private int daysWorkerOn;
    private int daysToComplete;


    public Building(String name)
    {
          this.name = name;
          this.daysWorkerOn = 0;
          this.daysToComplete = 3;
          this.metalCost = 0;
          this.woodCost = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWoodCost() {
        return woodCost;
    }

    public void setWoodCost(int woodCost) {
        this.woodCost = woodCost;
    }

    public int getMetalCost() {
        return metalCost;
    }

    public void setMetalCost(int metalCost) {
        this.metalCost = metalCost;
    }

    public int getDaysWorkerOn() {
        return daysWorkerOn;
    }

    public void setDaysWorkerOn(int daysWorkerOn) {
        this.daysWorkerOn = daysWorkerOn;
    }

    public int getDaysToComplete() {
        return daysToComplete;
    }

    public void setDaysToComplete(int daysToComplete) {
        this.daysToComplete = daysToComplete;
    }
}