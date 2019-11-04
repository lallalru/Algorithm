package Week06;

import java.io.*;
import java.util.Scanner;

public class Quick_Sort {
    static int[] sorted;
    public static void main(String[] args) {
        int [] A;
        String str ="";

        try{
            File file = new File("data06.txt");
            FileReader file_reader = new FileReader(file);
            int cur = 0;
            while((cur = file_reader.read()) != -1){
                str += (char)cur;
            }
            String[] words = str.split(",");
            A = new int[words.length];
            for(int i = 0; i<words.length; i++){
                A[i] = Integer.parseInt(words[i]);
            }

            System.out.println("1. ÀÏ¹Ý Quick Sorting   2. ·£´ý Quick Sorting :");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            if(choice==1) {
                quickSort(A, 0, A.length - 1);
                File output = new File("data6_quick.txt");
                try {
                    FileWriter fw = new FileWriter(output);
                    for (int k = 0; k < A.length; k++) {
                        fw.write(A[k] + ",");
                    }
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                quickSort_withRandom(A,0,A.length-1);
                File output = new File("data6_quickRandom.txt");
                try {
                    FileWriter fw = new FileWriter(output);
                    for (int k = 0; k < A.length; k++) {
                        fw.write(A[k] + ",");
                    }
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            file_reader.close();
        }catch (FileNotFoundException e) {
            e.getStackTrace();
        }catch(IOException e){
            e.getStackTrace();
        }
    }



    public static void quickSort(int[] array, int start, int end){
        int center=0;
        if(start<end){
            center = partition(array,start,end);
            quickSort(array,start,center-1);
            quickSort(array,center+1,end);
        }
    }

    public static int partition(int []array, int ss, int ee){
        int x=array[ee];
        int i=ss-1;
        int temp=0;
        for(int j=ss;j<=ee-1;j++){
            if(array[j]<=x){
                i+=1;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        i+=1;
        temp = array[i];
        array[i] = array[ee];
        array[ee] = temp;
        return i;
    }

    public static void quickSort_withRandom(int[] array, int start, int end){
        int center=0;
        if(start<end){
            center = randomizedPartition(array,start,end);
            quickSort_withRandom(array,start,center-1);
            quickSort_withRandom(array,center+1,end);
        }
    }

    public static int randomizedPartition(int[]array, int ss, int ee){
        int temp =0;
        int i = (int) (Math.random() * (ee - ss + 1)) + ss;
        temp = array[i];
        array[i] = array[ee];
        array[ee] = temp;
        return partition(array,ss,ee);
    }
}



