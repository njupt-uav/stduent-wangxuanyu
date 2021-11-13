package sa2;

import BatchTest.ChangeParameter;
import Resource.Map;
import nsga2.Individual;
import nsga2.InitIndividual3;
import tool.Read2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

	//批量实验sa主要运行程序
	public static void samain(String s) throws IOException, CloneNotSupportedException {
		FileWriter out = new FileWriter(ChangeParameter.fileOutPath, true);
		Map map = new Map();
		Read2.read2(map);
		Individual in = new Individual();
		InitIndividual3.initIndividual3(in, map);
		ArrayList<Individual> arr = new ArrayList<>();
		arr.add(in);
		while (Iterator.T > 0.1) {
			Iterator.iterator(in, map, arr);
//		  System.out.println("温度为:"+Iterator.T);
		}
		SelectTheFirstRank.selectTheFirstRank(arr);
		out.write(s);
		out.write("\r\n");
		for (int i = 0; i < arr.size(); i++) {
			Individual inn = arr.get(i);
			out.write(inn.fitness1 + "  " + inn.fitness2);
			out.write("\r\n");
		}
		out.write("\r\n");
		out.write("\r\n");
		out.close();

	}
}
