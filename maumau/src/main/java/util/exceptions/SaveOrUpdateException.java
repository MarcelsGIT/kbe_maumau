package util.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveOrUpdateException.
 */
public class SaveOrUpdateException extends RuntimeException implements ICustomException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3809457372713962393L;

	/**
	 * Instantiates a new save or update exception.
	 */
	public SaveOrUpdateException() {
		
	}

	/**
	 * Prints the failure message.
	 */
	@Override
	public void printFailureMessage() {
		// TODO Auto-generated method stub
		System.out.println("Error ID: " + serialVersionUID);
		System.out.println("Cannot save nor update the Tables");
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
