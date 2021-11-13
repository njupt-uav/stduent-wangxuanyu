package compare;

import java.util.*;


public class PFSet {

	public ArrayList<PF> set;
	int num;//��ֵͬ������

	public double getbestCost() {
		int n = set.size();
		double mincost = set.get(0).getCost();
		for (int i = 1; i < n; i++) {
			double cost = set.get(i).getCost();
			if (mincost > cost) mincost = cost;
		}
		return mincost;
	}

	public void computeNormalization(PFRange pfrange) {
		int n = this.set.size();
		for (int i = 0; i < n; i++) {
			this.set.get(i).CostNomalization(pfrange.getMaxcost(), pfrange.getMincost());
			this.set.get(i).DDNomalization(pfrange.getMaxdd(), pfrange.getMindd());
		}
	}

	public double AverageQuality()//ֵԽСԽ��
	{

		double AQ = 0;
		int size = set.size();
		for (int i = 0; i <= 50; i++) {
			double lambda1 = (double) i / 50;
			double lambda2 = (double) (50 - i) / 50;
			double minvalue = 20000000;
			for (int j = 0; j < size; j++) {
				PF x = set.get(j);
				double value = Math.max(lambda1 * x.getCostNormalization(), lambda2 * x.getDDNormalization()) + 0.01 * (lambda1 * x.getCostNormalization() + lambda2 * x.getDDNormalization());
				if (minvalue > value) minvalue = value;
			}
			AQ += minvalue;
		}
		AQ = AQ / 51;
//		if(AQ>1)
//		{
//			System.out.println("Strange value:");
//			for(int j=0;j<size;j++)
//			{
//				PF x = set.get(j);
//				x.printNormal();
//			}
//		}
		return AQ;
	}

//	public double MaximumSpread(PFSet optSet)//ֵԽ��Խ��
//	{
////		optSet.print();
////		System.out.println("------------------------");
////		this.print();
//		double min_opt_cost = optSet.getSet().get(0).getCostNormalization();
//		double minEcost = set.get(0).getCostNormalization();
//		double max_opt_cost = optSet.getSet().get(0).getCostNormalization();	
//		double maxEcost = set.get(0).getCostNormalization();
//		double minRdd = optSet.getSet().get(0).getDDNormalization();
//		double minEdd = set.get(0).getDDNormalization();
//		double maxRdd = optSet.getSet().get(0).getDDNormalization();
//		double maxEdd = set.get(0).getDDNormalization();
//		int sizeE = set.size();
//		for(int i=1;i<sizeE;i++)
//		{
//			PF pf = set.get(i);
//			if(pf.getCostNormalization()>maxEcost) maxEcost = pf.getCostNormalization();
//			if(minEcost>pf.getCostNormalization()) minEcost = pf.getCostNormalization();
//			if(pf.getDDNormalization()>maxEdd) maxEdd = pf.getDDNormalization();
//			if(minEdd>pf.getDDNormalization()) minEdd = pf.getDDNormalization();
//		}
//		int sizeopt = optSet.getSet().size();
//		for(int i=0;i<sizeopt;i++)
//		{
//			PF pf = optSet.getSet().get(i);
//			if(pf.getCostNormalization()>max_opt_cost) max_opt_cost = pf.getCostNormalization();
//			if(min_opt_cost>pf.getCostNormalization()) min_opt_cost = pf.getCostNormalization();
//			if(pf.getDDNormalization()>maxRdd) maxRdd = pf.getDDNormalization();
//			if(minRdd>pf.getDDNormalization()) minRdd = pf.getDDNormalization();
//		}
////		System.out.println("Math.pow(("+maxEcost+"-"+minEcost+")/("+max_opt_cost+"-"+min_opt_cost+"), 2)+Math.pow(("+maxEdd+"-"+minEdd+")/("+maxRdd+"-"+minRdd+"), 2)");
//		if(max_opt_cost-min_opt_cost==0||maxRdd-minRdd==0) return 0;
//		return Math.sqrt((Math.pow((maxEcost-minEcost)/(max_opt_cost-min_opt_cost), 2)+Math.pow((maxEdd-minEdd)/(maxRdd-minRdd), 2))/2);
//	}

