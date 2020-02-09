public class zadanie {

    public static void main(String[] args) {
    }

    public static int liczba(int n) {
        if (n == 0) {
            return 0;
        }
            if (n == 1) {
                return 1;
            }


            return liczba(n - 1) + liczba(n - 2);



    }
}