package nsga2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

import Resource.Map;
import Resource.UAV;

import method.DetermineIfTheDroneCanAccomplishItsMission;
import subiterations.GA;
import subiterations.SubGreedy;
import subiterations.SubSa;
//
public class Exchange4 {
	public static String subIterator="sa";
	public static ArrayList<Individual> exchange4(Individual a,Individual b,Map map) throws CloneNotSupportedException
	{
		
		int x=a.uav.size();
		int y=b.uav.size();
		
		//������arraylist�������1-���˻�������n����������˳��
	    ArrayList<Integer> uava=new ArrayList<Integer>();
	    ArrayList<Integer> uavb=new ArrayList<Integer>();
	   
	    
	    //������hashset��������Ѿ��������˻��Ľڵ�
	    HashSet<Integer> ash=new HashSet<Integer>();
	    HashSet<Integer> bsh=new HashSet<Integer>();
	    for(int i=0;i<x;i++)
	    {
	    	uava.add(i);
	    }
	    for(int i=0;i<y;i++)
	    {
	    	uavb.add(i);
	    }
//	    Collections.shuffle(uava);
//	    Collections.shuffle(uavb);
	    
	    Collections.sort(a.uav, new MyComparator());
	    Collections.sort(b.uav,new MyComparator());
	    int x1=x/3*2;
	    int y1=y/3*2;
	    Individual son1=new Individual();
	    Individual son2=new Individual();
	    for(int i=0;i<x1;i++)
	    {
	    	int t=uava.get(i);
	    	UAV u=a.uav.get(t);
	    	UAV u1=new UAV();
	    	u1.timeToCompleteAllTasks=u.timeToCompleteAllTasks;
	    	u1.length=u.length;
	    	u1.nodeSequence.addAll(u.nodeSequence);
	    	u1.nodeSet.addAll(u.nodeSet);
	    	son1.uav.add(u1);
	    	
	    	ash.addAll(u.nodeSet);
	    }
	    
	    for(int i=0;i<y1;i++)
	    {
	    	int t=uavb.get(i);
	    	UAV u=b.uav.get(t);
	    	UAV u1=new UAV();
	    	u1.timeToCompleteAllTasks=u.timeToCompleteAllTasks;
	    	u1.length=u.length;
	    	u1.nodeSequence.addAll(u.nodeSequence);
	    	u1.nodeSet.addAll(u.nodeSet);
	    	son2.uav.add(u1);
	    	
	    	bsh.addAll(u.nodeSet);
	    }
	    
	    ArrayList<HashSet<Integer>>arrayhashseta=new ArrayList<>();
	    ArrayList<HashSet<Integer>>arrayhashsetb=new ArrayList<>();
	    for(int i=0;i<a.uav.size();i++)
	    {
	    	HashSet<Integer>hs=new HashSet<Integer>(); 
	    	UAV u=a.uav.get(i);
	    	for(Integer x11:u.nodeSet)
	    	{
	    		if(!bsh.contains(x11))
	    		{
	    			hs.add(x11);
	    		}
	    	}
	    	arrayhashsetb.add(hs);
	    	
	    }
	    
	    for(int i=0;i<b.uav.size();i++)
	    {
	    	HashSet<Integer>hs=new HashSet<Integer>(); 
	    	UAV u=b.uav.get(i);
	    	for(Integer x11:u.nodeSet)
	    	{
	    		if(!ash.contains(x11))
	    		{
	    			hs.add(x11);
	    		}
	    	}
	    	arrayhashseta.add(hs);
	    }
	  //�����arrayhashseta��arrayhashsetbװ���ǽڵ㼯��
	    son1.uav.add(new UAV());
		son2.uav.add(new UAV());
	    
	    Iterator<HashSet<Integer>> it=arrayhashseta.iterator();
		   while(it.hasNext())
		   {
			   HashSet<Integer> k=it.next();
			   if(k.isEmpty())continue;
			   UAV u=son1.uav.get(son1.uav.size()-1);
			   UAV ucopy=new UAV();
			   ucopy.nodeSequence.addAll(u.nodeSequence);
			   ucopy.nodeSet.addAll(u.nodeSet);
			   ucopy.nodeSet.addAll(k);
			   
			   
			   
			   if(DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(ucopy, map))
			   {
				   switch (subIterator){
				      case "sa":
				    	  SubSa.subSa(ucopy, map);
				    	  break;
				      case "Greedy":
				    	  SubGreedy.subGreedy(ucopy, map);
				    	  break;
				      case "ga":
				    	  GA.ga(ucopy, map);
				    	  break;
				      case "no":
				    	  break;
				   }
				   u.nodeSequence.clear();
				   u.nodeSequence.addAll(ucopy.nodeSequence);
				  
				   u.nodeSet.addAll(ucopy.nodeSet);
				   u.length=ucopy.length;
				   u.timeToCompleteAllTasks=ucopy.timeToCompleteAllTasks;
			   }
			   else
			   {
				   UAV u2=new UAV();
				   u2.nodeSet.addAll(k);
				   u2.nodeSequence.addAll(k);
				   u2.nodeSequence.addAll(k);
				   Collections.sort(u2.nodeSequence);
				   DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(u2, map);
				   son1.uav.add(u2);
			   }
			   
		   }
		   
		   
		   it=arrayhashsetb.iterator();
		   while(it.hasNext())
		   {
			   HashSet<Integer> k=it.next();
			   if(k.isEmpty())continue;
			   UAV u=son2.uav.get(son2.uav.size()-1);
			   UAV ucopy=new UAV();
			   ucopy.nodeSet.addAll(u.nodeSet);
			   ucopy.nodeSet.addAll(k);
			   ucopy.nodeSequence.addAll(u.nodeSequence);
			   if(DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(ucopy, map))
			   {
				   switch (subIterator){
				      case "sa":
				    	  SubSa.subSa(ucopy, map);
				    	  break;
				      case "Greedy":
				    	  SubGreedy.subGreedy(ucopy, map);
				    	  break;
				      case "ga":
				    	  GA.ga(ucopy, map);
				    	  break;
				   }
				   //SubGreedy.subGreedy(ucopy, map);
				  // GA.ga(ucopy, map);
				  
				   u.nodeSequence.clear();
				   u.nodeSequence.addAll(ucopy.nodeSequence);
				  
				   u.nodeSet.addAll(ucopy.nodeSet);
				   u.length=ucopy.length;
				   u.timeToCompleteAllTasks=ucopy.timeToCompleteAllTasks;
			   }
			   else
			   {
				   UAV u2=new UAV();
				   u2.nodeSet.addAll(k);
				   u2.nodeSequence.addAll(k);
				   u2.nodeSequence.addAll(k);
				   Collections.sort(u2.nodeSequence);
				   DetermineIfTheDroneCanAccomplishItsMission.determineIfTheDroneCanAccomplishItsMission(u2, map);
				   son2.uav.add(u2);
			   }
			   
		   }
		   ArrayList<Individual>result=new ArrayList<Individual>();
		    if(son1.uav.get(son1.uav.size()-1).nodeSet.isEmpty())
		    {
		    	son1.uav.remove(son1.uav.size()-1);
		    }
		    
		    if(son2.uav.get(son2.uav.size()-1).nodeSet.isEmpty())
		    {
		    	son2.uav.remove(son2.uav.size()-1);
		    }
		    
		    
		   son1.calfitness1();
		   son1.calfitness2();
		   son2.calfitness1();
		   son2.calfitness2();
		   result.add(son1);
		   result.add(son2);
		   return result;
	 
	}
}

class MyComparator implements Comparator<UAV>
{

	@Override
	public int compare(UAV o1, UAV o2) {
		// TODO Auto-generated method stub
		//ԽС��Խ��
	  double a=o1.timeToCompleteAllTasks/o1.nodeSet.size();
	  double b=o2.timeToCompleteAllTasks/o2.nodeSet.size();
	  if(a<b)return 1;
	  if(a>b)return -1;
	  return 0;
	}
	
}
