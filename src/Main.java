import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Village village;
        village = new Village();
//        village.Run();
//
    }
}

//    public static void main(String[] args) {
//        Village a = new Village();
////          System.out.println("###########################################");
////          System.out.println(a.buildings.size());
//
//        Task task1 = () -> a.AddWood();
//        Task task2 = () -> a.AddFood();
//        Task task3 = () -> a.Build();
//        Task task4 = () -> a.AddMetal();
//
//        a.AddWorker("1", "AddMetal", task4);
//        a.AddWorker("2", "AddWood", task1);
//        a.AddWorker("3", "AddFood", task2);
//        a.AddWorker("4", "Build", task3);
//
//
////          a.workers.get(0).DoWork();
//
//        /// Check Bury Dead
//        a.workers.get(2).setAlive(false);
//        System.out.println(a.workers.size());
//        a.BuryDead();
//        System.out.println(a.workers.size());
//
//
////          for(int i=0; i<a.workers.size();i++){
////              a.workers.get(i).DoWork();
////          }
//
//    }


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