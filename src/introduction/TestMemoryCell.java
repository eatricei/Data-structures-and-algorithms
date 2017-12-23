package introduction;

public class TestMemoryCell {
	public static  void main(String[] args){
		MemoryCell m=new MemoryCell();
		m.write(37);
		int val=(Integer)m.read();
		System.out.println("Contents are: "+val);
	}
}
