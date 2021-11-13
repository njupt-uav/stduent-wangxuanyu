package compare;


//本类用来存放所有分类的参数
public class Parameter {

//主方法有nsga3,退火，贪心，变邻域 "NSGA2","SA","GREEDY","VN"
public static String[] mainMethods= {"NSGA2","SA","GREEDY","VN"};

//无人机的速度有40和22两种"40","22","50","30"
public static String[] speed= {"40","22","50","30"};

//迭代方法"variation2","ReduceTime","variation4"
public static String[]  iterator= {"ReduceTime"};


//任务期限有有完成时间的5倍和10倍和15倍  "5","10","15"
public static String[]  deadline= {"5"};  
//自有无人机的数量因子  任务数量n/自有无人机数量=factor  10,15,20
public static int[] selfOwnUAVFactor= { 20};
//租界无人机是自有无人机价格的倍数2 ,4, 6
public static int[] priceMagnificationEnum= {6};

//地图+节点时间分布有6种 "att532uniform","node150uniform","node60uniform","att48uniform"

public static String[]  map= {"node150uniform","node60uniform","att48uniform"};



//传输时间是计算时间的长度
public static String[] timeMagnification= {" 0.25", "0.5","0.75","rand"};


//子迭代有4种，分别是贪心，随机，退火，遗传  "sa","no"
public static String[]  subIterator= {"sa"};


}
