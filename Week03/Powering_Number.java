package Week03;


public class Powering_Number {
    public static void main(String args[]){
        System.out.print(pow(2,15));
    }

    public static int pow(int a,int n){
        if(n==0)
            return 1;
        else if( n==1)
            return a;
        else if( n%2==0)
            return pow(a,n/2)* pow(a,n/2);
        else 
            return pow(a,(n-1)/2) * pow(a,(n-1)/2) *a;
    }
}
