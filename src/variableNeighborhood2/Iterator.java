package variableNeighborhood2;

import java.util.ArrayList;
import java.util.HashSet;

import Resource.Map;
import Resource.UAV;
import nsga2.Individual;

public class Iterator {

public static void iterator(Map map,ArrayList<Individual> arr) throws CloneNotSupportedException
{
	HashSet<String>hs=new HashSet<>();
	for(int i=0;i<arr.size();i++)
	{
		Individual a=arr.get(i);
		hs.add(a.fitness1+" "+a.fitness2);
	}
	
	//�Ե�ǰ������individual������������
	for(int i=0;i<arr.size();i++)
	{
		Individual in=arr.get(i);
		//ÿ��individual����һ�ٴ�
	  for(int j=0;j<100;j++)
	  {
		Individual clone=in.clone();
		
		Variation2.variation2(clone, map);
		//����ѵ���֧���Ž�ȥ
		if((clone.fitness1<in.fitness1&&clone.fitness2>=clone.fitness2)||(clone.fitness1>=in.fitness1&&clone.fitness2<clone.fitness2))
		{
			if(!hs.contains(clone.fitness1+" "+clone.fitness2))
			arr.add(clone);
			
			hs.add(clone.fitness1+" "+clone.fitness2);
		}
		
		//����ѵ��ȵ�ǰ���õĽ⣬����
		if(clone.fitness1<in.fitness1&&clone.fitness2<in.fitness2)
		{
			arr.add(clone);
			SelectTheFirstRank.selectTheFirstRank(arr);
			return;
		}
		
	  }
	  SelectTheFirstRank.selectTheFirstRank(arr);
	}
	
}
		
	
	public static void iterator2(Map map,ArrayList<Individual> arr) throws CloneNotSupportedException
	{
		HashSet<String>hs=new HashSet<>();
		for(int i=0;i<arr.size();i++)
		{
			Individual a=arr.get(i);
			hs.add(a.fitness1+" "+a.fitness2);
		}
		
		//�Ե�ǰ������individual������������
		for(int i=0;i<arr.size();i++)
		{
			Individual in=arr.get(i);
			//ÿ��individual����һ�ٴ�
		  for(int j=0;j<100;j++)
		  {
			Individual clone=in.clone();
			
			
			Variation3.variation3(clone, map);
			//����ѵ���֧���Ž�ȥ
			if((clone.fitness1<in.fitness1&&clone.fitness2>=clone.fitness2)||(clone.fitness1>=in.fitness1&&clone.fitness2<clone.fitness2))
			{
				if(!hs.contains(clone.fitness1+" "+clone.fitness2))
				arr.add(clone);
				
				hs.add(clone.fitness1+" "+clone.fitness2);
			}
			
			//����ѵ��ȵ�ǰ���õĽ⣬����
			if(clone.fitness1<in.fitness1&&clone.fitness2<in.fitness2)
			{
				arr.add(clone);
				SelectTheFirstRank.selectTheFirstRank(arr);
				return;
			}
			
		  }
		  SelectTheFirstRank.selectTheFirstRank(arr);
			
		}
}
}
