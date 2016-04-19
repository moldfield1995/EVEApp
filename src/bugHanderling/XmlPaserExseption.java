package bugHanderling;

public class XmlPaserExseption extends Exception {
	public XmlPaserExseption(String message, ErrorLevel errorLevel){
		super(message);
		ErrorLog.LogExseption(message, errorLevel);
	}
}
