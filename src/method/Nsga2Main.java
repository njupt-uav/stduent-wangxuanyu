package method;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Resource.Map;
import Resource.UAV;
import compare.PF;
import compare.PFSet;
import kmeans.Kmean;
import nsga2.Exchange;
import nsga2.GenerateNextPopulation;
import nsga2.Individual;
import nsga2.InitIndividual;
import nsga2.NSGA2Select;
import nsga2.Population;
import nsga2.SelectTheFirstRank;
import nsga2.Variation;
import nsga2.Variation2;
import tool.Read;
import tool.Read2;

public class Nsga2Main {
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		
		/*
		 * ����ĳ�������������ʼ����ʱ��ʱ��
		 */
		Calendar Cld = Calendar.getInstance();
        int YY = Cld.get(Calendar.YEAR) ;
        int MM = Cld.get(Calendar.MONTH)+1;
        int DD = Cld.get(Calendar.DATE);
        int HH = Cld.get(Calendar.HOUR_OF_DAY);
        int mm = Cld.get(Calendar.MINUTE);
        int SS = Cld.get(Calendar.SECOND);
        int MI = Cld.get(Calendar.MILLISECOND);   
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date before=new Date();
        Date now=new Date();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date));
        
        
        
	/*
	 * ������
	 */
		Map map=new Map();
	
		Kmean kmean=new Kmean(5, 200);
	
        //��ʼ���㼯��KMeansCluster����
        kmean.init();
        //ʹ��KMeansCluster������о���
        kmean.runKmeans();
        
        
       
//		Read.read(map);            
////		
//		
//		Population father=new Population();
//		
//		father.init(map);
//	    System.out.println("init");
//		ArrayList<Individual> firstRank=SelectTheFirstRank.selectTheFirstRank(father);
//		System.out.println("first");
//		for(int i=0;i<5;i++)
//		{
//		Population child=GenerateNextPopulation.generateNextPopulation(father, map,i);
//	    father=NSGA2Select.nsga2(father, child);
//	    System.out.println(i);
//	   	 if((i)%50==0)
//	   	 {
//	   		 firstRank=SelectTheFirstRank.selectTheFirstRank(father);
//	   		for(Individual in:firstRank)
//			{
////				for(int y=0;y<in.uav.size();y++)
////				   System.out.println(in.uav.get(y).nodeSequence+" "+in.uav.get(y).length+"   "+in.uav.get(y).timeToCompleteAllTasks);
//				System.out.println("�ܺ�ʱ "+in.fitness1+" ʱ��"+in.fitness2+" ���˻�����"+in.uav.size());
//			}
//	   	 }
//	   	firstRank=SelectTheFirstRank.selectTheFirstRank(father);
////	   now = new Date();
////	    if((now.getTime()-before.getTime())/1000>60)break;
//		}
//		for(Individual in:firstRank)
//		{
////			for(int y=0;y<in.uav.size();y++)
////			   System.out.println(in.uav.get(y).nodeSequence+" "+in.uav.get(y).length+"   "+in.uav.get(y).timeToCompleteAllTasks);
//			System.out.println("�ܺ�ʱ "+in.fitness1+" ʱ��"+in.fitness2+" ���˻�����"+in.uav.size());
//		}
		Read.distribution="normal";
        for(int n=0;n<20;n++)
        {
        	System.out.println(n);
        	Read.FILEOUTPATH="D:\\NAGA2ʵ��2\\node1173"+Read.distribution+n+".txt";
        	Read.read(map);            
    		Population father=new Population();
    		
    		father.init(map);
    	    System.out.println("init");
    		ArrayList<Individual> firstRank=SelectTheFirstRank.selectTheFirstRank(father);
    		System.out.println("first");
    		for(int i=0;i<0;i++)
    		{
    		Population child=GenerateNextPopulation.generateNextPopulation(father, map,i);
    	    father=NSGA2Select.nsga2(father, child);
    	    System.out.println(i);
    	   	 if((i)%50==0)
    	   	 {
    	   		 firstRank=SelectTheFirstRank.selectTheFirstRank(father);
    	   		for(Individual in:firstRank)
    			{
//    				for(int y=0;y<in.uav.size();y++)
//    				   System.out.println(in.uav.get(y).nodeSequence+" "+in.uav.get(y).length+"   "+in.uav.get(y).timeToCompleteAllTasks);
    				System.out.println("�ܺ�ʱ "+in.fitness1+" ʱ��"+in.fitness2+" ���˻�����"+in.uav.size());
    			}
    	   	 }
    	   	firstRank=SelectTheFirstRank.selectTheFirstRank(father);
//    	   now = new Date();
//    	    if((now.getTime()-before.getTime())/1000>60)break;
    		}
    		for(Individual in:firstRank)
    		{
//    			for(int y=0;y<in.uav.size();y++)
//    			   System.out.println(in.uav.get(y).nodeSequence+" "+in.uav.get(y).length+"   "+in.uav.get(y).timeToCompleteAllTasks);
    			System.out.println("�ܺ�ʱ "+in.fitness1+" ʱ��"+in.fitness2+" ���˻�����"+in.uav.size());
    		}
        }
        
		
	
		
