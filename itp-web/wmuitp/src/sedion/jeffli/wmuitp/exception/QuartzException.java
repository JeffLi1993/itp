package sedion.jeffli.wmuitp.exception;

public class QuartzException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public QuartzException() {
		super();
	}

	public QuartzException(String message) {
		super(message);
	}

	public QuartzException(String message, Throwable cause) {
		super(message, cause);
	}
}
