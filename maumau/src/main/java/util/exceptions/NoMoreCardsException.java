package util.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class NoMoreCardsException.
 */
public class NoMoreCardsException extends RuntimeException implements ICustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7057202463672952299L;

	/**
	 * Instantiates a new no more cards exception.
	 */
	public NoMoreCardsException() {
		super();
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
