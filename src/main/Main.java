package main;

import bugHanderling.XmlPaserExseption;
import dataHanderler.XmlHanderler;

public class Main {

	public static void main(String[] args) {
		XmlHanderler xH = new XmlHanderler();
		try {
			xH.localRead("marketstat.xml");
		} catch (XmlPaserExseption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
