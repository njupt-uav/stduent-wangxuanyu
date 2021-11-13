package nsga2;

import Resource.Map;
import Resource.UAV;
import method.Calfitness;
import method.DetermineIfTheDroneCanAccomplishItsMission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/*
 * ��ʼ����Ⱥ
 */
public class InitIndividual {
    //���������˻�ÿһ̨���ж�һ���Ƿ��ܹ��������
    public static void initIndividual(Individual individual, Map map) throws CloneNotSupportedException {

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

        //���˻�Ⱥ�ĸ��ư�
        ArrayList<UAV> uListCopy = new ArrayList<UAV>();
        uList.add(u);
        int x;
        UAV u1;
        Iterator<Integer> it = temp.iterator();
        while (it.hasNext()) {

            //�ó�һ���ڵ�
            x = it.next();
            //�����е����˻����ҵ��ܹ���������������˻�
            uListCopy.clear();    //���Ǹ���
            uListCopy.addAll(uList);    //���Ǹ���
            Collections.shuffle(uListCopy);
            boolean flag = false;
            Iterator<UAV> it1 = uListCopy.iterator();

            while (it1.hasNext()) {
                u1 = it1.next();
                UAV uxx = new UAV();
                uxx.nodeSet.addAll(u1.nodeSet);
                uxx.nodeSet.add(x);
                uxx.nodeSequence.addAll(u1.nodeSequence);
                if (DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(uxx, map) == true) {

//							individual.selectTheUAVForTheNode[x]=uList.indexOf(u1);
                    u1.length = uxx.length;
                    u1.nodeSequence = uxx.nodeSequence;
                    u1.nodeSet = uxx.nodeSet;
                    u1.timeToCompleteAllTasks = uxx.timeToCompleteAllTasks;
                    //�����ڲ�whileѭ��
                    flag = true;
                    break;
                } else {

                }
            }
            if (flag == false)//������е����˻����޷����ѡ��������
            {

                u1 = new UAV();
                u1.nodeSet.add(x);
                u1.nodeSequence.add(x);
                u1.nodeSequence.add(x);
                Calfitness.calfitness(map, u1);
                uList.add(u1);

//					System.out.println(u1.length);
            }
        }
        for (int k = uList.size() - 1; k >= 0; k--) {
            if (uList.get(k).nodeSet.size() == 0)
                uList.remove(k);
        }

        individual.uav = uList;
        individual.calfitness1();
        individual.calfitness2();

//	for(int y=0;y<individual.uav.size();y++)
//	{
//		if(individual.uav.get(y).length==0)
//			System.out.println("initindividual");
//	}

    }
}		
			
		
		  

