package cn.tedu.store.service.ex;

public class OrderCreationException 
	extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8241290237707933589L;

	public OrderCreationException() {
		super();
	}

	public OrderCreationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderCreationException(String message) {
		super(message);
	}

	public OrderCreationException(Throwable cause) {
		super(cause);
	}

}
