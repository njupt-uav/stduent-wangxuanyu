package generateMap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class GenerateMap3 {
//纵的很长，横的很短
	  public static void main(String[] args) throws IOException {
	    	 FileOutputStream fos=new FileOutputStream("D:\\matlab代码\\scdt3.txt");
	 		OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
	 		
	    	 int xLength=8000;
	    	 int yLength=6000;
	    	 //中心节点的数目
	    	 int centreNum=10;
	    	 //围绕中心节点的节点的数目
	    	 int aroundNum=10;
	    	 //左右长度
	    	 int lateralRange=50;
	    	 //上下长度
	    	 int portaitRange=1600;
	    	 int count=1;
	    	 for(int i=0;i<centreNum;i++)
	    	 {
	    		float x=(float) (xLength*Math.random());
	 	    	float y=(float) (yLength*Math.random());
	 	    	osw.write(count+++"  "+x+"   "+y+"\t\n");	 	    	
	    	    for(int j=0;j<aroundNum;j++)
	    	    {
	    	    	float xx=(float) (x-lateralRange+lateralRange*Math.random()*2);
	    	    	float yy=(float) (y-portaitRange+portaitRange*Math.random()*2);
	    	    	while(xx<0||xx>xLength)
	    	    	{
	    	    		xx=(float) (x-lateralRange+lateralRange*Math.random()*2);
	    	    	}
	    	    	while(yy<0||yy>yLength)
	    	    	{
	    	    		yy=(float) (y-portaitRange+portaitRange*Math.random()*2);
	    	    	}
	    	    	osw.write(count+++"  "+xx+"   "+yy+"\t\n");
	    	    }
	    	 }
	    	 osw.close();
		}
}
