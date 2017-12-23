package list;

public class Test {
	public static void main(String[] args) {
		MyArrayList<String> list=new MyArrayList<String>();
		list.add("a");
		list.add(0, "b");
		list.add("c");
		list.add("d");
		for(String s:list)
			System.out.println(s);
		list.remove(0);
		list.clear();
		list.trimToSize();
		for(String s:list)
			System.out.println(s);
		System.out.println("-------------------------");
		MyLinkedList<String> link=new MyLinkedList<String>();
		link.add("a");
		link.add("a");
		link.add(0, "b");
		link.add("c");
		link.add("d");
		for(String s:link)
			System.out.println(s);
		System.out.println(link.get(3));
		System.out.println(link.contains("e"));
	}
}
