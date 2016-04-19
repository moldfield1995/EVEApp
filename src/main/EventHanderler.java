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

/**
 * This class is the entry point for the GUI. It dose all the process and then
 * updates the values of the GUI
 * 
 * @author Matthew Oldfield
 * @version 1.0
 *
 */
public class EventHanderler {
	private XmlHanderler xmlHanderler;
	private ItemHanderler dataHanderler;
	private ListManagment listManagment;
	private DefaultTableModel componetDTM;
	private DefaultTableModel resultsDTM;
	private BigDecimal profitLoss;

	public EventHanderler() {
		xmlHanderler = new XmlHanderler();
		dataHanderler = new ItemHanderler();
		listManagment = new ListManagment();
		profitLoss = new BigDecimal(0.00);
	}

	// take the input and dose pulls if required
	public void Update(int listID, boolean buy) {
		if (listID < 0) {
			return;
		}
		// Rest the tables if needed
		if (componetDTM.getRowCount() > 0) {
			clearTables();
		}
		// Get Items and format it for pulling
		ListItems items = listManagment.getElementAt(listID);
		int[] componets = items.getComponets();
		int[] componetsAmount = items.getComponetsAmounts();
		int length = componets.length;
		int[] resutls = items.getResults();
		int[] resultsAmount = items.getResultsAmounts();
		length = length + resutls.length;
		ArrayList<Integer> pullsNeeded = new ArrayList<Integer>(); // Needs looking in to
		// Fine the Items we don't have
		for (int j : componets)
			if (dataHanderler.getItem(j) == null)
				pullsNeeded.add(j);
		for (int m : resutls)
			if (dataHanderler.getItem(m) == null)
				pullsNeeded.add(m);
		//Check if we need to pull anything
		int pullsize = pullsNeeded.size();
		if (pullsize > 0) {
			String[] pullArray = new String[pullsize];
			for (int a = 0; a < pullsize; a++) {
				pullArray[a] = "" + pullsNeeded.get(a);
			}
			pullsNeeded = null;
			dataHanderler.addItem(xmlHanderler.pullRead(pullArray, null));
		}
		// Tests if we are getting the buy or sell price
		int buyOrSell;
		if (buy)
			buyOrSell = 0;
		else
			buyOrSell = 1;
		//Set up the the tables
		for (int i = 0; i < componets.length; i++) {
			ItemMarketData imd = dataHanderler.getItem(componets[i]);
			String[] table = new String[5];
			table[0] = "" + imd.getID();
			table[1] = imd.GetAtribute(buyOrSell, 3).toString();
			table[2] = "" + componetsAmount[i];
			table[3] = "" + (imd.GetAtribute(buyOrSell, 3).multiply(new BigDecimal(componetsAmount[i])));
			profitLoss = profitLoss.subtract(new BigDecimal(table[3]));
			componetDTM.addRow(table);
		}
		for (int i = 0; i < resutls.length; i++) {
			ItemMarketData imd = dataHanderler.getItem(resutls[i]);
			String[] table = new String[5];
			table[0] = "" + imd.getID();
			table[1] = imd.GetAtribute(buyOrSell, 3).toString();
			table[2] = "" + resultsAmount[i];
			table[3] = "" + (imd.GetAtribute(buyOrSell, 3).multiply(new BigDecimal(resultsAmount[i])));
			profitLoss = profitLoss.add(new BigDecimal(table[3]));
			resultsDTM.addRow(table);
		}

	}

	private void clearTables() {
		componetDTM.setRowCount(0);
		resultsDTM.setRowCount(0);
		profitLoss = BigDecimal.ZERO;
	}

	public ArrayList<ItemMarketData> getArray() {
		return dataHanderler.getArray();
	}

	public ItemMarketData getItem(int i) {
		return dataHanderler.getItem(i);
	}

	public ListManagment getList() {
		return listManagment;
	}

	public void setComDTM(DefaultTableModel dtm) {
		this.componetDTM = dtm;
	}

	public void setResDTM(DefaultTableModel dtm) {
		this.resultsDTM = dtm;
	}

	public DefaultTableModel getComDTM() {
		return componetDTM;
	}

	public DefaultTableModel getResDTM() {
		return resultsDTM;
	}

	public BigDecimal getProfitLoss() {
		return profitLoss;
	}

}
// calls pulls and updates database with everything that the Gui needs and then
// passes it to the GUI