package Week08;

import java.util.Stack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Segmented_Least_Square {
    class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        (new Segmented_Least_Square()).Least_Square();
    }

    public void Least_Square() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data08.txt"));
            String line = null;
            String[] splitString = null;
            line = reader.readLine();
            splitString = line.split(",");

            int number = Integer.parseInt(splitString[0]);
            Point[] point = new Point[number+1];
            double cost = 0;
            int temp = 1;

            for(int i = 1; i <= number; i++) {
                double one = Double.parseDouble(splitString[temp]);
                temp++;
                double two = Double.parseDouble(splitString[temp]);
                temp++;
                point[i] = new Point(one, two);
            }

            cost = Double.parseDouble(splitString[1+2*number]);

            reader.close();
            solve(point,number,cost);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solve(Point[] p, int n, double cost) {
        double[] sigmaX = new double[n+1];
        double[] sigmaY = new double[n+1];
        double[] sigmaXsquare = new double[n+1];
        double[] sigmaYsquare = new double[n+1];
        double[] sigmaXY = new double[n+1];

        for(int i = 1; i <= n; i++) {
            sigmaX[i] = sigmaX[i-1] + p[i].x;
            sigmaY[i] = sigmaY[i-1] + p[i].y;
            sigmaXsquare[i] = sigmaXsquare[i-1] + p[i].x * p[i].x;
            sigmaYsquare[i] = sigmaYsquare[i-1] + p[i].y * p[i].y;
            sigmaXY[i] = sigmaXY[i-1] + p[i].x * p[i].y;
        }

        double[][] SSE = new double[n+1][n+1];
        double[][] axi = new double[n+1][n+1];
        double[][] b = new double[n+1][n+1];

        for(int j = 1; j <= n; j++) {
            for(int i = 1; i < j; i++) {
                int before_n = j - i + 1;
                double _sigXsquare = sigmaXsquare[j] - sigmaXsquare[i-1];
                double _sigYsquare = sigmaYsquare[j] - sigmaYsquare[i-1];
                double _sigX = sigmaX[j] - sigmaX[i-1];
                double _sigY = sigmaY[j] - sigmaY[i-1];
                double _sigXY = sigmaXY[j] - sigmaXY[i-1];

                axi[i][j] = (before_n * _sigXY - (_sigX*_sigY) )/(before_n * _sigXsquare - _sigX * _sigX);
                b[i][j] = (_sigY - axi[i][j] * _sigX) / before_n;
                SSE[i][j] = _sigYsquare +(axi[i][j] * axi[i][j]) * _sigXsquare + before_n * (b[i][j] * b[i][j]) - (2 * (axi[i][j]) * _sigXY) + (2 * axi[i][j] * b[i][j] * _sigX) - (2 * b[i][j] * _sigY);
            }
        }

        double[] M = new double[n+1];
        int[] miniRange = new int[n+1];

        for(int j = 1; j <= n; j++) {
            double min = 999999999;
            int miniR = 0;
            for(int i = 1; i <= j; i++) {
                double temp = SSE[i][j] + cost + M[i-1];
                if(min > temp) {
                    min = temp;
                    miniR = i;
                }
            }
            M[j] = min;
            miniRange[j] = miniR;
        }

        System.out.printf("Cost of the optimal Solution : %.6f\n\n", M[n]);
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = n, j = miniRange[n]; i > 0; i = j - 1, j = miniRange[i]) {
            stack.push(i);
            stack.push(j);
        }
        System.out.println("An optimal solution :");
        while (!stack.empty())   {
            int i = stack.pop();
            int j = stack.pop();
            System.out.printf("[Segment %2d - %2d] : y = %.6f * x + %.6f  // square error : %.6f\n",
                    i, j, axi[i][j], b[i][j], SSE[i][j]);
        }
    }
}