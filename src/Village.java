import java.util.*;

public class Village {
    private int food, wood, metal, metalPerDay = 1, woodPerDay = 1, foodPerDay = 5, daysGone, maxWorkers;
    // TODO: don't understand the swedish exactly for foodPerDay starting point
    private List<Worker> workers = new ArrayList<>();
    private final List<Building> buildings = new ArrayList<>();
    private final List<Building> projects = new ArrayList<>();

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
        public Castle() {
            super("castle", 50, 50, 50);
            wood -= 50;
            metal -= 50;
        }

        @Override
        public void impact() {
            System.out.println("The Castle is complete;You Won! it took " + daysGone + " days to finish the game!");
        }
    }

    class Farm extends Building {
        public Farm() {
            super("farm", 5, 2, 5);
            wood -= 5;
            metal -= 2;
        }

        @Override
        public void impact() {
            foodPerDay += 10;
        }
    }

    class Quarry extends Building {
        public Quarry() {
            super("quarry", 3, 5, 7);
            wood -= 3;
            metal -= 5;
        }

        @Override
        public void impact() {
            metalPerDay += 2;
        }
    }

    class House extends Building {
        public House() {
            super("house", 5, 0, 3);
            wood -= 5;
            metal -= 0;
        }

        @Override
        public void impact() {
            maxWorkers += 2;
        }
    }


    class WoodMill extends Building {
        public WoodMill() {
            super("woodmill", 5, 1, 5);
            wood -= 5;
            metal -= 1;
        }

        @Override
        public void impact() {
            woodPerDay += 2;
        }
    }


    public Village() {
        food = 10;
        buildings.add(new House());
        buildings.add(new House());
        buildings.add(new House());
        buildings.forEach(Building::impact);
    }


    public void AddWorker(String name, String occupation, Task task) {
        if (maxWorkers - workers.size() > 0)
            workers.add(new Worker(name, occupation, task));
        else System.out.println("cannot add more workers; need more houses");
    }

    public void AddProject(String name) {
        switch (name) {
            case "house":
                if (wood >= 5)
                    projects.add(new House());
                else System.out.println("insufficient resources");
                break;
            case "farm":
                if (wood >= 5 && metal >= 2)
                    projects.add(new Farm());
                else System.out.println("insufficient resources");
                break;
            case "castle":
                if (wood >= 50 && metal >= 50)
                    projects.add(new Castle());
                else System.out.println("insufficient resources");
                break;
            case "woodmill":
                if (wood >= 5 && metal >= 1)
                    projects.add(new WoodMill());
                else System.out.println("insufficient resources");
                break;
            case "quarry":
                if (wood >= 3 && metal >= 5)
                    projects.add(new Quarry());
                else System.out.println("insufficient resources");
                break;

            default:
                System.out.println("bad project name");
        }
    }

    public void Day() {
        System.out.println("Day " + daysGone);
        BuryDead();
        workers.stream().limit(food).forEach(Worker::Feed);
        food = Math.max(0, food - workers.size());
        workers.forEach(Worker::DoWork);
        daysGone++;
    }

     public void AddFood() {
        food += foodPerDay;
        System.out.println("added " + foodPerDay + " food");
    }

     public void AddMetal() {
        metal += metalPerDay;
        System.out.println("added " + metalPerDay + " metal");

    }

     public void AddWood() {
        wood += woodPerDay;
        System.out.println("added " + woodPerDay + " wood");

    }

     public void Build() {
        if (projects.isEmpty()) return;
        Building selectedProject = projects.get(0);
        System.out.println("building project " + selectedProject.getName() );
        selectedProject.incrementDaysWorkedOn();
        if (selectedProject.complete()) {
            System.out.println("project " + selectedProject.getName() + " is now complete!" );
            projects.remove(selectedProject);
            buildings.add(selectedProject);
            selectedProject.impact();
        }
    }


    private void BuryDead() {
        workers = workers.stream().filter(Worker::alive).toList();
        if (workers.isEmpty() && food == 0)
            System.out.println("Game Over!");
    }


}