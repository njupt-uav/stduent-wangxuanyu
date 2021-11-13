package nsga2;

import java.util.ArrayList;
import java.util.HashMap;


public class NSGA2Select {
    /*
     * nsga2��ѡ�񲿷�
     */
    public static Population nsga2(Population father, Population child) { // ������Ⱥ�����и��嶼����ɸѡ
        HashMap<Integer, Individual> fatherAndChild = new HashMap<Integer, Individual>();
        fatherAndChild.putAll(father.map);
        for (int i = 0; i < Population.MAXSIZE; i++) {
            fatherAndChild.put(i + father.map.size(), child.map.get(i));
        }


        // ��ÿ��������д����ҳ�����֧���֧�����ĸ���
        fatherAndChild.forEach((key, in) -> {
            in.n = 0;
            in.dominate.clear();
        });
        fatherAndChild.forEach((key1, in) -> {
            fatherAndChild.forEach((key, o) -> {
                if (((in.fitness1 < o.fitness1) && (in.fitness2 < o.fitness2)) || ((in.fitness1 <= o.fitness1) && (in.fitness2 < o.fitness2)) || ((in.fitness1 < o.fitness1) && (in.fitness2 <= o.fitness2)))// �ҳ�in֧��ĸ������dominate��
                {
                    in.dominate.add(o);
                } else if (((in.fitness1 > o.fitness1) && (in.fitness2 > o.fitness2)) || ((in.fitness1 >= o.fitness1) && (in.fitness2 > o.fitness2)) || ((in.fitness1 > o.fitness1) && (in.fitness2 >= o.fitness2)))// ����һ��֧��in�ĸ����++n
                {
                    in.n++;
                }
            });
        });


        ArrayList<ArrayList<Individual>> rank = new ArrayList<ArrayList<Individual>>();// ����Ⱥ���зּ���
        // ����ArrayList�Ǵ��ÿһ��rank���ڲ��ArrayList�Ǵ�Ÿ�rank��Ӧ�ĸ���

        while (!fatherAndChild.isEmpty())// �����������и�����зּ����ּ������rank������
        {
            ArrayList<Individual> po = new ArrayList<Individual>();// ������ŵ�x rank�ĸ��壬�����x��while�ĵ�x��ѭ��
            for (int i = 0; i < Population.MAXSIZE * 2; i++)// �Թ�ϣ���е�ÿ��������б���
            {
                Individual indi = fatherAndChild.get(i);
                if (indi == null)
                    continue;
                if (indi.n == 0)// ������ָø����nΪ0����û��֧��ø������������
                {
                    po.add(indi);// ���ø�������n rank�ļ�����
                    fatherAndChild.remove(i);// �Ѹø���ӹ�ϣ����ȥ��

//					for (Individual in : indi.dominate) {
//						--in.n;
//					}
                }
            }
            for (Individual indi : po)
                for (Individual in : indi.dominate) {
                    in.n--;
                }
            rank.add(po);// ��rank�����и��嶼����ArrayList�󽫸�ArrayList�����Ӧrank��λ��
        }

//		int t=0;
//		for(ArrayList<Individual> arr:rank)
//		{
//			System.out.println(++t);
//			for(Individual indi:arr)
//			{
//				System.out.println(indi.fitness1+" "+indi.fitness2);
//			}
//		}


        // �����ĸ�rank��������һ��֮��ᳬ��MAXSIZE
        int num = Population.MAXSIZE;
        int index = 0;// ��������ָʾ�ڼ�rank��
        while (true) {
            if (num - rank.get(index).size() > 0) {
                num = num - rank.get(index).size();
                ++index;

            } else {
                break;
            }
        }


        // �ҳ���rank
        ArrayList<Individual> po = rank.get(index);
        //System.out.println("���һ����"+po.size()+"��");
        // �Ը�rank����fitness1�Ĵ�С�������򣬸���fitness2Ҳ����

        po.sort((Individual o1, Individual o2) -> { // TODO Auto-generated method stub
//			int i = o1.fitness1 > o2.fitness1 ? 1 : -1;
//			return i;
                    if (o1.fitness1 > o2.fitness1) {
                        return 1;
                    } else if (o1.fitness1 < o2.fitness1) {
                        return -1;
                    } else {
                        return 0;
                    }

                }

        );

        //��һ��
        double fitness1sum = 0, fitness2sum = 0;
        int numSum = 0;
        for (Individual o : po) {
            fitness1sum += o.fitness1;
            fitness2sum += o.fitness2;
            numSum += o.uav.size();
        }


        // �Ը�rank��ÿһ�����������distance
        for (int i = 0; i < po.size(); i++) {
            if (i == 0 || i == po.size() - 1) {
                po.get(i).distance = (float) Double.MAX_VALUE;
            } else {
                po.get(i).distance = (float) (Math.abs((po.get(i - 1).fitness1 - po.get(i + 1).fitness1) / fitness1sum)
                        + Math.abs(po.get(i - 1).fitness2 - po.get(i + 1).fitness2) / fitness2sum
                );
            }
        }

        // �Ը�rank����distance��С��������
        po.sort((Individual o1, Individual o2) -> {
            // TODO Auto-generated method stub
//			int i = o1.distance > o2.distance ? -1 : 1;
//			return i;
            if (o1.distance > o2.distance) return -1;
            else if (o1.distance < o2.distance) return 1;
            return 0;
        });
        // ������ѡ�еĸ�������µ�ArrayList��
        ArrayList<Individual> newpo = new ArrayList<Individual>();
        for (int i = 0; i < index; i++) {
            newpo.addAll(rank.get(i));
        }
        // ͬ��
        for (int i = 0; i < num; i++) {
            newpo.add(po.get(i));
        }
        //System.out.println("����"+num+"��");
        // ����һ����Ⱥ�����ղ�ɸѡ�������и�����Ϊ����Ⱥ����ɲ���
        Population pp = new Population();
        for (int i = 0; i < Population.MAXSIZE; i++) {
            pp.map.put(i, newpo.get(i));
        }
        return pp;// ���ظ���Ⱥ
    }

}
