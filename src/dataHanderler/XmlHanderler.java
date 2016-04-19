package dataHanderler;

import itemManagement.ItemMarketData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Random;

import javax.management.modelmbean.XMLParseException;

import org.w3c.dom.Document;

import bugHanderling.XmlPaserExseption;

public class XmlHanderler {
private XmlReader xmlReader;
private File orignalFile;
private Document lastDock;
private boolean readCheck;
private static String baseURL = "http://api.eve-central.com/api/marketstat?cachebuster="; 
private static String typeString = "&typeid=";
private static String systemlimitString = "&usesystem=";
public XmlHanderler()
{
	xmlReader = new XmlReader();
	readCheck = true;
}

public ArrayList<ItemMarketData> localRead(String fileLocation) throws XmlPaserExseption
{
	ArrayList<ItemMarketData> IMDArray = new ArrayList<ItemMarketData>();
	if (!readCheck)
	{
		//Something Went wrong last time
		System.out.println("Read check = false");
	}
	readCheck = false;
	orignalFile = new File(fileLocation);
	lastDock = xmlReader.parsexmlFile(orignalFile);
	IMDArray = xmlReader.GetItemArrays(lastDock);
	readCheck = true;
	orignalFile = null;
	lastDock = null;
	return IMDArray;
}



public ArrayList<ItemMarketData> pullRead(String[] inputs, String systemID) throws XmlPaserExseption 
{
	//pulling from eveCentral
	String sID = "30000142";
	if(systemID != null)
	{
		if(systemID.compareTo("") != 0)
			sID = systemID;
	}
	
	String stringUrl = "";
	String fileLocation = "Data"+File.separator + "ID";
	ReadableByteChannel inputConnection = null;
	FileOutputStream fileWriter = null;
	try {
		Random r = new Random();
		stringUrl = baseURL + Integer.toString(r.nextInt(1000));
		stringUrl += systemlimitString + sID;
		for(int i = 0; i < inputs.length ; i++){
			stringUrl += typeString + inputs[i];
			//fileLocation += "," + inputs[i];
		}
		fileLocation += ".xml";
		URL marketUrl = new URL(stringUrl);
		inputConnection = Channels.newChannel(marketUrl.openStream());
		fileWriter = new FileOutputStream(fileLocation);
		fileWriter.getChannel().transferFrom(inputConnection, 0, Long.MAX_VALUE);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
		if(fileWriter != null)
		{
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(inputConnection != null)
		{
			try {
				inputConnection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return localRead(fileLocation);
	
}

public boolean getReadCheck(){return readCheck;}

}
