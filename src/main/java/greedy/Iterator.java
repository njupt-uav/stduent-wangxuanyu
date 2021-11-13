package greedy;

import Resource.Map;
import Resource.UAV;
import nsga2.Individual;
import sa.Population;
import sa.Variation2;

import java.util.ArrayList;

public class Iterator {


    static boolean flag;

    public static greedy.Population iterator(greedy.Population father, Map map) throws CloneNotSupportedException {
        flag = false;
        Population p = new Population();
        //这两层for循环是用来复制原来的
        for (int i = 0; i < father.map.size(); i++) {
            /*
             * 接下来执行复制操作
             */
            Individual individual;
            individual = father.map.get(i).clone();

            individual.uav = new ArrayList<UAV>();
            for (UAV u1 : father.map.get(i).uav) {
                UAV ux = new UAV();
                ux.timeToCompleteAllTasks = u1.timeToCompleteAllTasks;
                ux.length = u1.length;
                for (int ttt = 0; ttt < u1.nodeSequence.size(); ttt++) {
                    ux.nodeSequence.add(u1.nodeSequence.get(ttt));
                }
                for (Integer x : u1.nodeSet) {
                    ux.nodeSet.add(x);
                }

                individual.uav.add(ux);
            }
            p.map.put(i, individual);
            // System.out.println(individual.fitness1+" "+individual.fitness2);
//    		 if(individual.fitness1==Float.MAX_VALUE||individual.fitness2==Float.MAX_VALUE)
//    		 {
//    			 for(int t=0;t<individual.uav.size();t++)
//    			 System.out.println(individual.uav.get(t).nodeSequence);
//    		 }
        }

        for (int i = 0; i < p.map.size(); i++) {
            Variation2.variation2(p.map.get(i), map);
        }
        for (int i = 0; i < p.map.size(); i++) {
            if ((p.map.get(i).fitness1 < father.map.get(i).fitness1 && p.map.get(i).fitness2 < father.map.get(i).fitness2) ||
                    (p.map.get(i).fitness1 <= father.map.get(i).fitness1 && p.map.get(i).fitness2 < father.map.get(i).fitness2) ||
                    (p.map.get(i).fitness1 < father.map.get(i).fitness1 && p.map.get(i).fitness2 <= father.map.get(i).fitness2)) {
                father.map.put(i, p.map.get(i));
                flag = true;
            }
        }

        return father;
    }
}