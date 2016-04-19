package dataHanderler;

import itemManagement.ItemMarketData;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import bugHanderling.ErrorLevel;
import bugHanderling.XmlPaserExseption;

public class XmlReader {

	XmlReader() {

	}

	public Document parsexmlFile(File xml) throws XmlPaserExseption {
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(xml);
		} 
		catch (FactoryConfigurationError | ParserConfigurationException  config) {
			throw new XmlPaserExseption("Unable to set Up document builder correctly." + config.getCause(), ErrorLevel.error);
		} 
		catch (SAXException sax)
		{
			throw new XmlPaserExseption("Unable to parse the document to XML" + sax.getCause(), ErrorLevel.error);
		}
		catch (IOException io)
		{
			throw new XmlPaserExseption("Unable to read the document parse it to XML" + io.getCause(), ErrorLevel.error);
		}
		return doc;
	}

	public ArrayList<ItemMarketData> GetItemArrays(Document doc) {
		//array list that will be returned
		ArrayList<ItemMarketData> itemData = new ArrayList<ItemMarketData>();
		Element Root = doc.getDocumentElement();
		NodeList rootNL = Root.getElementsByTagName("marketstat");
		//Test if there is a marketstat in the XML
		if (rootNL.getLength() > 0 && rootNL != null) {
			//Grabs the marketStat of the XML document
			Element marketStat = (Element) rootNL.item(0);
			NodeList marketNL = marketStat.getElementsByTagName("type");
			//checks that we have pulled items
			if (marketNL.getLength() > 0 && marketNL != null) {
				for (int i = 0; i < marketNL.getLength(); i++) {
					Element typeEle = (Element) marketNL.item(i);
					ItemMarketData Imd = createClass(typeEle);
					if (Imd == null) {
						System.out.println("Class Creation Failed");
					} else
						itemData.add(Imd);
				}
			} else {
				if (marketNL == null)
					System.out.println("makretNL is null");
				else
					System.out.println("marketNL had " + marketNL.getLength() + " elements");
			}
		} else {
			if (rootNL == null)
				System.out.println("rootNL is null");
			else
				System.out.println("rootNL had " + rootNL.getLength() + " elements");
		}

		return itemData;
	}

	private ItemMarketData createClass(Element element) {
		ArrayList<BigDecimal> buyArray = new ArrayList<BigDecimal>();
		ArrayList<BigDecimal> sellArray = new ArrayList<BigDecimal>();
		ItemMarketData imd;
		int id = Integer.parseInt(element.getAttribute("id"));
		NodeList buyNl = element.getElementsByTagName("buy");
		Element buy = (Element) buyNl.item(0);
		NodeList sellNl = element.getElementsByTagName("sell");
		Element sell = (Element) sellNl.item(0);

		buyArray.addAll(exstractElements(buy));
		sellArray.addAll(exstractElements(sell));

		if (!buyArray.isEmpty() && !sellArray.isEmpty())
			imd = new ItemMarketData(id, buyArray, sellArray);
		else
			imd = null;

		return imd;
	}
//needs changing
	private String getValue(Element e, String type) {
		String textVal = null;
		NodeList nl = e.getElementsByTagName(type);
		Element el = (Element) nl.item(0);
		textVal = el.getFirstChild().getNodeValue();
		return textVal;
	}

	private ArrayList<BigDecimal> exstractElements(Element e) {
		ArrayList<BigDecimal> a = new ArrayList<BigDecimal>();
		a.add(new BigDecimal(getValue(e, "volume")));
		a.add(new BigDecimal(getValue(e, "avg")));
		a.add(new BigDecimal(getValue(e, "max")));
		a.add(new BigDecimal(getValue(e, "min")));
		return a;
	}

}
