package Week04;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Closest_Pair {

    public static void main(String[] args) {

        String str = "";
        int size = 0;
        Queue<Double> xy = new LinkedList<>();
        Queue<Double> x = new LinkedList<>();
        Queue<Double> y = new LinkedList<>();

        try{
            File file = new File("data04_closest.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            System.out.print("Input Data: ");
            while((line = bufReader.readLine()) != null){
                System.out.println(line);
                String[] words = line.split(",");
                for (int i = 1; i < words.length; i++) {
                    x.offer(Double.parseDouble((words[i-1])));
                    y.offer(Double.parseDouble((words[i])));
                }
                size = size+1;
            }
            XY[] xyarray = new XY[size];
            int temp=0;
            while(temp!=size){
                xyarray[temp] = new XY(x.poll(), y.poll());
                temp++;
            }
            Arrays.sort(xyarray, new X_Sort());

            int middle;  //중간 L 값
            if(xyarray.length%2==1) {
                middle = (xyarray.length+1)/2;
            }else{
                middle = xyarray.length/2;
            }


            double shortest=999999999;
            double left=999999999;
            double right = 999999999;
            double temp_distance =0;
            if(xyarray.length<3){
                for (int i=0; i<xyarray.length;i++){
                    for(int j=1; j<xyarray.length; j++){
                        if (shortest>distance(xyarray[i], xyarray[j]) ){
                            shortest =distance(xyarray[i], xyarray[j]);
                        }
                    }
                }
            }else {
                for (int i = 0; i < middle-1; i++) {
                    temp_distance =distance(xyarray[i], xyarray[i+1]);
                    if(temp_distance==0) {
                        break;
                    }else{
                        if (left > temp_distance) {
                            left = temp_distance;
                        }
                    }
                }
                for (int i = middle; i < xyarray.length-2; i++) {
                    temp_distance =distance(xyarray[i], xyarray[i+1]);
                    if(temp_distance==0) {
                        break;
                    }else {
                        if (right > temp_distance) {
                            right = temp_distance;
                        }
                    }
                }
                if (shortest>left)
                    shortest=left;
                if(shortest>right)
                    shortest= right;
            }

            Queue<XY> range = new LinkedList<>();
            Double center = xyarray[middle].X;
            Double start=0.0;
            Double end=0.0;
            int count =0;
            for(int i=0; i<xyarray.length;i++){
                if (xyarray[i].X>=center-shortest && xyarray[i].X<=center+shortest){
                    if(start==0 && count==0) {
                        start = (double)i;
                        count ++;
                    }
                    range.offer(xyarray[i]);
                    end =(double)i;
                }
            }

            XY[] yarray = new XY[(int) (end-start+1)];
            for(int i=0;i<yarray.length;i++){
                yarray[i]=range.poll();
            }

            Arrays.sort(yarray, new Y_Sort());

            Double result=999999999.0;
            for (int i = 0; i < yarray.length-1; i++) {
                temp_distance =distance(yarray[i], yarray[i+1]);
                if(temp_distance==0) {
                    break;
                }else{
                    if (result > temp_distance) {
                        result = temp_distance;
                    }
                }
            }
            if (shortest<result)
                result = shortest;

            System.out.println("Output Data: "+result);

            bufReader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
    }//main 끝

    public static double distance(XY a, XY b){
        Double length, height=0.0;
        length = abs(b.X - a.X);
        height = abs(b.Y - a.Y);
        return sqrt(Math.pow(length,2.0) + Math.pow(height, 2.0));
    }
}
