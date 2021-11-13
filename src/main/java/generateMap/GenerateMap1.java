package generateMap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class GenerateMap1 {
	//多个聚集区
     public static void main(String[] args) throws IOException {
    	 FileOutputStream fos=new FileOutputStream("scdt1.txt");
 		OutputStreamWriter osw=new OutputStreamWriter(fos, StandardCharsets.UTF_8);
 		
    	 int xLength=8000;
    	 int yLength=6000;
    	 //中心节点的数目
    	 int centreNum=10;
    	 //围绕中心节点的节点的数目
    	 int aroundNum=6;
    	 //左右长度
    	 int lateralRange=400;
    	 //上下长度
    	 int portaitRange=400;
    	 int count=1;
    	 osw.write(centreNum*aroundNum+"\n");
    	 for(int i=1;i<=centreNum;i++)
    	 {
    		float x=(float) (xLength*Math.random());
 	    	float y=(float) (yLength*Math.random());
 	    	osw.write(count+++"  "+x+"   "+y+"\n");
 	    	
 	    	
    	    for(int j=1;j<aroundNum;j++)
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
    	    	osw.write(count+++"  "+xx+"   "+yy+"\n");
    	    }
    	 }
    	 osw.close();
	}
      
}
