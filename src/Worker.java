public class Worker {
    private String name, occupation;
    private int daysHungry;
    private Task task;

    public boolean hungry() {
        return daysHungry > 0;
    }

    public boolean alive() {
        return daysHungry < 40;
    }

    public void Feed() {
        if (alive()) daysHungry = 0;
    }

    public Worker(String name, String occupation, Task task) {
        this.name = name;
        this.occupation = occupation;
        this.task = task;
    }

    public void DoWork() {
        if (alive() && !hungry()) task.run();
        daysHungry++;
    }

}
