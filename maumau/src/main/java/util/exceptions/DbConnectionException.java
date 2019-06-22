package util.exceptions;

public class DbConnectionException extends RuntimeException implements ICustomException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -437705939189955251L;
	
	public DbConnectionException() {
		
	}
	
	public void printFailureMessage() {
		System.out.println("Error ID: " + serialVersionUID);
		System.out.println("Cannot connect to Db");
		System.out.println("Check Db Creds");
		this.printStackTrace();
	}
	
	public void printCustomFailureMessage(String message) {
		System.out.println(message);
	}
	
	public void printCustomFailureMessages(String ... messages){
		for(String message : messages) {
			System.out.println(message);
		}
	}

}
