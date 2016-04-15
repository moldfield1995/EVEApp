package listHanderler;

import javax.swing.DefaultListModel;
//current Hard coded List for the Ores. will be looking in to a file storage system.
@SuppressWarnings("serial")
public class ListManagment extends DefaultListModel<ListItems> {
	public ListManagment(){
		super();
		LoadLongList();

	}
	

	public void addElement(int[] componets,int[] cAmount, int[] results, int[]rAmount, String name)
	{
		super.addElement(new ListItems(componets, cAmount, results, rAmount, name));
	}
	
	private void LoadLongList()
	{
		super.addElement(new ListItems(new int[]{1230},new int[]{100},new int[]{34}, new int[]{415},"Veldspar" ));
		super.addElement(new ListItems(new int[]{17470},new int[]{100},new int[]{34}, new int[]{436},"Concentrated Veldspar" ));
		super.addElement(new ListItems(new int[]{17471},new int[]{100},new int[]{34}, new int[]{457},"Dense Veldspar" ));
		super.addElement(new ListItems(new int[]{1228},new int[]{100},new int[]{34,35}, new int[]{346,173},"Scordite" ));
		super.addElement(new ListItems(new int[]{17463},new int[]{100},new int[]{34,35}, new int[]{364,182},"Condensed Scordite" ));
		super.addElement(new ListItems(new int[]{17464},new int[]{100},new int[]{34,35}, new int[]{381,190},"Massive Scordite" ));
		super.addElement(new ListItems(new int[]{1224},new int[]{100},new int[]{34,35,36,38}, new int[]{351,25,50,5},"Pyroxeres" ));
		super.addElement(new ListItems(new int[]{17459},new int[]{100},new int[]{34,35,36,38}, new int[]{369,26,53,5},"Solid Pyroxeres" ));
		super.addElement(new ListItems(new int[]{17460},new int[]{100},new int[]{34,35,36,38}, new int[]{387,27,55,5},"Viscous Pyroxeres" ));
		super.addElement(new ListItems(new int[]{18},new int[]{100},new int[]{34,35,36}, new int[]{107,213,107},"Plagioclase" ));
		super.addElement(new ListItems(new int[]{17455},new int[]{100},new int[]{34,35,36}, new int[]{113,224,113},"Azure Plagioclase" ));
		super.addElement(new ListItems(new int[]{17456},new int[]{100},new int[]{34,35,36}, new int[]{118,235,118},"Rich Plagioclase" ));
		super.addElement(new ListItems(new int[]{1227},new int[]{100},new int[]{34,35,37}, new int[]{85,34,85},"Omber" ));
		super.addElement(new ListItems(new int[]{17867},new int[]{100},new int[]{34,35,37}, new int[]{90,36,90},"Silvery Omber" ));
		super.addElement(new ListItems(new int[]{17868},new int[]{100},new int[]{34,35,37}, new int[]{94,38,94},"Golden Omber" ));
		super.addElement(new ListItems(new int[]{20},new int[]{100},new int[]{34,37,38}, new int[]{134,267,134},"Kernite" ));
		super.addElement(new ListItems(new int[]{17452},new int[]{100},new int[]{34,37,38}, new int[]{141,281,141},"Luminous Kernite" ));
		super.addElement(new ListItems(new int[]{17453},new int[]{100},new int[]{34,37,38}, new int[]{148,294,148},"Fiery Kernite" ));
		super.addElement(new ListItems(new int[]{1226},new int[]{100},new int[]{36,38,39}, new int[]{350,75,8},"Jaspet" ));
		super.addElement(new ListItems(new int[]{17448},new int[]{100},new int[]{36,38,39}, new int[]{367,78,8},"Pure Jaspet" ));
		super.addElement(new ListItems(new int[]{17449},new int[]{100},new int[]{36,38,39}, new int[]{385,82,8},"Pristine Jaspet" ));
		super.addElement(new ListItems(new int[]{1231},new int[]{100},new int[]{34,37,38,39}, new int[]{2200,100,120,15},"Hemorphite" ));
		super.addElement(new ListItems(new int[]{17444},new int[]{100},new int[]{34,37,38,39}, new int[]{2310,105,126,16},"Vivid Hemorphite" ));
		super.addElement(new ListItems(new int[]{17445},new int[]{100},new int[]{34,37,38,39}, new int[]{2420,110,132,17},"Radiant Hemorphite" ));
		super.addElement(new ListItems(new int[]{21},new int[]{100},new int[]{35,37,38,39}, new int[]{1100,200,100,19},"Hedbergite" ));
		super.addElement(new ListItems(new int[]{17440},new int[]{100},new int[]{35,37,38,39}, new int[]{1155,210,105,20},"Vitric Hedbergite" ));
		super.addElement(new ListItems(new int[]{17441},new int[]{100},new int[]{35,37,38,39}, new int[]{1270,220,110,21},"Glazed Hedbergite" ));
		super.addElement(new ListItems(new int[]{1229},new int[]{100},new int[]{35,36,37}, new int[]{2200,2400,300},"Gneiss" ));
		super.addElement(new ListItems(new int[]{17865},new int[]{100},new int[]{35,36,37}, new int[]{2310,2520,315},"Iridescent Gneiss" ));
		super.addElement(new ListItems(new int[]{17866},new int[]{100},new int[]{35,36,37}, new int[]{2420,2640,330},"Prismatic Gneiss" ));
		super.addElement(new ListItems(new int[]{1232},new int[]{100},new int[]{34,36,37}, new int[]{10000,1600,120},"Dark Ochre" ));
		super.addElement(new ListItems(new int[]{17436},new int[]{100},new int[]{34,36,37}, new int[]{10500,1680,126},"Onyx Ochre" ));
		super.addElement(new ListItems(new int[]{17437},new int[]{100},new int[]{34,36,37}, new int[]{11000,1760,132},"Obsidian Ochre" ));
		super.addElement(new ListItems(new int[]{19},new int[]{100},new int[]{34,35,36,37}, new int[]{56000,12050,2100,450},"Spodumain" ));
		super.addElement(new ListItems(new int[]{17466},new int[]{100},new int[]{34,35,36,37}, new int[]{58800,12652,2205,472},"Bright Spodumain" ));
		super.addElement(new ListItems(new int[]{17467},new int[]{100},new int[]{34,35,36,37}, new int[]{61600,13255,2310,495},"Gleaming Spodumain" ));
	}
	
	
}
