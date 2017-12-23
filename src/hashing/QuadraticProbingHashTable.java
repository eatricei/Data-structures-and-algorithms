package hashing;

public class QuadraticProbingHashTable<AnyType> {
	private static final int DEFAULT_TABLE_SIZE=11;
	private HashEntry<AnyType>[] array;
	private int currentSize;
	
	public QuadraticProbingHashTable(){
		this(DEFAULT_TABLE_SIZE);
	}
	public QuadraticProbingHashTable(int size){
		allocateArray(size);
		makeEmpty();
	}
	private static class HashEntry<AnyType>{
		public AnyType element;
		public boolean isActive;
		public HashEntry(AnyType e){
			this(e,true);
		}
		public HashEntry(AnyType e,boolean i){
			element=e;
			isActive=i;
		}
	}
	/**
	 * internal method to allocate array
	 * @param arraySize
	 */
	private void allocateArray(int arraySize){
		array=new HashEntry[nextPrime(arraySize)];
	}
	/**
	 * find an item in the hash table
	 * @param x
	 * @return
	 */
	public boolean contains(AnyType x){
		int currentPos=findPos(x);
		return isActive(currentPos);
	}
	/**
	 * method that performs quadratic probing resolution in half-empty table
	 * @param x
	 * @return
	 */
	private int findPos(AnyType x){
		int offset=1;
		int currentPos=myhash(x);
		while(array[currentPos]!=null &&
				!array[currentPos].element.equals(x)){
			currentPos+=offset;
			offset+=2;
			if(currentPos>=array.length)
				currentPos-=array.length;
		}
		return currentPos;
	}
	/**
	 * return true if currentPos exists and is active
	 * @param currentPos
	 * @return
	 */
	private boolean isActive(int currentPos){
		return array[currentPos]!=null&&array[currentPos].isActive;
	}
	/**
	 * make the hash table logically empty
	 */
	public void makeEmpty(){
		currentSize=0;
		for(int i=0;i<array.length;i++)
			array[i]=null;
	}
	/**
	 * insert into the hash table 
	 * if the item is already present,do nothing
	 * @param x
	 */
	public boolean insert(AnyType x){
		int currentPos=findPos(x);
		if(isActive(currentPos))
			return false;
		array[currentPos]=new HashEntry<>(x,true);
		currentSize++;
		if(currentSize>array.length/2)
			rehash();
		return true;
	}
	/**
	 * remove from the hash table
	 * @param x
	 */
	public void remove(AnyType x){
		int currentPos=findPos(x);
		if(isActive(currentPos))
			array[currentPos].isActive=false;
	}
	/**
	 * rehashing for quadratic probing hash table
	 */
	private void rehash(){
		HashEntry<AnyType>[] oldArray=array;
		allocateArray(nextPrime(2*oldArray.length));
		currentSize=0;
		for(int i=0;i<oldArray.length;i++)
			if(oldArray[i]!=null&&oldArray[i].isActive)
				insert(oldArray[i].element);
	}
	 private int myhash( AnyType x )
	 {
	     int hashVal = x.hashCode( );

	     hashVal %= array.length;
	     if( hashVal < 0 )
	         hashVal += array.length;

	     return hashVal;
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
	public static void main( String [ ] args ){
        QuadraticProbingHashTable<String> H = new QuadraticProbingHashTable<>();

        
        long startTime = System.currentTimeMillis( );
        
        final int NUMS = 2000000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );


        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            H.insert( ""+i );
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            if( H.insert( ""+i ) )
                System.out.println( "OOPS!!! " + i );
        for( int i = 1; i < NUMS; i+= 2 )
            H.remove( ""+i );
        for( int i = 2; i < NUMS; i+=2 )
            if( !H.contains( ""+i ) )
                System.out.println( "Find fails " + i );
        for( int i = 1; i < NUMS; i+=2 ){
            if( H.contains( ""+i ) )
                System.out.println( "OOPS!!! " +  i  );
        }      
        long endTime = System.currentTimeMillis( );      
        System.out.println( "Elapsed time: " + (endTime - startTime) );
    }
}
