package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 采用分治策略
 * 最坏情况O(N^2) 最好情况O(N logN) 平均情况O(N logN)
 * Created by swj on 2017/12/25.
 */
public class QuickSort {
    /**
     * 简单递归排序算法
     * @param items
     */
    public static void sort( List<Integer> items){
        if ( items.size() > 1){
            List<Integer> smaller = new ArrayList<>( );
            List<Integer> same = new ArrayList<>( );
            List<Integer> larger= new ArrayList<>( );
            Integer chosenItem = items.get( items.size() / 2 );
            for( Integer i : items){
                if( i < chosenItem){
                    smaller.add( i );
                }else if ( i > chosenItem){
                    larger.add( i );
                }else{
                    same.add( i );
                }
            }
            sort( smaller );
            sort( larger );

            items.clear();
            items.addAll( smaller );
            items.addAll( same );
            items.addAll( larger );

        }
    }

    /**
     * QuickSort algorithm
     * @param a an array of Comparable items
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>>
                        void quickSort( AnyType [] a){
        quickSort(a, 0, a.length-1);
    }

    private static final int CUTOFF = 3;

    /**
     * Internal quickSort method that makes recursive calls.
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>>
                        void quickSort( AnyType [] a, int left, int right){
        if ( left + CUTOFF <= right){
            AnyType pivot = median3(a, left, right);
            //Begin partitioning
            int i = left, j = right - 1;
            for (;;){
                while ( a[ ++i ].compareTo( pivot ) < 0){}
                while ( a[ --j ].compareTo( pivot ) > 0){}
                if ( i < j ){
                    swapReferences(a, i, j);
                }else {
                    break;
                }
            }
            swapReferences(a, i, right - 1); //restore pivot

            quickSort(a, left, i-1);//sort small elements
            quickSort(a, i+1, right);//sort large elements
        }else {
            // Do an insertion sort on the subarray
            new Insertion().insertionSort(a);
        }
    }
    /**
     * return median of left, center, and right.
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     * @return
     */
    private static <AnyType extends Comparable<? super AnyType>>
                    AnyType median3( AnyType [] a, int left, int right){
        int center = ( left + right ) / 2;
        if (a[ center ].compareTo(a[ left ]) < 0){
            swapReferences(a, left, center);
        }
        if (a[ right ].compareTo(a[ left ]) < 0){
            swapReferences(a, left, right);
        }
        if (a[ right ].compareTo(a[ center ]) < 0){
            swapReferences(a, center, right);
        }
        //Place pivot at position right-1
        swapReferences(a, center, right - 1);
        return a[ right - 1 ];
    }

    /**
     * Method to swap to elements in an array.
     * @param a
     * @param index1
     * @param index2
     * @param <AnyType>
     */
    public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    /**
     * Internal selection method that makes recursive calls
     * Uses median-of-three partitioning and a cutoff of 10.
     * 快速选择 线性期望时间算法
     * @param a
     * @param left
     * @param right
     * @param k
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>>
                    void quickSelect(AnyType [] a, int left, int right, int k){
        if ( left + CUTOFF <= right){
            AnyType pivot = median3(a, left, right);
            int i = left, j = right - 1;
            for (;;){
                while ( a[ ++i ].compareTo( pivot ) < 0){}
                while ( a[ --j ].compareTo( pivot ) > 0){}
                if ( i < j )
                    swapReferences(a, i, j);
                else
                    break;
            }
            swapReferences(a, i, right - 1);
            if ( k <= i ){
                quickSelect(a, left, i - 1, k);
            }else if ( k > i+1){
                quickSelect(a, i+1, right, k);
            }
        }else{
            new Insertion().insertionSort(a);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        MergeSortEntity [] arr = new MergeSortEntity[10];
        Integer [] ints = new Integer[]{3,5,9,8,4,6,2,5,35,152,-5};
        for (int i = 9 ;i >= 0;i --){
            list.add(i);
            arr[i] = new MergeSortEntity("swj", 15, i);
        }
        sort(list);
        quickSort(arr);
        quickSelect(ints, 0, ints.length-1, 1);  //快速选择解决选择问题
        System.out.println(Arrays.toString(ints));
        System.out.println(list.toString());
        System.out.println(Arrays.toString(arr));
    }
}
