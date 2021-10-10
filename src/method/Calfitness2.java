package method;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import Resource.Map;
import Resource.UAV;

public class Calfitness2 {
	//�޸���calfitnessû�㴫��ʱ�������
	
	public static String chargeMethod="charge";
	
	
	//����ʱ��Ĳ���
	public static float transmissionTimeParameter = (float) (Math.random()*1); 
	//��������ʱ���Ĳ���
	static float returnTransmissionTimeParameter=(float)0.01;
	
	
	
	public static ArrayList<?> calfitness(Map map ,UAV u1) throws CloneNotSupportedException
	{	
		u1.index=map.whichChargingStationIsCloser[u1.nodeSequence.get(0)];
		UAV u=new UAV();
		u=(UAV) u1.clone();
		u.nodeSequence=new  ArrayList<Integer>();
		u.nodeSequence.addAll(u1.nodeSequence);
		u.nodeSet=new HashSet<Integer>();
		u.nodeSet.addAll(u1.nodeSet);
		
//		if(u.nodeSequence.size()!=u.nodeSet.size()*2)
//		{
//	           System.out.println("calfitness2");
//	           System.out.println(u.nodeSequence);
//	           for(int x:u.nodeSet)
//	           {
//	        	   System.out.print(x+" ");
//	           }
//		}
//		Charge2.charge2(u, map);
		switch(chargeMethod)
		{
		  case "charge":
			Charge.charge(u, map);
			break;
		  case "charge2":
			Charge2.charge2(u, map);
			break;
		}
//		if(u.nodeSet.size()==1)
//		{
//			System.out.println(map.csNum);
//			 System.out.println(u.nodeSequence+"calfitness2");
//             System.out.println("deadlineΪ"+map.node[u.nodeSequence.get(0)].deadline);
//             System.out.println("���о���+����ʱ��Ϊ"+map.distance[map.whichChargingStationIsCloser[u1.nodeSequence.get(0)]][u1.nodeSequence.get(0)]/UAV.speed*2+map.node[u.nodeSequence.get(0)].missionTime);
//		}
		
		int theInitialPosition=u1.index;
		
		/**
	          *�������˻��������ܳ���
	      */
		float length=0;
		int index=u.index;//���˻���ǰ����λ��
	   Iterator<Integer> it=u.nodeSequence.iterator();//�Խڵ���б���
	   int next;
	   while(it.hasNext())
	   {
		   next=it.next();
		   map.node[next].isVisited=false;
		   map.node[next].iscal=false;
		   length+=map.distance[index][next];
		   map.node[next].missionTimeLeft=map.node[next].missionTime;
		   index=next;
		  
	   }
	   
	   /**
		 * ���˻��������ݵĽڵ�˳��
		 */
		@SuppressWarnings("unchecked")
		HashSet<Integer> hs=new HashSet();//����ȷ�����˻�������������Ҫ�����ڵ��˳��
		LinkedList<Integer> ReturnOrder=new LinkedList<Integer>();//���������˻��������ݽڵ��˳��
		for(int i:u.nodeSequence)
		{//���
			if(hs.contains(i)&&i>=map.csNum)
			{
				ReturnOrder.add(i);
			}
			else if(i>=map.csNum)   hs.add(i);
		}
		
		
		/**
		 * ���˻�����������ʱ��
		 */
		index=u.index;//���˻���ǰ����
		float TimeThatHasNowPassed=0;//��ǰ�Ѿ���ȥ��ʱ��
		ArrayList<Integer> TheNodesThatNeedToBeEvaluated=new ArrayList<Integer>();//��������Ѿ������˻���ȡ��
		//��δ���м��������//���������
		HashSet<Integer> hSet=new HashSet<Integer>();//��������Ѿ�������ɵ�����
		it=u.nodeSequence.iterator();	
		//���˻�����ʱ��
		float time;
		
	    while(it.hasNext())
	    {    
	    	next=it.next();
	    	 time=map.distance[next][index]/u.speed;//���˻�����ʱ��
	    	
	    	
	    	if(map.node[next].isVisited==false||next<map.csNum)//�ýڵ�û�б���������˵������ȡ�ýڵ������
	    	{ 
	    		//������ڵ���Ϊ�Ѿ�������
	    		if(next>=map.csNum)map.node[next].isVisited=true;
	    		TimeThatHasNowPassed+=time+map.node[next].missionTime*transmissionTimeParameter;//��Ϊ�ýڵ�û�б����������������˻��������ݾ��ߣ��뿪��ʱ������䵽��ʱ��
	    		if(TheNodesThatNeedToBeEvaluated.size()==0)//����뿪��һ���ڵ��ʱ��û��������Լ��㣬˵������������Ϊ��
	    		{   
	    			if(next>=map.csNum)//��Ҫ��֤����ڵ㲻�ǳ��ڵ�
	    			TheNodesThatNeedToBeEvaluated.add(next);//��Ϊֻ��һ���������Բ���Ҫ����
	    		}
	    		else//����뿪�ϸ��ڵ��ʱ����������Լ���
	    		{
	    			while(TheNodesThatNeedToBeEvaluated.size()!=0)
	    			{
	    				if(map.node[TheNodesThatNeedToBeEvaluated.get(0)].missionTimeLeft<time)//��������������֮����ʱ��
	    				{//˵���������Ѿ�������
	    					time-=map.node[TheNodesThatNeedToBeEvaluated.get(0)].missionTimeLeft;
	    					hSet.add(TheNodesThatNeedToBeEvaluated.get(0));
	    					TheNodesThatNeedToBeEvaluated.remove(0);
	    				}
	    				else//������ﱾ�ڵ��ʱ��û������
	    				{
	    					//�������������Ҫ���㣬������Ҫ�Ѹ������Ѿ������ʱ���ȥ
	    					map.node[TheNodesThatNeedToBeEvaluated.get(0)].missionTimeLeft-=time;
	    					//ָʾ��������ڵ��ʱ�����ڼ�������ڵ�
	    					map.node[TheNodesThatNeedToBeEvaluated.get(0)].iscal=true;
	    					break;
	    			 	}
	    			}
	    				
	    			//whileѭ��֮���Ϊ���������Ҫô�ǵ���ýڵ�ʱ�����Ѿ�ȫ�������ˣ�Ҫô�ǻ�����Ҫ���������	
	    				if(TheNodesThatNeedToBeEvaluated.size()==0)//����Ѿ�ȫ��������ɣ���ôֻ��Ҫ���뼴��
	    				{
	    					if(next>=map.csNum)
	    					TheNodesThatNeedToBeEvaluated.add(next);
	    				}
	    				else
	    				{
	    					if(map.node[TheNodesThatNeedToBeEvaluated.get(0)].iscal==true)//���������ǽ��ղ��Ѿ�����Ľڵ�
	                           //�������ڵ�һλ����
	    					{
	    						if(next>=map.csNum)//Ҫȷ���ýڵ������ߴ������ڵ�
	    						{
	    						int x=TheNodesThatNeedToBeEvaluated.get(0);
	    						TheNodesThatNeedToBeEvaluated.remove(0);
	    						TheNodesThatNeedToBeEvaluated.add(next);
	    						TheNodesThatNeedToBeEvaluated.sort((o1,o2)->{
	    			    			int i=ReturnOrder.indexOf(o1)>ReturnOrder.indexOf(o2)?1:-1;
	    			    			return i;
	    			    		});
	    						TheNodesThatNeedToBeEvaluated.add(0, x);
	    						}
	    					}
	    					else
	    					{
	    						if(next>=map.csNum)//ȷ���ýڵ������ߴ������ڵ�
	    						{
	    						TheNodesThatNeedToBeEvaluated.add(next);
	    						TheNodesThatNeedToBeEvaluated.sort((o1,o2)->{
	    			    			int i=ReturnOrder.indexOf(o1)>ReturnOrder.indexOf(o2)?1:-1;
	    			    			return i;
	    			    		});
	    						}
	    					}
	    					
	    				}
	    			
	    		}
	        }
	    	else//������ڵ��Ѿ���������//˵��Ҫ��������
	    	{
	    		TimeThatHasNowPassed+=time+returnTransmissionTimeParameter*map.node[next].missionTime;//
	    		if(hSet.contains(next))//�����������ڵ��ʱ����������Ѿ����������
	    		{
	    			if(TimeThatHasNowPassed>map.node[next].deadline)//������ص�ʱ���Ѿ������˽�ֹ����
	    				{//u.timeToCompleteAllTasks=Float.MAX_VALUE;
	    				return null;//ֱ�ӷ���null
	    				}
	    		}
	    		else//������ﱾ�ڵ��ʱ��û�м������
	    		{
	    			while(!hSet.contains(next))//ֻҪnext��û�м������
	    			{
	    				TimeThatHasNowPassed+=map.node[TheNodesThatNeedToBeEvaluated.get(0)].missionTime;
	    				hSet.add(TheNodesThatNeedToBeEvaluated.get(0));
	    				TheNodesThatNeedToBeEvaluated.remove(0);
	    			}
	    			//������ɺ󷵻�����
	    			if(TimeThatHasNowPassed>map.node[next].deadline)//������ص�ʱ���Ѿ������˽�ֹ����
	    				{//u.timeToCompleteAllTasks=Float.MAX_VALUE;
	    				return null;//ֱ�ӷ���null
	    				}
	    		}
	        }
	           index=next;
	}
	          length+=map.distance[theInitialPosition][index];
	          TimeThatHasNowPassed+=map.distance[theInitialPosition][index]/u.speed;
	            ArrayList ret=new ArrayList();
	            ret.add(length);//��һ�����ط��г��ȣ��ڶ�������ʱ��
	            //����ʱ��
	            ret.add(TimeThatHasNowPassed);
	            u1.length=length;
	            u1.timeToCompleteAllTasks=TimeThatHasNowPassed;
	            return ret;
	            
	}

}
