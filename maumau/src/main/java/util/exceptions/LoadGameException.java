package util.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadGameException.
 */
public class LoadGameException extends RuntimeException implements ICustomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -438133794148949029L;

	/**
	 * Instantiates a new load game exception.
	 */
	public LoadGameException() {
		
	}
	
	/**
	 * Prints the failure message.
	 */
	@Override
	public void printFailureMessage() {
		// TODO Auto-generated method stub
		System.out.println("Error ID: " + serialVersionUID);
		System.out.println("Cannot load entities from Database");
		System.out.println("Make sure you are operating on the right database");
		this.printStackTrace();
	}

	/**
	 * Prints the custom failure message.
	 *
	 * @param message the message
	 */
	@Override
	public void printCustomFailureMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	/**
	 * Prints the custom failure messages.
	 *
	 * @param messages the messages
	 */
	@Override
	public void printCustomFailureMessages(String... messages) {
		// TODO Auto-generated method stub
		for(String message : messages) {
			System.out.println(message);
		}
	}

}
