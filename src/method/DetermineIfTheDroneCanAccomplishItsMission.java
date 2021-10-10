package method;

import java.util.ArrayList;
import java.util.HashSet;

import Resource.Map;
import Resource.UAV;
import subiterations.RandomGeneratedSequence;
/*
 * 判断无人机是否能够完成分给他的任务
 */
public class DetermineIfTheDroneCanAccomplishItsMission {
	public static boolean determineIfTheDroneCanAccomplishItsMission(UAV u,Map map) throws CloneNotSupportedException
	{
		//用来存放现有序列
		ArrayList<Integer>cunchu=new ArrayList();
//		System.out.println(u.nodeSequence+"determineIfTheDroneCanAccomplishItsMission");
		cunchu.addAll(u.nodeSequence);
		HashSet<Integer>hs2=new HashSet();
		hs2.addAll(u.nodeSet);
		//查看到底是哪个节点新进来了
		hs2.removeAll(cunchu);
		int p=-1;
		//正常情况下，hs2里面只会有一个节点//除非exchange4方法会出现多个节点
		
		for(int x:hs2)
		{
			p=x;
		}
		//如果没有节点那么就出问题了//如果是exchange4方法没有节点是没有问题的
		if(hs2.size()<0)
		{
			Calfitness2.calfitness(map, u);
			return true;
		}
		
		
//		for(int x:map.whichChargingStationIsCloser)
//		{
//			System.out.print(x+" ");
//		}
		ArrayList<?> arr;
		//如果只有一个元素，直接算就行了
		if(u.nodeSet.size()==1)
		{
			u.nodeSequence.clear();
			u.nodeSequence.addAll(u.nodeSet);
			u.nodeSequence.addAll(u.nodeSet);
//			arr=Calfitness.calfitness(map, u);
//		if(arr!=null)return true;
//		else return false;
			
			return (Calfitness2.calfitness(map, u)!=null);
		}
		
		
		//这个是根据任务的截止日期前后来安排任务顺序
		if(DetermineTheTraversalOrderInOrderOfTheDeadline.determineTheTraversalOrderInOrderOfTheDeadline(u, map))
		{
			if(u.nodeSequence.size()!=u.nodeSet.size()*2)
			{
				System.out.println("judge+1");
			}
			return true;
		
		}
		//轮盘赌来安排任务顺序//根据时间和距离
		if(ThePathGeneratedByARouletteWheelOfTimeAndDistance.thePathGeneratedByARouletteWheelOfTimeAndDistance(u, map))
		{
			if(u.nodeSequence.size()!=u.nodeSet.size()*2)
			{
				System.out.println("judge+2");
			}
			return true;
		}
		
		
		u.nodeSequence.clear();
		u.nodeSequence.addAll(cunchu);
		//插入排序
		if(hs2.size()==1)
		if(InsertIntoArrange.insertIntoArrange(u,map,p))
		{
			if(u.nodeSequence.size()!=u.nodeSet.size()*2)
			{
				System.out.println("judge+3");
			}
			return true;
		}
		
		if(hs2.size()>1)
		if(InsertIntoArrange.insertIntoArrange(u, map, hs2))
		{
			if(u.nodeSequence.size()!=u.nodeSet.size()*2)
			{
				System.out.println("judge+4");
			}
		          return true;
		}
		
		
		//如果只需要遍历小于4个节点的话，只有90种可能，全部计算一遍就行了
//		if(u.nodeSet.size()<4)
//		{
////			if(TheWholeArrangement.theWholeArrangement(u, map)==true)return true;
////			return false;
//			return TheWholeArrangement.theWholeArrangement(u, map);
//		}
//		
		
	
		
		
		
		//如果大于等于四个节点，全排列就太多了，只能随机生成一些
//	for(int i=0;i<50;i++)
//	{   //随机生成一段序列
//		RandomGeneratedSequence.randomGeneratedSequence(u,map);
//		//选取离第一个无线传感器节点最近的无人机
////		u.index=map.whichChargingStationIsCloser[u.nodeSequence.get(0)];
//		//传回参数
//		arr=Calfitness.calfitness(map, u);
//		
//		if(arr!=null)  return true;
//			
//	}
		return false;
	}
}
