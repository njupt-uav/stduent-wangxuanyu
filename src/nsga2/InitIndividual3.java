package nsga2;

import Resource.Map;
import Resource.UAV;
import method.Calfitness2;
import method.DetermineIfTheDroneCanAccomplishItsMission;

import java.util.ArrayList;
import java.util.Collections;

public class InitIndividual3 {
	public static void initIndividual3(Individual individual, Map map) throws CloneNotSupportedException {

//		ArrayList<ArrayList<Integer>>arr=new ArrayList<>();
//		   //ÿ��ar��һ����
//		   for(int i=0;i<map.csNum;i++)
//		   {
//			   ArrayList<Integer>ar=new ArrayList<>();
//			   arr.add(ar);
//			
//		   }
//		   //������еĴ�
//		   for(int i=map.csNum;i<map.node.length;i++)
//		   {
//			   arr.get(map.whichChargingStationIsCloser[i]).add(i);
//		   }
//		
//		   
//		   ArrayList<UAV>tem=new ArrayList<>();
//		   tem.add(new UAV());
//		   //��һ��ѭ����ÿ����������˻�Ⱥ
//		   for(int i=0;i<arr.size();i++)
//		   {//�ڶ���ѭ���Ǹþ�������˻�
//			   for(int j=0;j<arr.get(i).size();j++)
//			   {
//				  UAV u=tem.get(tem.size()-1);
//				  UAV uCopy=new UAV();
//				  uCopy.nodeSet.addAll(u.nodeSet);
//				  uCopy.nodeSequence.addAll(u.nodeSequence);
//				  uCopy.nodeSet.add(arr.get(i).get(j));
//				  if(DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(uCopy, map))
//				  {
//					  u.nodeSet.clear();
//					  u.nodeSequence.clear();
//					  u.nodeSequence.addAll(uCopy.nodeSequence);
//					  u.nodeSet.addAll(uCopy.nodeSet);
//					  u.timeToCompleteAllTasks=uCopy.timeToCompleteAllTasks;
//				  }
//				  else
//				  {
//					  UAV u1=new UAV();
//					  u1.nodeSet.add(arr.get(i).get(j));
//					  u1.nodeSequence.add(arr.get(i).get(j));
//					  u1.nodeSequence.add(arr.get(i).get(j));
//					  Calfitness.calfitness(map, u1);
//					  tem.add(u1);
//				  }
//			   }
//		   }
//		   individual.uav=tem;
//		   individual.calfitness1();
//		   individual.calfitness2();

		//����������������û�б����ʹ���
		boolean[] flag = new boolean[map.distance.length];
		//������δ����Ľڵ㶼����ȥ//��һ��δ����Ľڵ�û��
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = map.csNum + 1; i < map.totalNum; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
//		
		ArrayList<UAV> tem = new ArrayList<>();
		UAV uu = new UAV();
		//��һ���ڵ�������һ̨���˻�
		uu.nodeSet.add(map.csNum);
		uu.nodeSequence.add(map.csNum);
		uu.nodeSequence.add(map.csNum);
		list.remove(new Integer(map.csNum));
		//������ڵ���Ϊ�����ʹ�
		flag[map.csNum] = true;
		tem.add(uu);
		Calfitness2.calfitness(map, uu);
		//disCopy�������������������
		float[][] disCopy = new float[map.totalNum][map.totalNum];
		for (int i = 0; i < map.distance.length; i++)
			for (int j = 0; j < map.distance.length; j++) {
				disCopy[i][j] = map.distance[i][j];
			}
		//������¼ÿ�����˻���һ��������ʲô
		int[] first = new int[500];
		first[tem.size() - 1] = map.csNum;

		while (!list.isEmpty()) {
			UAV u = tem.get(tem.size() - 1);
			if (u.nodeSequence.size() != u.nodeSet.size() * 2) {
				System.out.println("init");
				System.out.println(u.nodeSequence);
				for (int x : u.nodeSet) {
					System.out.print(x + " ");
				}
			}


			//��bug
			if (u.timeToCompleteAllTasks == Float.MAX_VALUE) {
				try {
					throw new Exception("��ʼ������");
				} catch (Exception e) {
					System.out.println(u.nodeSequence);
				}
			}


			int min = list.get(0);
			for (int i = map.csNum + 1; i < map.totalNum; i++) {
				//�ҳ���ǰ�����һ���ڵ�����Ľڵ�
				if (disCopy[first[tem.size() - 1]][i] < disCopy[first[tem.size() - 1]][min]
						&& !flag[i]) {
					min = i;
				}
			}
			list.remove(new Integer(min));
			//�ҳ�����ģ�������̨���˻��ܷ���ɣ�������������һ����ɵ�Ŀ�ꡣ
			flag[min] = true;

			UAV copy = new UAV();
			copy.nodeSet.addAll(u.nodeSet);
			copy.nodeSequence.addAll(u.nodeSequence);

//		     {
//		           System.out.println("�޸�ǰ");
//		           System.out.println(copy.nodeSequence);
//		           for(int x:copy.nodeSet)
//		           {
//		        	   System.out.print(x+" ");
//		           }
//		     }
			copy.nodeSet.add(min);

			if (DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(copy, map)) {

//		    	 {
//			           System.out.println("�޸ĺ�");
//			           System.out.println(copy.nodeSequence);
//			           for(int x:copy.nodeSet)
//			           {
//			        	   System.out.print(x+" ");
//			           }
//			     }
				u.nodeSequence.clear();
				u.nodeSequence.addAll(copy.nodeSequence);
				u.nodeSet.clear();
				u.nodeSet.addAll(copy.nodeSet);
				u.timeToCompleteAllTasks = copy.timeToCompleteAllTasks;
				for (int i = map.csNum + 1; i < map.totalNum; i++) {
					//���¾���
					if (disCopy[first[tem.size() - 1]][i] < disCopy[i][min] && !flag[i]) {
						disCopy[first[tem.size() - 1]][i] = disCopy[i][min];
					}
				}

			} else {
				disCopy = new float[map.totalNum][map.totalNum];
				for (int i = 0; i < map.distance.length; i++)
					for (int j = 0; j < map.distance.length; j++) {
						disCopy[i][j] = map.distance[i][j];
					}
				u = new UAV();
				u.nodeSequence.add(min);
				u.nodeSequence.add(min);
				u.nodeSet.add(min);
				Calfitness2.calfitness(map, u);
				tem.add(u);
				first[tem.size() - 1] = min;
			}
		}
		individual.uav = tem;
		individual.calfitness1();
		individual.calfitness2();

	}
}
