package nsga2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import Resource.Map;
import Resource.UAV;

public class GenerateNextPopulation2 {
	//variation
	public static Population generateNextPopulation(Population pop,Map map,int iteratorTimes) throws CloneNotSupportedException
	{ 
		HashSet<Integer>hs=new HashSet<Integer>(300);
		float x1=(float) 0,x2=(float)0 ,x3=(float) 0.6,x4=(float) 0.61,t=(float) 0.6;


		 Population p=new Population();
		 //这两层for循环是用来复制原来的
		 for(int i=0;i<pop.map.size();i++)
		 {   /*
		      *首先把这些individual的length和time放入hashset中
		      */
//			 String s=String.valueOf(pop.map.get(i).fitness1)+String.valueOf(pop.map.get(i).fitness2);
//			 hs.add(s.hashCode());
//			 
			 /*
			  * 接下来执行复制操作
			  */                                                                                                         
			 Individual individual;
			 individual=pop.map.get(i).clone();
			 individual.dominate=new ArrayList<Individual>();
			 individual.uav=new ArrayList<UAV>();
			 for(UAV u1:pop.map.get(i).uav)
			 {
				 UAV ux=new UAV();
				 ux.timeToCompleteAllTasks=u1.timeToCompleteAllTasks;
				 ux.length=u1.length;
				 for(int ttt=0;ttt<u1.nodeSequence.size();ttt++)
				 {
					 ux.nodeSequence.add(u1.nodeSequence.get(ttt));
				 }
				 for(Integer x:u1.nodeSet)
				 {
					 ux.nodeSet.add(x);
				 }
				 
				 individual.uav.add(ux);
			 }
			 
			 p.map.put(i, individual);
			 String s=individual.fitness1+""+individual.fitness2;
			 hs.add(s.hashCode());
//			 for(UAV uxx:individual.uav)
//				 for(Integer p1:uxx.nodeSet)
//				 {
//					 int count=Collections.frequency(uxx.nodeSequence, p1);
//					 if(count!=2)System.out.println(uxx.nodeSequence+"generate"+uxx.nodeSet);
//				 }
			
		 }
		 
		 
		 
		 
		
		 float rand;
		 float rand2=(float) Math.random();
		
		 if(rand2>t)
		 for(int i=0;i<p.map.size();i++)
		 {
			  rand=(float) Math.random();
			 if(rand>x1&&rand<x2)
			 {
				//long time=System.currentTimeMillis();
				 Variation.variation(p.map.get(i), map);
				 //time=System.currentTimeMillis()-time;
				 //System.out.println("variation"+time);
				 String s=p.map.get(i).fitness1+""+p.map.get(i).fitness2;
				 if(hs.contains(s.hashCode()))
				 {
					 Variation2.variation2(p.map.get(i), map);
				 }
			 }
			 else
			 if(rand>x2&&rand<x3)
			 {
				// long time=System.currentTimeMillis();
				 Variation2.variation2(p.map.get(i), map);
				// time=System.currentTimeMillis()-time;
				// System.out.println("variation2"+time);
				 String s=p.map.get(i).fitness1+""+p.map.get(i).fitness2;
				 if(hs.contains(s.hashCode()))
				 {
					 Variation2.variation2(p.map.get(i), map);
				 }
			 }
			 else
			 if(rand>x3&&rand<x4)
			 {
				// long time=System.currentTimeMillis();
				 InitIndividual.initIndividual(p.map.get(i), map);
				// time=System.currentTimeMillis()-time;
				// System.out.println("individual"+time);
				 String s=p.map.get(i).fitness1+""+p.map.get(i).fitness2;
				 if(hs.contains(s.hashCode()))
				 {
					 Variation2.variation2(p.map.get(i), map);
				 }
			
			 }
			 else
			 {
				// long time=System.currentTimeMillis();
				 ReduceTime.reduceTime(p.map.get(i), map);
				 
				// time=System.currentTimeMillis()-time;
				// System.out.println("reduceTime"+time);
				 String s=p.map.get(i).fitness1+""+p.map.get(i).fitness2;
				 if(hs.contains(s.hashCode()))
				 {
					 Variation2.variation2(p.map.get(i), map);
				 }
			 }
			
		 }
		 
		 
		 else {
			 
			 
			 ArrayList<Integer> array=new ArrayList();
			 for(int j=0;j<pop.map.size();j++)
			 {
				 array.add(j);
			 }
			 Collections.shuffle(array);
			  for(int i=0;i<p.map.size()/2;i++)  
			  {  
				 // long time=System.currentTimeMillis();
				  ArrayList<Individual> arr=Exchange4.exchange4(p.map.get(array.size()-1), p.map.get(array.size()-2), map); 
				//  time=System.currentTimeMillis()-time;
				// System.out.println("exchange"+time);
				  array.remove(array.size()-1);
				  array.remove(array.size()-1);
				 p.map.put(i, (Individual) arr.get(0));
				 p.map.put(i+p.map.size()/2, (Individual) arr.get(1));
			  }
		 
		 } 
		 return p;
	}
	
}
