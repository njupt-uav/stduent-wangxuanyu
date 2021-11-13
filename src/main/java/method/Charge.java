package method;

import Resource.Map;
import Resource.UAV;

public  class Charge {
	public static void  charge(UAV u,Map map)
	{     
		u.index=map.whichChargingStationIsCloser[u.nodeSequence.get(0)];
		u.energyLeft=u.totalEnergy;
		int index=u.index;//���˻����ڵ�λ��
//		for(Integer x:u.nodeSequence)
//		{
//			System.out.println(u.nodeSequence+" charge");
//		}
		for(int i=0;i<u.nodeSequence.size();i++)
		{
			float time=(map.distance[index][u.nodeSequence.get(i)]/u.speed)/60;
			float energyConsume=time*u.flyEnergyConsume;
			u.energyLeft-=energyConsume;
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
			
			if(u.energyLeft<(map.distance[u.nodeSequence.get(i)][chargeIndex]/u.speed)*u.flyEnergyConsume)//������������Էɵ����ڵ���
				{
					u.nodeSequence.add(i, chargeIndex);//Ҫ��ȥ�����ܵ�����ڵ�
					u.energyLeft=u.totalEnergy;
					i++;
				}
			index=u.nodeSequence.get(i);
		}

	}
}
