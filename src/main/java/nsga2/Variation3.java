package nsga2;

import Resource.Map;
import Resource.UAV;
import heapSort.HeapSort;
import method.Calfitness;
import method.DetermineIfTheDroneCanAccomplishItsMission;
import subiterations.GA;
import subiterations.SubGreedy;
import subiterations.SubSa;

import java.util.ArrayList;

public class Variation3 {
    //heapsort//�����һ�����˻�������ȡ��һ������Ȼ���ҳ�����е�ǰ�ٷ�֮���ٵ����˻������������ܷ�����������
    public static void variation3(Individual individual, Map map) throws CloneNotSupportedException {
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
        ArrayList<UAV> copy = new ArrayList();
        for (int i = 0; i < individual.uav.size(); i++) {
            UAV u2 = individual.uav.get(i);
            if (u2 == u) continue;
            copy.add(u2);
        }
        int num = (int) (copy.size() * 0.2);
        HeapSort.heapSort(copy, num);
        boolean flag = false;
        for (int i = copy.size() - 1; i >= copy.size() - num - 1; i--) {
            UAV ux = copy.get(i);
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


                ux.nodeSequence.clear();
                ux.nodeSequence.addAll(u1.nodeSequence);
                ux.length = u1.length;
                ux.nodeSet.clear();
                ux.nodeSet.addAll(u1.nodeSet);
                ux.timeToCompleteAllTasks = u1.timeToCompleteAllTasks;
                flag = true;
                break;
            }
        }
        if (flag == false) {
            UAV u1 = new UAV();
            u1.nodeSet.add(x);
            u1.nodeSequence.add(x);
            u1.nodeSequence.add(x);
//            	 System.out.println(u1.nodeSet);
            Calfitness.calfitness(map, u1);
            //if(u1.nodeSequence.size()!=u1.nodeSet.size()*2)System.out.println("�����ɵ����˻�����");
            individual.uav.add(u1);

        }
        individual.calfitness1();
        individual.calfitness2();


    }


}
