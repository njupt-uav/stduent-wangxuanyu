package method;

import Resource.Map;
import Resource.UAV;

public class Charge2 {
	public static void  charge2(UAV u,Map map)
	{     
		//���˻����ڵ�λ��
		u.index=map.whichChargingStationIsCloser[u.nodeSequence.get(0)];
		//���˻�ʣ�����
		u.energyLeft=UAV.totalEnergy;
		//���˻����ڵ�λ��
		int index=u.index;

		for(int i=0;i<u.nodeSequence.size();i++)
		{
			//������һ��λ����Ҫ���ѵ�ʱ��
			float time=(map.distance[index][u.nodeSequence.get(i)]/UAV.speed)/60;
			//������һ��λ����Ҫ���ѵĵ���
			float energyConsume=time*UAV.flyEnergyConsume;
			//���µ���
			u.energyLeft-=energyConsume;
			//����Ĵ����ǿ����˻������ĸ����վ����
			float length=map.distance[u.nodeSequence.get(i)][0];
			int chargeIndex=0;
			for(int j=1;j<map.csNum;j++)//���˻���������ڵ��������ĳ��ڵ����ĸ�
				{
					if(length>map.distance[u.nodeSequence.get(i)][j])
					{
						length=map.distance[u.nodeSequence.get(i)][j];	
						chargeIndex=j;
					}
				}
			
		
			
			
			if(u.energyLeft<(map.distance[u.nodeSequence.get(i)][chargeIndex]/UAV.speed)*UAV.flyEnergyConsume)//������������Էɵ����ڵ���
				{
					u.nodeSequence.add(i, chargeIndex);//Ҫ��ȥ�����ܵ�����ڵ�
					u.energyLeft=UAV.totalEnergy;
					i++;
				}
			else  if(i+1<u.nodeSequence.size()&&u.energyLeft<UAV.totalEnergy*0.3)
			   {
					float length1=map.distance[u.nodeSequence.get(i+1)][chargeIndex];
					
				
				if(length<length1)
				{
					u.nodeSequence.add(i, chargeIndex);//Ҫ��ȥ�����ܵ�����ڵ�
					u.energyLeft=UAV.totalEnergy;
					i++;
				}
			   }
			index=u.nodeSequence.get(i);
		}

	}
}
