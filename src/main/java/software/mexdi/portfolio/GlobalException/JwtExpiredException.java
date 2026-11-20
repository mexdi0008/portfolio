package software.mexdi.portfolio.GlobalException;

public class JwtExpiredException extends RuntimeException {
	public JwtExpiredException(String message) {
		super(message);
	}
}
