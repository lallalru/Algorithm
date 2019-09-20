package Week02;

public class Insertion {
    public static void main (String args[]) {
        int[] a = {8, 2, 4, 9, 3, 6};
        int temp;

        for (int i = 1; i <= 5; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                } else {
                    break;
                }
            }
        }
        for (int k = 0; k < 6; k++) {
            System.out.print(a[k]+" ");
        }
    }
}
