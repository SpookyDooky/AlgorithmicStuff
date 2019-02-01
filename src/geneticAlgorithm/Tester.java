package geneticAlgorithm;
import java.util.Arrays;

public class Tester {

    public static void main(String[] args){
        int size = 1000;
        int[][] testData = new int[size][size];

        int[] testDataStart = new int[size];
        int[] testDataEnd = new int[size];

        int maxNumber = 1000;
        for(int x = 0;x < size;x++){
            testDataStart[x] = (int)(Math.random() * maxNumber);
            testDataEnd[x] = (int)(Math.random() * maxNumber);
            for(int y = 0;y < size;y++){
                if(testData[y][x] == 0) {
                    testData[x][y] = (int) (Math.random() * maxNumber);
                } else {
                    testData[x][y] = testData[y][x];
                }
            }
        }

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(200,2000,25);
        int result = geneticAlgorithm.findOptimal(testData,testDataStart,testDataEnd);
    }
}
