package compare;


//��������������з���Ĳ���
public class Parameter {

//��������nsga3,�˻�̰�ģ������� "NSGA2","SA","GREEDY","VN"
public static String[] mainMethods= {"NSGA2","SA","GREEDY","VN"};

//���˻����ٶ���40��22����"40","22","50","30"
public static String[] speed= {"40","22","50","30"};

//��������"variation2","ReduceTime","variation4"
public static String[]  iterator= {"ReduceTime"};


//���������������ʱ���5����10����15��  "5","10","15"
public static String[]  deadline= {"5"};  
//�������˻�����������  ��������n/�������˻�����=factor  10,15,20
public static int[] selfOwnUAVFactor= { 20};
//������˻����������˻��۸�ı���2 ,4, 6
public static int[] priceMagnificationEnum= {6};

//��ͼ+�ڵ�ʱ��ֲ���6�� "att532uniform","node150uniform","node60uniform","att48uniform"

public static String[]  map= {"node150uniform","node60uniform","att48uniform"};



//����ʱ���Ǽ���ʱ��ĳ���
public static String[] timeMagnification= {" 0.25", "0.5","0.75","rand"};


//�ӵ�����4�֣��ֱ���̰�ģ�������˻��Ŵ�  "sa","no"
public static String[]  subIterator= {"sa"};


}
