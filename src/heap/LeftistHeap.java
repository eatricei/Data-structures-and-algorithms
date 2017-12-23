package heap;
/**
 * LeftistHeap
 * @author swj
 *
 * @param <AnyType>
 */
public class LeftistHeap<AnyType extends Comparable<? super AnyType>>{
	
	private Node<AnyType> root;
	
	private static class Node<AnyType>{
		AnyType element;
		Node<AnyType> left;
		Node<AnyType> right;
		int npl;//null path length(零路径长)
		
		Node(AnyType theElement){
			this(theElement,null,null);
		}
		Node(AnyType theElement,Node<AnyType> lt,Node<AnyType> rt){
			element=theElement;
			left=lt;
			right=rt;
			npl=0;
		}
	}
	
	public LeftistHeap(){
		root=null;
	}
	
	public boolean isEmpty(){
		return root==null;
	}
	
	public void makeEmpty(){
		root=null;
	}
	/**
	 * merge rhs into the priority queue
	 * rhs becomes empty.rhs must be different from this
	 * @param rhs the another leftist heap
	 */
	public void merge(LeftistHeap<AnyType> rhs){
		if(this==rhs)	//avoid aliasing problems(避免混叠问题)
			return;
		root=merge(root,rhs.root);
		rhs.root=null;
	}
	/**
	 * internal method to merge two roots
	 * deals with deviant cases and calls recursive mergel
	 * @param h1
	 * @param h2
	 * @return
	 */
	private Node<AnyType> merge(Node<AnyType> h1,Node<AnyType> h2){
		if(h1==null)
			return h2;
		if(h2==null)
			return h1;
		if(h1.element.compareTo(h2.element)<0)
			return mergel(h1,h2);
		else
			return mergel(h2,h1);
	}
	/**
	 * internal method to merge two roots
	 * assumes trees are not empty and h1's root contains smallest item.
	 * @param h1
	 * @param h2
	 * @return
	 */
	private Node<AnyType> mergel(Node<AnyType> h1,Node<AnyType> h2){
		if(h1.left==null)
			h1.left=h2;
		else{
			h1.right=merge(h1.right,h2);
			if(h1.left.npl<h1.right.npl)
				swapChildren(h1);
			h1.npl=h1.right.npl+1;
		}
		return h1;
	}
	/**
	 * swaps t's two children
	 * @param t
	 */
	private static <AnyType> void swapChildren(Node<AnyType> t){
		Node<AnyType> tmp=t.left;
		t.left=t.right;
		t.right=tmp;
	}
	/**
	 * insert into the priority queue,maintaining heap order
	 * @param x
	 */
	public void insert(AnyType x){
		root=merge(new Node<>(x),root);
	}
	/**
	 * remove the smallest item from the priority queue2
	 * @return
	 * @throws UnderflowException
	 */
	public AnyType deleteMin() {
		if(isEmpty())
			throw new UnderflowException();
		AnyType minItem=root.element;
		root=merge(root.left,root.right);
		return minItem;
	}
	/**
	 * find the smallest item in the priority queue
	 * @return
	 * @throws UnderflowException
	 */
	public AnyType findMin() {
		if(isEmpty())
			throw new UnderflowException();
		return root.element;
	}
	
	
	public static void main(String[] args){
		int numItems=100;
		LeftistHeap<Integer> h=new LeftistHeap<>();
		LeftistHeap<Integer> h1=new LeftistHeap<>();
		int i=37;
		
		for(i=37;i!=0;i=(i+37)%numItems)
			if(i%2==0)
				h1.insert(i);
			else
				h.insert(i);
		h.merge(h1);
		for(i=1;i<numItems;i++)
			if(h.deleteMin()!=i)
				System.out.println("Oops!"+i);
	}
}