package nsga2;

import Resource.Map;
import kmeans.Kmean;
import tool.Read;

import java.io.IOException;

public class NSGA2 {


    public static void main(String[] args) throws IOException, CloneNotSupportedException {


        Population p1 = new Population();
        Population p2 = new Population();
        Map map = new Map();
        Kmean kmean = new Kmean(4, 200);
        //初始化点集、KMeansCluster对象
        kmean.init();
        //使用KMeansCluster对象进行聚类
        kmean.runKmeans();
        Read.read(map);
        p1.init(map);
        p2.init(map);
	
	/*ArrayList<Individual> p=NSGA2.nsga2_ReturnsTheSolutionOfTheFirstRank(p2, p1);
	for(int i=0;i<p.size();i++)
	{
		System.out.println(p.get(i).fitness1+"  "+p.get(i).fitness2);
	}*/
        for (int i = 0; i < 1000; i++) {
            p1 = new Population();
            p1.init(map);
            p2 = NSGA2Select.nsga2(p1, p2);
        }
        for (int i = 0; i < Population.MAXSIZE; i++) {
            System.out.println(-p2.map.get(i).fitness1 + "  " + -p2.map.get(i).fitness2);
        }
    }

}
