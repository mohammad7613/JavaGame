public class Utils {
    public static void repeat(Runnable function, int n) {
        for (int i = 0; i < n; i++) {
            function.run();
        }
    }
}