	//��pfset�к�����pfset�������ɸѡ���optset�ж����ظ�
	public PFSet intersection(PFSet optSet) {
		PFSet pfset = new PFSet();
		int size = optSet.getSet().size();
		String contri = this.set.get(0).getContributor();
		for (int i = 0; i < size; i++) {
			PF pf = optSet.getSet().get(i);
			if (pf.getContributor().equalsIgnoreCase(contri)) {
				pfset.addPF(pf);
			}
//			else System.out.println(contri+"vs"+pf.getContributor());
		}
		return pfset;
	}

	public double MaximumSpread(PFSet optSet)//ֵԽ��Խ��[0,1]
	{
		//�ѵ�ǰSET����optSet�����Ĳ���ȡ��������
//		optSet.print();
//		System.out.println("------------------------");
//		this.print();
//		System.out.println("-----�󽻼�---------");
//		optSet.printNormal();
//		System.out.println("-----XXX---------");
//		this.printNormal();
		PFSet intersaction = this.intersection(optSet);
//		System.out.println("000000000000000000");
//		intersaction.printNormal();
		if (intersaction.getNum() == 0) return 0;
		double min_opt_cost = optSet.getSet().get(0).getCostNormalization();
		double minEcost = intersaction.getSet().get(0).getCostNormalization();
		double max_opt_cost = optSet.getSet().get(0).getCostNormalization();
		double maxEcost = intersaction.getSet().get(0).getCostNormalization();
		double minRdd = optSet.getSet().get(0).getDDNormalization();
		double minEdd = intersaction.getSet().get(0).getDDNormalization();
		double maxRdd = optSet.getSet().get(0).getDDNormalization();
		double maxEdd = intersaction.getSet().get(0).getDDNormalization();
		int sizeE = intersaction.getSet().size();
		for (int i = 1; i < sizeE; i++) {
			PF pf = intersaction.getSet().get(i);
			if (pf.getCostNormalization() > maxEcost) maxEcost = pf.getCostNormalization();
			if (minEcost > pf.getCostNormalization()) minEcost = pf.getCostNormalization();
			if (pf.getDDNormalization() > maxEdd) maxEdd = pf.getDDNormalization();
			if (minEdd > pf.getDDNormalization()) minEdd = pf.getDDNormalization();
		}
		int sizeopt = optSet.getSet().size();
		for (int i = 0; i < sizeopt; i++) {
			PF pf = optSet.getSet().get(i);
			if (pf.getCostNormalization() > max_opt_cost) max_opt_cost = pf.getCostNormalization();
			if (min_opt_cost > pf.getCostNormalization()) min_opt_cost = pf.getCostNormalization();
			if (pf.getDDNormalization() > maxRdd) maxRdd = pf.getDDNormalization();
			if (minRdd > pf.getDDNormalization()) minRdd = pf.getDDNormalization();
		}
//		System.out.println("Math.pow(("+maxEcost+"-"+minEcost+")/("+max_opt_cost+"-"+min_opt_cost+"), 2)+Math.pow(("+maxEdd+"-"+minEdd+")/("+maxRdd+"-"+minRdd+"), 2)");
		if (max_opt_cost - min_opt_cost == 0 || maxRdd - minRdd == 0)//������ż�costֵֻ��һ������ô����Ҳ��ֻ��һ��
		{
			return 1;
		}
		double MS = Math.sqrt((Math.pow((maxEcost - minEcost) / (max_opt_cost - min_opt_cost), 2) + Math.pow((maxEdd - minEdd) / (maxRdd - minRdd), 2)) / 2);
		if (MS > 1) {
			System.out.println("Strange value");
		}
		return MS;
	}

