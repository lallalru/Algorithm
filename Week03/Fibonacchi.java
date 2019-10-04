package Week03;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Scanner;

import static Week03.Powering_Number.pow;

public class Fibonacchi {
    public static void main(String []args){
        int way, num;
        long start, end;

        System.out.println("방법 입력: ");
        System.out.println("1 : Recursion");
        System.out.println("2 : Array");
        System.out.println("3 : Recursive ");
        Scanner input = new Scanner(System.in);
        way =input.nextInt();

        System.out.print("숫자 입력: ");
        num = input.nextInt();

        if (way == 1) {
            System.out.println("======================================");
            for(int q=0; q<=num; q++){
                start = System.nanoTime();
                BigInteger temp = Fibo_Recur(q);
                end = System.nanoTime();
                if(q<10)
                    System.out.println("<"+ q + "> = "+ temp + "\t\t\t" +String.format("%1.9f",(double)((end-start)/1000000000.0))+" sec");
                else
                    System.out.println("<"+ q + "> = "+ temp + "\t\t\t" +String.format("%1.9f",(double)((end-start)/1000000000.0))+" sec");
                if(q!=0 && q%10==0)
                    System.out.println("======================================");
            }
        }else if(way==2){
            Fibo_Array(num);
        }else if (way==3){
            System.out.println("======================================");
            for(int q=0; q<=num; q++){
                start = System.nanoTime();
                BigInteger temp = Fibo_squaring(q);
                end = System.nanoTime();
                if(q<10)
                    System.out.println("<"+ q + "> = "+ temp + "\t\t\t" +String.format("%1.9f",(double)((end-start)/1000000000.0))+" sec");
                else
                    System.out.println("<"+ q + "> = "+ temp + "\t\t\t" +String.format("%1.9f",(double)((end-start)/1000000000.0))+" sec");
                if(q!=0 && q%10==0)
                    System.out.println("======================================");
            }
        }else{
            System.out.print("방법은 1,2,3 중에서 입력해주세요");
            way = input.nextInt();
        }

    }

    public static BigInteger Fibo_Recur(int a){
        BigInteger result;
        if(a<2)
            result =BigInteger.valueOf(a);
        else
            result = Fibo_Recur(a-1).add((Fibo_Recur(a-2)));
        return result;
    }

    public static BigInteger Fibo_Array(int a){
        BigInteger []arr = new BigInteger [a+1];
        long start, end;
        start = System.nanoTime();
        arr[0]=BigInteger.valueOf(0); arr[1]=BigInteger.valueOf(1);
        end = System.nanoTime();
        System.out.println("<"+ 0 + "> = "+ arr[0] + "\t\t" +String.format("%1.9f",(double)((end-start)/1000000000.0))+" sec");
        System.out.println("<"+ 1 + "> = "+ arr[1] + "\t\t" +String.format("%1.9f",(double)((end-start)/1000000000.0))+" sec");
        for(int i=2;i<=a;i++){
            arr[i] = arr[i-1].add(arr[i-2]);
            end = System.nanoTime();
            System.out.println("<"+ i + "> = "+ arr[i] + "\t\t" +String.format("%1.9f",(double)((end-start)/1000000000.0))+" sec");
        }
        return arr[a];
    }

    public static BigInteger Fibo_squaring(int a){
        BigInteger [][]base = {{BigInteger.valueOf(1),BigInteger.valueOf(1)}, {BigInteger.valueOf(1),BigInteger.valueOf(0)}};
        BigInteger [][]result_pow;

        if (a < 2)
            return (BigInteger.valueOf(a));
        else {
            result_pow = POW(base, a-1);
            return result_pow[0][0];
        }
    }


    public static BigInteger[][] POW(BigInteger [][]a, int n){
        if(n==1){
            return a;
        }
        else if(n%2==0){
            return multi(POW(a,n/2) , POW(a, n/2));
        }else{
            return multi(a, multi(POW(a,(n-1)/2) , POW(a, (n-1)/2)));
        }
    }

    public static BigInteger[][] multi(BigInteger [][]c,BigInteger [][]d){
        BigInteger [][]temp =new BigInteger[2][2];
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                temp[i][j]=BigInteger.valueOf(0);
                for(int k=0; k<2; k++){
                    temp[i][j]= temp[i][j].add(c[i][k].multiply(d[k][j]) );
                }
            }
        }
        return temp;
    }

}
