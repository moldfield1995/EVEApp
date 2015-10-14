package main;

import dataHanderler.XmlHanderler;
import itemManagement.ItemHanderler;
import itemManagement.ItemMarketData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.DefaultTableModel;

import listHanderler.ListItems;
import listHanderler.ListManagment;

public class EventHanderler {
	private XmlHanderler xmlHanderler;
	private ItemHanderler dataHanderler;
	private ListManagment listManagment;
	private DefaultTableModel dTM;
	//Somthing that implements JList?
	
	public EventHanderler(){
	xmlHanderler = new XmlHanderler();
	dataHanderler = new ItemHanderler();
	listManagment = new ListManagment();
}
	//take the input and dose pulls if required
	public void Update(int i, boolean sell)
	{
		if(i<0)
		{
			return;
		}
		//get Items and format it for pulling
		ListItems items = listManagment.getElementAt(i);
		int[] componets = items.getComponets();
		int length = componets.length;
		int[] resutls = items.getResults();
		length = length + resutls.length;
		int[] pullsNeeded = new int[length];
		int ittorate = 0;
		
		for (int j : componets) { 
			if(dataHanderler.getItem(j) == null)
			{
				pullsNeeded[ittorate] = j;
				ittorate++;
			}
		}
		for (int m : resutls) { 
			if(dataHanderler.getItem(m) == null)
			{
				pullsNeeded[ittorate] = m;
				ittorate++;
			}
		}
		String[] pullArray = new String[ittorate];
		for (int a = 0; a < ittorate; a++)
		{
			pullArray[a] = "" + pullsNeeded[a];
		}
		if(pullArray.length > 0)
			dataHanderler.addItem(xmlHanderler.pullRead(pullArray, null));
		//array lists and such.
		
		
		if(sell){
			for (int com : componets) {
				ItemMarketData imd = dataHanderler.getItem(com);
				String[] table = new String[5];
				table[0] = Integer.toString(imd.getID());
				
			}
		}
		else
		{
			
		}
		
//		String[] array = {"34","35","36"};
//		
//		ArrayList<ItemMarketData> imdArray = xmlHanderler.pullRead(array, "30000142");
//		dataHanderler.addItem(imdArray);
		
	}
	
	public ArrayList<ItemMarketData> getArray(){return dataHanderler.getArray();}
	public ItemMarketData getItem(int i){return dataHanderler.getItem(i);}
	public ListManagment getList(){return listManagment;}
	public void setDTM(DefaultTableModel dtm){this.dTM = dtm;}
}
//calls pulls and updates database with everything that the Gui needs and then passes it to the GUI