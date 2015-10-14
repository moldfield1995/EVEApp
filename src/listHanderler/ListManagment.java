package listHanderler;

import javax.swing.DefaultListModel;

public class ListManagment extends DefaultListModel<ListItems> {
	public ListManagment(){
		super();

		super.addElement(new ListItems(new int[]{1230},new int[]{100},new int[]{34}, new int[]{435},"veldspar" ));
	}
	

	public void addElement(int[] componets,int[] cAmount, int[] results, int[]rAmount, String name)
	{
		super.addElement(new ListItems(componets, cAmount, results, rAmount, name));
	}
	
	
}
