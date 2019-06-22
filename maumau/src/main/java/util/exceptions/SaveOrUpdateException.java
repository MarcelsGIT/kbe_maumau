package util.exceptions;

public class SaveOrUpdateException extends RuntimeException implements ICustomException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3809457372713962393L;

	public SaveOrUpdateException() {
		
	}

	@Override
	public void printFailureMessage() {
		// TODO Auto-generated method stub
		System.out.println("Error ID: " + serialVersionUID);
		System.out.println("Cannot save nor update the Tables");
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
