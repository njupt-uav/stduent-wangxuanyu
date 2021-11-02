package kmeans;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Kmean {
    // ��������ĵ㼯
    public ArrayList<Point> points;

    // �����������浽�ļ�
    FileWriter out = null;
    FileWriter out1 = null;

    // ��ʽ��double���͵������������λС��
    DecimalFormat dFormat = new DecimalFormat("00.00");

    // ����ִ�о���Ķ���
    public KMeansCluster kMeansCluster;

    // �ص���������������
    public int numCluster;
    public int numIterator = 200;

    // �㼯������������ָ�������ĵ㼯
    public int numPoints;

    //�ڵ���Դ���ļ�·��
    public static String sourceName = "att532.txt";

    //����������·��
    public static String FILEPATH = "D:\\java����\\nsga2\\�½��ı��ĵ� (2).txt";

    public static void main(String[] args) throws IOException {
        //ָ���㼯�������صĸ�������������
        Kmean kmeans = new Kmean(53, 200);

        //��ʼ���㼯��KMeansCluster����
        kmeans.init();

        //ʹ��KMeansCluster������о���
        kmeans.runKmeans();

        kmeans.printRes(sourceName);
        kmeans.saveResToFile(FILEPATH);
    }

    public Kmean(int cluster_number, int iterrator_number) {
        this.numCluster = cluster_number;
        this.numIterator = iterrator_number;
    }

    public void init() {
        try {
            this.initPoints();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        kMeansCluster = new KMeansCluster(numCluster, numIterator, points);
    }

    public void runKmeans() {
        kMeansCluster.runKmeans();
    }

    // ��ʼ���㼯
    public void initPoints() throws IOException {
        Point tmpPoint;
        int[] x;
        int[] y;
        String strbuff;
        BufferedReader data = new BufferedReader(new InputStreamReader(
                new FileInputStream(sourceName)));

        strbuff = data.readLine();
        numPoints = Integer.valueOf(strbuff);//�ļ���һ���ǳ�������
        points = new ArrayList<>(numPoints);
        x = new int[numPoints];
        y = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            strbuff = data.readLine();// �ӵڶ������ݿ�ʼ��ȡ�����ݸ�ʽ1 6734 1453
            String[] strcol = strbuff.split(" +");// �ַ��ָ�
            BigDecimal db1 = new BigDecimal(strcol[1]);
            BigDecimal db2 = new BigDecimal(strcol[2]);


            x[i] = new Double(Double.valueOf(db1.toPlainString())).intValue();// x����
            y[i] = new Double(Double.valueOf(db2.toPlainString())).intValue();// y����
            tmpPoint = new Point(x[i], y[i]);
            points.add(tmpPoint);
        }
        data.close();
    }

    public void printRes(String filePath) throws IOException {

        System.out.println("==================Centers-I====================");
        out1 = new FileWriter(new File(FILEPATH));
        out = new FileWriter((new File(FILEPATH)), true);
        out1.write(" ");
        out.write("the number of total" + "\r\n");
        out.write(String.valueOf(numPoints + this.numCluster) + "\r\n");
        out.write("the number of charge station" + "\r\n");
        out.write(String.valueOf(this.numCluster) + "\r\n");
        out.write("==================Centers-I====================");


        for (Point center : kMeansCluster.centers) {
            System.out.println(center.toString());
            out.write("\r\n");
            out.write(String.valueOf(center.getClusterID()));
            out.write("  ");

            out.write(dFormat.format(center.getX()));
            out.write("  ");
            out.write(dFormat.format(center.getY()));
            out.flush();
        }
        out.write("\r\n");
        out.write("==================Centers-I====================");
        out.write("\r\n");
        out.close();
        out1.close();

        System.out.println("==================Points====================");

        for (Point point : points) {
            System.out.println(point.toString());
        }
    }

    public void saveResToFile(String filePath) {
        try {
            out = new FileWriter(new File(FILEPATH), true);

            for (Point point : points) {
                out.write(String.valueOf(point.getClusterID()));
                out.write("  ");

                out.write(dFormat.format(point.getX()));
                out.write("  ");
                out.write(dFormat.format(point.getY()));
                out.write("\r\n");
            }

            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


