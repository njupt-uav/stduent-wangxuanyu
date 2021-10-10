package variableNeighborhood;

import java.util.ArrayList;
import java.util.HashMap;

import nsga2.Individual;

public class SelectTheFirstRank {
	 public static ArrayList<Individual> selectTheFirstRank(Population father)
	   {
		   HashMap<Integer, Individual> fatherAndChild = new HashMap<Integer, Individual>();
			fatherAndChild.putAll(father.map);
			
			 fatherAndChild.forEach((key,in)->{
		    	   in.n=0;
		    	   in.dominate.clear();
		       });
			 fatherAndChild.forEach((key1,in)->{
					fatherAndChild.forEach((key, o) -> {
						if (((in.fitness1 < o.fitness1) && (in.fitness2 <o.fitness2))||((in.fitness1 <=o.fitness1) && (in.fitness2 < o.fitness2))||((in.fitness1 < o.fitness1) && (in.fitness2 <= o.fitness2)))// �ҳ�in֧��ĸ������dominate��
						{
							in.dominate.add(o);	   
						}else
						if (((in.fitness1 > o.fitness1) && (in.fitness2 > o.fitness2))||((in.fitness1 >= o.fitness1) && (in.fitness2 > o.fitness2))||((in.fitness1 > o.fitness1) && (in.fitness2 >= o.fitness2)))// ����һ��֧��in�ĸ����++n
						{
							in.n++;
						}
					});
				 });
			
				
				ArrayList<ArrayList<Individual>> rank = new ArrayList<ArrayList<Individual>>();// ����Ⱥ���зּ���
				// ����ArrayList�Ǵ��ÿһ��rank���ڲ��ArrayList�Ǵ�Ÿ�rank��Ӧ�ĸ���

				// �����������и�����зּ����ּ������rank������
				
					ArrayList<Individual> po = new ArrayList<Individual>();// ������ŵ�x rank�ĸ��壬�����x��while�ĵ�x��ѭ��
					for (int i = 0; i < Population.MAXSIZE ; i++)// �Թ�ϣ���е�ÿ��������б���
					{
						Individual indi = fatherAndChild.get(i);
//						if (indi == null)
//							continue;
						if (indi.n == 0)// ������ָø����nΪ0����û��֧��ø������������
						{
							po.add(indi);// ���ø�������n rank�ļ�����
//							fatherAndChild.remove(i);// �Ѹø���ӹ�ϣ����ȥ��

//							for (Individual in : indi.dominate) {
//								--in.n;
//							}
						}
					}
//					for(Individual indi:po)
//						for(Individual in:indi.dominate)
//						{
//							in.n--;
//						}
					return po;// ��rank�����и��嶼����ArrayList�󽫸�ArrayList�����Ӧrank��λ��
				
			
	   }
}
