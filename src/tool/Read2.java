package tool;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;


import Resource.Map;
import Resource.Node;
import Resource.UAV;

//��������������ȡRead���Ѿ����ɵ����ݵģ�Read�����ɵ�������Ӧ�ð���
//�ܽڵ�����������վ����
//ÿ���ڵ�����Ļ�վ  ÿ���ڵ��x���� y���������ʱ��  ����
//52 
//0 6452.0 2191.87 23.149616
//1 7519.42 4290.75 84.63723
//
public class Read2 {
	
	public static void read2(Map map) throws IOException
	{
	   double[] x;
	   double[] y;
		String strbuff;
		BufferedReader data = new BufferedReader(new InputStreamReader(
				new FileInputStream(Map.filepath)));
		
		strbuff=data.readLine();//��һ��
		strbuff=strbuff.replace(" ", "");
		map.totalNum=Integer.valueOf(strbuff);//�ļ���һ���ǳ�������
		switch(map.totalNum)
		{
		case 52:
			map.csNum=4;
			break;
		case 66:
			map.csNum=6;
			break;
		case 165:
			map.csNum=15;
			break;
		case 585 :
			map.csNum=53;
		}
	

		map.whichChargingStationIsCloser=new int[map.totalNum];
		map.distance = new float[map.totalNum][map.totalNum];
		x = new double[map.totalNum];
		y = new double[map.totalNum];
		map.wsNum=map.totalNum-map.csNum;
		
		map.node=(Resource.Node[]) new Node[map.totalNum];	
		
	
		
		for(int j=0;j<map.csNum;j++)
		{	
			strbuff=data.readLine();
			String[] strcol = strbuff.split(" ");
			map.whichChargingStationIsCloser[j]=Integer.valueOf(strcol[0]);
			//
		//	float  minTime=(float) (map.distance[map.whichChargingStationIsCloser[j]][j]/UAV.speed*2+map.node[j].missionTime*1.75);
			Node node=new Node(Double.valueOf(strcol[1]),Double.valueOf(strcol[2]),Float.valueOf(strcol[3]));
			map.node[j]=node;
			x[j]=Double.valueOf(strcol[1]);
			y[j]=Double.valueOf(strcol[2]);
		}
			
		
	for (int i = map.csNum; i < map.totalNum; i++) 
	{
		strbuff=data.readLine();
		String[] strcol = strbuff.split(" ");
		map.whichChargingStationIsCloser[i]=Integer.valueOf(strcol[0]);
		//float  minTime=(float) (map.distance[map.whichChargingStationIsCloser[i]][i]/UAV.speed*2+map.node[i].missionTime*1.75);
		Node node=new Node(Double.valueOf(strcol[1]),Double.valueOf(strcol[2]),Float.valueOf(strcol[3]));
		map.node[i]=node;
		x[i]=Double.valueOf(strcol[1]);
		y[i]=Double.valueOf(strcol[2]);
	}

	for(int i=0;i<map.totalNum;i++)//��ʼ��ÿ������֮��ľ���
		for(int j=0;j<map.totalNum;j++)
		{
			if(i<=j)
			{
				map.distance[i][j]=(float) Math.sqrt(Math.pow(x[i]-x[j],2)+Math.pow(y[i]-y[j], 2));
			}
			else
			map.distance[i][j]=map.distance[j][i];
		}
		
	
		//�޸�deadline
		for(int i=map.csNum;i<map.totalNum;i++)
		{
			float  minTime=(float) (map.distance[map.whichChargingStationIsCloser[i]][i]/UAV.speed*2+map.node[i].missionTime*1.75);
			map.node[i].deadline=Node.alpha*minTime;		
		}
		
		data.close();
		
	}
	

}
