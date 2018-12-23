import sorting.Sorter;

import java.util.Random;

public class Main {

    //Stuff to check the performance
    public static void main(String[] args) {
        int[] randomOrdered = new int[100000000];
        Random rand = new Random();

        double totalTime = 0;

        //for(int y = 0; y < 1;y++) {
        //    for(int x = 0; x < randomOrdered.length;x++){
        //        randomOrdered[x] = rand.nextInt((100000 - 1) + 1) + 1;
        //    }
        //    long startTime = System.currentTimeMillis();
//
        //    Sorter.mergeSort(randomOrdered);
        //    long endTime = System.currentTimeMillis();
        //    double finalTime = (endTime - startTime) / 1000.0;
        //    totalTime += finalTime;
        //}

        for(int y = 0; y < 20;y++) {
            for (int x = 0; x < randomOrdered.length; x++) {
                randomOrdered[x] = rand.nextInt((50 - 1) + 1) + 1;
            }

            System.out.println("STARTING SORT");
            long startTime = System.currentTimeMillis();

            Sorter.mergeSort(randomOrdered);

            long endTime = System.currentTimeMillis();
            double finalTime = (endTime - startTime) / 1000.0;

            //for(int x = 0; x < randomOrdered.length;x++){
            //    System.out.println(randomOrdered[x]);
            //}
            System.out.println(finalTime);
            totalTime += finalTime;
        }

        System.out.println("AVG: " + totalTime/20.0);
    }
}
