
package kmeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KMeansCluster
{
    // ����������
    public int k ;

    // ����������
    public int maxIter = 50;

    // ���Ե㼯
    public List<Point> points;

    // ���ĵ�
    public List<Point> centers;

    public static final double MINDISTANCE = 10000.00;

    public KMeansCluster(int k, int maxIter, List<Point> points) {
        this.k = k;
        this.maxIter = maxIter;
        this.points = points;

        //��ʼ�����ĵ�
        initCenters();
    }

    /*
     * ��ʼ����������
     * �����ѡȡ�����ǣ��ӵ㼯�а����г�ȡK����Ϊ��ʼ��������
     */
    public void initCenters()
    {
        centers = new ArrayList<>(k);
        ArrayList<Integer>arr=new ArrayList();
        for(int i=0;i<points.size();i++)
        {
        	arr.add(i);
        }
        Collections.shuffle(arr);
        for (int i = 0; i < k; i++)
        {
            Point tmPoint = points.get(arr.get(i));
            Point center = new Point(tmPoint.getX(), tmPoint.getY());
            center.setClusterID(i + 1);
            centers.add(center);
        }
    }


    /*
     * ֹͣ�����������������
     */
    public void runKmeans()
    {
        // �ѵ�������
        int count = 1;

        while (count++ <= maxIter)
        {
            // ����ÿ���㣬ȷ����������
            for (Point point : points)
            {
                assignPointToCluster(point);
            }

            //�������ĵ�
            adjustCenters();
        }
    }



    /*
     * �����������ģ�������ƽ���ķ�������µĴ���
     */
    public void adjustCenters()
    {
        double sumx[] = new double[k];
        double sumy[] = new double[k];
        int count[] = new int[k];

        // ����ÿ���صĺ�������֮��
        for (int i = 0; i < k; i++)
        {
            sumx[i] = 0.0;
            sumy[i] = 0.0;
            count[i] = 0;
        }

        // ����ÿ���صĺ��������ܺ͡���¼ÿ���صĸ���
        for (Point point : points)
        {
            int clusterID = point.getClusterID();

            // System.out.println(clusterID);
            sumx[clusterID - 1] += point.getX();
            sumy[clusterID - 1] += point.getY();
            count[clusterID - 1]++;
        }

        // ���´�������
        for (int i = 0; i < k; i++)
        {
            Point tmpPoint = centers.get(i);
            tmpPoint.setX(sumx[i] / count[i]);
            tmpPoint.setY(sumy[i] / count[i]);
            tmpPoint.setClusterID(i + 1);

            centers.set(i, tmpPoint);
        }
    }


    /*���ֵ㵽ĳ�����У�ŷʽ�����׼
     * �Դ����ÿ���㣬�ҵ���������Ĵ����ĵ㣬���˵���뵽��
     */
    public void assignPointToCluster(Point point)
    {
        double minDistance = MINDISTANCE;

        int clusterID = -1;

        for (Point center : centers)
        {
            double dis = EurDistance(point, center);
            if (dis < minDistance)
            {
                minDistance = dis;
                clusterID = center.getClusterID();
            }
        }
        point.setClusterID(clusterID);

    }

    //ŷʽ���룬�����������
    public double EurDistance(Point point, Point center)
    {
        double detX = point.getX() - center.getX();
        double detY = point.getY() - center.getY();

        return Math.sqrt(detX * detX + detY * detY);
    }
}
