package introduction;

public class Ep1_8 {

	public static void main(String[] args){
		int i=0;
		double sum=0;
		while(true){
			sum+=Math.pow(i,4)/Math.pow(4, i);
			i++;
			System.out.println(sum);
		}
		
	}
}
