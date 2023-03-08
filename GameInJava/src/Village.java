import java.io.Console;
import java.util.*;

interface Work{
    void work();
}

public class Village {
       private int food = 10;
       private int wood ;
       private   int metal ;
       private int metalPerDay;
       private int woodPerDay;
       private int foodPerDay;
       private int daysGone;
       private ArrayList<Worker> workers ;
        //   Here need to add other variables
       private ArrayList<Building> buildings;
       private ArrayList<Building> project;

        interface Task{

        }

       public Village() {
            //  need to add three buildings and 10 food. Put all the other variable in the village to zoro or empty
            this.food = 10;
            this.wood = 0;
            this.metal = 0;
            this.workers = new ArrayList<Worker>();// create empty array list
            this.project = new ArrayList<Building>();
            this.buildings = new ArrayList<Building>();
            this.metalPerDay = 0;
            this.foodPerDay = 0;
            this.woodPerDay = 0;
            this.daysGone = 0;

            Building name1 = new Building("a");
            Building name2 = new Building("b");
            Building name3 = new Building("c");
            // Here you make mistake the complete version is that
//            System.out.println(buildings.size());
            buildings.add(name1);
            buildings.add(name2);
            buildings.add(name3);
//            System.out.println("******************************");
//            System.out.println(buildings.size());
        }
        //getters till Food
        public int GetFood() {return food ;}
        //seters till Food
        public void SetFood(int food) { this.food = food;}

        public int GetWood() {return wood ;}
        public void SetWood(int wood) { this.wood = wood;}

        public int GetMetal() {return metal ;}
        public void SetMetal(int metal) {this.metal= metal;}

        public ArrayList GetWorkers() {return workers;}
        public ArrayList SetWorkers(String workers){return this.workers;}

        public ArrayList<Building> GetProject() {return project ;}
        public ArrayList SetBuilding(String building){return this.project;}

        public ArrayList<Building> GetBuildings(){return buildings;}
        public   ArrayList SetBuilding(int buildings){  return this.buildings;}

        public int GetMetalPerDay(){return metalPerDay;}
        public void SetMetalPerDay(int metalPerDay) { this.metalPerDay = metalPerDay;}

        public int GetFoodPerDay(){return foodPerDay;}
        public void SetFoodPerDay(int foodPerDay) { this.foodPerDay = foodPerDay;}

        public int GetWoodPerDay(){return woodPerDay;}
        public void SetWoodPerDay(int woodPerDay){ this.woodPerDay = woodPerDay;}

        //getters n Setters for DaysGone
        public int GetDaysGone(){return daysGone;}
        public void SetDaysGone(int daysGone){this.daysGone = daysGone;}

        public void AddWorker(String name, String occupation, Work task)
        {
             Worker a = new Worker(name,occupation,task);
             this.workers.add(a);
        }
        public void AddProject(String name)
        {

        }
        public void Day()
        {
             // You need to implement the buryDead function here
             this.BuryDead();
             // running the worker DoWork
             for(int i=0; i<this.workers.size(); i++){
                 workers.get(i).DoWork();
             }
             this.FeedWorkers();
        }
        private void AddFood()
        {
            // you need implement this
            this.food += 5;
//            System.out.println("Food");
        }
        private void AddMetal()
        {
            // you need implement
            this.metal += 1;
//            System.out.println("Metal");
        }
        private void AddWood()
        {
             this.wood += 1;
            System.out.println("Wood");
        }
        private void Build ()
        {
                //  you need to implement
            for(int i=0;i<this.project.size();i++) {
                if (this.project.get(i).getMetalCost() < this.metal && this.project.get(i).getWoodCost() < this.wood){
                    this.project.get(i).setDaysWorkerOn(this.project.get(i).getDaysWorkerOn() + 1);
                    if (this.project.get(i).getDaysWorkerOn() > this.project.get(i).getDaysToComplete()) {
                        // Impact of building on the village
                        this.metal -= this.project.get(i).getMetalCost();
                        this.wood -= this.project.get(i).getWoodCost();
                        // add the project to the building list
                        buildings.add(project.get(i));
                        // remove the project form the list the project
                        project.remove(i);
                    }
                }
            }
        }
        private void  FeedWorkers()
        {
            for(int i=0; i<this.workers.size(); i++){
            // remove the if based on the fact you have implemented the buryDead() function in the start of the Day
                    if(this.food>0){
                        this.food -= 1;
                        workers.get(i).setHungry(false);
                        workers.get(i).setDaysHungry(0);
                    }
                    else{
                        workers.get(i).setHungry(true);
                        workers.get(i).setDaysHungry(workers.get(i).getDaysHungry()+1);
                        if(workers.get(i).getDaysHungry()>=40){
                            workers.get(i).setAlive(false);
                        }
                    }
            }
        }
        private void BuryDead()
        {
              // you need to implement the codes here.
              // for loops which is going through the worker list
              // then you need to check if the ith worker is alive or not. If it is not alive you need remove the worker from the list.
              for(int i=0; i<this.workers.size(); i++) {
                    if(!workers.get(i).isAlive())
                    {
                        workers.remove(i);
                    }
              }
        }

//    public void Run() {
//        Scanner input = new Scanner(System.in);
//        while(true) {
//           System.out.println("what would you like to do");
//           System.out.println("1.add wood");
//           System.out.println("2.feed worker");
//           System.out.println("3.Quit");
//          //Console cnsl =  System.console();
//           String input1 = Console.readLine();
//           //String  = input.nextLine();
//           switch(input)
//           {
//               case "1":
//                   AddWorkerInput();
//                   break;
//               case "2":
//                   FeedAllWorkers();
//                   break;
//               case"3":
//                   return;
//               default:
//                   System.out.println("Not an option.");
//                   break;
//           }
//       }
//    }
//    public void  FeedAllWorkers()
//    {
//        for (Worker:worker; workers)
//        {
//            worker.Eat();
//        }
//    }
      public static void main(String[] args){
          Village a = new Village();
//          System.out.println("###########################################");
//          System.out.println(a.buildings.size());

          Work task1 = () -> a.AddWood();
          Work task2 = () -> a.AddFood();
          Work task3 = () -> a.Build();
          Work task4 = () -> a.AddMetal();

          a.AddWorker("1","AddMetal",task4);
          a.AddWorker("2","AddWood",task1);
          a.AddWorker("3","AddFood",task2);
          a.AddWorker("4","Build",task3);


//          a.workers.get(0).DoWork();

          /// Check Bury Dead
          a.workers.get(2).setAlive(false);
          System.out.println(a.workers.size());
          a.BuryDead();
          System.out.println(a.workers.size());


//          for(int i=0; i<a.workers.size();i++){
//              a.workers.get(i).DoWork();
//          }

      }

}