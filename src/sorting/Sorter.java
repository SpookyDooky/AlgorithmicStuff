package sorting;

import java.util.ArrayList;

public class Sorter {


    //1M numbers in 0.17 seconds
    public static void mergeSort(int[] array) {
        mergeDivider(array,0,array.length-1);
    }

    private static void mergeDivider(int[] array, int lowBound, int highBound) {
        if(lowBound < highBound) {
            int middle = (lowBound + highBound)/2;

            if((highBound - lowBound) <= 5){
                mergeSortInsertionSortSpeedUp(array,lowBound,highBound);
            } else {
                mergeDivider(array, lowBound, middle);
                mergeDivider(array, middle + 1, highBound);

                if(array[middle] > array[middle + 1]){
                    mergeSortMerger(array, lowBound, middle, highBound);
                }
            }
        }
    }

    //Merge method, efficiency is O(n)
    private static void mergeSortMerger(int[] array, int lowBound, int middle, int highBound) {

        int left_index = lowBound, right_index = middle + 1,temp_index = 0;
        int[] temp_holder = new int[highBound - lowBound + 1];

        while(left_index <= middle && right_index <= highBound){
            if(array[left_index] < array[right_index]){
                temp_holder[temp_index++] = array[left_index++];
            } else {
                temp_holder[temp_index++] = array[right_index++];
            }
        }

        while(left_index <= middle){
            temp_holder[temp_index++] = array[left_index++];
        }
        while(right_index <= highBound){
            temp_holder[temp_index++] = array[right_index++];
        }

        for(int x = lowBound, k = 0; x <=highBound;x++,k++){
            array[x] = temp_holder[k];
        }
    }

    private static void mergeSortInsertionSortSpeedUp(int[] array, int left, int right){
        for(int x = left; x <= right;x++){
            int temp = array[x];
            int before = x - 1;
            while(before >= left && array[before] > temp){
                array[before+1] = array[before];
                before = before - 1;
            }
            array[before+1] = temp;
        }
    }

    public static void insertionSort(int[] array){
        int array_length = array.length;
        for(int x = 0; x < array_length;x++){
            int current = array[x];
            int before = x - 1;
            while(before >= 0 && array[before] > current){
                array[before+1] = array[before];
                before = before - 1;
            }
            array[before+1] = current;
        }
    }

    private static void insertionSorter(){

    }
}
