package Resource;

public class Map implements Cloneable {
//��ͼ������Դ�ļ�
public static String  filepath="node48uniform";
public Node[] node;
public int csNum;//���վ����
public int wsNum;//���ߴ�����������
public int totalNum;//���վ+���ߴ�������������
public float[][]distance;
public int [] whichChargingStationIsCloser;


public float[][] getDistance() {
	return distance;
}
public void setDistance(float[][] distance) {
	this.distance = distance;
}

public Map clone() 
{  
   Map map = null;  
    try{  
        map = (Map)super.clone();  
       }catch(CloneNotSupportedException e) 
    {  
        e.printStackTrace();  
    }  
    return map;  
}



}  




