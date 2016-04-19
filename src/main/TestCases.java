package main;

import static org.junit.Assert.*;

import itemManagement.CustomCompairor;
import itemManagement.ItemHanderler;
import itemManagement.ItemMarketData;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import bugHanderling.ErrorLevel;
import bugHanderling.XmlPaserExseption;
import dataHanderler.XmlHanderler;

//Needs to be evaluated and re done, extra test needed and Time evaluation needed.
public class TestCases {

	private XmlHanderler xmlHandle;
	private String fileLocation;

	@Before
	public void setUp() throws Exception {
		xmlHandle = new XmlHanderler();
		fileLocation = "";

	}

	private void printOutIMD(ItemMarketData itemMarketData) {
		System.out.println("ID:" + itemMarketData.getID());
		System.out.println("Buy:");
		System.out.println("Volume:" + itemMarketData.GetAtribute(0, 0));
		System.out.println("avg:" + itemMarketData.GetAtribute(0, 1));
		System.out.println("max:" + itemMarketData.GetAtribute(0, 2));
		System.out.println("min:" + itemMarketData.GetAtribute(0, 3));
		System.out.println("Sell:");
		System.out.println("Volume:" + itemMarketData.GetAtribute(1, 0));
		System.out.println("avg:" + itemMarketData.GetAtribute(1, 1));
		System.out.println("max:" + itemMarketData.GetAtribute(1, 2));
		System.out.println("min:" + itemMarketData.GetAtribute(1, 3));
	}
	
	@Test
	public void LoadingFromFile() throws XmlPaserExseption {
		fileLocation = "marketstat.xml";
		@SuppressWarnings("unused")
		File loadTest;
		try {
			loadTest = new File(fileLocation);
		} catch (Exception e) {
			e.printStackTrace();
			fail("IO error");
		}
		ArrayList<ItemMarketData> imdArray = xmlHandle.localRead(fileLocation);
		assertTrue(imdArray.size() == 1);
		assertTrue(xmlHandle.getReadCheck());
		ItemMarketData imd = imdArray.get(0);
		assertNotNull(imd);
		// printOutIMD(imd);
	}


	@Test
	public void SortTest() {
		ArrayList<ItemMarketData> imdArray = new ArrayList<ItemMarketData>();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			imdArray.add(new ItemMarketData(r.nextInt(2000), null, null));
		}
		ItemHanderler dH = new ItemHanderler();
		dH.addItem(imdArray);
		ArrayList<ItemMarketData> sorted = dH.getArray();
		for (int itt = 1; itt < sorted.size(); itt++) {
			if (sorted.get(itt - 1).getID() > sorted.get(itt).getID()) {
				System.out.println(
						"i-1 =" + sorted.get(itt - 1).getID() + " i =" + sorted.get(itt).getID() + " itt =" + itt);
				fail("sorted incorectly");
			}
		}
	}

	@Test
	public void XMLEveCentralPull() throws XmlPaserExseption {
		xmlHandle = new XmlHanderler();
		String[] array = { "34", "35", "36" };

		ArrayList<ItemMarketData> imdArray = xmlHandle.pullRead(array, "30000142");
		assertTrue(imdArray.size() == 3);
		assertTrue(xmlHandle.getReadCheck());
		for (ItemMarketData itemMarketData : imdArray) {
			assertNotNull(itemMarketData);
			// printOutIMD(itemMarketData);
		}

	}
	/**
	 * Test the speed of my custom item sorter
	 */
	@Test
	public void ItemHanderlerSpeedTest() {
		ItemHanderler array = new ItemHanderler();
		ArrayList<ItemMarketData> imdArray = new ArrayList<ItemMarketData>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			imdArray.add(new ItemMarketData(r.nextInt(200000), null, null));
		}
		long starttime = System.currentTimeMillis();
		array.addItem(imdArray);
		long timetaken = System.currentTimeMillis() - starttime;
		System.out.println("ItemHanderler took " + timetaken + " milli seconds");
	}

	@Test
	public void JavaComparatorSpeedTest() {
		ArrayList<ItemMarketData> imdArray = new ArrayList<ItemMarketData>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			imdArray.add(new ItemMarketData(r.nextInt(200000), null, null));
		}
		CustomCompairor c = new CustomCompairor();
		long starttime = System.currentTimeMillis();
		imdArray.sort(c);
		long timetaken = System.currentTimeMillis() - starttime;
		System.out.println("Coustom Commpatator took " + timetaken + " milli seconds");
	}
	@Test
	public void ExseptionTest()
	{
		try{
			throw new XmlPaserExseption("it broke",ErrorLevel.faital);
		}
		catch(Exception e)
		{
			
		}
		
	}
}
