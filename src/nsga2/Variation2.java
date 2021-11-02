package nsga2;

import Resource.Map;
import Resource.UAV;
import method.Calfitness2;
import method.DetermineIfTheDroneCanAccomplishItsMission;
import subiterations.GA;
import subiterations.SubGreedy;
import subiterations.SubSa;

import java.util.ArrayList;
import java.util.Collections;

public class Variation2 {
	//�����һ̨���˻����ó�һ���ڵ㣬�����������˻�
	public static void variation2(Individual individual, Map map) throws CloneNotSupportedException {
		if (individual.uav.size() == 0 || individual.uav.size() == 1) return;
		//x�����˻��ı�š��������
		int x = (int) (Math.random() * individual.uav.size());
		//���Ƕ�Ӧ�����˻�
		UAV u = individual.uav.get(x);

		//��bug
		if (u.timeToCompleteAllTasks == Float.MAX_VALUE) {
			try {
				throw new Exception("variation2���˻������ͳ�����");
			} catch (Exception e) {
				System.out.println(u.nodeSequence + "λ��1");
			}
		}
		//���ڴ�����˻�Ҫ�����Ľڵ�
		ArrayList<Integer> arr = new ArrayList<Integer>();
		//�����˻�Ҫ�����Ľڵ����arr��
		arr.addAll(u.nodeSet);
		//x�ǽڵ���arr�е�λ�ã��������
		x = (int) Math.random() * arr.size();
		//x�ǽڵ�ı��
		x = (int) arr.get(x);
		u.nodeSet.remove(x);
		if (u.nodeSet.isEmpty()) individual.uav.remove(u);
		else {
			// System.out.println(u.nodeSequence+"start");
			for (int t = u.nodeSequence.size() - 1; t >= 0; t--) {
				if (u.nodeSequence.get(t) == x)
					u.nodeSequence.remove(t);
			}
			// System.out.println(x);
			Calfitness2.calfitness(map, u);

			if (u.timeToCompleteAllTasks == Float.MAX_VALUE) {
				try {
					throw new Exception("variation2���˻����������ڵ�󷴶�������");
				} catch (Exception e) {
					System.out.println(u.nodeSequence + "λ��2");
				}
			}

			// if(u.timeToCompleteAllTasks==Float.MAX_VALUE)System.out.println("variation2,1");
		}

		boolean flag = false;
		ArrayList<UAV> uavCopy = new ArrayList<UAV>();
		uavCopy.addAll(individual.uav);
		Collections.shuffle(uavCopy);
		for (UAV ux : uavCopy) {
			if (ux == u) continue;
			UAV u1 = new UAV();
			u1.nodeSet.addAll(ux.nodeSet);
			u1.nodeSet.add(x);
			u1.nodeSequence.addAll(ux.nodeSequence);
			if (DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(u1, map)) {

				if (u1.nodeSet.size() > 3)
					switch (Exchange4.subIterator) {
						case "sa":
							SubSa.subSa(u1, map);
							break;
						case "Greedy":
							SubGreedy.subGreedy(u1, map);
							break;
						case "ga":
							GA.ga(u1, map);
							break;
						case "no":
							break;
					}


				ux.nodeSequence = u1.nodeSequence;
				ux.length = u1.length;
				ux.nodeSet = u1.nodeSet;
				ux.timeToCompleteAllTasks = u1.timeToCompleteAllTasks;
				// if(ux.timeToCompleteAllTasks==Float.MAX_VALUE)System.out.println("variation2,2");
				flag = true;
				break;
			}

		}
		//������˻������Ҳ���һ��������ɸ���������˻�����������һ�����˻�
		if (flag == false) {
			UAV u1 = new UAV();
			u1.nodeSet.add(x);
			u1.nodeSequence.add(x);
			u1.nodeSequence.add(x);
//        	 System.out.println(u1.nodeSet);
			Calfitness2.calfitness(map, u1);
			if (u1.timeToCompleteAllTasks == Float.MAX_VALUE) {
				System.out.println(u.nodeSequence + "λ��5");
				System.out.println("deadlineΪ" + map.node[u.nodeSequence.get(0)].deadline);
				System.out.println("���о���+����ʱ��Ϊ" + map.distance[map.whichChargingStationIsCloser[u.nodeSequence.get(0)]][u.nodeSequence.get(0)] / UAV.speed * 2 + map.node[u.nodeSequence.get(0)].missionTime);
			}
			//if(u1.nodeSequence.size()!=u1.nodeSet.size()*2)System.out.println("�����ɵ����˻�����");
			individual.uav.add(u1);
		}

//         for(int i=0;i<individual.uav.size();i++)
//         {
//        	 
//        	 UAV u2=individual.uav.get(i);
//        	 for(Integer pp:u2.nodeSet)
//        	 {
//        		 individual.selectTheUAVForTheNode[pp]=i;
//        	 }
//         }


		individual.UAVNum = individual.uav.size();
		individual.calfitness1();
		individual.calfitness2();

//         int max=0;
//    	 for(int i=0;i<individual.selectTheUAVForTheNode.length;i++)
//    	 {
//    		 if(max<individual.selectTheUAVForTheNode[i])max=individual.selectTheUAVForTheNode[i];
//    	 }
//    	 if(max!=individual.uav.size()-1)
//    		 {
//    		 for(int i=0;i<individual.selectTheUAVForTheNode.length;i++)
//    		 System.out.print(individual.selectTheUAVForTheNode[i]+" ");
//    	     System.out.println(individual.uav.size()+"variation2");
//    		 }
		return;
	}


}
