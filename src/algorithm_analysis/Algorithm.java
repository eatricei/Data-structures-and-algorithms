package algorithm_analysis;

public class Algorithm {
	public static final int NOT_FOUND=-1;
	public static <AnyType extends Comparable<? super AnyType>>
	int binarySearch(AnyType[] a,AnyType x){
		int low=0,high=a.length-1;
		while(low<=high){
			int mid=(low+high)/2;
			if(a[mid].compareTo(x)<0)
				low=mid+1;
			else if(a[mid].compareTo(x)>0)
				high=mid-1;
			else return mid;
		}
		return NOT_FOUND;
	}
	public static long gcd(long m,long n){
		while(n!=0){
			long rem=m%n;
			m=n;
			n=rem;
		}
		return m;
	}
}
