package sa2;

import Resource.Map;
import Resource.UAV;
import method.Calfitness;
import method.DetermineIfTheDroneCanAccomplishItsMission;
import nsga2.Individual;

import java.util.ArrayList;
import java.util.Collections;

public class Variation2 {
	public static void variation2(Individual individual, Map map) throws CloneNotSupportedException {
		if (individual.uav.size() == 0 || individual.uav.size() == 1) return;
		//x�����˻��ı�š��������
		int x = (int) (Math.random() * individual.uav.size());
		//���Ƕ�Ӧ�����˻�
		UAV u = individual.uav.get(x);
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

			Calfitness.calfitness(map, u);
		}

		if (Math.random() > 0.8) {

			UAV u11 = new UAV();
			u11.nodeSet.add(x);
			u11.nodeSequence.add(x);
			u11.nodeSequence.add(x);
//        	 System.out.println(u1.nodeSet);
			Calfitness.calfitness(map, u11);
			//if(u1.nodeSequence.size()!=u1.nodeSet.size()*2)System.out.println("�����ɵ����˻�����");
			individual.uav.add(u11);
			individual.UAVNum = individual.uav.size();
			individual.calfitness1();
			individual.calfitness2();
			return;
		}

		boolean flag = false;
		ArrayList<UAV> uavCopy = new ArrayList<UAV>();
		uavCopy.addAll(individual.uav);
		Collections.shuffle(uavCopy);

		UAV ux = uavCopy.get(0);

		if (ux == u) {
			if (uavCopy.size() == 1) {
				UAV uxx = new UAV();
				uxx.nodeSet.add(x);
				uxx.nodeSequence.add(x);
				uxx.nodeSequence.add(x);
				Calfitness.calfitness(map, uxx);
				//if(u1.nodeSequence.size()!=u1.nodeSet.size()*2)System.out.println("�����ɵ����˻�����");
				individual.uav.add(uxx);
				individual.UAVNum = individual.uav.size();
				individual.calfitness1();
				individual.calfitness2();
				return;
			} else {
				ux = uavCopy.get(1);
			}
		}

		UAV u1 = new UAV();
		u1.nodeSet.addAll(ux.nodeSet);
		u1.nodeSet.add(x);
		if (DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(u1, map)) {
			ux.nodeSequence = u1.nodeSequence;
			ux.length = u1.length;
			ux.nodeSet = u1.nodeSet;
			ux.timeToCompleteAllTasks = u1.timeToCompleteAllTasks;
			flag = true;

		} else {
			UAV u11 = new UAV();
			u11.nodeSet.add(x);
			u11.nodeSequence.add(x);
			u11.nodeSequence.add(x);
//            	 System.out.println(u1.nodeSet);
			Calfitness.calfitness(map, u11);
			//if(u1.nodeSequence.size()!=u1.nodeSet.size()*2)System.out.println("�����ɵ����˻�����");
			individual.uav.add(u11);
		}

		//������˻������Ҳ���һ��������ɸ���������˻�����������һ�����˻�


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