	public double distanceMetricAvg(PFSet optSet)//ֵԽСԽ��
	{
//		R.print();
//		System.out.println("------------------------");
//		this.print();
		double costRange = 0;
		double mincost = set.get(0).getCostNormalization();
		double maxcost = set.get(0).getCostNormalization();
		double ddRange = 0;
		double mindd = set.get(0).getDDNormalization();
		double maxdd = set.get(0).getDDNormalization();

		int sizeE = set.size();
		for (int i = 1; i < sizeE; i++) {
			PF pf = set.get(i);
			if (pf.getCostNormalization() > maxcost) maxcost = pf.getCostNormalization();
			if (mincost > pf.getCostNormalization()) mincost = pf.getCostNormalization();
			if (pf.getDDNormalization() > maxdd) maxdd = pf.getDDNormalization();
			if (mindd > pf.getDDNormalization()) mindd = pf.getDDNormalization();
		}
		int sizeR = optSet.getSet().size();
		for (int i = 0; i < sizeR; i++) {
			PF pf = optSet.getSet().get(i);
			if (pf.getCostNormalization() > maxcost) maxcost = pf.getCostNormalization();
			if (mincost > pf.getCostNormalization()) mincost = pf.getCostNormalization();
			if (pf.getDDNormalization() > maxdd) maxdd = pf.getDDNormalization();
			if (mindd > pf.getDDNormalization()) mindd = pf.getDDNormalization();
		}

		costRange = maxcost - mincost;
		ddRange = maxdd - mindd;
//		if(costRange==0||ddRange==0) return -1;
//		System.out.println("costrange="+costRange+" ddRange="+ddRange);

		double disSummary = 0;
		HashSet<String> explored = new HashSet<String>();

		for (int i = 0; i < sizeR; i++) {
			PF xR = optSet.getSet().get(i);
			if (explored.contains(xR.toValueString())) continue;
			else explored.add(xR.toValueString());
//			System.out.println("xR="+xR.toString());
			double mindistance = 10000;
			for (int j = 0; j < sizeE; j++) {
				PF x = set.get(j);
//				System.out.println("x="+x.toString());
				double distanceOncost = -1;
				if (costRange == 0) distanceOncost = 0;
				else {
					distanceOncost = (x.getCostNormalization() - xR.getCostNormalization()) / costRange;
				}
				double distanceOndd = -1;
				if (ddRange == 0)
					distanceOndd = 0;
				else distanceOndd = (x.getDDNormalization() - xR.getDDNormalization()) / ddRange;
				double distance = Math.max(distanceOncost, distanceOndd);
				if (mindistance > distance) mindistance = distance;
//				System.out.println("distanceOncost="+distanceOncost+" distanceOndd="+distanceOndd+"  distance="+distance+" mindistance="+mindistance);
			}
			disSummary += mindistance;
		}
		return disSummary / optSet.getNum();
	}

	public double distanceMetricMax(PFSet R)//ֵԽСԽ��
	{
		HashSet<String> explored = new HashSet<String>();
		double costRange = 0;
		double mincost = set.get(0).getCostNormalization();
		double maxcost = set.get(0).getCostNormalization();
		double ddRange = 0;
		double mindd = set.get(0).getDDNormalization();
		double maxdd = set.get(0).getDDNormalization();

		int sizeE = set.size();
		for (int i = 1; i < sizeE; i++) {
			PF pf = set.get(i);

			if (pf.getCostNormalization() > maxcost) maxcost = pf.getCostNormalization();
			if (mincost > pf.getCostNormalization()) mincost = pf.getCostNormalization();
			if (pf.getDDNormalization() > maxdd) maxdd = pf.getDDNormalization();
			if (mindd > pf.getDDNormalization()) mindd = pf.getDDNormalization();
		}
		int sizeR = R.getSet().size();
		for (int i = 0; i < sizeR; i++) {
			PF pf = R.getSet().get(i);
			if (pf.getCostNormalization() > maxcost) maxcost = pf.getCostNormalization();
			if (mincost > pf.getCostNormalization()) mincost = pf.getCostNormalization();
			if (pf.getDDNormalization() > maxdd) maxdd = pf.getDDNormalization();
			if (mindd > pf.getDDNormalization()) mindd = pf.getDDNormalization();
		}

		costRange = maxcost - mincost;
		ddRange = maxdd - mindd;
//		if(costRange==0||ddRange==0) return -1;
		double maxdistance = 0;
		for (int i = 0; i < sizeR; i++) {
			PF xR = R.getSet().get(i);
			if (explored.contains(xR.toValueString())) continue;
			else explored.add(xR.toValueString());

			double mindistance = 10000;
			for (int j = 0; j < sizeE; j++) {
				PF x = set.get(j);
				double distanceOncost = -1;
				if (costRange == 0) distanceOncost = 0;
				else {
					distanceOncost = (x.getCostNormalization() - xR.getCostNormalization()) / costRange;
				}
				double distanceOndd = -1;
				if (ddRange == 0)
					distanceOndd = 0;
				else distanceOndd = (x.getDDNormalization() - xR.getDDNormalization()) / ddRange;
				double distance = distanceOncost > distanceOndd ? distanceOncost : distanceOndd;
				if (mindistance > distance) mindistance = distance;
			}
			if (mindistance > maxdistance) maxdistance = mindistance;
		}
		return maxdistance;
	}

