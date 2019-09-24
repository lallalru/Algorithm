package Week02;

import java.io.*;
import java.util.Arrays;

public class Merge {
        static int[] sorted;

        public static void merge(int a[], int m, int middle, int n) {
            int i = m;             // 첫 번째 부분집합의 시작 위치 설정
            int j = middle+1;     // 두 번째 부분집합의 시작 위치 설정
            int k = m;             // 배열 sorted에 정렬된 원소를 저장할 위치 설정

            while(i<=middle && j<=n) {
                if(a[i]<=a[j]) {
                    sorted[k] = a[i];
                    i++;
                }else {
                    sorted[k] = a[j];
                    j++;
                }
                k++;
            }
            if(i>middle) {
                for(int t=j;t<=n;t++,k++) {
                    sorted[k] = a[t];
                }
            }else {
                for(int t=i;t<=middle;t++,k++) {
                    sorted[k] = a[t];
                }
            }

            for(int t=m;t<=n;t++) {
                a[t] = sorted[t];
            }
        }


        public static void mergeSort(int a[], int m, int n) {
            int middle;
            if(m<n) {
                middle = (m+n)/2;
                mergeSort(a, m, middle);    // 앞 부분에 대한 분할 작업 수행
                mergeSort(a, middle+1, n);    // 뒷 부분에 대한 분할 작업 수행
                merge(a, m, middle, n);        // 부분집합에 대하여 정렬과 병합 작업 수행
            }
        }
        public static void main(String[] args) {

            int [] a;
            //int size = a.length;
            String str ="";
            int temp;

            try{
                File file = new File("data02.txt");
                FileReader file_reader = new FileReader(file);
                int cur = 0;
                while((cur = file_reader.read()) != -1){
                    //System.out.print((char)cur);
                    str += (char)cur;
                }
                String[] words = str.split(",");
                a = new int[words.length];
                for(int i = 0; i<words.length; i++){
                    a[i] = Integer.parseInt(words[i]);
                }

            sorted = new int[a.length];
            mergeSort(a, 0, a.length-1);

            File output = new File("output_Merge.txt");

            try {
                FileWriter fw = new FileWriter(output);
                for (int k = 0; k <a.length; k++) {
                    fw.write(a[k]+" ");
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println(Arrays.toString(a));
                file_reader.close();
            }catch (FileNotFoundException e) {
                e.getStackTrace();
            }catch(IOException e){
                e.getStackTrace();
            }
        }
}

