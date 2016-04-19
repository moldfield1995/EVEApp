package itemManagement;

import java.util.Comparator;
/**
 * Needs testing may be more optimal for sorting.
 * @author Matthew Oldfield
 *
 */
public class CustomCompairor implements Comparator<ItemMarketData> {

	@Override
	public int compare(ItemMarketData o1, ItemMarketData o2) {
		// TODO Auto-generated method stub
		return o1.getID() - o2.getID();
	}
 
}