	public double CMetric(PFSet E)//�Ƚ������㷨֮��ĺû��ģ������ʺ����Ž�Ƚ�,ֵԽ��Խ��
	{
		double metric = 0;
		int sizeE = E.getSet().size();
		int size = this.set.size();
		for (int i = 0; i < sizeE; i++) {
			PF pf = E.getSet().get(i);
			for (int j = 0; j < size; j++) {
				PF x = set.get(j);
				//x��this�Ľ⣬pf��E�Ľ�
				if (x.compare(pf) == -1) {
					metric++;
					break;
				}
			}
		}
		return metric / sizeE;
	}

	public void print() {
		for (int i = 0; i < set.size(); i++)
			set.get(i).print();
	}

	public void printNormal() {
		for (int i = 0; i < set.size(); i++)
			set.get(i).printNormal();
	}

	public PFSet() {
		set = new ArrayList<PF>();
		num = 0;
	}

	public void addpfset(PFSet pf) {
		this.set.addAll(pf.set);
	}

	public void mergeWithSet(PFSet set) {
		Iterator<PF> it = set.getSet().iterator();
		while (it.hasNext()) {
			PF pf = it.next();
			this.set.add(pf);
		}
	}

	public ArrayList<PF> getSet() {
		return set;
	}

	public int getNum() {
		return num;
	}


	public HashMap<String, Double> Statistic(boolean print) {
//		if(print) 
//		{
//			System.out.println("===========================================");
//			for(int i=0;i<set.size();i++)
//				set.get(i).print();
//		}
		this.print();
		System.out.println("-------------------");
		if (num == 0) {
			System.out.println("Empty Pareto Frontier Set");
			return null;
		}
		if (this.validation() == false) {
			System.out.println("WRONG Pareto Frontier Set");

			return null;
		}
		if (num == 0) return null;
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		Iterator<PF> it = set.iterator();
		while (it.hasNext()) {
			PF pf = it.next();
			String contributor = pf.getContributor();
			if (result.containsKey(contributor))
				result.put(contributor, result.get(contributor) + 1);
			else result.put(contributor, 1);
		}
		HashMap<String, Double> finalresult = new HashMap<String, Double>();
		Iterator<String> cit = result.keySet().iterator();
		while (cit.hasNext()) {
			String method = cit.next();
			int connum = result.get(method);
			double ratio = (double) connum / (double) num;
			if (print) {
				System.out.println(method + "\t" + ratio);
			}
			finalresult.put(method, ratio);
		}
		return finalresult;
	}

	public boolean validation() {

		int size = set.size();
		for (int i = 0; i < size; i++) {
			PF pf1 = set.get(i);
			for (int j = i + 1; j < size; j++) {
				PF pf2 = set.get(j);
				int compare = pf1.compare(pf2);
				if (compare == 0 && !pf1.getContributor().equalsIgnoreCase(pf2.getContributor())) continue;
				if (compare != 2) {
					System.out.println("Validation Error: " + pf1.toString() + " <> " + pf2.toString());
					this.print();
					return false;
				}
			}
		}
		return true;
	}

