package main;

import dataHanderler.XmlHanderler;

public class Main {

	public static void main(String[] args) {
		XmlHanderler xH = new XmlHanderler();
		xH.localRead("marketstat.xml");
	}

}
