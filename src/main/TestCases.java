package main;

import static org.junit.Assert.*;
import itemManagement.ItemHanderler;
import itemManagement.ItemMarketData;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import dataHanderler.XmlHanderler;

public class TestCases {

	private XmlHanderler xmlHandle;
	private String fileLocation;
	private Boolean fullTest = false;
	@Before
	public void setUp() throws Exception {
		xmlHandle = new XmlHanderler();
		fileLocation = "";

	}

	@Test
	public void test() {
		if(fullTest)
		{
			test1();
			for (int i = 0; i <= 1000; i++)
			{
				test2();
			}
		}
		test3();
	}


	private void test1()
	{
		fileLocation = "marketstat.xml";
		@SuppressWarnings("unused")
		File loadTest;
		try
		{
			loadTest = new File(fileLocation);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			fail("IO error");
		}
		ArrayList<ItemMarketData> imdArray = xmlHandle.localRead(fileLocation);
		if(xmlHandle.getReadCheck())
		{
			if(imdArray.size() > 0)
			{
				ItemMarketData imd = imdArray.get(0);
				System.out.println("ID:" + imd.getID());
				System.out.println("Buy:");
				System.out.println("Volume:" + imd.GetAtribute(0, 0));
				System.out.println("avg:" + imd.GetAtribute(0, 1));
				System.out.println("max:" + imd.GetAtribute(0, 2));
				System.out.println("min:" + imd.GetAtribute(0, 3));
				System.out.println("Sell:");
				System.out.println("Volume:" + imd.GetAtribute(1, 0));
				System.out.println("avg:" + imd.GetAtribute(1, 1));
				System.out.println("max:" + imd.GetAtribute(1, 2));
				System.out.println("min:" + imd.GetAtribute(1, 3));

			}
			else 
			{
				fail("Array size was 0");
			}
		}
		else
		{
			fail("read check = false");
		}
		System.out.println("test 1 was exicuted without problems");
	}

	private void test2()
	{
		ArrayList<ItemMarketData> imdArray=  new ArrayList<ItemMarketData>();
		for (int i = 0; i <10; i++)
		{
			Random r = new Random();
			imdArray.add(new ItemMarketData(r.nextInt(2000),null,null));
			System.out.println(imdArray.get(i).getID() + "");
		}
		ItemHanderler dH = new ItemHanderler();
		dH.addItem(imdArray);
		ArrayList<ItemMarketData> sorted = dH.getArray();
		for(int itt = 1; itt < sorted.size(); itt++)
		{
			if(sorted.get(itt-1).getID() > sorted.get(itt).getID())
			{
				System.out.println("i-1 =" +sorted.get(itt-1).getID() + " i =" + sorted.get(itt).getID() + " itt ="+ itt  );

				for (int a = 0; a<sorted.size();a++ )
				{
					System.out.print("index " + a + " ID:" + sorted.get(a).getID() + " ");
				}
				fail("sorted incorectly");
			}
		}
		System.out.println("Passed");
	}

	
	private void test3()
	{
		xmlHandle = new XmlHanderler();
		String[] array = {"34","35","36"};

		ArrayList<ItemMarketData> imdArray = xmlHandle.pullRead(array, "30000142");
		if(xmlHandle.getReadCheck())
		{
			int i = 0;
			while(imdArray.size() > i)
			{
				ItemMarketData imd = imdArray.get(i);
				System.out.println("ID:" + imd.getID());
				System.out.println("Buy:");
				System.out.println("Volume:" + imd.GetAtribute(0, 0));
				System.out.println("avg:" + imd.GetAtribute(0, 1));
				System.out.println("max:" + imd.GetAtribute(0, 2));
				System.out.println("min:" + imd.GetAtribute(0, 3));
				System.out.println("Sell:");
				System.out.println("Volume:" + imd.GetAtribute(1, 0));
				System.out.println("avg:" + imd.GetAtribute(1, 1));
				System.out.println("max:" + imd.GetAtribute(1, 2));
				System.out.println("min:" + imd.GetAtribute(1, 3));
				i++;
			}

		}
		else
		{
			fail("read check = false");
		}
	}
	
}
