package variableNeighborhood;

import BatchTest.ChangeParameter;
import Resource.Map;
import kmeans.Kmean;
import nsga2.Individual;
import tool.Read;
import tool.Read2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VNMain {
	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		Map map = new Map();
		Kmean kmean = new Kmean(4, 200);
		//初始化点集、KMeansCluster对象
		kmean.init();
		//使用KMeansCluster对象进行聚类
		kmean.runKmeans();
		Read.read(map);

		Population father = new Population();
		father.init(map);
		ArrayList<Individual> firstRank0 = SelectTheFirstRank.selectTheFirstRank(father);
		for (int i = 0; i < firstRank0.size(); i++) {
			System.out.println(firstRank0.get(i).fitness1 + " " + firstRank0.get(i).fitness2 + " " + firstRank0.get(i).uav.size());
		}


		for (int i = 0; i < 10; i++) {
			father = Iterator.iterator(father, map, i);
			father = Iterator2.iterator(father, map);
			System.out.println(i);
		}
		ArrayList<Individual> firstRank = new ArrayList<>();
		firstRank = SelectTheFirstRank.selectTheFirstRank(father);
		for (int i = 0; i < firstRank.size(); i++) {
			System.out.println(firstRank.get(i).fitness1 + " " + firstRank.get(i).fitness2 + " " + firstRank.get(i).uav.size());
		}

	}

	public static void vnmain(String s) throws IOException, CloneNotSupportedException {
		FileWriter out = new FileWriter(ChangeParameter.fileOutPath, true);
		long time = System.currentTimeMillis();
		Map map = new Map();
		Read2.read2(map);

		Population father = new Population();
		father.init(map);

		ArrayList<Individual> firstRank0 = SelectTheFirstRank.selectTheFirstRank(father);


		for (int i = 0; i < 500; i++) {
			father = Iterator.iterator(father, map, i);
			System.out.println(s + "第" + i + "次循环" + "迭代1");
		}

		for (int i = 0; i < 500; i++) {
			father = Iterator2.iterator(father, map);
			System.out.println(s + "第" + i + "次循环" + "迭代2");
		}
		ArrayList<Individual> firstRank = new ArrayList<>();
		firstRank = SelectTheFirstRank.selectTheFirstRank(father);
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
