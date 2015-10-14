package listHanderler;



public class ListItems {
	private String listName;
	private int[] componetsList;
	private int[] componetsAmount;
	private int[] resutlsList;
	private int[] resutlsAmount;
	
	ListItems(int[] componets, int[] cAmount, int[] results, int[] rAmount, String name){
		componetsList = componets;
		componetsAmount = cAmount;
		resutlsList = results;
		resutlsAmount = rAmount;
		listName = name;
	}
	public int[] getComponets(){return componetsList;}
	public int[] getComponetsAmounts(){return componetsAmount;}
	public int[] getResults(){return resutlsList;}
	public int[] getResultsAmounts(){return resutlsAmount;}
	public String toString(){
		return listName;
	}
}
