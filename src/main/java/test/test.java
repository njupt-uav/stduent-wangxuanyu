package test;

import Resource.Map;
import kmeans.Kmean;
import tool.Read;

import java.io.IOException;

public class test {
	public static void main(String[] args) throws IOException {
		Map map = new Map();
		Kmean kmean = new Kmean(4, 200);
		//初始化点集、KMeansCluster对象
		kmean.init();
		//使用KMeansCluster对象进行聚类
		kmean.runKmeans();
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		Read.read(map);
		for (int i = 0; i < map.distance.length; i++)
			for (int j = 0; j < map.distance[0].length; j++) {
				max = Math.max(max, map.distance[i][j]);
				if (i == j) continue;
				min = Math.min(min, map.distance[i][j]);
			}
		System.out.println(max);
		System.out.println(min);
	}
}
