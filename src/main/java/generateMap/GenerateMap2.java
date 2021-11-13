package generateMap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class GenerateMap2 {
	//随机分布
	 public static void main(String[] args) throws IOException {
    	 FileOutputStream fos=new FileOutputStream("scdt2.txt");
 		OutputStreamWriter osw=new OutputStreamWriter(fos, StandardCharsets.UTF_8);
 		
    	 int xLength=8000;
    	 int yLength=6000;
    	 //中心节点的数目
    	 int centreNum=150;
    	
    	
    	 int count=1;
    	 osw.write(centreNum+"\n");
    	 for(int i=0;i<centreNum;i++)
    	 {
    		float x=(float) (xLength*Math.random());
 	    	float y=(float) (yLength*Math.random());
 	    	osw.write(count+++"  "+x+"   "+y+"\n");
    	 }
    	 osw.close();
	}
}