	public boolean addPF(PF pf) {
		//cost=2188.0 dd=0.0
//		if(pf.getCost()==2188)
//		{
//			System.out.println("Error come hereeeeeeeeeeeeeeeeee to add "+pf.toString());
//			this.print();
//		}
		HashSet<String> forcount = new HashSet<String>();
		if (num == 0) {
			set.add(pf);
			num++;
//			if(pf.getCost()==2188)
//			{
//				System.out.println("num==0"+pf.toString()+" num="+num);
//				this.print();
//			}
			return true;
		}

		int size = set.size();
		int comparecase = -5;//һ�������ܵ�ֵ
		boolean iscomparable = false;
		int stopindex = -1;
		for (int i = 0; i < size; i++)//pf̫������
		{

			PF obj = set.get(i);
			int compare = obj.compare(pf);
			forcount.add(obj.getvaluestring());
			if (compare == -1) {
//				if(pf.getCost()==2188)
//				{
//					System.out.println("������pfС�ģ�˵��pf�󣬿϶��Ӳ����� to add "+pf.toString()+" num="+num);
//					this.print();
//				}
				return false;//������pfС�ģ�˵��pf�󣬿϶��Ӳ�����
			}
			if (compare == 0) {
				if (obj.contributor.equalsIgnoreCase(pf.contributor)) {
//					if(pf.getCost()==2188)
//					{
//						System.out.println("�����������ͬһ���ˣ���Ҫ�ӽ���"+pf.toString()+" num="+num);
//						this.print();
//					}
					return false;//�����������ͬһ���ˣ���Ҫ�ӽ���
				} else {

					comparecase = 0;
					iscomparable = true;//�����ɱȽϵ����
				}
			}
			if (compare == 1)//pf�Ǹ�С�����
			{

				comparecase = 1;
				iscomparable = true;//�����ɱȽϵ����
				stopindex = i;
				break;
			}
		}

		if (!iscomparable)//˵�������ɱȽ�
		{
			set.add(pf);
			num++;
//			if(pf.getCost()==2188)
//			{
//				System.out.println("˵�������ɱȽ�"+pf.toString()+" num="+num);
//				this.print();
//			}
			return true;
		}

		if (comparecase == 0)//��ͬ�����ǲ�ͬ������
		{
			set.add(pf);
//			if(pf.getCost()==2188)
//			{
//				System.out.println("��ͬ�����ǲ�ͬ������"+pf.toString()+" num="+num);
//				this.print();
//			}
			return true;
		}
		if (comparecase == 1) {
			PF toremove = set.get(stopindex);
			forcount.remove(toremove.getvaluestring());
//			System.out.println("remove "+forcount.toString());
			forcount.add(pf.getvaluestring());
//			System.out.println("add "+forcount.toString());
			set.set(stopindex, pf);
			for (int i = stopindex + 1; i < size; i++) {
				if (set.get(i).compare(pf) == 1) {
					forcount.remove(set.get(i).getvaluestring());
//					System.out.println("remove "+forcount.toString());

					set.remove(i);
					i--;
					size--;
				} else {
					forcount.add(set.get(i).getvaluestring());
//					System.out.println("add "+forcount.toString());

				}
			}
			num = forcount.size();
//			if(pf.getCost()==2188)
//			{
//				System.out.println("comparecase==C.PF_MORE"+pf.toString()+" num="+num);
//				this.print();
//			}
			return true;
		}

//		if(pf.getCost()==2188)
//		{
//			System.out.println("final return "+pf.toString()+" num="+num);
//			this.print();
//		}
		return false;
	}


	public static void main(String args[]) {
		String[] methods = {"M1", "M2", "M3", "M4"};
		Random r = new Random();
		PFSet pfset = new PFSet();
//		for(int i=0;i<200;i++)
//		{
//			PF pf = new PF(r.nextInt(10),r.nextInt(10),methods[r.nextInt(4)]);
//			pf.print();
//			System.out.println(pfset.addPF(pf)+" num="+pfset.getNum()+"------------------");
//		}
		PF pf1 = new PF(0.0, 0.0, methods[r.nextInt(4)]);
		pf1.print();
		PF pf2 = new PF(0.0, 0.2, methods[r.nextInt(4)]);
		System.out.println(pfset.addPF(pf1) + " num=" + pfset.getNum() + "------------------");
		pfset.print();
		System.out.println(pfset.addPF(pf2) + " num=" + pfset.getNum() + "------------------");
		pfset.print();

		System.out.println("totalnum=" + pfset.getNum());
		pfset.Statistic(true);
	}

