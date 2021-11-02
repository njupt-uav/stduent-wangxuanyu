package method;

import Resource.Map;

import java.util.ArrayList;
import java.util.Collections;

/*
 * ��֦������û����
 */
public class Pruning {
	public static boolean pruning(ArrayList<Integer> arr, Map map)//�������Լ�����˻��Ƿ��ܹ�ʤ�θ�����
	{
		float sum = 0;
		Collections.sort(arr, (o1, o2) ->
		{
			int i = map.node[o1].deadline > map.node[o2].deadline ? 1 : -1;
			return i;
		});

		for (Integer in : arr) {
			if (in >= map.csNum) {
				sum += map.node[in].missionTime;
			}
			if (sum > map.node[in].deadline)
				return false;
		}
		return true;
	}
}
