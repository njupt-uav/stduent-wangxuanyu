package nsga2;

import Resource.UAV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Individual implements Cloneable {
    public static int selfOwnedNum = 20;
    public static int priceMagnification = 3;
    //��Ŀ���Ż���������Ӧ��
    public float fitness1 = (float) (Math.random() * 10);
    public float fitness2 = (float) (Math.random() * 10);
    //	int num;//���˻�������
    public int n = 0;//��Ⱥ���ж��ٸ���֧��ø���
    public ArrayList<Individual> dominate = new ArrayList<Individual>();//������Ÿø���֧��ĸ���
    /*
     * ����Ĳ�����nsga2�õ�
     */
    //���������˻�������
    public int UAVNum;
    public ArrayList<UAV> uav = new ArrayList<>();
    float distance = 0;//��ľ���


    //    public int [] 	selectTheUAVForTheNode;//�����index����ڵ����ţ������ŵ��ǵ�index���ڵ㱻��n�����˻�����
    public Individual() {

    }

    public Individual clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        Individual in = new Individual();
        in.fitness1 = this.fitness1;
        in.fitness2 = this.fitness2;
        for (UAV u1 : this.uav) {
            UAV u = new UAV();
            u.nodeSequence.addAll(u1.nodeSequence);
            u.nodeSet.addAll(u1.nodeSet);
            u.timeToCompleteAllTasks = u1.timeToCompleteAllTasks;

            in.uav.add(u);
        }
        return in;
    }


    //��Ӧ��1�����˻�������ʱ��
    public void calfitness1() {
        this.fitness1 = 0;
        ArrayList<UAV> p = new ArrayList();
        p.addAll(this.uav);
        Collections.sort(p, new Comparator<UAV>() {

            @Override
            public int compare(UAV o1, UAV o2) {
                // TODO Auto-generated method stub
                if (o1.timeToCompleteAllTasks == o2.timeToCompleteAllTasks) {
                    return 0;
                }
                return o1.timeToCompleteAllTasks > o2.timeToCompleteAllTasks ? -1 : 1;
            }

        });
        for (int i = 0; i < p.size(); i++) {
            if (i >= selfOwnedNum) {
                this.fitness1 += priceMagnification * p.get(i).timeToCompleteAllTasks;
            } else {
                this.fitness1 += p.get(i).timeToCompleteAllTasks;
            }
        }
    }

    //��Ӧ��2����ٷɻػ��ص����˻����ķ�ʱ��
    public void calfitness2() {
        float x = Float.MIN_VALUE;
        for (UAV u : this.uav) {
            if (u.timeToCompleteAllTasks > x) {
                x = (float) u.timeToCompleteAllTasks;
            }
        }
        fitness2 = x;
    }


}
