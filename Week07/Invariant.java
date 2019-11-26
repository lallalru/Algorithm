package Week07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Invariant {

    public static void main(String[] args) {
        String str ="";
        try{
            File file = new File("data07_a.txt");
            FileReader file_reader = new FileReader(file);
            int cur = 0;
            while((cur = file_reader.read()) != -1){
                //System.out.print((char)cur);
                str += (char)cur;
            }
            String[] words = str.split(", ");
            int a[] = new int[words.length];
            for(int i = 0; i<words.length; i++){
                a[i] = Integer.parseInt(words[i]);
            }

            File file2 = new File("data07_b.txt");
            FileReader file_reader2 = new FileReader(file2);
            int cur2 = 0;
            str ="";
            while((cur2 = file_reader2.read()) != -1){
                str += (char)cur2;
            }
            String[] words2 = str.split(", ");
            int b[] = new int[words2.length];
            for(int i = 0; i<words2.length; i++){
                b[i] = Integer.parseInt(words2[i]);
            }
            int[] c = new int[2000];

            System.arraycopy(a, 0, c, 0, 1000);
            System.arraycopy(b, 0, c, 1000, 1000);
            Arrays.sort(c);
//            System.out.println("n-2번째 : "+ c[a.length-2] );
//            System.out.println("n-1번째 : "+ c[a.length-1] );
//            System.out.println("n번째 : "+ c[a.length] );
//            System.out.println("n+1번째 : "+ c[a.length+1] );

            System.out.println("결과값: "+sort(a, 0, a.length, b, 0, b.length));

            file_reader.close();
        }catch (FileNotFoundException e) {
            e.getStackTrace();
        }catch(IOException e){
            e.getStackTrace();
        }

    }

    public static int sort(int[] A, int leftA, int rightA, int[] B, int leftB, int rightB) {
        int midA = (leftA + rightA) / 2;
        int midB = (leftB + rightB) / 2;

        if((rightA - leftA) <= 1 && (rightB - leftB) <= 1) {
            System.out.println("중앙값="+midA + "  a[midA]= "+A[midA]+ "  왼쪽 값 a["+leftA + "]= "+A[leftA] + " 오른쪽 값 a["+rightA+"]= "+A[rightA]);
            System.out.println("중앙값="+midB + "  b[midB]= "+B[midB]+ "  왼쪽 값 b["+leftB + "]= "+B[leftB] + " 오른쪽 값 b["+rightB+"]= "+B[rightB]);
            return A[midA] > B[midB] ? A[midA] : B[midB];
        }

        if(A[midA] > B[midB]) {
            //A는 left<--mid, B는 mid-->right
            System.out.println(1);
            return sort(B, midB, rightB, A, leftA, midA);
        } else if(A[midA] < B[midB]){
            //A mid-->right, B left<--mid
            System.out.println(2);
            return sort(A, midA, rightA, B, leftB, midB);
        } else {
            return -1;
        }
    }
}