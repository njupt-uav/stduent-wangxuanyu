package method;

/*
 * ����������̬�ֲ��������������û���ϼǲ�����
 * 
 */
public class NormalRandom {
	public static double normalRandom1(double a, double b) {//ע�������a�Ǿ�ֵ��b�Ƿ�����ڱ�׼���ƽ��
		 double temp = 12;
		  double x = 0;
		  for (int i = 0; i < temp; i++)
		    x = x + (Math.random());
		    x = (x - temp / 2) / (Math.sqrt(temp / 12));
		   x = a + x * Math.sqrt(b); return x;
		 }

		 public static double normalRandom2(double a, double b) {
		    double pi = 3.1415926535;
		    double r1 = Math.random();
		   Math.random();
		   Math.random();
		   Math.random();
		   Math.random();
		   Math.random();
		   Math.random();
		   Math.random();
		    double r2 = Math.random();
		    double u = Math.sqrt((-2) * Math.log(r1)) * Math.cos(2 * pi * r2);
		    double z = a + u * Math.sqrt(b); return (z);
		  }

		 public static double  normalRandom3(double a, double b) {
		    double f = 0;
		    double c0 = 2.515517, c1 = 0.802853, c2 = 0.010328;
		    double d1 = 1.432788, d2 = 0.189269, d3 = 0.001308; double w;
		    double r = Math.random();
		    if (r <= 0.5) w = r;
		     else w = 1 - r;
		    if ((r - 0.5) > 0) f = 1;
		    else if ((r - 0.5) < 0) f = -1;
		   double y = Math.sqrt((-2) * Math.log(w));
		    double x = f * (y - (c0 + c1 * y + c2 * y * y) / (1 + d1 * y + d2 * y * y + d3 * y * y * y));
		    double z = a + x * Math.sqrt(b); return (z);
		  }

		  //Ȼ���ж����ĸ���̬�ֲ�����������ķ������£�
		  public static double  normalRandom(double a, double b) {
		    double r = Math.random() * 9;
		    switch ((int) r / 3) {
		    case 0: return normalRandom1(a, b);
		    case 1: return normalRandom2(a, b);
		    case 2: return normalRandom3(a, b);
		   }
		    return 0.0;
		   }
		  
		  public static void main(String[] args) {
			for(int i=0;i<100;i++)
			{
				System.out.println(normalRandom(10,1));
				
			}
		}
}
