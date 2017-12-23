package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ep3_1 {

	public static <AnyType> void printLots(List<Integer> P,List<AnyType> L){
		Iterator<Integer> itP=P.iterator();
		Iterator<AnyType> itL=L.iterator();
		AnyType value=null;
		int pitem=0;
		int start=0;
		while(itP.hasNext()&&itL.hasNext()){
			/*int pitem=itP.next();
			System.out.println("要寻找的位置是"+pitem);
			for(int i=start;i<pitem;i++){
				value=itL.next();
				start++;
			}
			System.out.println("对应的值为"+value);*/
			pitem=itP.next();
			System.out.println("要寻找的位置是"+pitem);
			while(start<pitem&&itL.hasNext()){
				start++;
				value=itL.next();
			}
			System.out.println("对应的值为"+value);
		}
	}
	public static void main(String[] args) {
		List<Integer> P=new ArrayList<Integer>();
		List<String> L=new ArrayList<String>();
		P.add(1);P.add(3);P.add(4);P.add(6);P.add(10);
		L.add("a");L.add("b");L.add("c");L.add("d");L.add("e");L.add("f");
		printLots(P,L);
	}
}
