package itemManagement;

import java.util.ArrayList;

public class ItemHanderler {
	private ArrayList<ItemMarketData> sortedList;
	private ArrayList<ItemMarketData> unSortedList;
	// Everything used by sort Lists, bulk memory asingment
	private	boolean sorted = false;
	private	int currentId;
	private	int lastIndex = 0;
	private	boolean higher = true;
	private	int avarageSeparation = 0;
	private	int maxSize = 0;
	private	int currentItterations = 0;
	private	int maxItterations = 1000;
	private	int totalSeparation =0;
	private	int distanceTest =0;
	private	int amountSorted = 0;



	public ItemHanderler()
	{
		sortedList = new ArrayList<ItemMarketData>();
		unSortedList = new ArrayList<ItemMarketData>();
	}

	public void addItem(ArrayList<ItemMarketData> newItems)
	{
		if (!unSortedList.isEmpty())
		{
			newSortList();
		}

		unSortedList.addAll(newItems);
		newSortList();
	}


	public ArrayList<ItemMarketData> getArray(){return sortedList;}

	public ItemMarketData getItem(int currentId)
	{
		if(sortedList.size() == 0)
		{
			return null;
		}
		if(sortedList.size() == 1)
		{
			if(currentId == sortedList.get(0).getID())
			{
				return sortedList.get(0);
			}
			else
				return null;
		}

		if(sortedList.get(0).getID() > currentId)
		{
			return null;
		}
		if(sortedList.get(sortedList.size() - 1).getID() < currentId)
		{
			return null;
		}
		lastIndex = 0;
		int tests = 0;
		maxSize = sortedList.size() - 1;
		boolean topTriger = false;
		higher = true;
		avarageSeparation = 0;
		totalSeparation = sortedList.get(maxSize).getID() - sortedList.get(0).getID();
		avarageSeparation = totalSeparation / maxSize;
		
		if(currentId - sortedList.get(0).getID() ==1 || avarageSeparation ==0 )
		{
			distanceTest = 1;
		}
		else
		{
			distanceTest = (currentId - sortedList.get(0).getID()) / avarageSeparation;
		}
		//System.out.println("DT:"+ distanceTest + "i:"+i);
		if (distanceTest <= maxSize)
		{
			if(sortedList.get(maxSize).getID()>=currentId)
			{
				higher = false;
				lastIndex = maxSize;
			}
		}
		else
		{
			if(distanceTest >= sortedList.size())
				distanceTest = sortedList.size()-1;
			lastIndex = distanceTest;

			if(sortedList.get(distanceTest).getID() >= currentId)
			{
				higher = false;
			} // else higher is true (default state)
		}


		while (tests <= 10000) {
			if(sortedList.get(lastIndex).getID() == currentId)
			{
				return sortedList.get(lastIndex);
			}
			if (lastIndex != 0 && lastIndex != sortedList.size()-1)
				if((sortedList.get(lastIndex).getID() > currentId && sortedList.get(lastIndex - 1).getID() < currentId) ||
						(sortedList.get(lastIndex).getID() < currentId && sortedList.get(lastIndex + 1).getID() > currentId)){
					return null;
				}
			if (higher)
			{
				if (lastIndex == maxSize)
				{
					if (topTriger)
					{
						System.out.println("Unable to find ID:" + currentId + " retruning null: DataHanderler.getItem() TT");
						return null;
					}
					higher = false;
					topTriger = true;
				}
				else
					lastIndex++;
			}
			else
			{
				if (lastIndex == 0)
				{
					if (topTriger)
					{
						System.out.println("Unable to find ID:" + currentId + " retruning null: DataHanderler.getItem() TT");
						return null;
					}
					higher = false;
					topTriger = true;
				}
				else
					lastIndex--;
			}

		}

		System.out.println("Unable to find ID:" + currentId + " retruning null: DataHanderler.getItem()");
		return null;
	}


