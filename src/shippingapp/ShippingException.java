package shippingapp;

public class ShippingException extends Exception {
	  public ShippingException() { super(); }
	  public ShippingException(String message) { super(message); }
	  public ShippingException(String message, Throwable cause) { super(message, cause); }
	  public ShippingException(Throwable cause) { super(cause); }
}
