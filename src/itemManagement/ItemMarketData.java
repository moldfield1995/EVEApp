package itemManagement;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ItemMarketData {
	private int typeId;
	private BigDecimal[] buyArray;
	private BigDecimal[] sellArray;
	private boolean nullClass;

public ItemMarketData(int id,ArrayList<BigDecimal> buy, ArrayList<BigDecimal> sell )
{
	 typeId = id;
	 buyArray = new BigDecimal[4];
	 sellArray = new BigDecimal[4];
	 if (buy == null || sell == null)
	 {
		 nullClass = true;
	 }
	 else if (buy.size() != 4 || sell.size() != 4)
	 {
		 
	 }
	 else
	 {
		 for (int l = 0; l<4 && l <buy.size() && l< sell.size();l++)
		 {
				buyArray[l] = buy.get(l); 
				sellArray[l] = sell.get(l);
		 }
		 nullClass = false;
	 }
	 
	 
}

public boolean isNullClass() {
	return nullClass;
}

public BigDecimal GetAtribute(int array, int type)
{
	if (nullClass)
		return null;
	try
	{
	if (array == 0)
		return buyArray[type];
	else
		return sellArray[type];
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return BigDecimal.ZERO;
	}
}


public int getID(){return typeId;}

public BigDecimal[] GetArray(int id)
{
	if (nullClass)
		return null;
	
	if (id == 0)
		return buyArray;
	else
		return sellArray;
}

public String toString(){
	String s = null;
	if (nullClass)
	 s = "Null"; 
	else
	{
		//s = "ID:" + Integer.toString(typeId) + ",B1:" + BigDecimal.toString(buyArray[0])+ ",B2:" + BigDecimal.toString(buyArray[1])+
		//		",B3:" + BigDecimal.toString(buyArray[2])+ ",B4:" + BigDecimal.toString(buyArray[3])+ ",S1:" + BigDecimal.toString(sellArray[0])
		//		+ ",S2:" + BigDecimal.toString(sellArray[1])+ ",S3:" + BigDecimal.toString(sellArray[2])+ ",S4:" + BigDecimal.toString(sellArray[3]);
	}
	
	
	
	return s;
}

}


