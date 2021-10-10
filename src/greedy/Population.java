package greedy;

import java.util.HashMap;

import Resource.Map;
import nsga2.Individual;
import nsga2.InitIndividual;
import nsga2.InitIndividual3;


public class Population {
	public static final int MAXSIZE=100;//种群数量
	 //这里的MATH.pow(2,9)是提前设定好hashmap的大小，避免重构
	public HashMap<Integer, Individual> map=new HashMap<Integer, Individual>((int) Math.pow(2, 10));//用于存放该种群的所有个体
	 
	 
	 
	 public void init(Map m) throws CloneNotSupportedException
	 {
		 
		 for(int i=0;i<Population.MAXSIZE;i++)
		 {
			 
			 Individual in= new Individual();
			 InitIndividual3.initIndividual3(in, m);
			 map.put(i, in);
			/*
			System.out.print(i+" ");
			System.out.print(-in.fitness1+" ");
			System.out.println(-in.fitness2);
	*/	 }
	 }
}
