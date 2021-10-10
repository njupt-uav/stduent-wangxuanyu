package variableNeighborhood2;



import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import BatchTest.ChangeParameter;
import Resource.Map;
import compare.PF;
import compare.PFSet;
//import greedy2.Iterator;
import kmeans.Kmean;
import nsga2.Individual;
import nsga2.InitIndividual3;
import tool.Read;
import tool.Read2;

public class VnMain1 {
//public static void main(String[] args) throws IOException, CloneNotSupportedException {
//	    Map map=new Map();
//		Read2.read2( map);
//		 Individual in= new Individual();
//		 InitIndividual3.initIndividual3(in, map);
//		 ArrayList<Individual>arr=new ArrayList<>();
//		 for(int i=0;i<500;i++)
//		 {
//			 Iterator.iterator(map, arr);
//			
//		 }
//		 
//		 for(int i=0;i<500;i++)
//		 {
//			 Iterator.iterator2(map, arr);
//		 }
//		 SelectTheFirstRank.selectTheFirstRank(arr);
//}

//批量实验主要运行程序
public static void vnMain(String s) throws IOException, CloneNotSupportedException {
	FileWriter out=new FileWriter(ChangeParameter.fileOutPath,true);

	        Map map=new Map();
			Read2.read2( map);
			 Individual in= new Individual();
			 InitIndividual3.initIndividual3(in, map);
			 ArrayList<Individual>arr=new ArrayList<>();
			 arr.add(in);
			 for(int i=0;i<1000;i++)
			 {
				 Iterator.iterator(map, arr);
				 System.out.println(s+"vn1第"+i+"次迭代");
			 }
			 for(int i=0;i<1000;i++)
			 {
				 Iterator.iterator2(map, arr);
				 System.out.println(s+"vn2第"+i+"次迭代");
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

public static void VnMain2(String s) throws IOException, CloneNotSupportedException {
	FileWriter out=new FileWriter(ChangeParameter.fileOutPath,true);

	        Map map=new Map();
			Read2.read2( map);
			 Individual in= new Individual();
			 InitIndividual3.initIndividual3(in, map);
			 ArrayList<Individual>arr=new ArrayList<>();
			Variation3.Iterator1(map, arr);
			Variation3.Iterator2(map, arr);
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
