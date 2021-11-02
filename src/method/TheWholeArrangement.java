package method;

import Resource.Map;
import Resource.UAV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * ȫ����
 */
public class TheWholeArrangement {
	public static boolean theWholeArrangement(UAV u, Map map) throws CloneNotSupportedException {
		/*
		 * ��ʼ�����飬��u.nodesequence��������
		 */
		u.nodeSequence.clear();
		u.nodeSequence.addAll(u.nodeSet);
		u.nodeSequence.addAll(u.nodeSet);
		Collections.sort(u.nodeSequence);

		//������������ȫ���еĽ����
		List<List<Integer>> result = new ArrayList();

		int[] temp = new int[u.nodeSequence.size()];
		boolean[] flag = new boolean[u.nodeSequence.size()];


		DFS(u.nodeSequence, flag, result, temp, 0);
		UAV resultuav = new UAV();
		float min = Float.MAX_VALUE;
		for (List<Integer> list : result) {
			UAV ux = new UAV();
			ux.nodeSequence = (ArrayList<Integer>) list;
			ux.index = map.whichChargingStationIsCloser[u.nodeSequence.get(0)];
			Calfitness.calfitness(map, ux);
			//�����ҳ����ʱ����С���Ǹ����
			if (ux.timeToCompleteAllTasks < min) {
				min = ux.timeToCompleteAllTasks;
				resultuav = ux;
			}
		}
		//����ܹ������ɹ����򷵻ؾ�����
		if (resultuav.timeToCompleteAllTasks != Float.MAX_VALUE) {
			u.nodeSequence = resultuav.nodeSequence;
			// System.out.println(u.nodeSequence);
			u.timeToCompleteAllTasks = resultuav.timeToCompleteAllTasks;
			u.length = resultuav.length;
			return true;
		}
		return false;
	}

	//����������������صĽ������result�У���������п��ܵ�����
	private static void DFS(ArrayList<Integer> nodeSequence, boolean[] flag, List<List<Integer>> result, int[] temp, int n) {
		if (n >= nodeSequence.size()) {
			List<Integer> list = new ArrayList();
			for (int i = 0; i < nodeSequence.size(); i++) {
				list.add(temp[i]);
			}

			result.add(list);
			return;
		}

		for (int i = 0; i < nodeSequence.size(); i++) {
			if (i > 0 && nodeSequence.get(i) == nodeSequence.get(i - 1) && flag[i - 1] == true) continue;
			if (flag[i] == false) {
				flag[i] = true;
				temp[n] = nodeSequence.get(i);
				DFS(nodeSequence, flag, result, temp, n + 1);
				flag[i] = false;
			}
		}
	}


}
