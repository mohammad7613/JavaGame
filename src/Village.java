import java.util.*;

public class Village {
    private int food, wood, metal, metalPerDay = 1, woodPerDay = 1, foodPerDay = 5, daysGone, maxWorkers;
    // TODO: don't understand the swedish exactly for foodPerDay starting point
    private List<Worker> workers;
    private List<Building> buildings;
    private List<Building> projects;

    public int getFood() {
        return food;
    }

    public int getWood() {
        return wood;
    }

    public int getMetal() {
        return metal;
    }

    public int getDaysGone() {
        return daysGone;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public List<Building> getProjects() {
        return projects;
    }

    class Castle extends Building {
        public Castle(String name) {
            super(name, 50, 50, 50);
        }

        @Override
        public void impact() {
            System.out.println("The Castle is complete;You Won! it took " + daysGone + " days to finish the game!");
        }
    }

    class Farm extends Building {
        public Farm(String name) {
            super(name, 5, 2, 5);
        }

        @Override
        public void impact() {
            foodPerDay += 10;
        }
    }

    class Quarry extends Building {
        public Quarry(String name) {
            super(name, 3, 5, 7);
        }

        @Override
        public void impact() {
            metalPerDay += 2;
        }
    }

    class House extends Building {
        public House(String name) {
            super(name, 5, 0, 3);
        }

        @Override
        public void impact() {
            maxWorkers += 2;
        }
    }


    class Woodmill extends Building {
        public Woodmill(String name) {
            super(name, 5, 1, 5);
        }

        @Override
        public void impact() {
            woodPerDay += 2;
        }
    }


    public Village() {
        this.food = 10;
        // TODO: add 3 houses to building
    }


    public void AddWorker(String name, String occupation, Task task) {
        if (maxWorkers - workers.size() > 0)
            workers.add(new Worker(name, occupation, task));
    }

    public void AddProject(String name) {
    }

    public void Day() {
        BuryDead();
        workers.forEach(Worker::DoWork);
        workers.stream().limit(food).forEach(Worker::Feed);
        food = Math.max(0, food - workers.size());
        daysGone++;
    }

    private void AddFood() {
        food += foodPerDay;
    }

    private void AddMetal() {
        metal += metalPerDay;
    }

    private void AddWood() {
        wood += woodPerDay;
    }

    private void Build() {
        if (projects.isEmpty()) return;
        Building selectedProject = projects.get(0);
        selectedProject.incrementDaysWorkedOn();
        if (selectedProject.complete()) {
            projects.remove(selectedProject);
            buildings.add(selectedProject);
            selectedProject.impact();
        }
    }


    private void BuryDead() {
        workers = workers.stream().filter(Worker::alive).toList();
        if (workers.isEmpty())
            System.out.println("Game Over!");
    }


}