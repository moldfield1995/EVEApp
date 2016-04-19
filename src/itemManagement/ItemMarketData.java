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
	String s;
	if (nullClass)
	 s = "Null"; 
	else
	{
		s = "ID:" + Integer.toString(typeId) + ",B1:" + buyArray[0].toString()+ ",B2:" + buyArray[1].toString()+
				",B3:" + buyArray[2].toString() + ",B4:" + buyArray[3].toString()+ ",S1:" + sellArray[0].toString()
				+ ",S2:" + sellArray[1].toString()+ ",S3:" + sellArray[2].toString()+ ",S4:" + sellArray[3].toString();
	}
	
	
	
	return s;
}

}


