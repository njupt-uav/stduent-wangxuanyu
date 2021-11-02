package nsga2;

import Resource.Map;

import java.util.HashMap;
import java.util.HashSet;

public class Population {
	public static final int MAXSIZE = 100;//��Ⱥ����
	//�����MATH.pow(2,9)����ǰ�趨��hashmap�Ĵ�С�������ع�
	public HashMap<Integer, Individual> map = new HashMap<Integer, Individual>((int) Math.pow(2, 10));//���ڴ�Ÿ���Ⱥ�����и���


	public void init(Map m) throws CloneNotSupportedException {

		HashSet<String> hs = new HashSet();
		for (int i = 0; i < Population.MAXSIZE; i++) {
//	 System.out.println("Population"+" "+i);
			Individual in = new Individual();
			InitIndividual3.initIndividual3(in, m);
//		 System.out.println("initindividual3");
			String s = in.fitness1 + " " + in.fitness2;

//		 System.out.println(hs.contains(s));

			int x = 0;
			while (hs.contains(s)) {
//			 System.out.println(x);
				Variation2.variation2(in, m);
				s = in.fitness1 + " " + in.fitness2;
				if (x++ > 100) {

					ReduceTime.reduceTime(in, m);
					s = in.fitness1 + " " + in.fitness2;
				}
			}
			hs.add(s);
			map.put(i, in);
		/*
		System.out.print(i+" ");
		System.out.print(-in.fitness1+" ");
		System.out.println(-in.fitness2);
*/
		}
	}


}
