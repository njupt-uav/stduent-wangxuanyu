package greedy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import BatchTest.ChangeParameter;
import Resource.Map;
import compare.PF;
import compare.PFSet;
import kmeans.Kmean;
import nsga2.Individual;
import tool.Read;
import tool.Read2;

public class GreedyMain {
public static void main(String[] args) throws IOException, CloneNotSupportedException {
	    Map map=new Map();
		Read2.read2( map);
		System.out.println(1);
		Population father=new Population();
		father.init(map);
		System.out.println(2);
		Date before=new Date();
		Date now=new Date();
		int count=0;
		for(int i=0;i<1000;i++)
		{
			System.out.println(i);
			father=Iterator.iterator(father,map);	
			now=new Date();
//			if((now.getTime()-before.getTime())/1000>600)break;
			
			//如果连续二十次没有优化，说明已经进入了局部最优，可以跳出了
			if(Iterator.flag==false)count++;
			else
			{
				count=0;
			}
			if(count>20)break;
			
//			System.out.println(Iterator.T);
		}
		ArrayList<Individual> firstRank=new ArrayList<>();
		firstRank=SelectTheFirstRank.selectTheFirstRank(father);
		for(int i=0;i<firstRank.size();i++)
		{
			System.out.println(firstRank.get(i).fitness1+" "+firstRank.get(i).fitness2+" "+firstRank.get(i).uav.size());
		}
}

//批量实验主要运行程序
public static void greedymain(String s) throws IOException, CloneNotSupportedException {
	        Map map=new Map();
			Read2.read2( map);
			System.out.println(1);
			Population father=new Population();
			father.init(map);
			System.out.println(2);
			Date before=new Date();
			Date now=new Date();
			int count=0;
			for(int i=0;i<1000;i++)
			{
				System.out.println(s+"第"+i+"次循环");
				father=Iterator.iterator(father,map);	
				
				now=new Date();
//				if((now.getTime()-before.getTime())/1000>600)break;
				
				//如果连续二十次没有优化，说明已经进入了局部最优，可以跳出了
				if(Iterator.flag==false)count++;
				else
				{
					count=0;
				}
				if(count>20)break;
				
//				System.out.println(Iterator.T);
			}
			ArrayList<Individual> firstRank=new ArrayList<>();
			firstRank=SelectTheFirstRank.selectTheFirstRank(father);
		FileWriter out=new FileWriter(ChangeParameter.fileOutPath,true);
		out.write(s);
		out.write("\r\n");
		for(int  i=0;i<firstRank.size();i++)
		{
			Individual in=firstRank.get(i);
			out.write(in.fitness1+"  "+in.fitness2);
			out.write("\r\n");
		}
		out.write("\r\n");
		out.write("\r\n");
		out.close();
		
}
}