//		Individual indi=new Individual();
//		InitIndividual.initIndividual(indi, map);
//		Variation.variation(indi, map);
	    /*
		 * ���µĴ��������������������ʱ��
		 */
		
		    cal = Calendar.getInstance();
	        date = cal.getTime();
		System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date));
		System.out.println("charge");
	}
	
	
	
	
	
	
	
	
	
	
       public static  PFSet nsga2() throws IOException, CloneNotSupportedException  {
		System.out.println("����nsga2");
		/*
		 * ����ĳ�������������ʼ����ʱ��ʱ��
		 */
		Calendar Cld = Calendar.getInstance();
        int YY = Cld.get(Calendar.YEAR) ;
        int MM = Cld.get(Calendar.MONTH)+1;
        int DD = Cld.get(Calendar.DATE);
        int HH = Cld.get(Calendar.HOUR_OF_DAY);
        int mm = Cld.get(Calendar.MINUTE);
        int SS = Cld.get(Calendar.SECOND);
        int MI = Cld.get(Calendar.MILLISECOND);   
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date before=new Date();
        Date now=new Date();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date));
        
        
        
	/*
	 * ������
	 */
		Map map=new Map();
	
		Kmean kmean=new Kmean(5, 200);
	
        //��ʼ���㼯��KMeansCluster����
        kmean.init();
       
        //ʹ��KMeansCluster������о���
        kmean.runKmeans();
    
		Read.read( map);            
		
		
		Population father=new Population();
		
		father.init(map);
	    System.out.println("init");
		ArrayList<Individual> firstRank=SelectTheFirstRank.selectTheFirstRank(father);
		System.out.println("first");
		for(int i=0;i<1000;i++)
		{
		Population child=GenerateNextPopulation.generateNextPopulation(father, map,i);
	    father=NSGA2Select.nsga2(father, child);
	  //  System.out.println(i);
//	   	 if((i)%50==0)
//	   	 {
//	   		 firstRank=SelectTheFirstRank.selectTheFirstRank(father);
//	   		for(Individual in:firstRank)
//			{
////				for(int y=0;y<in.uav.size();y++)
////				   System.out.println(in.uav.get(y).nodeSequence+" "+in.uav.get(y).length+"   "+in.uav.get(y).timeToCompleteAllTasks);
//				System.out.println("�ܺ�ʱ "+in.fitness1+" ʱ��"+in.fitness2+" ���˻�����"+in.uav.size());
//			}
//	   	 }
	   	firstRank=SelectTheFirstRank.selectTheFirstRank(father);
//	   now = new Date();
//	    if((now.getTime()-before.getTime())/1000>60)break;
		}
		for(Individual in:firstRank)
		{
//			for(int y=0;y<in.uav.size();y++)
//			   System.out.println(in.uav.get(y).nodeSequence+" "+in.uav.get(y).length+"   "+in.uav.get(y).timeToCompleteAllTasks);
			System.out.println("�ܺ�ʱ "+in.fitness1+" ʱ��"+in.fitness2+" ���˻�����"+in.uav.size());
		}
		
		
	
		
//		Individual indi=new Individual();
//		InitIndividual.initIndividual(indi, map);
//		Variation.variation(indi, map);
	    /*
		 * ���µĴ��������������������ʱ��
		 */
		
		    cal = Calendar.getInstance();
	        date = cal.getTime();
		System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date));
//	   now = new Date();
//	    if((now.getTime()-before.getTime())/1000>60)break;
		
		PFSet pfset=new PFSet();
		for(Individual in:firstRank)
		{
//			for(int y=0;y<in.uav.size();y++)
//			   System.out.println(in.uav.get(y).nodeSequence+" "+in.uav.get(y).length+"   "+in.uav.get(y).timeToCompleteAllTasks);
			//System.out.println("�ܺ�ʱ "+in.fitness1+" ʱ��"+in.fitness2+" ���˻�����"+in.uav.size());
			PF pf=new PF(in.fitness1,in.fitness2,"nsga2");
			pfset.addPF(pf);
		}
		
		
	
		
//		Individual indi=new Individual();
//		InitIndividual.initIndividual(indi, map);
//		Variation.variation(indi, map);
	    /*
		 * ���µĴ��������������������ʱ��
		 */
		
		    cal = Calendar.getInstance();
	        date = cal.getTime();
	
		return pfset;
	}

}