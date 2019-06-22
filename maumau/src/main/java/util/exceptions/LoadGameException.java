package util.exceptions;

public class LoadGameException extends RuntimeException implements ICustomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -438133794148949029L;

	public LoadGameException() {
		
	}
	
	@Override
	public void printFailureMessage() {
		// TODO Auto-generated method stub
		System.out.println("Error ID: " + serialVersionUID);
		System.out.println("Cannot load entities from Database");
		System.out.println("Make sure you are operating on the right database");
		this.printStackTrace();
	}

	@Override
	public void printCustomFailureMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	@Override
	public void printCustomFailureMessages(String... messages) {
		// TODO Auto-generated method stub
		for(String message : messages) {
			System.out.println(message);
		}
	}

}
