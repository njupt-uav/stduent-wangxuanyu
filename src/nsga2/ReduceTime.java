package nsga2;

import java.util.ArrayList;
import java.util.Collections;

import Resource.Map;
import Resource.UAV;
import method.Calfitness;
import method.Calfitness2;
import method.DetermineIfTheDroneCanAccomplishItsMission;
import subiterations.GA;
import subiterations.SubGreedy;
import subiterations.SubSa;

public class ReduceTime {
  public static void reduceTime(Individual individual,Map map) throws CloneNotSupportedException
  {
	  //�ҳ���ʱ����Ǹ����˻�
	  UAV u=individual.uav.get(0);
	  for(UAV ux:individual.uav)
	  {
		  if(u.timeToCompleteAllTasks<ux.timeToCompleteAllTasks)
		  {
			  u=ux;
		  }
	  }

	   if(u.nodeSet.size()==1)return;
	  /*
	   * x�������ʱ��������˻������ѡ���һ���ڵ�
	   */
	  int x=(int) (Math.random()*u.nodeSequence.size());
	  x=u.nodeSequence.get(x);
      
	
	  
	  boolean flag=false;
	  /*
	   * ����Ūһ��Arraylist������Ϊ���ܹ������һ̨���˻�������ÿ�ζ�ѡ���һ̨���˻�
	   */
	  ArrayList<UAV> uav=new ArrayList();
	  uav.addAll(individual.uav);
	  Collections.shuffle(uav);
	  for(UAV ux:uav)
	  {
		  if(ux==u)continue;
		  
		  UAV uavCopy=new UAV();
		  uavCopy.nodeSet.addAll(ux.nodeSet);
		  uavCopy.nodeSequence.addAll(ux.nodeSequence);
		  uavCopy.nodeSet.add(x);
		  if(DetermineIfTheDroneCanAccomplishItsMission.
		     determineIfTheDroneCanAccomplishItsMission(uavCopy, map)
			 &&uavCopy.timeToCompleteAllTasks<u.timeToCompleteAllTasks)
		  {
			  
			  if(uavCopy.nodeSet.size()>3)
	        		switch(Exchange4.subIterator)
	        		{
	        		  case "sa":
				    	  SubSa.subSa(uavCopy, map);
				    	  break;
				      case "Greedy":
				    	  SubGreedy.subGreedy(uavCopy, map);
				    	  break;
				      case "ga":
				    	  GA.ga(uavCopy, map);
				    	  break;
				      case "no":
	                    break;
	        		}
			  ux.nodeSequence.clear();
			  ux.nodeSet.clear();
			  ux.nodeSequence.addAll(uavCopy.nodeSequence);
			  ux.nodeSet.addAll(uavCopy.nodeSet);
			  ux.length=uavCopy.length;
			  ux.timeToCompleteAllTasks=uavCopy.timeToCompleteAllTasks;
			  flag=true;
			  break;
		  }
	  }
	  /*
	   * ��������������˻� �ᵼ����������˻�������������
	   */
	  if(flag==false)
	  {
		  UAV u1=new UAV();
		  u1.nodeSet.add(x);
		  u1.nodeSequence.add(x);
		  u1.nodeSequence.add(x);
		  Calfitness2.calfitness(map, u1);
		  individual.uav.add(u1);
	  }
		 
	  
	 
	  
	  u.nodeSet.remove(x);
	  u.nodeSequence.remove(Integer.valueOf(x));
	  u.nodeSequence.remove(Integer.valueOf(x));
	  Calfitness2.calfitness(map, u);
	  
		
	  
	  for(int i=individual.uav.size()-1;i>=0;i--)
	  {
		  if(individual.uav.get(i).nodeSet.size()==0)
			  individual.uav.remove(i);
	  }
	  

	  
	  
        individual.calfitness1();
        individual.calfitness2();
       
  }
  
}
