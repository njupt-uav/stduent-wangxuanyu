package variableNeighborhood;

import java.util.ArrayList;
import java.util.Collections;

import Resource.Map;
import Resource.UAV;
import method.Calfitness;
import method.DetermineIfTheDroneCanAccomplishItsMission;
import nsga2.Individual;
import subiterations.GA;

public class Variation2 {
	
	   public static void variation2(Individual individual,Map map) throws CloneNotSupportedException
	    {
	    	if(individual.uav.size()==0||individual.uav.size()==1)return;
	    	//x�����˻��ı�š��������
	    	int x=(int) (Math.random()*individual.uav.size());
	    	//���Ƕ�Ӧ�����˻�
	         UAV u=individual.uav.get(x);
	         //���ڴ�����˻�Ҫ�����Ľڵ�
	         ArrayList<Integer> arr=new ArrayList<Integer>();
	         //�����˻�Ҫ�����Ľڵ����arr��
	         arr.addAll(u.nodeSet);
	         //x�ǽڵ���arr�е�λ�ã��������
	         x=(int)Math.random()*arr.size();
	         //x�ǽڵ�ı��
	         x=(int) arr.get(x);
	         u.nodeSet.remove(x);
	         if(u.nodeSet.isEmpty())individual.uav.remove(u);
	         else
	         {
	        	// System.out.println(u.nodeSequence+"start");
	        	for(int t=u.nodeSequence.size()-1;t>=0;t--)
	        	{
	        		if(u.nodeSequence.get(t)==x)
	        			u.nodeSequence.remove(t);
	        	}
	        // System.out.println(x);
	         Calfitness.calfitness(map, u);
	        // if(u.timeToCompleteAllTasks==Float.MAX_VALUE)System.out.println("variation2,1");
	         }
	       
	         boolean flag=false;
	         ArrayList<UAV> uavCopy=new ArrayList<UAV>();
	         uavCopy.addAll(individual.uav);
	         Collections.shuffle(uavCopy);
	         for(UAV ux:uavCopy)
	         {
	        	 if(ux==u)continue;
	        	 UAV u1=new UAV();
	        	 u1.nodeSet.addAll(ux.nodeSet);
	        	 u1.nodeSet.add(x);
	        	 if(DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(u1, map))
	        	 { 
	        		 GA.ga(u1, map);
	        		 ux.nodeSequence=u1.nodeSequence;
	        		 ux.length=u1.length;
	        		 ux.nodeSet=u1.nodeSet;
	        		 ux.timeToCompleteAllTasks=u1.timeToCompleteAllTasks;
	        		// if(ux.timeToCompleteAllTasks==Float.MAX_VALUE)System.out.println("variation2,2");
	        		 flag=true;
	        		 break;
	        	 }
	        	
	         }
	         //������˻������Ҳ���һ��������ɸ���������˻�����������һ�����˻�
	         if(flag==false)
	         {
	        	 UAV u1=new UAV();
	        	 u1.nodeSet.add(x);
	        	 u1.nodeSequence.add(x);
	        	 u1.nodeSequence.add(x);
//	        	 System.out.println(u1.nodeSet);
	        	 Calfitness.calfitness(map, u1);
	        	 if(u1.timeToCompleteAllTasks==Float.MAX_VALUE)System.out.println("variation2,3");
	        	 //if(u1.nodeSequence.size()!=u1.nodeSet.size()*2)System.out.println("�����ɵ����˻�����");
	        	 individual.uav.add(u1);
	         }
	        
//	         for(int i=0;i<individual.uav.size();i++)
//	         {
//	        	 
//	        	 UAV u2=individual.uav.get(i);
//	        	 for(Integer pp:u2.nodeSet)
//	        	 {
//	        		 individual.selectTheUAVForTheNode[pp]=i;
//	        	 }
//	         }

	        
	         individual.UAVNum=individual.uav.size();
	         individual.calfitness1();
	         individual.calfitness2();
	         
//	         int max=0;
//	    	 for(int i=0;i<individual.selectTheUAVForTheNode.length;i++)
//	    	 {
//	    		 if(max<individual.selectTheUAVForTheNode[i])max=individual.selectTheUAVForTheNode[i];
//	    	 }
//	    	 if(max!=individual.uav.size()-1)
//	    		 {
//	    		 for(int i=0;i<individual.selectTheUAVForTheNode.length;i++)
//	    		 System.out.print(individual.selectTheUAVForTheNode[i]+" ");
//	    	     System.out.println(individual.uav.size()+"variation2");
//	    		 }
	         return ;
	    }
}
