package BatchTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import compare.PF;
import compare.PFRange;
import compare.PFSet;
import compare.SelectTheFirstRank;



public class ProcessingData2 {
	//处理在同1个文件的数据//处理组件校准
	public static List<List<String>> read(String filePath) throws IOException
	{
		File file = new File(filePath);
	    BufferedReader reader;
	    reader = new BufferedReader(new FileReader(file));
	    String s;
	    List<List<String>>result=new ArrayList<List<String>>();
	    while((s=reader.readLine())!=null)
	    {
	    	ArrayList<String>temp=new ArrayList<String>();
	        temp.add(s);
	    	while(!(s=reader.readLine()).equals(""))
	    	{
	    		temp.add(s);
	    	}
	    	result.add(temp);
	    	reader.readLine();
	    	
	    }
	    reader.close();
	    return result;
	}

	
	public static void write(List<List<String>>result) throws IOException
	{
		String[] map= {"att48uniform","att532uniform","node60uniform","node150uniform"};
		//"variation2",
		String[] iterator= {"variation4","ReduceTime"};
	    String[]  subIterator= {"sa","no"};
	    String[] speed= {"22","30","40","50"};
	    String[]  deadline= {"5","10","15"}; 
	    //传输时长因子
	    float[] transmitFactor= {(float) 0.25,(float) 0.5,(float) 0.75};
	  //自有无人机的数量因子  任务数量n/自有无人机数量=factor  10,15,20
	    int[] selfOwnUAVFactor= {10,15,20};
	    
	    HashMap<String,Integer>hm=new HashMap<>();
	    for(int i=0;i<result.size();i++)
	    {
	    	hm.put(result.get(i).get(0), i);
//	    	hm.put(result.get(i).get(0), result.get(i));
	    }
	    List<String>list;
	    for(int a=0;a<map.length;a++)
	    	for(int b=0;b<speed.length;b++)
	    		for(int c=0;c<deadline.length;c++)
	    			for(int f=0;f<transmitFactor.length;f++)
	    				for(int g=0;g<selfOwnUAVFactor.length;g++)
	    		{
	    			PFSet[]p=new PFSet[iterator.length*subIterator.length];
	    			int i=0;
	    			for(int d=0;d<iterator.length;d++)
	    				for(int e=0;e<subIterator.length;e++)
	    				{
	    					String map1=map[a];
	    					String speed1=speed[b];
	    					String deadline1=deadline[c];
	    					float transmitFactor1=transmitFactor[f];
	    				    String iterator1=iterator[d];
	    				    String subIterator1=subIterator[e];
	    				    int selfOwnUAVFactor1=selfOwnUAVFactor[g];
	    				     String name=map1+" "+"NSGA2"+" "+speed1+" "+deadline1+" "+subIterator1+" "+iterator1+" "+selfOwnUAVFactor1+" "+transmitFactor1;
	    				     if(!hm.containsKey(name))continue;
	    				     list=result.get(hm.get(name));
	    				     p[i]=GeneratePfSet(list,name);
	    				     PFRange pfRange1=new PFRange();
	    				 	pfRange1.firstupdate(p[i]);
	    				 	p[i++].computeNormalization(pfRange1);
	    				 	
	    				}
	    			 processData(p);
	    		}
	}
	
	
	public static void processData(PFSet[]p) throws IOException
	{
		ArrayList<ArrayList>arr=new ArrayList<>();
		PFSet pi=new PFSet();
		for(int i=0;i<p.length;i++)
		{
			if(p[i]==null)continue;
			String[]strs=p[i].set.get(0).getContributor().split(" +");
			ArrayList array=new ArrayList();
			arr.add(array);
			//先放名字
			array.add(p[i].set.get(0).getContributor());
			switch(strs[0])
			{
			case "att48uniform":
				array.add(0);
				break;
			case "node60uniform":
			    array.add(1);
			    break;
			case "node150uniform":
				array.add(2);
				break;
			case "att532uniform":
				array.add(3);
				break;
			}
			
			switch(strs[2])
			{
			case "22":
				array.add(0);
				break;
			case "30":
			    array.add(1);
			    break;
			case "40":
				array.add(2);
				break;
			case "50":
				array.add(3);
				break;
			}
			
			switch(strs[3])
			{
			case "5":
				array.add(0);
				break;
			case "10":
			    array.add(1);
			    break;
			case "15":
				array.add(2);
				break;
			}
			
			switch(strs[4])
			{
			case "sa":
				array.add(0);
				break;
			case "no":
			    array.add(1);
			    break;
			}
			
			
			switch(strs[5])
			{
			case "variation4":
				array.add(0);
				break;
			case "ReduceTime":
				array.add(1);
				break;
			case "variation2":
				array.add(2);
				break;
			}
			
			switch(strs[6])
			{
			case "10":
				array.add(0);
				break;
			case "15":
				array.add(1);
				break;
			case "20":
				array.add(2);
				break;
			}
			
			switch(strs[7])
			{
			case "0.25":
				array.add(0);
				break;
			case "0.5":
				array.add(1);
				break;
			case "0.75":
				array.add(2);
				break;
			}
			
			//将本组的pfset取非支配解
			pi.set=SelectTheFirstRank.selectTheFirstRank(pi, p[i]);
//			pi.set.addAll(p[i].set);
		}
		  
		for(int i=0;i<arr.size();i++)
		{
			if(arr.size()==0)break;
			ArrayList array=arr.get(i);
			array.add(p[i].AverageQuality());
			array.add(p[i].distanceMetricAvg2(pi));
			array.add(p[i].distanceMetricMax2(pi));
			array.add(p[i].MaximumSpread(pi));
			array.add((double)p[i].intersection(pi).set.size()/(double)pi.set.size());
		}
		FileWriter writer=new FileWriter("单方法结果.txt",true);
		for(int i=0;i<arr.size();i++)
		{
			if(arr.size()==0)break;
			ArrayList tem=arr.get(i);
			for(int j=0;j<tem.size();j++)
			{
				if(tem.get(j).getClass().equals(Double.class))
				{
					writer.write((double) tem.get(j)+"\t");
				}else if(tem.get(j).getClass().equals(Integer.class))
				{
					writer.write((int) tem.get(j)+"\t");
				}else
				writer.write((String) tem.get(j)+"\t");
			}
			
			writer.write("\n");
		}

		writer.close();
	}
	
	public static PFSet GeneratePfSet(List<String>temp,String name)
	{
		PFSet pfSet=new PFSet();
		for(int i=1;i<temp.size();i++)
		{
			String []strs=temp.get(i).split(" ");
			PF pf=new PF(Double.valueOf(strs[0]),Double.valueOf(strs[2]),name);
			pfSet.set.add(pf);
		}
		return pfSet;
	}
	
	
     public static void main(String[] args) throws IOException {
    	 List<List<String>>result=read("D:\\java代码\\nsga2\\piresult.txt");
    	 for(List<String> list : result)
    	 {
    		 System.out.println(list);
    	 }
    	 write(result);
	}
	
}