	private void newSortList()
	{
		 currentItterations = 0;
		 maxItterations = 1000;
		 amountSorted = 0;
		if(sortedList.size() <=1)
		{
			if (sortedList.size() == 0)
			{
				sortedList.add(unSortedList.get(0));
				amountSorted++;
			}

			if (sortedList.size() == 1)
			{
				if (unSortedList.get(amountSorted).getID() > sortedList.get(0).getID())
				{
					sortedList.add(unSortedList.get(amountSorted));
				}
				else
				{
					sortedList.add(0, unSortedList.get(amountSorted));
				}

				amountSorted++;
			}
		}
		
		while(amountSorted < unSortedList.size())
		{
			 sorted = false;
			currentId = unSortedList.get(amountSorted).getID();
			lastIndex = 0;
			higher = true;
			avarageSeparation = 0;
			maxSize = sortedList.size() - 1;


			//First check
			if(sortedList.get(0).getID() >= currentId)
			{
				if(sortedList.get(0).getID() == currentId)
				{
					sortedList.remove(0);
					maxSize--;
				}
				//System.out.println(currentId +"was sorted at the bottem bellow:"+sortedList.get(0).getID() );
				//current unsorted is the lowest ID
				sortedList.add(0, unSortedList.get(amountSorted));
				sorted = true;
			}
			else
			{
				//System.out.println(sortedList.get(0).getID() + " was lower than:" + currentId + " ID pull test:" + unSortedList.get(i).getID() );
				higher = true;
			}

			if (sortedList.get(maxSize).getID() <= currentId)
			{
				if(sortedList.get(maxSize).getID() == currentId){
					sortedList.remove(maxSize);
					maxSize--;
				}
				//System.out.println(currentId+"was sorted to the top");
				sortedList.add(maxSize +1 ,unSortedList.get(amountSorted));
				sorted = true;
			}



			if(!sorted)
			{
				totalSeparation = sortedList.get(maxSize).getID() - sortedList.get(0).getID();
				avarageSeparation = totalSeparation / maxSize;
				if(currentId - sortedList.get(0).getID() ==1 || avarageSeparation ==0)
				{
					distanceTest = 1;
				}
				else
				{
					distanceTest = (currentId - sortedList.get(0).getID()) / avarageSeparation;
				}
				//System.out.println("DT:"+ distanceTest + "i:"+i);
				if (distanceTest <= maxSize)
				{
					if(sortedList.get(maxSize).getID()>=currentId)
					{
						higher = false;
						lastIndex = maxSize;
					}
				}
				else
				{
					lastIndex = distanceTest;
					if(sortedList.get(distanceTest).getID() >= currentId)
					{
						higher = false;
					} // else higher is true (default state)
				}
			}
			//System.out.println("distance test =" + distanceTest + " the sum of the ID was " + (currentId - sortedList.get(0).getID()) + " AS:" + avarageSeparation );


			if (lastIndex == 0 && !sorted)
			{
				lastIndex = 1;
				//System.out.println("last index was 0");
			}

			while (!sorted && currentItterations <= maxItterations)
			{
				//System.out.println("LI:"+lastIndex+ " MS:" + maxSize);
				if (sortedList.get(lastIndex).getID() == currentId)
				{
					//System.out.println("sortedStage1");
					sortedList.remove(lastIndex);
					sortedList.add(lastIndex,unSortedList.get(amountSorted));
					sorted = true;
				}
				else if(higher)
				{
					if(sortedList.get(lastIndex).getID() <= currentId)
					{ 
						if(sortedList.get(lastIndex+1).getID()>= currentId )
						{
							if(sortedList.get(lastIndex).getID() == currentId){
								sortedList.remove(lastIndex);
								maxSize--;
							}
							else if(sortedList.get(lastIndex+1).getID()>= currentId)
							{
								sortedList.remove(lastIndex+1);
								maxSize--;
							}
							//System.out.println("sortedStage2");
							sortedList.add(lastIndex , unSortedList.get(amountSorted));
							sorted = true;
						} //else we need to go higher
						lastIndex++;
					}
					else
					{
						higher = false;
					}

				}
				if (!sorted && !higher)
				{
					if(sortedList.get(lastIndex).getID() >= currentId)
					{
						if (sortedList.get(lastIndex-1).getID() <= currentId)
						{
							if(sortedList.get(lastIndex).getID() == currentId){
								sortedList.remove(lastIndex);
								maxSize--;
							}
							else if(sortedList.get(lastIndex-1).getID() == currentId)
							{
								sortedList.remove(lastIndex-1);
								maxSize--;
								lastIndex--;
							}
							//System.out.println("sortedStage3");
							sortedList.add(lastIndex, unSortedList.get(amountSorted));
							sorted = true;
						} //else we need to go lower
						lastIndex--;
					}
					else
					{
						higher = true;
					}
				}


				currentItterations++;
			}

			//System.out.println("Max itt:"+ currentItterations +" I:"+ i );

			if (currentItterations >= maxItterations )
			{
				System.out.println("Max itterations reached");
				System.out.println("The current Id was: " + unSortedList.get(amountSorted).getID());
				System.out.println("the current index was: " + lastIndex + ". The Id of the index was "+ sortedList.get(lastIndex).getID());

				if(higher)
					System.out.println("The system was trying to go Higher and the id above was " + sortedList.get(lastIndex+1).getID());
				else
				{
					System.out.print("The system was trying to go Lower and the id below was");
					if (lastIndex-1 >= 0)
					{
						System.out.print("" + sortedList.get(amountSorted -1).getID());
					}
					else
						System.out.print("was Unavalabe at the index was 0");
				}
				System.out.println("i ==" + amountSorted);
			}

			currentItterations = 0;
			//at the end of sorting an element
			amountSorted++;

		}
		unSortedList.clear();

	}


}





