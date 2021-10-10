package BatchTest;

import java.io.IOException;

import compare.PFRange;
import compare.PFSet;
import compare.Parameter;
import compare.SelectTheFirstRank;
import greedy.GreedyMain;
import method.Nsga2Main;
import sa.SaMain;
import variableNeighborhood.VNMain;

public class RuKou {
public static void main(String[] args) throws IOException, CloneNotSupportedException {

	
	//本类最后维护日期2021.4.17
    //主要用来做组件校准
 	//输出路径
	ChangeParameter.fileOutPath="D:\\批量实验结果\\"+"times.txt";
	
	for(int a=0;a<Parameter.map.length;a++)
		for(int b=0;b<Parameter.speed.length;b++)
			for(int e=0;e<Parameter.deadline.length;e++)
			  for(int c=0;c<Parameter.iterator.length;c++)
				 for(int d=0;d<Parameter.subIterator.length;d++)
					 for(int f=0;f<Parameter.selfOwnUAVFactor.length;f++)
						 for(int g=0;g<Parameter.priceMagnificationEnum.length;g++)
							 for(int h=0;h<Parameter.timeMagnification.length;h++)
				     {
						int selfOwnUAVFactor= Parameter.selfOwnUAVFactor[f];
						int priceMagnification=Parameter.priceMagnificationEnum[g];
						String map=Parameter.map[a];
						String speed=Parameter.speed[b];
                        String iterator=Parameter.iterator[c];
						String subIterator=Parameter.subIterator[d];
						String deadline=Parameter.deadline[e];	
						String timeMagni=Parameter.timeMagnification[h];
						ChangeParameter.changeParameter("NSGA2",speed,subIterator,iterator,map,deadline
								,selfOwnUAVFactor,priceMagnification,timeMagni);
					 } 
	
}
}
