package sorting;

public class Sorter {


    public static void mergeSort(int[] array) {
        mergeDivider(array,0,(array.length-1)/2);
        mergeDivider(array,((array.length - 1)/2) +1, array.length -1);
        mergeSortMerger(array,0,(array.length-1)/2,array.length-1);
    }

    private static void mergeDivider(int[] array, int lowBound, int highBound) {
        if(lowBound < highBound) {
            int middle = (lowBound + highBound)/2;

            if((highBound - lowBound) <= 47){
                mergeSortInsertionSortSpeedUp(array,lowBound,highBound);
            } else {
                //Normal divide and conquer
                mergeDivider(array, lowBound, middle);
                mergeDivider(array, middle + 1, highBound);

                if(array[middle] > array[middle + 1]){
                    mergeSortMerger(array, lowBound, middle, highBound);
                }

            }
        }
    }

    //Merge method
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
                before--;
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
                before--;
            }
            array[before+1] = current;
        }
    }

    private static void insertionSorter(){

    }
}
