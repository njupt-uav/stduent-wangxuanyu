package variableNeighborhood;

import Resource.Map;
import nsga2.Individual;
import nsga2.InitIndividual3;

import java.util.HashMap;

public class Population {

	public static int MAXSIZE = 200;//��Ⱥ����
	//�����MATH.pow(2,9)����ǰ�趨��hashmap�Ĵ�С�������ع�
	public HashMap<Integer, Individual> map = new HashMap<Integer, Individual>((int) Math.pow(2, 10));//���ڴ�Ÿ���Ⱥ�����и���


	public void init(Map m) throws CloneNotSupportedException {

		for (int i = 0; i < Population.MAXSIZE; i++) {
			Individual in = new Individual();
			InitIndividual3.initIndividual3(in, m);
			map.put(i, in);
				/*
				System.out.print(i+" ");
				System.out.print(-in.fitness1+" ");
				System.out.println(-in.fitness2);
		*/
		}
	}
}