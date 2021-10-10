package BatchTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import compare.PF;
import compare.PFRange;
import compare.PFSet;
import compare.SelectTheFirstRank;

public class ProcessingData {
	//已废弃
public static ArrayList<String> ReadResult(String fileName){
	File file = new File(fileName);
    BufferedReader reader = null;
    ArrayList<String>result=new ArrayList();
    try {
       
        reader = new BufferedReader(new FileReader(file));
        String tempString = null;
      
//一次读一行，读入null时文件结束
        while ((tempString = reader.readLine()) != null) {
//把当前行号显示出来
//            System.out.println(tempString);
        //	if(tempString.equals(""))continue;
            result.add(tempString);
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
            }
        }
    }
    return result;
}

public static PFSet GeneratePfSet(String s,ArrayList<String>result)
{
	
	PFSet pfSet=new PFSet();
	for(int i=0;i<result.size();i++)
	{
		String ss=result.get(i);
		if(result.get(i).equals(s))
		{
			i++;
			while(i<result.size())
			{
				ss=result.get(i);
				if(!result.get(i).equals(""))
				{
					String []strs=result.get(i).split(" ");
					PF pf=new PF(Double.valueOf(strs[0]),Double.valueOf(strs[2]),s);
					pfSet.set.add(pf);
					i++;
				}
				else
				{
					break;
				}
			}
		}
	}
	return pfSet;
}

public static void compare(PFSet pfset1,PFSet pfset2)
{
	//这两个selectTheFirstRank是为了处理一个参数多次实验导致的pfset中有支配解
	pfset1.set=SelectTheFirstRank.selectTheFirstRank(pfset1, new PFSet());
	pfset2.set=SelectTheFirstRank.selectTheFirstRank(pfset2, new PFSet());
	
	String con1=pfset1.set.get(0).getContributor();
	String con2=pfset2.set.get(0).getContributor();
	PFRange pfRange1=new PFRange();
	pfRange1.firstupdate(pfset1);
	pfset1.computeNormalization(pfRange1);
	PFRange pfRange2=new PFRange();
	pfRange2.firstupdate(pfset2);
	pfset2.computeNormalization(pfRange2);
	
	System.out.println(con1+" AverageQulity="+pfset1.AverageQuality()+" 越小越好");
	System.out.println(con2+" AverageQulity="+pfset2.AverageQuality());
	System.out.println("pfset1.CMetric(pfset2): "+pfset1.CMetric(pfset2)+"越大越好");
	System.out.println("pfset2.CMetric(pfset1): "+pfset2.CMetric(pfset1));
	PFSet pi=new PFSet();
	pi.set=SelectTheFirstRank.selectTheFirstRank(pfset1, pfset2);
	PFRange pfRange3=new PFRange();
	pfRange3.firstupdate(pi);
	pi.computeNormalization(pfRange3);
	
	System.out.println("pfset1: maximumspread"+pfset1.MaximumSpread(pi)+" 越大越好");
	System.out.println("pfset2: maximumspread"+pfset2.MaximumSpread(pi));
	System.out.println("pfset1: distanceMetrixavg "+pfset1.distanceMetricAvg(pi)+" 越小越好");
	System.out.println("pfset1: distancemetricmax "+pfset1.distanceMetricMax(pi)+" 越小越好");
	
//	System.out.println("pfset2: distanceMetrixavg "+pfset2.distanceMetricAvg(pi));
	System.out.println("pfset2: distancemetricmax "+pfset2.distanceMetricMax(pi));
	System.out.println("pf1占比: "+pfset1.intersection(pi).set.size());
	System.out.println("pf2占比: "+pfset2.intersection(pi).set.size());
	System.out.println("optset总数: "+pi.set.size());
	
}

public static void main(String[] args) {
	String fileName="充电方式对比";
	ArrayList<String>result=ReadResult(fileName);
//	for(String s:result)
//	{
//		System.out.println(s);
//	}
	PFSet pfSet1=GeneratePfSet("att48uniform NSGA2 40 Greedy variation2 5 charge",result);
//	System.out.println();
//	for(PF pf:pfSet.set)
//	{
//		System.out.println(pf.getCost()+" "+pf.getDD());
//	}
	PFSet pfSet2=GeneratePfSet("att48uniform NSGA2 40 Greedy variation2 5 charge2",result);
	compare(pfSet2,pfSet1);
}
}
