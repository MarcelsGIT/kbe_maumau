package util.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class DbConnectionException.
 */
public class DbConnectionException extends RuntimeException implements ICustomException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -437705939189955251L;
	
	/**
	 * Instantiates a new db connection exception.
	 */
	public DbConnectionException() {
		
	}
	
	/**
	 * Prints the failure message.
	 */
	public void printFailureMessage() {
		System.out.println("Error ID: " + serialVersionUID);
		System.out.println("Cannot connect to Db");
		System.out.println("Check Db Creds");
		this.printStackTrace();
	}
	
	/**
	 * Prints the custom failure message.
	 *
	 * @param message the message
	 */
	public void printCustomFailureMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * Prints the custom failure messages.
	 *
	 * @param messages the messages
	 */
	public void printCustomFailureMessages(String ... messages){
		for(String message : messages) {
			System.out.println(message);
		}
	}

}
