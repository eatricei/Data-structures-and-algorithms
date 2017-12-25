package sort;

import java.util.Arrays;

/**
 * divide-and-conquer 分治策略
 * O(N log N)
 * Created by swj on 2017/12/25.
 */
public class MergeSort {
    /**
     * MergeSort algorithm
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType [] a ){
        AnyType [] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    /**
     * Internal method that makes recursive calls
     * @param a an array of Comparable items
     * @param tmpArray an array to place that merged result
     * @param left the left-most index of the subarray
     * @param right the right-most index of the subarray
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType [] a, AnyType [] tmpArray, int left, int right){
        if(left < right){
            int center = ( left + right ) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge( a, tmpArray, left, center+1, right);
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray
     * @param a
     * @param tmpArray
     * @param leftPos
     * @param rightPos the index of the start of the second half
     * @param rightEnd
     * @param <AnyType>
     */
    public static <AnyType extends  Comparable<? super AnyType>>
                        void merge(AnyType [] a, AnyType [] tmpArray, int leftPos, int rightPos, int rightEnd){
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while( leftPos <= leftEnd && rightPos <= rightEnd ){
            if( a[ leftPos ].compareTo( a[rightPos]) <= 0){
                tmpArray[ tmpPos++ ] = a[ leftPos++ ];
            }else{
                tmpArray[ tmpPos++ ] = a[ rightPos++ ];
            }
        }
        while ( leftPos <= leftEnd ){
            tmpArray[ tmpPos++ ] = a[ leftPos++ ];
        }
        while( rightPos <= rightEnd ){
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];
        }

        for( int i = 0; i < numElements; i++,rightEnd--){
            a[ rightEnd ] = tmpArray[ rightEnd ];
        }
    }

    public static void main(String[] args) {
        MergeSortEntity [] arr = new MergeSortEntity[10];
        int score = 50;
        for(int i = 0; i < arr.length; i++ ){
            arr[ i ] = new MergeSortEntity("swj", 20, score--);
        }
        mergeSort(arr);
        System.out.printf(Arrays.toString(arr));
    }
}
