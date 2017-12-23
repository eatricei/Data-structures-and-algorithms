package heap;
/**
 * BinaryHeap
 * @author swj
 *
 * @param <AnyType>
 */
public class BinaryHeap <AnyType extends Comparable<? super AnyType>>{
	
	private static final int DEFAULT_CAPACITY=10;
	private int currentSize;
	private AnyType[] array;
	
	public BinaryHeap(){
		this(DEFAULT_CAPACITY);
	}
	public BinaryHeap(int capacity){
		currentSize=0;
		array=(AnyType[])new Comparable[capacity+1];
	}
	public BinaryHeap(AnyType[] items){
		currentSize=items.length;
		array=(AnyType[])new Comparable[(currentSize+2)*11/10];
		
		int i=1;
		for(AnyType item:items)
			array[i++]=item;
		buildHeap();
	}
	/**
	 * establish heap order property from an arbitrary
	 */
	private void buildHeap(){
		for(int i=currentSize/2;i>0;i--)
			percolateDown(i);
	}
	/**
	 * insert into the priority queue,maintaining heap order
	 * duplicates are allowed
	 * @param x
	 */
	public void insert(AnyType x){
		if(currentSize==array.length-1)
			enlargeArray(array.length*2+1);
		
		int hole=++currentSize;
		for(array[0]=x;x.compareTo(array[hole/2])<0;hole/=2)//percolate up
			array[hole]=array[hole/2];
		array[hole]=x;
	}
	
	
	private void enlargeArray(int newSize){
		AnyType[] old=array;
		array=(AnyType[])new Comparable[newSize];
		for(int i=0;i<old.length;i++)
			array[i]=old[i];
	}
	/**
	 * find the smallest item in the priority queue
	 * @return
	 * @throws UnderflowException 
	 */
	public AnyType findMin() {
		if(isEmpty())
			throw new UnderflowException();
		return array[1];
	}
	/**
	 * remove the smallest item from the priority queue
	 * @return
	 * @throws UnderflowException 
	 */
	public AnyType deleteMin()  {
		if(isEmpty())
			throw new UnderflowException();
		AnyType minItem=findMin();
		array[1]=array[currentSize--];
		percolateDown(1);
		return minItem;
	}
	/**
	 * test if the priority queue is logically empty
	 * @return
	 */
	public boolean isEmpty(){
		return currentSize==0;
	}
	/**
	 * internal method to percolate down in the heap
	 * @param hole
	 */
	private void percolateDown(int hole){
		int child;
		AnyType tmp=array[hole];
		
		for(;hole*2<=currentSize;hole=child){	//一直将hole往下移
			child=hole*2;//左儿子
			if(child!=currentSize&&
					array[child+1].compareTo(array[child])<0)//右儿子存在且比左儿子小则选右儿子
				child++;
			if(array[child].compareTo(tmp)<0)//儿子比洞小则把儿子赋给洞
				array[hole]=array[child];
			else
				break;
		}
		array[hole]=tmp;
		
	}
	
	public static void main(String[] args)   {
		int numItems=10000;
		BinaryHeap<Integer> h=new BinaryHeap<>();
		int i=37;
		for(i=37;i!=0;i=(i+37)%numItems)
			h.insert(i);
		for(i=1;i<numItems;i++)
			if(h.deleteMin()!=i)
				System.out.println("Oops! "+i);
	}
}
