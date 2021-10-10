package BatchTest;

import java.io.IOException;

import compare.Parameter;

//
//import java.io.IOException;
//
//import compare.PFRange;
//import compare.PFSet;
//import compare.Parameter;
//import compare.SelectTheFirstRank;
//import greedy.GreedyMain;
//import method.Nsga2Main;
//import sa.SaMain;
//import variableNeighborhood.VNMain;
//
public class RuKou2 {//本类最后维护日期:2021.9.4
	//主要用来做主算法比较
  public static void main(String[] args) throws IOException, CloneNotSupportedException {
		ChangeParameter.fileOutPath="D:\\批量实验结果\\"+"batchTest2021_9_4.txt";
		for(int i=0;i<Parameter.mainMethods.length;i++)
		for(int a=0;a<Parameter.map.length;a++)
			for(int b=0;b<Parameter.speed.length;b++)
				for(int e=0;e<Parameter.deadline.length;e++)
				  for(int c=0;c<Parameter.iterator.length;c++)
					 for(int d=0;d<Parameter.subIterator.length;d++)
						 for(int f=0;f<Parameter.selfOwnUAVFactor.length;f++)
							 for(int g=0;g<Parameter.priceMagnificationEnum.length;g++)
								 for(int h=0;h<Parameter.timeMagnification.length;h++)
					     {
						    String mainMethod=Parameter.mainMethods[i];
							int selfOwnUAVFactor= Parameter.selfOwnUAVFactor[f];
							int priceMagnification=Parameter.priceMagnificationEnum[g];
							String map=Parameter.map[a];
							String speed=Parameter.speed[b];
	                        String iterator=Parameter.iterator[c];
							String subIterator=Parameter.subIterator[d];
							String deadline=Parameter.deadline[e];	
							String timeMagni=Parameter.timeMagnification[h];
							ChangeParameter.changeParameter(mainMethod,speed,subIterator,iterator,map,deadline
									,selfOwnUAVFactor,priceMagnification,timeMagni);
						 }
}
}
	
