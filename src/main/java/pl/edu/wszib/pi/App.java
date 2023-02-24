package pl.edu.wszib.pi;

import java.util.*;

public class App {

    public static final List<Integer> partialNumbersOfPointsInsideCircle = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int totalPoints = 500000000;
        int pointsInsideCircle = 0;

        int thredsNumber = 7;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < thredsNumber; i++) {
            threads.add(new Thread(new PointsInsideCircleCounter(totalPoints/thredsNumber)));
            threads.get(i).start();
        }

        try{
            for(Thread thread : threads){
                thread.join();
            }
        }catch (InterruptedException e){}

        for(int num : partialNumbersOfPointsInsideCircle){
            pointsInsideCircle += num;
        }

        System.out.println("Total points: " + totalPoints);
        System.out.println("Points inside circle: " + pointsInsideCircle);
        System.out.println("Percentage: " + (((double) pointsInsideCircle / (double) totalPoints) * 100) + " %");
        double circleField =  4.0 * ((double) pointsInsideCircle / (double) totalPoints);
        System.out.println("Circle field/PI value: " + circleField);

        long endTime = System.currentTimeMillis();
        System.out.println( "Execution time: " + ((double) (endTime - startTime) / 1000.0) + " s");
    }
}
