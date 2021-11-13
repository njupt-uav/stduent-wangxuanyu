package nsga2;

import Resource.Map;
import Resource.UAV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class GenerateNextPopulation {
    public static String iterator = "variation2";

    public static Population generateNextPopulation(Population pop, Map map, int iteratorTimes) throws CloneNotSupportedException {
        HashSet<Integer> hs = new HashSet<>(300);
        float x1 = (float) 0, x2 = (float) 0.3, x3 = (float) 0.8, x4 = (float) 0.8, t = (float) 0.3;
        switch (iterator) {
            case "variation2":
                x1 = 0;
                x2 = 0;
                x3 = 1;
                break;
            case "variation2+variation4":
                x1 = 0;
                x2 = (float) 0.4;
                x3 = (float) 1;
                break;
            case "variation2+variation3+variation4":
                x1 = 0;
                x2 = (float) 0.3;
                x3 = (float) 0.7;
                x4 = (float) 0.95;
                break;
            case "ReduceTime":
                x1 = 0;
                x2 = 0;
                x3 = 0;
                x4 = 0;
                break;
            case "variation4":
                x1 = 0;
                x2 = 1;
                break;
        }

        Population p = new Population();
        //������forѭ������������ԭ����
        for (int i = 0; i < pop.map.size(); i++) {   /*
         *���Ȱ���Щindividual��length��time����hashset��
         */
//		 String s=String.valueOf(pop.map.get(i).fitness1)+String.valueOf(pop.map.get(i).fitness2);
//		 hs.add(s.hashCode());
//		 
            /*
             * ������ִ�и��Ʋ���
             */
            Individual individual;
            individual = pop.map.get(i).clone();
            individual.dominate = new ArrayList<>();
            individual.uav = new ArrayList<>();
//		 System.out.println("��ʼ");
            for (UAV u1 : pop.map.get(i).uav) {
//			 System.out.println("generateNextPopulation"+u1.nodeSequence);
                UAV ux = new UAV();
                ux.timeToCompleteAllTasks = u1.timeToCompleteAllTasks;
                ux.length = u1.length;
                ux.nodeSequence.addAll(u1.nodeSequence);
                ux.nodeSet.addAll(u1.nodeSet);

                individual.uav.add(ux);
            }

            p.map.put(i, individual);
            String s = individual.fitness1 + "" + individual.fitness2;
            hs.add(s.hashCode());
//		 for(UAV uxx:individual.uav)
//			 for(Integer p1:uxx.nodeSet)
//			 {
//				 int count=Collections.frequency(uxx.nodeSequence, p1);
//				 if(count!=2)System.out.println(uxx.nodeSequence+"generate"+uxx.nodeSet);
//			 }

        }


        float rand;
        float rand2 = (float) Math.random();

        if (rand2 > t)
            for (int i = 0; i < p.map.size(); i++) {
                rand = (float) Math.random();
                if (rand > x1 && rand < x2) {
//			long time=System.currentTimeMillis();
                    Variation4.variation4(p.map.get(i), map);


                    String s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                    int count = 0;
                    while (hs.contains(s.hashCode())) {
                        Variation2.variation2(p.map.get(i), map);
                        s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                        if (count++ > 100) {
                            ReduceTime.reduceTime(p.map.get(i), map);
                            s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                        }
                    }
//			 time=System.currentTimeMillis()-time;
//			 System.out.println("variation4 "+time);
                } else if (rand > x2 && rand < x3) {
//			 long time=System.currentTimeMillis();
                    Variation2.variation2(p.map.get(i), map);
//			 time=System.currentTimeMillis()-time;
//			 System.out.println("variation2 "+time);
                    String s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                    int count = 0;
//			 time=System.currentTimeMillis();
                    while (hs.contains(s.hashCode())) {
                        Variation2.variation2(p.map.get(i), map);
                        s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                        if (count++ > 100) {
                            ReduceTime.reduceTime(p.map.get(i), map);
                            s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                        }
                    }
//			 time=System.currentTimeMillis()-time;
//			 System.out.println("variation2ȥ�� "+time);
                } else if (rand > x3 && rand < x4) {
//			 long time=System.currentTimeMillis();
                    InitIndividual3.initIndividual3(p.map.get(i), map);

                    String s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                    int count = 0;
                    while (hs.contains(s.hashCode())) {
                        Variation2.variation2(p.map.get(i), map);
                        s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                        if (count++ > 100) {
                            ReduceTime.reduceTime(p.map.get(i), map);
                            s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                        }
                    }
//			 time=System.currentTimeMillis()-time;
//			 System.out.println("initindividual "+time);
                } else {
//			 long time=System.currentTimeMillis();
                    ReduceTime.reduceTime(p.map.get(i), map);


                    String s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                    while (hs.contains(s.hashCode())) {
                        Variation2.variation2(p.map.get(i), map);
                        s = p.map.get(i).fitness1 + "" + p.map.get(i).fitness2;
                    }
//			 time=System.currentTimeMillis()-time;
//			 System.out.println("reduceTime"+time);
                }

            }
        else {
            ArrayList<Integer> array = new ArrayList<>();
            for (int j = 0; j < pop.map.size(); j++) {
                array.add(j);
            }
            Collections.shuffle(array);
            String s;
            for (int i = 0; i < p.map.size() / 2; i++) {
//			  long time=System.currentTimeMillis();
                ArrayList<Individual> arr = Exchange4.exchange4(p.map.get(array.get(array.size() - 1)), p.map.get(array.get(array.size() - 2)), map);
//			  time=System.currentTimeMillis()-time;
//			  System.out.println("exchange "+time);

                s = p.map.get(array.get(array.size() - 1)).fitness1 + "" + p.map.get(array.get(array.size() - 1)).fitness2;


//			  time=System.currentTimeMillis();
                int count = 0;
                while (hs.contains(s.hashCode())) {
                    Variation2.variation2(p.map.get(array.get(array.size() - 1)), map);
                    s = p.map.get(array.get(array.size() - 1)).fitness1 + "" + p.map.get(array.get(array.size() - 1)).fitness2;
                    if (count++ > 100) {
                        ReduceTime.reduceTime(p.map.get(i), map);
                        s = p.map.get(array.get(array.size() - 1)).fitness1 + "" + p.map.get(array.get(array.size() - 1)).fitness2;
                    }
                }

                s = p.map.get(array.get(array.size() - 2)).fitness1 + "" + p.map.get(array.get(array.size() - 2)).fitness2;
                int count1 = 0;
                while (hs.contains(s.hashCode())) {
                    Variation2.variation2(p.map.get(array.get(array.size() - 2)), map);
                    s = p.map.get(array.get(array.size() - 2)).fitness1 + "" + p.map.get(array.get(array.size() - 2)).fitness2;
                    if (count1++ > 100) {
                        ReduceTime.reduceTime(p.map.get(array.get(array.size() - 2)), map);
                        s = p.map.get(array.get(array.size() - 2)).fitness1 + "" + p.map.get(array.get(array.size() - 2)).fitness2;
                    }
                }
//			  time=System.currentTimeMillis()-time;
//			 System.out.println("exchangeȥ�� "+time);

                array.remove(array.size() - 1);
                array.remove(array.size() - 1);
            }

        }
        return p;
    }
}
