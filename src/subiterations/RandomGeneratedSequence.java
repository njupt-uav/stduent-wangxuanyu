package subiterations;

import java.util.Collections;

import Resource.Map;
import Resource.UAV;
import method.Charge;
public class RandomGeneratedSequence {
   public static void	randomGeneratedSequence(UAV u,Map map)
   {  
	   u.nodeSequence.clear();
	   u.nodeSequence.addAll(u.nodeSet);
	  u.nodeSequence.addAll(u.nodeSet);
	  
	   //������������
	   Collections.shuffle(u.nodeSequence);
	  
   }
}
