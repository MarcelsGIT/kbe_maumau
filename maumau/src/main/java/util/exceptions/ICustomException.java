package util.exceptions;

public interface ICustomException {
	public void printFailureMessage();
	
	public void printCustomFailureMessage(String message);
	
	public void printCustomFailureMessages(String ... messages);
}
