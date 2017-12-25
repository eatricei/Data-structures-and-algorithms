package sort;


/**
 * 使用希尔增量的希尔排序(有更好的增量)
 * Created by swj on 2017/12/23.
 */
public class ShellSort {

    public static <AnyType extends Comparable<? super AnyType>> void shellsort(AnyType [] a){
        int j;
        for( int gap = a.length / 2; gap>0; gap /= 2 ){
            for ( int i = gap; i<a.length; i++){
                AnyType tmp = a[i];
                for ( j = 1;j >= gap &&
                        tmp.compareTo( a[j - gap])<0; j -= gap )
                    a[j] = a[j-gap];
                a[j] = tmp;
            }
        }
    }
}
