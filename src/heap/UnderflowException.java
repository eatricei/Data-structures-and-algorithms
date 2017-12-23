package heap;

public class UnderflowException extends RuntimeException {
	public UnderflowException(){
		super();
	}
	public UnderflowException(String message){
		super(message);
	}
}
