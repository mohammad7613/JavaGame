import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Village v = new Village();
        while (true) {
            System.out.println("what would you like to do");
            System.out.println("1.day");
            System.out.println("2.add project");
            System.out.println("3.add worker");
            System.out.println("4.quit");

            switch (input.nextInt()) {
                case 1:
                    v.Day();
                    break;
                case 2:
                    System.out.println("enter project name from (farm,castle, windmill, quarry,house): ");
                    v.AddProject(input.nextLine());
                    break;
                case 3:
                    System.out.println("enter worker task : (wood,food,metal,build)");
                    switch (input.nextLine()) {
                        case "wood" -> v.AddWorker("w", "", v::AddWood);
                        case "metal" -> v.AddWorker("w", "", v::AddMetal);
                        case "food" -> v.AddWorker("w", "", v::AddFood);
                        case "build" -> v.AddWorker("w", "", v::Build);
                        default -> System.out.println("bad input");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("invalid command");
                    break;
            }
        }
    }
}