package Resource;

import java.text.Normalizer;

import method.NormalRandom;

public class Node {
public double x;
public double y;

public boolean isVisited=false;//�����˻�û�������ýڵ�Ϊfalse��������һ��Ϊtrue
public boolean iscal=false;//������ڵ�������Ѿ��������������Ϊtrue

//�������Ĳ�����Ϊ�����ߴ��������õģ����ڵ�ֻ��Ҫ�������
public float missionTime;//��ɱ��ڵ���������ʱ��
public float missionTimeLeft;//���ֵ���Ա䣬��Ҫ��ɱ�����ʣ���ʱ��
static float trasTime;//���䱾�����Դ��������ʱ��
static float transTime2;//���䱾�������������ʱ��
public float deadline;//�������ʱ��û���ؾ����������ʧ��

//������ֵ����ýڵ�����������Ҫ���˻������ʱ����������
static float calMinTime=10;
static float calMaxTime=100;
//alpha����ָ��deadline��ϵ��
public static float  alpha=(float) 5.0;

public Node(double x2,double y2)
{
	this.x=x2;
	this.y=y2;
	this.missionTime=(float) (Math.random()*(this.calMaxTime-this.calMinTime)+this.calMinTime);//������ɸ���������ʱ��
	this.deadline=this.missionTime*this.alpha;
	this.missionTimeLeft=this.missionTime;
	this.trasTime=(float) (0.1*this.missionTime);
	this.transTime2=(float) (0.01*this.missionTime);
}

public Node(double x2,double y2,double a,double b)
//aΪ3*60��bΪ1.02
{
	this.x=x2;
	this.y=y2;
	this.missionTime=(float) NormalRandom.normalRandom(a, b);
	this.deadline=this.missionTime*this.alpha;
	this.missionTimeLeft=this.missionTime;
	this.trasTime=(float) (0.1*this.missionTime);
	this.transTime2=(float) (0.01*this.missionTime);
}

public Node(double x2,double y2,float missionTime)
{
	this.x=x2;
	this.y=y2;
	this.missionTime=missionTime;
	this.deadline=this.missionTime*this.alpha;
	this.missionTimeLeft=this.missionTime;
	this.trasTime=(float) (0.1*this.missionTime);
	this.transTime2=(float) (0.01*this.missionTime);
}

//minTime�Ǿ�������ڵ���������˻��ɹ�ȥ��ɵ����ʱ��
public Node(double x2,double y2,float missionTime,float minTime)
{
	this.x=x2;
	this.y=y2;
	this.missionTime=missionTime;
	this.deadline=minTime*this.alpha;
	this.missionTimeLeft=this.missionTime;
	this.trasTime=(float) (0.1*this.missionTime);
	this.transTime2=(float) (0.01*this.missionTime);
}


}
