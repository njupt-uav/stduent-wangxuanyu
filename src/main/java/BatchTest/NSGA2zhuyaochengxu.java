package BatchTest;


import Resource.Map;
import nsga2.*;
import tool.Read2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NSGA2zhuyaochengxu {


	public static void running(String s) throws CloneNotSupportedException, IOException {
		FileWriter out = new FileWriter(ChangeParameter.fileOutPath, true);
		long time = System.currentTimeMillis();
		Map map = new Map();

		Read2.read2(map);

		Population father = new Population();
//		 long time=System.currentTimeMillis();
		father.init(map);
//		 time=System.currentTimeMillis()-time;
//		  System.out.println("主要运行程序 -init "+time);

		ArrayList<Individual> firstRank;
		for (int i = 0; i < 400; i++) {
			System.out.println(s + " 第" + i + "次循环");
			Population child;
//		   time=System.currentTimeMillis();
			child = GenerateNextPopulation.generateNextPopulation(father, map, i);
//			  time=System.currentTimeMillis()-time;
//			  System.out.println("主要运行程序 -gener "+time);
//			  time=System.currentTimeMillis();
			father = NSGA2Select.nsga2(father, child);
//		 time=System.currentTimeMillis()-time;
//		  System.out.println("主要运行程序 -nsga2 "+time);

		}

		firstRank = SelectTheFirstRank.selectTheFirstRank(father);
//			String fileOutPath="att48uniform批量实验结果";
		time = System.currentTimeMillis() - time;
		out.write(s);
		out.write("\r\n");
		out.write(String.valueOf(time));
		out.write("\r\n");
		for (int i = 0; i < firstRank.size(); i++) {
			Individual in = firstRank.get(i);
			out.write(in.fitness1 + "  " + in.fitness2);
			out.write("\r\n");
		}
		out.write("\r\n");
		out.write("\r\n");
		out.close();


	}


}
