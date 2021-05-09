package cvc.travels.business;

public class TreatableException extends Exception {

	
	private final String errorCode;
	private final String description;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TreatableException(String errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
