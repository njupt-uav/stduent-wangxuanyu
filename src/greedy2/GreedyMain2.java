package greedy2;


import BatchTest.ChangeParameter;
import Resource.Map;
import nsga2.Individual;
import nsga2.InitIndividual3;
import tool.Read2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GreedyMain2 {
//public static void main(String[] args) throws IOException, CloneNotSupportedException {
//	    Map map=new Map();
//		Read2.read2( map);
//		 Individual in= new Individual();
//		 InitIndividual3.initIndividual3(in, map);
//		 ArrayList<Individual>arr=new ArrayList<>();
//		 arr.add(in);
//		 for(int i=0;i<5000;i++)
//		Iterator.iterator(in, map, arr);
//		 SelectTheFirstRank.selectTheFirstRank(arr);
//		 
//		 
//}

	//批量实验主要运行程序
	public static void greedymain(String s) throws IOException, CloneNotSupportedException {
		FileWriter out = new FileWriter(ChangeParameter.fileOutPath, true);
		Map map = new Map();
		Read2.read2(map);
		Individual in = new Individual();
		InitIndividual3.initIndividual3(in, map);
		ArrayList<Individual> arr = new ArrayList<>();
		arr.add(in);
		for (int i = 0; i < 3000; i++) {
			Iterator.iterator(in, map, arr);
			System.out.println(s + "GREEDY第" + i + "次迭代");
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
