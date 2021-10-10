package sa2;

import Resource.Map;
import Resource.UAV;
import method.Calfitness;
import method.DetermineIfTheDroneCanAccomplishItsMission;
import nsga2.Individual;

public class Variation {
public static void variation(Individual individual,Map map ) throws CloneNotSupportedException
{
	int[]individualselectTheUAVForTheNode=new int[map.totalNum];
	for(int i=0;i<individual.uav.size();i++)
	{
		UAV u=individual.uav.get(i);
		for(Integer x:u.nodeSet)
		{
			individualselectTheUAVForTheNode[x]=i;
		}
	}
	    //x������ҳ�����һ���ڵ�
		int x=(int) (Math.random()*(map.totalNum-map.csNum)+map.csNum);
		//yҲ������ҳ�����һ���ڵ�
		int y=(int) (Math.random()*(map.totalNum-map.csNum)+map.csNum);
		//x�ڵ������������u1��ɵ�
		UAV u1=individual.uav.get(individualselectTheUAVForTheNode[x]);
		//y�ڵ������������u2��ɵ�
		UAV u2=individual.uav.get(individualselectTheUAVForTheNode[y]);
		u1.nodeSet.remove(x);
		u1.nodeSequence.remove(Integer.valueOf(x));
		u1.nodeSequence.remove(Integer.valueOf(x));
		u2.nodeSet.remove(y);
		u2.nodeSequence.remove(Integer.valueOf(y));
		u2.nodeSequence.remove(Integer.valueOf(y));
		
			
		
			
		
		UAV uav1Copy=new UAV();
		uav1Copy.nodeSet.addAll(u1.nodeSet);
		uav1Copy.nodeSet.add(y);
		java.util.Iterator<UAV> it1=individual.uav.iterator();
		 if(DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(uav1Copy,map)==true)
	        {
	        	u1.nodeSequence=uav1Copy.nodeSequence;
	        	u1.nodeSet=uav1Copy.nodeSet;
	            u1.length=uav1Copy.length;
	        	u1.timeToCompleteAllTasks=uav1Copy.timeToCompleteAllTasks;
	        }
	        else
	        {
	        	
	             boolean flag=false;
	        	while(it1.hasNext())
	        	{
	        		
	        		UAV ux=(UAV) it1.next();
	        		if(ux==u1||ux==u2)continue;
	        		UAV uav1=new UAV();
	        		uav1.nodeSet.addAll(ux.nodeSet);
	        		uav1.nodeSet.add(y);
	        		
	        		if(DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(uav1,map)==true)
	        		{
	        			ux.nodeSequence=uav1.nodeSequence;
	        			ux.length=uav1.length;
	        			ux.nodeSet=uav1.nodeSet;
	        			ux.timeToCompleteAllTasks=uav1.timeToCompleteAllTasks;
	        		
	        			flag=true;
	        			break;
	        		}
	        		
	        	}
	        	//�������˻����޷����
	        	if(flag==false)
	        	{
	        		UAV uxx=new UAV();
	        		individual.uav.add(uxx);
	        		uxx.nodeSet.add(y);
	        		uxx.nodeSequence.add(y);
	        		uxx.nodeSequence.add(y);
	        		Calfitness.calfitness(map, uxx);
//	        		if(u1.length==0)System.out.println("u1+variation");
	        	}
}
		 
		 
		    UAV u2Copy=new UAV();
			u2Copy.nodeSet.addAll(u2.nodeSet);
			u2Copy.nodeSet.add(x);
			java.util.Iterator<UAV> it=individual.uav.iterator();
			 if(DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(u2Copy,map)==true)
		        {
		        	u2.nodeSequence=u2Copy.nodeSequence;
		        	u2.nodeSet=u2Copy.nodeSet;
		            u2.length=u2Copy.length;
		        	u2.timeToCompleteAllTasks=u2.timeToCompleteAllTasks;
		        }
		        else
		        {
		        	
		             boolean flag=false;
		        	while(it.hasNext())
		        	{
		        		
		        		UAV ux=(UAV) it.next();
		        		if(ux==u1||ux==u2)continue;
		        		UAV uav1=new UAV();
		        		uav1.nodeSet.addAll(ux.nodeSet);
		        		uav1.nodeSet.add(x);
		        		
		        		if(DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(uav1,map)==true)
		        		{
		        			ux.nodeSequence=uav1.nodeSequence;
		        			ux.length=uav1.length;
		        			ux.nodeSet=uav1.nodeSet;
		        			ux.timeToCompleteAllTasks=uav1.timeToCompleteAllTasks;
		        		
		        			flag=true;
		        			break;
		        		}
		        		
		        	}
		        	//�������˻����޷����
		        	if(flag==false)
		        	{
		        		UAV uxx=new UAV();
		        		individual.uav.add(uxx);
		        		uxx.nodeSet.add(x);
		        		uxx.nodeSequence.add(x);
		        		uxx.nodeSequence.add(x);
		        		Calfitness.calfitness(map, uxx);
//		        		if(u1.length==0)System.out.println("u1+variation");
		        	}
	}
			 Calfitness.calfitness(map, u1);
			 Calfitness.calfitness(map, u2);
		 individual.calfitness1();
		 individual.calfitness2();
}
}
