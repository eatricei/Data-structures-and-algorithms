package sort;

import java.util.ArrayList;

/**
 * Created by swj on 2017/12/25.
 */
public class RadixSort {

    /**
     * Radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all have same length
     * @param arr
     * @param stringLen
     */
    @SuppressWarnings("unchecked")
    public static void radixSortA(String [] arr, int stringLen){
        final int BUCKETS = 256;
        ArrayList<String> [] buckets = new ArrayList[ BUCKETS ];
        for (int i = 0;i < BUCKETS;i++)
            buckets[ i ] = new ArrayList<>();
        for ( int pos = stringLen - 1; pos >=0; pos--){
            for (String s: arr)
                buckets[s.charAt( pos )].add(s);

            int idx = 0;
            for (ArrayList<String> thisBucket: buckets) {
                for (String s : thisBucket) {
                    arr[ idx++ ] = s;
                }
                thisBucket.clear();
            }
        }
    }

    public static void main(String[] args) {

    }
}
