package Resource;

import java.util.ArrayList;
import java.util.HashSet;


//speed=20,flyEnergyConsume=900;

public class UAV implements Cloneable{
public int x,y;//���˻�����
public static float speed=22;//���˻������ٶ�//��λ��ÿ��//40//22
public static float totalEnergy=548*60;//��λ��ʱ��������548w�Ĺ������60����
public float energyLeft=totalEnergy;

public static float flyEnergyConsume=850;//���˻������ܺ�//��λw//1250//850
public HashSet<Integer> nodeSet=new HashSet<Integer>() ;//���������Ҫ���еĽڵ�
public ArrayList<Integer> nodeSequence=new ArrayList<Integer>();//������Žڵ�����
public float length=0;//�������нڵ�ķ��г���
public int index;//�����˻���������
public float timeToCompleteAllTasks=Float.MAX_VALUE;


@Override
public Object clone() throws CloneNotSupportedException {
    return super.clone();
}


public UAV()
{
	
}


public void init(HashSet<Integer> arr)
{
	this.nodeSet=arr;
}


}
