package variableNeighborhood;

import Resource.Map;
import Resource.UAV;
import method.Calfitness;
import nsga2.Individual;

public class Variation {
	public static void reduceTime(Individual individual, Map map) throws CloneNotSupportedException {

		//�ҳ���ʱ����Ǹ����˻�
		UAV u = individual.uav.get(0);
		for (UAV ux : individual.uav) {
			if (u.timeToCompleteAllTasks < ux.timeToCompleteAllTasks) {
				u = ux;
			}
		}

		if (u.nodeSet.size() == 1) return;
		/*
		 * x�������ʱ��������˻������ѡ���һ���ڵ�
		 */
		int x = (int) (Math.random() * u.nodeSequence.size());
		x = u.nodeSequence.get(x);


		UAV u1 = new UAV();
		u1.nodeSet.add(x);
		u1.nodeSequence.add(x);
		u1.nodeSequence.add(x);
		Calfitness.calfitness(map, u1);
		individual.uav.add(u1);


		u.nodeSet.remove(x);
		u.nodeSequence.remove(Integer.valueOf(x));
		u.nodeSequence.remove(Integer.valueOf(x));
		Calfitness.calfitness(map, u);


		for (int i = individual.uav.size() - 1; i >= 0; i--) {
			if (individual.uav.get(i).nodeSet.size() == 0)
				individual.uav.remove(i);
		}


		individual.calfitness1();
		individual.calfitness2();

	}
}
