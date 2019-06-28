package util.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICustomException.
 */
public interface ICustomException {
	
	/**
	 * Prints the failure message.
	 */
	public void printFailureMessage();
	
	/**
	 * Prints the custom failure message.
	 *
	 * @param message the message
	 */
	public void printCustomFailureMessage(String message);
	
	/**
	 * Prints the custom failure messages.
	 *
	 * @param messages the messages
	 */
	public void printCustomFailureMessages(String ... messages);
}
