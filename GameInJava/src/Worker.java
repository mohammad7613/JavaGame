public class Worker {
    // you need to make all the variable here
    private String name;
    private String occupation;
    private boolean Hungry;
    private int daysHungry;

    private boolean alive;

    private Work task;// it is a functional interface


    public Worker(String name, String occupation, Work task)
    {
        this.name = name;
        this.alive = true;
        this.occupation = occupation;
        this.Hungry = false;
        this.daysHungry = 0;
        this.task = task;
    }

//    public String GetName(){return name;}
//    public String SetName(String name){return this.name;}
//
//    public String GetOccupatinon(){return occupation;}
//    public String SetOccupation(String occupation){return this. occupation;}
//
//    public int GetHungry(){return Hungry;}
//    public int SetHungry(int Hungry){return this. Hungry;}
//
//    public boolean GetDaysHungry (){return daysHungry;}
//
//    public boolean GetDaysHungry (boolean daysHungry){return this.daysHungry;}


    public boolean DoWork()
    {
        if(this.isHungry()){
            return false;
        }
        else{
            // we need to implement the codes here
            task.work();
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isHungry() {
        return Hungry;
    }

    public void setHungry(boolean hungry) {
        Hungry = hungry;
    }

    public int getDaysHungry() {
        return daysHungry;
    }

    public void setDaysHungry(int daysHungry) {
        this.daysHungry = daysHungry;
    }
}