	public double distanceMetricAvg2(PFSet optSet)//ֵԽСԽ��
	{
//		R.print();
//		System.out.println("------------------------");
//		this.print();
		double costRange = 0;
		double mincost = set.get(0).getCost();
		double maxcost = set.get(0).getCost();
		double ddRange = 0;
		double mindd = set.get(0).getDD();
		double maxdd = set.get(0).getDD();

		int sizeE = set.size();
		for (int i = 1; i < sizeE; i++) {
			PF pf = set.get(i);
			if (pf.getCost() > maxcost) maxcost = pf.getCost();
			if (mincost > pf.getCost()) mincost = pf.getCost();
			if (pf.getDD() > maxdd) maxdd = pf.getDD();
			if (mindd > pf.getDD()) mindd = pf.getDD();
		}
		int sizeR = optSet.getSet().size();
		for (int i = 0; i < sizeR; i++) {
			PF pf = optSet.getSet().get(i);
			if (pf.getCost() > maxcost) maxcost = pf.getCost();
			if (mincost > pf.getCost()) mincost = pf.getCost();
			if (pf.getDD() > maxdd) maxdd = pf.getDD();
			if (mindd > pf.getDD()) mindd = pf.getDD();
		}

		costRange = maxcost - mincost;
		ddRange = maxdd - mindd;
//		if(costRange==0||ddRange==0) return -1;
//		System.out.println("costrange="+costRange+" ddRange="+ddRange);

		double disSummary = 0;
		HashSet<String> explored = new HashSet<String>();

		for (int i = 0; i < sizeR; i++) {
			PF xR = optSet.getSet().get(i);
			if (explored.contains(xR.toValueString())) continue;
			else explored.add(xR.toValueString());
//			System.out.println("xR="+xR.toString());
			double mindistance = 10000;
			for (int j = 0; j < sizeE; j++) {
				PF x = set.get(j);
//				System.out.println("x="+x.toString());
				double distanceOncost = -1;
				if (costRange == 0) distanceOncost = 0;
				else {
					distanceOncost = (x.getCost() - xR.getCost()) / costRange;
				}
				double distanceOndd = -1;
				if (ddRange == 0)
					distanceOndd = 0;
				else distanceOndd = (x.getDD() - xR.getDD()) / ddRange;
				double distance = Math.max(distanceOncost, distanceOndd);
				if (mindistance > distance) mindistance = distance;
//				System.out.println("distanceOncost="+distanceOncost+" distanceOndd="+distanceOndd+"  distance="+distance+" mindistance="+mindistance);
			}
			disSummary += mindistance;
		}
		return disSummary / optSet.set.size();
	}

	public double distanceMetricMax2(PFSet R)//ֵԽСԽ��
	{
		HashSet<String> explored = new HashSet<String>();
		double costRange = 0;
		double mincost = set.get(0).getCost();
		double maxcost = set.get(0).getCost();
		double ddRange = 0;
		double mindd = set.get(0).getDD();
		double maxdd = set.get(0).getDD();

		int sizeE = set.size();
		for (int i = 1; i < sizeE; i++) {
			PF pf = set.get(i);

			if (pf.getCost() > maxcost) maxcost = pf.getCost();
			if (mincost > pf.getCost()) mincost = pf.getCost();
			if (pf.getDD() > maxdd) maxdd = pf.getDD();
			if (mindd > pf.getDD()) mindd = pf.getDD();
		}
		int sizeR = R.getSet().size();
		for (int i = 0; i < sizeR; i++) {
			PF pf = R.getSet().get(i);
			if (pf.getCost() > maxcost) maxcost = pf.getCost();
			if (mincost > pf.getCost()) mincost = pf.getCost();
			if (pf.getDD() > maxdd) maxdd = pf.getDD();
			if (mindd > pf.getDD()) mindd = pf.getDD();
		}

		costRange = maxcost - mincost;
		ddRange = maxdd - mindd;
//		if(costRange==0||ddRange==0) return -1;
		double maxdistance = 0;
		for (int i = 0; i < sizeR; i++) {
			PF xR = R.getSet().get(i);
			if (explored.contains(xR.toValueString())) continue;
			else explored.add(xR.toValueString());

			double mindistance = 10000;
			for (int j = 0; j < sizeE; j++) {
				PF x = set.get(j);
				double distanceOncost = -1;
				if (costRange == 0) distanceOncost = 0;
				else {
					distanceOncost = (x.getCost() - xR.getCost()) / costRange;
				}
				double distanceOndd = -1;
				if (ddRange == 0)
					distanceOndd = 0;
				else distanceOndd = (x.getDD() - xR.getDD()) / ddRange;
				double distance = distanceOncost > distanceOndd ? distanceOncost : distanceOndd;
				if (mindistance > distance) mindistance = distance;
			}
			if (mindistance > maxdistance) maxdistance = mindistance;
		}
		return maxdistance;
	}

}

