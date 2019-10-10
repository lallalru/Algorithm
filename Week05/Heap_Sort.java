package Week05;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Heap_Sort {
    public static void main(String[]args) {
        INDEX[] heap = new INDEX[17];
        Queue<Integer> x = new LinkedList<>();
        Queue<String> y = new LinkedList<>();
        int index = 0;

        try {
            File file = new File("data05.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            System.out.println("*****���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ� *****");
            while ((line = bufReader.readLine()) != null) {
//                System.out.println(line);
                String[] words = line.split(",");
                for (int i = 1; i < words.length; i++) {
                    x.offer(Integer.parseInt((words[i - 1])));
                    y.offer((words[i]));
                }
                heap[index] = new INDEX(x.poll(), y.poll());
                index++;
            }
            maxHeap(heap);
            for (int i = 0; i < heap.length; i++) {
                System.out.println(heap[i]);
            }
            bufReader.close();
        } catch (
                FileNotFoundException e) {
            // TODO: handle exception
        } catch (
                IOException e) {
            System.out.println(e);
        }

        int input=0;
        int input2=0;
        int input3=0;
        String newname ="";

        while (true) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("1. �۾��߰�  2. �ִ밪  3. �ִ� �켱 ���� �۾� ó��");
            System.out.println("4. ���� Ű�� ����  5. �۾� ����  6. ����");
            System.out.println("---------------------------------------------------");
            Scanner sc = new Scanner((System.in));
            input = sc.nextInt();

            switch (input){
                case 1:
                    System.out.println("�Է��� �����ǰ��� �̸��� �Է����ּ���( ex)123  �̷�������) :");
                    Scanner sc2 = new Scanner(System.in);
                    input2 = sc2.nextInt();
                    newname = sc2.nextLine();
                    INDEX newdata = new INDEX(input2,newname);
                    heap = insert(heap,newdata);
                    for (int i = 0; i < heap.length; i++) {
                        System.out.println(heap[i]);
                    }
                    break;
                case 2:
                    max(heap);
                    break;
                case 3:
                    System.out.println("�ִ� �켱 ���� ���� "+heap[0]+ "  �� �׸��� �����մϴ�. ");
                    heap = extract_max(heap);
                    for (int i = 0; i < heap.length; i++) {
                        System.out.println(heap[i]);
                    }
                    break;
                case 4:
                    System.out.println("������ų ������ ���� ������ ���� �Է����ּ���( ex)1 100) :");
                    Scanner sc3 = new Scanner(System.in);
                    input2 = sc3.nextInt();
                    input3 = sc3.nextInt();
                    heap = increase_key(heap, input2, input3);
                    for (int i = 0; i < heap.length; i++) {
                        System.out.println(heap[i]);
                    }
                    break;
                case 5:
                    System.out.println("���Ž�ų ������ ���� �Է����ּ���( ex)1 ) :");
                    Scanner sc4 = new Scanner(System.in);
                    input2 = sc4.nextInt();
                    heap = delete(heap, input2);
                    for (int i = 0; i < heap.length; i++) {
                        System.out.println(heap[i]);
                    }
                    break;
                case 6:
                    break;
            }
            if(input==6)
                break;
        }
    }

    public static INDEX[] insert(INDEX[]a, INDEX data){  //11111
        INDEX[] temp1 = new INDEX[a.length+1] ;
        System.arraycopy(a,0,temp1,0,a.length);
        temp1[a.length] = data;
        maxHeap(temp1);
        return temp1;
    }

    public static void max(INDEX[]a){
        System.out.println("�ִ밪 : "+ a[0]);
    }  //2222

    public static INDEX[] extract_max(INDEX[]a){  //3333
        INDEX[] temp2 = new INDEX[a.length-1] ;
        System.arraycopy(a,1,temp2,0,a.length-1);
        maxHeap(temp2);
        return temp2;
    }

    public static INDEX[] increase_key(INDEX[]a, int past, int now){//4444
        INDEX[] temp4= new INDEX[a.length];
        System.arraycopy(a,0,temp4,0,a.length);
        for(int i=0;i<temp4.length;i++){
            if(temp4[i].num==past){
                temp4[i].num=now;
            }
        }
        maxHeap(temp4);
        return temp4;
    }

    public static INDEX[] delete(INDEX[]a, int del){  //5555
        INDEX[] temp = new INDEX[a.length-1] ;
        for(int i=0;i<a.length;i++){
            if(a[i].num == del){
                System.arraycopy(a,i+1,temp,i,a.length-i-1);
                break;
            }
            System.arraycopy(a,i,temp,i,1);
        }
        maxHeap(temp);
        return temp;
    }

    public static void maxHeap(INDEX[] a){
        INDEX temp;
        int limit=0;
        for(int i=1;i<10;i++){
            if(Math.pow(2,i)-1>=a.length){
                limit = (int) (Math.pow(2,i-1)-2);
                break;
            }
        }

        for(int i=0;i< limit;i++){
            if (2*i+1>=a.length){
                continue;
            }else if(2*i+1<a.length && 2*i+2>=a.length){
                if (a[i].num<a[2*i+1].num){
                    temp = a[i];
                    a[i] = a[2*i+1];
                    a[2*i+1]=temp;
                    maxHeap(a);
                }
            }else{
                if (a[i].num<a[2*i+1].num){
                    temp = a[i];
                    a[i] = a[2*i+1];
                    a[2*i+1]=temp;
                    maxHeap(a);
                }
                if(a[i].num<a[2*i+2].num){
                    temp = a[i];
                    a[i] = a[2*i+2];
                    a[2*i+2]=temp;
                    maxHeap(a);
                }
            }
        }
    }


}

