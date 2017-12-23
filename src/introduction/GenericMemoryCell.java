package introduction;

public class GenericMemoryCell<AnyType> {

	private AnyType storedValue;
	public AnyType read(){
		return storedValue;
	}
	public void write(AnyType x){
		storedValue=x;
	}
	public static void main(String[] args) {
		GenericMemoryCell<Integer> m=new GenericMemoryCell<>();
		m.write(5);
		int val=m.read();
		System.out.println(val);
	}
}
