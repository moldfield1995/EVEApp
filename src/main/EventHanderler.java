package main;

import dataHanderler.XmlHanderler;
import itemManagement.ItemHanderler;
import itemManagement.ItemMarketData;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.DefaultTableModel;

import listHanderler.ListItems;
import listHanderler.ListManagment;

public class EventHanderler {
	private XmlHanderler xmlHanderler;
	private ItemHanderler dataHanderler;
	private ListManagment listManagment;
	private DefaultTableModel componetDTM;
	private DefaultTableModel resultsDTM;
	private BigDecimal profitLoss;
	
	public EventHanderler(){
	xmlHanderler = new XmlHanderler();
	dataHanderler = new ItemHanderler();
	listManagment = new ListManagment();
	profitLoss = new BigDecimal(0.00);
}
	//take the input and dose pulls if required
	public void Update(int listID, boolean sell)
	{
		if(listID<0)
		{
			return;
		}
		if(componetDTM.getRowCount() >0)
		{
			clearTables();
		}
		//get Items and format it for pulling
		ListItems items = listManagment.getElementAt(listID);
		int[] componets = items.getComponets();
		int[] componetsAmount = items.getComponetsAmounts();
		int length = componets.length;
		int[] resutls = items.getResults();
		int[] resultsAmount = items.getResultsAmounts();
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
			for (int i = 0 ; i < componets.length; i++) {
				ItemMarketData imd = dataHanderler.getItem(componets[i]);
				String[] table = new String[5];
				table[0] = ""+imd.getID();
				table[1] = imd.GetAtribute(1, 3).toString();
				table[2] =""+componetsAmount[i];
				table[3] = "" + (imd.GetAtribute(1, 3).multiply( new BigDecimal(componetsAmount[i])));
				profitLoss = profitLoss.subtract(new BigDecimal(table[3]));
				componetDTM.addRow(table);
			}
			
			for (int i = 0 ; i < resutls.length; i++) {
				ItemMarketData imd = dataHanderler.getItem(resutls[i]);
				String[] table = new String[5];
				table[0] = ""+imd.getID();
				table[1] = imd.GetAtribute(1, 3).toString();
				table[2] =""+resultsAmount[i];
				table[3] = "" + (imd.GetAtribute(1, 3).multiply( new BigDecimal(resultsAmount[i])));
				profitLoss = profitLoss.add(new BigDecimal(table[3]));
				resultsDTM.addRow(table);
			}
		}
		else
		{
			for (int i = 0 ; i < componets.length; i++) {
				ItemMarketData imd = dataHanderler.getItem(componets[i]);
				String[] table = new String[5];
				table[0] = ""+imd.getID();
				table[1] = imd.GetAtribute(0, 3).toString();
				table[2] =""+componetsAmount[i];
				table[3] = "" + (imd.GetAtribute(0, 3).multiply( new BigDecimal(componetsAmount[i])));
				profitLoss.subtract(new BigDecimal(table[3]));
				componetDTM.addRow(table);
			}
			for (int i = 0 ; i < resutls.length; i++) {
				ItemMarketData imd = dataHanderler.getItem(resutls[i]);
				String[] table = new String[5];
				table[0] = ""+imd.getID();
				table[1] = imd.GetAtribute(0, 3).toString();
				table[2] =""+resultsAmount[i];
				table[3] = "" + (imd.GetAtribute(0, 3).multiply( new BigDecimal(resultsAmount[i])));
				profitLoss.add(new BigDecimal(table[3]));
				resultsDTM.addRow(table);
			}
		}
		
//		String[] array = {"34","35","36"};
//		
//		ArrayList<ItemMarketData> imdArray = xmlHanderler.pullRead(array, "30000142");
//		dataHanderler.addItem(imdArray);
		
	}
	
	
	private void clearTables(){
		componetDTM.setRowCount(0);
		resultsDTM.setRowCount(0);
		profitLoss = BigDecimal.ZERO;
	}
	public ArrayList<ItemMarketData> getArray(){return dataHanderler.getArray();}
	public ItemMarketData getItem(int i){return dataHanderler.getItem(i);}
	public ListManagment getList(){return listManagment;}
	public void setComDTM(DefaultTableModel dtm){this.componetDTM = dtm;}
	public void setResDTM(DefaultTableModel dtm){this.resultsDTM = dtm;}
	public DefaultTableModel getComDTM() {return componetDTM;}
	public DefaultTableModel getResDTM() {return resultsDTM;}
	public BigDecimal getProfitLoss(){return profitLoss;}

}
//calls pulls and updates database with everything that the Gui needs and then passes it to the GUI