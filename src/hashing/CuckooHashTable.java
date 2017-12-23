package hashing;

import java.util.Random;

public class CuckooHashTable <AnyType>{
	private static final double MAX_LOAD=0.4;
	private static final int ALLOWED_REHASHES=1;
	private static final int DEFAULT_TABLE_SIZE=101;
	
	private final HashFamily<? super AnyType> hashFunctions;
	private final int numHashFunctions;
	private AnyType [] array;
	private int currentSize;
	/**
	 * Construct the hash table
	 * @param hf
	 */
	public CuckooHashTable(HashFamily<? super AnyType> hf){
		this(hf,DEFAULT_TABLE_SIZE);
	}
	
	/**
	 * construct the hash table
	 * @param hf
	 * @param size
	 */
	public CuckooHashTable(HashFamily<? super AnyType> hf,int size){
		allocateArray(nextPrime(size));
		doclear();
		hashFunctions=hf;
		numHashFunctions=hf.getNumberOfFunctions();
	}
	/**
	 * compute the hash code for x using specified hash function
	 * @param x
	 * @param which
	 * @return
	 */
	private int myhash(AnyType x,int which){
		int hashVal=hashFunctions.hash(x, which);
		hashVal %=array.length;
		if(hashVal<0)
			hashVal+=array.length;
		return hashVal;
	}
	/**
	 * method that searches all hash function places
	 * @param x
	 * @return
	 */
	private int findPos(AnyType x){
		for(int i=0;i<numHashFunctions;i++){
			int pos =myhash(x,i);
			if(array[pos]!=null&&array[pos].equals(x))
				return pos;
		}
		return -1;
	}
	/**
	 * get the length of the table
	 * @return
	 */
	public int capacity(){
		return array.length;
	}
	/**
	 * find an item in the hash table
	 * @param x
	 * @return
	 */
	public boolean contains(AnyType x){
		return findPos(x)!=-1;
	}
	/**
	 * remove from the hash table
	 * @param x
	 * @return
	 */
	public boolean remove(AnyType x){
		int pos =findPos(x);
		if(pos!=-1){
			array[pos]=null;
			currentSize--;
		}
		return pos!=-1;
	}
	/**
	 * insert into the hash table if the item is already present return false
	 * @param x
	 * @return
	 */
	public boolean insert(AnyType x){
		if(contains(x))
			return false;
		if(currentSize>=array.length*MAX_LOAD)
			expand();
		return insertHelperl(x);
	}
	private int rehashes=0;
	private Random r=new Random();
	
	private boolean insertHelperl(AnyType x){
		final int COUNT_LIMIT=100;
		while(true){
			int lastPos=-1;
			int pos;
			for(int count=0;count<COUNT_LIMIT;count++){
				for(int i=0;i<numHashFunctions;i++){
					pos=myhash(x,i);
					if(array[pos]==null){
						array[pos]=x;
						currentSize++;
						return true;
					}
				}
				
				int i=0;
				do{
					pos=myhash(x,r.nextInt(numHashFunctions));
				}while(pos==lastPos&&i++<5);
				AnyType tmp=array[lastPos=pos];
				array[pos]=x;
				x=tmp;
			}
			if(++rehashes>ALLOWED_REHASHES){
				expand();	//make the table bigger
				rehashes=0;		//reset the # of rehashes
			}else
				rehash();		//same table size new hash functions
		}
	}
	
	private void expand(){
		rehash((int)(array.length/MAX_LOAD));
	}
	private void rehash(){
		hashFunctions.generateNewFunctions();
		rehash(array.length);
	}
	private void rehash(int newLength){
		AnyType[] oldArray=array;
		allocateArray(nextPrime(newLength));
		currentSize=0;
		for(AnyType str:oldArray){
			if(str!=null)
				insert(str);
		}
	}
	private void doclear(){
		currentSize=0;
		for(int i=0;i<array.length;i++)
			array[i] = null;
	}
	private void allocateArray(int arraySize){
		array=(AnyType[]) new Object[arraySize];
	}
	
	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		for (; !isPrime(n); n += 2)
			;
		return n;
	}

	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		long cumulative = 0;

		final int NUMS = 2000000;
		final int GAP = 37;
		final int ATTEMPTS = 10;

		System.out.println("Checking... (no more output means success)");

		for (int att = 0; att < ATTEMPTS; att++) {
		//	System.out.println("ATTEMPT: " + att);

			CuckooHashTable<String> H = new CuckooHashTable<>(
					new StringHashFamily(3));
			// QuadraticProbingHashTable<String> H = new
			// QuadraticProbingHashTable<>( );

			long startTime = System.currentTimeMillis();

			for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
				H.insert("" + i);
			for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
				if (H.insert("" + i))
					System.out.println("OOPS!!! " + i);
			for (int i = 1; i < NUMS; i += 2)
				H.remove("" + i);

			for (int i = 2; i < NUMS; i += 2)
				if (!H.contains("" + i))
					System.out.println("Find fails " + i);

			for (int i = 1; i < NUMS; i += 2) {
				if (H.contains("" + i))
					System.out.println("OOPS!!! " + i);
			}

			long endTime = System.currentTimeMillis();

			cumulative += endTime - startTime;

			if (H.capacity() > NUMS * 4)
				System.out.println("LARGE CAPACITY " + H.capacity());
		}

		System.out.println("Total elapsed time is: " + cumulative);
	}

}
