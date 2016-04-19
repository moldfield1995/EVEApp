package bugHanderling;


public class ErrorLog {

	public static void LogExseption(String message, ErrorLevel errorLevel)
	{
		System.out.println(message + ". This was " + errorLevel);
	}
}
