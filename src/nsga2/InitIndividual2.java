package nsga2;

import Resource.Map;
import Resource.UAV;
import method.Calfitness;
import method.DetermineIfTheDroneCanAccomplishItsMission;

import java.util.ArrayList;
import java.util.Collections;

public class InitIndividual2 {
	//�����ж���ǰ�����˻��Ƿ��ܹ���ɵ�ǰ����ֻ�ж����ڵ����˻��ܷ���ɸ�����
	public static void initIndividual2(Individual individual, Map map) throws CloneNotSupportedException {

		int count = 0;
		//

		//���ǻ�û�з�������ߴ������ڵ��ѡ��
		ArrayList<Integer> temp = new ArrayList<Integer>();
		//ǰ�����ǳ��ڵ㣬����������ߴ������ڵ�
		for (int i = map.csNum; i < map.totalNum; i++) {
			temp.add(i);
		}
		Collections.shuffle(temp);
		UAV u = new UAV();
		//���˻�Ⱥ
		ArrayList<UAV> uList = new ArrayList<>();
		uList.add(u);
		for (int i = 0; i < temp.size(); i++) {
			int x = temp.get(i);
			UAV ux = new UAV();
			ux.nodeSet.addAll(u.nodeSet);
			ux.nodeSet.add(x);
			if (DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(ux, map) == true) {
				u.length = ux.length;
				u.nodeSequence = ux.nodeSequence;
				u.nodeSet = ux.nodeSet;
				u.timeToCompleteAllTasks = ux.timeToCompleteAllTasks;
			} else {
				u = new UAV();
				uList.add(u);
				u.nodeSet.add(x);
				u.nodeSequence.add(x);
				u.nodeSequence.add(x);
				Calfitness.calfitness(map, u);
			}
		}

		individual.uav = uList;
		individual.calfitness1();
		individual.calfitness2();

		//System.out.println(individual.fitness1+"+"+individual.fitness2);
//	for(int y=0;y<individual.uav.size();y++)
//	{
//		if(individual.uav.get(y).length==0)
//			System.out.println("initindividual");
//	}

	}
}
