package BatchTest;

import compare.PF;
import compare.PFRange;
import compare.PFSet;

import java.io.*;
import java.util.*;



public class ProcessingData3 {
	//处理在同一个文件的数据//处理不同算法之间的比较
	public static List<List<String>> read(String filePath) throws IOException
	{
		File file = new File(filePath);
	    BufferedReader reader = null;
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
		//"att48uniform","att532uniform",
		String[]  map= {"att48uniform","node60uniform","node150uniform"};
		String[]  iterator= {"ReduceTime"};
	    String[]  subIterator= {"sa"};
	    String[]  speed= {"50","22","30","40"};
	    String[]  deadline= {"5","10","15"}; 
	    String[]  MainMethods= {"SA","GREEDY","VN","NSGA2"};
	    int[] priceMagnificationEnum= {2,4,6};
		  //自有无人机的数量因子  任务数量n/自有无人机数量=factor  10,15,20
		    int[] selfOwnUAVFactor= {10,15,20};
		  String[] timeMagnification= {"0.25", "0.5","0.75","rand"};
	    HashMap<String,Integer>hm=new HashMap<>();
	    for(int i=0;i<result.size();i++)
	    {
	    	hm.put(result.get(i).get(0), i);
	    }
	    
	    List<String>listt;
//	    for(int f=0;f<2;f++)//第几组数据
	    for(int a=0;a<map.length;a++)
	    	for(int b=0;b<speed.length;b++)
	    		for(int c=0;c<deadline.length;c++)
	    			for(int h=0;h<priceMagnificationEnum.length;h++)
	    				for(int j=0;j<selfOwnUAVFactor.length;j++)
	    					for(int k=0;k<timeMagnification.length;k++)
	    		{
	    			PFSet[]p=new PFSet[iterator.length*subIterator.length*MainMethods.length];
	    			int i=0;
	    			for(int d=0;d<iterator.length;d++)
	    				for(int e=0;e<subIterator.length;e++) 
	    					 for(int g=0;g<MainMethods.length;g++)
	    			       {
	    						 String map1=map[a];
	 	    					String speed1=speed[b];
	 	    					String deadline1=deadline[c];
	 	    					int transmitFactor1=priceMagnificationEnum[h];
	 	    				    String iterator1=iterator[d];
	 	    				    String subIterator1=subIterator[e];
	 	    				    int selfOwnUAVFactor1=selfOwnUAVFactor[j];
	 	    				    String MainMethod=MainMethods[g];
	 	    				    String timeMagn=timeMagnification[k];
	 	    				   String name=map1+" "+MainMethod+" "+speed1+" "+deadline1+" "+subIterator1+" "+iterator1+" "+selfOwnUAVFactor1+" "+transmitFactor1+" "+timeMagn;
//	 	    				   name="att48uniform SA 50 5 no ReduceTime 15 0.25";
	    				    
	 	    				    if(!hm.containsKey(name))continue;
	    				     listt=result.get(hm.get(name));
	    				     p[i]=GeneratePfSet(listt,name);
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
			
			switch(strs[1])
			{
			case"SA":
				array.add(0);
				break;
			case"VN":
				array.add(1);
				break;
			case "GREEDY":
				array.add(2);
				break;
			case "NSGA2":
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
			
			
			switch(strs[5])
			{
			case "variation4":
				array.add(0);
				break;
			case "ReduceTime":
				array.add(1);
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
			case "2":
				array.add(0);
				break;
			case "4":
				array.add(1);
				break;
			case "6":
				array.add(2);
				break;
			}
			// 0.25", "0.5","0.75","rand
			switch(strs[8]) {
			     case"0.25" :
			         array.add(0);
			         break;
			     case"0.5":
			    	 array.add(1);
			    	 break;
			     case"0.75":
			    	 array.add(2);
			    	 break;
			     case"rand":
			    	 array.add(3);
			    	 break;
			}
//			if(strs[1].equals("NSGA2"))
//			{
//				array.add(1);
//				array.add(0);
//				array.add(0);
//				array.add(0);
//			}
//			else if(strs[1].equals("SA"))
//			{
//				array.add(0);
//				array.add(1);
//				array.add(0);
//				array.add(0);
//			}
//			else if(strs[1].equals("GREEDY"))
//			{
//				array.add(0);
//				array.add(0);
//				array.add(1);
//				array.add(0);
//			}
//			else //VN
//			{
//				array.add(0);
//				array.add(0);
//				array.add(0);
//				array.add(1);
//			}
//			pi.set=SelectTheFirstRank.selectTheFirstRank(pi, p[i]);
			pi.set.addAll(p[i].set);
		}
		  
		for(int i=0;i<arr.size();i++)
		{
			if(arr.size()==0)break;
			ArrayList array=arr.get(i);
			array.add(p[i].AverageQuality());
			array.add(p[i].distanceMetricAvg2(pi)<0?-p[i].distanceMetricAvg2(pi)/3:p[i].distanceMetricAvg2(pi)/2);
			array.add(p[i].distanceMetricMax2(pi));
			array.add(p[i].MaximumSpread(pi));
			array.add((double)p[i].intersection(pi).set.size()/(double)pi.set.size());
		}
		FileWriter writer=new FileWriter("main method compare2021.9.4.txt",true);
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
			
			if(strs.length<3) {
				continue;
			}
			PF pf=new PF(Double.valueOf(strs[0]),Double.valueOf(strs[2]),name);
			pfSet.set.add(pf);
		}
		return pfSet;
	}
	
	
     public static void main(String[] args) throws IOException {
    	 List<List<String>>result=read("D:\\批量实验结果\\2021-9-4汇总批量实验结果.txt");
    	 for(int i=0;i<result.size();i++) {
    		 ArrayList<String> arr=new ArrayList<String>(new HashSet<String>(result.get(i)));
    		 Collections.sort(arr, new Comparator< String >() {
    			              @Override
    			              public int compare(String o1, String o2) {
    			                String[]o11= o1.split(" +");
    			                String[]o22=o2.split(" +");
    			                return o22.length-o11.length;
    			              }
    			          });
    		 result.set(i, arr);
    	 }
    	
    	 write(result);
	}
	
}
