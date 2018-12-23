package sorting;

public class Sorter {

    public static void mergeSort(int[] array) {
        mergeDivider2(array,0,array.length-1);
    }

    private static void mergeDivider2(int[] array, int lowBound, int highBound) {
        if(lowBound < highBound){
            int iFirst = (highBound - lowBound)/3 + lowBound;
            int iSecond =(highBound - iFirst)/2 + iFirst;

            if((highBound - lowBound) <= 38){
                mergeSortInsertionSortSpeedUp(array,lowBound,highBound);
            } else {
                //Normal divide and conquer
                mergeDivider2(array,lowBound,iFirst);
                mergeDivider2(array,iFirst,iSecond);
                mergeDivider2(array,iSecond,highBound);

                triplePartionMerger(array,lowBound,iFirst,iSecond,highBound);
            }
        }
    }

    private static void triplePartionMerger(int[] array, int lowBound, int left, int right, int highBound){
        int first = lowBound;
        int second = left;
        int third = right;

        int[] temp_holder = new int[highBound - lowBound + 1];
        int main_index = 0;
        while(first < left && second < right && third < highBound){
            if(array[first] < array[second]){
                if(array[first] < array[third]){
                    temp_holder[main_index] = array[first];
                    first++;
                } else {
                    temp_holder[main_index] = array[third];
                    third++;
                }
            } else {
                if(array[second] < array[third]){
                    temp_holder[main_index] = array[second];
                    second++;
                } else {
                    temp_holder[main_index] = array[third];
                    third++;
                }
            }
            main_index++;
        }
        while(first < left && second < right){
            if(array[first] < array[second]){
                temp_holder[main_index] = array[first];
                first++;
            } else {
                temp_holder[main_index] = array[second];
                second++;
            }
            main_index++;
        }
        while(second < right && third < highBound){
            if(array[second] < array[third]){
                temp_holder[main_index] = array[second];
                second++;
            } else{
                temp_holder[main_index] = array[third];
                third++;
            }
            main_index++;
        }
        while(third < highBound && first < left){
            if(array[first] < array[third]){
                temp_holder[main_index] = array[first];
                first++;
            } else {
                temp_holder[main_index] = array[third];
                third++;
            }
            main_index++;
        }
        while(first < left){
            temp_holder[main_index] = array[first];
            first++;
            main_index++;
        }
        while(second < right){
            temp_holder[main_index] = array[second];
            second++;
            main_index++;
        }
        while(third < highBound){
            temp_holder[main_index] = array[third];
            third++;
            main_index++;
        }
        for(int x = lowBound, k = 0; x <highBound;x++,k++){
            array[x] = temp_holder[k];
        }
    }

    private static void mergeSortInsertionSortSpeedUp(int[] array, int left, int right){
        if(left == right){
            return;
        } else if(left + 1 == right){
            if(array[left] > array[right]){
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
            return;
        }

        for(int x = left + 1; x <= right;++x){
            linearInsertion(array,left,x,array[x]);
        }
    }

    private static void linearInsertion(int[] array, int first, int last, int value){
        if(value < array[first]){
            while(first != last--){
                array[last + 1] = array[last];
                array[first] = value;
            }
        } else unguarded_linear_insert(array,last,value);
    }

    private static void unguarded_linear_insert(int[] array, int last, int value){
        int previous = last;
        while(value < array[--previous]) array[last--] = array[previous];
        array[last] = value;
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
}
