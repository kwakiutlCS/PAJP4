package projecto4.grupo1.albertoricardo.exceptions;

public class UploadException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UploadException() {
		super();
	}
	
	public UploadException(String message) {
		super(message);
	}
	
	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UploadException(Throwable cause) {
		super(cause);
	}
	
	

}
