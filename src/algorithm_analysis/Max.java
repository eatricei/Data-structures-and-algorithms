package algorithm_analysis;

public class Max {
	public static int maxSubSum1(int[] a){
		int maxSum=0;
		for(int i=0;i<a.length;i++)
			for(int j=i;j<a.length;j++){
				int thisSum=0;
				for(int k=i;k<=j;k++)
					thisSum+=a[k];
				if(thisSum>maxSum)
					maxSum=thisSum;
			}
		return maxSum;
	}
	public static int maxSubSum2(int[] a){
		int maxSum=0;
		for(int i=0;i<a.length;i++){
			int thisSum=0;
			for(int j=i;j<a.length;j++){
				thisSum+=a[j];
				if(thisSum>maxSum)
					maxSum=thisSum;
			}
		}
		return maxSum;
	}
	public static int maxSubSum3(int[] a){
		return maxSumRec(a,0,a.length-1);
	}
	private static int maxSumRec(int[] a,int left,int right){
		if(left==right)
			if(a[left]>0)
				return a[left];
			else  return 0;
		int center=(left+right)/2;
		int maxLeftSum=maxSumRec(a,left,center);
		int maxRightSum=maxSumRec(a,center+1,right);
		
		int maxLeftBorderSum=0,leftBorderSum=0;
		for(int i=center;i>=left;i--)
		{
			leftBorderSum+=a[i];
			if(leftBorderSum>maxLeftBorderSum)
				maxLeftBorderSum=leftBorderSum;
		}
		
		int maxRightBorderSum=0,rightBorderSum=0;
		for(int i=center+1;i<=right;i++){
			rightBorderSum+=a[i];
			if(rightBorderSum>maxRightBorderSum)
				maxRightBorderSum=rightBorderSum;
		}
		
		return max3(maxLeftSum,maxRightSum,maxRightBorderSum+maxLeftBorderSum);
	}
	private static int max3(int maxLeftSum, int maxRightSum, int i) {
		int max=(maxLeftSum>maxRightSum)?maxLeftSum:maxRightSum;
		return (max>i)?max:i;
	}
	public static int maxSubSum4(int[] a){
		int maxSum=0,thisSum=0;
		for(int i=0;i<a.length;i++){
			thisSum+=a[i];
			if(thisSum>maxSum)
				maxSum=thisSum;
			else if(thisSum<0)
				thisSum=0;
			}
		return maxSum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a={-2,11,-4,13,-5,-2};
		System.out.println(maxSubSum1(a));
		System.out.println(maxSubSum2(a));
		System.out.println(maxSubSum3(a));
		System.out.println(maxSubSum4(a));
	}

}
