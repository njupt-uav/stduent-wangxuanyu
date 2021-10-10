package sa2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import BatchTest.ChangeParameter;
import Resource.Map;
import compare.PF;
import compare.PFSet;
import kmeans.Kmean;
import nsga2.Individual;
import nsga2.InitIndividual3;
import tool.Read;
import tool.Read2;

public class SaMain1 {
//   public static void main(String[] args) throws IOException, CloneNotSupportedException {
////	   FileWriter out=new FileWriter(ChangeParameter.fileOutPath,true);
//		Map map=new Map();
//		Read2.read2( map);
//		 Individual in= new Individual();
//		 InitIndividual3.initIndividual3(in, map);
//		 ArrayList<Individual>arr=new ArrayList<>();
//		 arr.add(in);
//	    while(Iterator.T>0.5)
//		{
//		  Iterator.iterator(in, map, arr);	
//		}
//	    SelectTheFirstRank.selectTheFirstRank(arr);
//}
//   
   
   //����ʵ��sa��Ҫ���г���
   public static void samain(String s) throws IOException, CloneNotSupportedException {
	   FileWriter out=new FileWriter(ChangeParameter.fileOutPath,true);
	   Map map=new Map();
		Read2.read2( map);
		 Individual in= new Individual();
		 InitIndividual3.initIndividual3(in, map);
		 ArrayList<Individual>arr=new ArrayList<>();
		 arr.add(in);
	    while(Iterator.T>0.1)
		{
		  Iterator.iterator(in, map, arr);	
//		  System.out.println("�¶�Ϊ:"+Iterator.T);
		}
	    SelectTheFirstRank.selectTheFirstRank(arr);
		out.write(s);
		out.write("\r\n");
		for(int  i=0;i<arr.size();i++)
		{
			Individual inn=arr.get(i);
			out.write(inn.fitness1+"  "+inn.fitness2);
			out.write("\r\n");
		}
		out.write("\r\n");
		out.write("\r\n");
		out.close();
		
}
}
