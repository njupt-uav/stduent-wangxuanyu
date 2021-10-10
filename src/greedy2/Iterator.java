package greedy2;

import java.util.ArrayList;

import Resource.Map;
import Resource.UAV;
import nsga2.Individual;
import sa.Population;
import sa.Variation2;

public class Iterator {

	
	 
     public static void iterator(Individual in,Map map,ArrayList<Individual>list) throws CloneNotSupportedException
     {
    	 Individual individual=in.clone();
		 Variation2.variation2(individual, map);
		 
		 if((individual.fitness1<=in.fitness1&&individual.fitness2<in.fitness2)||
			(individual.fitness1<in.fitness1&&individual.fitness2<=in.fitness2))
		 {
			in.fitness1=individual.fitness1;
			in.fitness2=individual.fitness2;
			in.uav=individual.uav;
		 }
		 
			 list.add(individual.clone());
			 SelectTheFirstRank.selectTheFirstRank(list);
			 if(!list.contains(in))
			 {
				 list.add(in);
			 }
		 
     }
}