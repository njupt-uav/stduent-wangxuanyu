package variableNeighborhood;

import java.util.ArrayList;

import Resource.Map;
import Resource.UAV;
import nsga2.Individual;


public class Iterator {
	 public static Population iterator(Population father,Map map,int times) throws CloneNotSupportedException
     {
    	  
    	 Population p=new Population();
    	 //这两层for循环是用来复制原来的
    	 for(int i=0;i<father.map.size();i++)
    	 {   
    		 /*
    		  * 接下来执行复制操作
    		  */
    		 Individual individual;
    		 individual=father.map.get(i).clone();
    		
    		 individual.uav=new ArrayList<UAV>();
    		 for(UAV u1:father.map.get(i).uav)
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
     }
    
    	 for(int i=0;i<p.map.size();i++)
    	 {
    		 Variation2.variation2(p.map.get(i), map);
    		 
    	 }
    	
    	 
    	 for(int i=0;i<p.map.size();i++)
    	 {
    		 if((p.map.get(i).fitness1<father.map.get(i).fitness1&&p.map.get(i).fitness2<father.map.get(i).fitness2)||
    				 (p.map.get(i).fitness1<=father.map.get(i).fitness1&&p.map.get(i).fitness2<father.map.get(i).fitness2)||
    				 (p.map.get(i).fitness1<father.map.get(i).fitness1&&p.map.get(i).fitness2<=father.map.get(i).fitness2))
    		 {
    			 father.map.put(i, p.map.get(i));
    		 }
    		
    	 }
    
    	 return father;
}

	
}
