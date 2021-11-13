package preproblem.main;

import BatchTest.ChangeParameter;
import Resource.Map;
import nsga2.Population;
import tool.Read2;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @description: 大论文前置场景的主运行方法
 * @author: zhaoyun_498900626@qq.com
 * @create: 2021-11-13 12:32
 **/
public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        FileWriter out = new FileWriter(ChangeParameter.fileOutPath, true);
        long time = System.currentTimeMillis();
        Map map = new Map();

        Read2.read2(map);

        Population father = new Population();
//		 long time=System.currentTimeMillis();
        father.init(map);
    }



}
