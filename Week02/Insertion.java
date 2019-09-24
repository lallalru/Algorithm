package Week02;

import java.io.*;

public class Insertion {
    public static void main (String args[]) {
        int [] a=null;
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

            for (int i = 1; i <a.length; i++) {
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
            File output = new File("output_Insertion.txt");

            try {
                FileWriter fw = new FileWriter(output);
                for (int k = 0; k <a.length; k++) {
                    fw.write(a[k]+" ");
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            for (int k = 0; k <a.length; k++) {
//                System.out.print(a[k]+" ");
//            }
            file_reader.close();
        }catch (FileNotFoundException e) {
            e.getStackTrace();
        }catch(IOException e){
            e.getStackTrace();
        }
    }
}
