package pl.edu.wszib.pi;

import java.util.Random;

public class PointsInsideCircleCounter implements Runnable {

    double yPoint, xPoint;
    int pointsInsidePartOfCircle = 0;
    int numberOfPointsToCheck;
    Random randomPoint = new Random();

    public PointsInsideCircleCounter(int numberOfPointsToCheck) {
        this.numberOfPointsToCheck = numberOfPointsToCheck;
    }

    @Override
    public void run() {
        for(int i = 0; i < numberOfPointsToCheck; i++){
            this.yPoint = this.randomPoint.nextDouble(1.0) * 2 - 1;
            this.xPoint = randomPoint.nextDouble(1.0) * 2 - 1;
            double distanceFromCenter = Math.sqrt(yPoint * yPoint + xPoint * xPoint);

            if(distanceFromCenter < 1){
                this.pointsInsidePartOfCircle++;
            }
        }

        synchronized (App.partialNumbersOfPointsInsideCircle){
            App.partialNumbersOfPointsInsideCircle.add(pointsInsidePartOfCircle);
        }
    }
}
