package method;

import Resource.Map;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 剪枝函数，没用上
 */
public class Pruning {
	public static boolean pruning(ArrayList<Integer> arr, Map map)//用来粗略检测无人机是否能够胜任该任务
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
