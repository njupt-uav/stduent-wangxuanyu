package BatchTest;

import Resource.Map;
import Resource.Node;
import Resource.UAV;
import greedy2.GreedyMain2;
import method.Calfitness2;
import nsga2.Exchange4;
import nsga2.GenerateNextPopulation;
import nsga2.Individual;
import sa2.SaMain1;
import variableNeighborhood2.VnMain1;

import java.io.IOException;


public class ChangeParameter {
	//这里控制着输出的路径
	public static String fileOutPath = "batchTest2021_9_5.txt";

	//alpha是传输时长，比例为0.25 0.5 0.75
	public static void changeParameter(String mainMethods, String speed, String subIterator, String iterator, String map, String deadline, int selfOwnUAVFactor, int priceMagnification, String timeMagni) throws IOException, CloneNotSupportedException {
		Individual.priceMagnification = priceMagnification;
		switch (timeMagni) {
			case "0.25":
				Calfitness2.transmissionTimeParameter = (float) 0.25;
				break;
			case "0.5":
				Calfitness2.transmissionTimeParameter = (float) 0.5;
				break;
			case "0.75":
				Calfitness2.transmissionTimeParameter = (float) 0.75;
				break;
			case "rand":
				Calfitness2.transmissionTimeParameter = (float) Math.random();
				break;
		}

		switch (speed) {
			case "40":
				UAV.speed = 40;
				UAV.flyEnergyConsume = 1250;
				break;
			case "30":
				UAV.speed = 30;
				UAV.flyEnergyConsume = 1000;
				break;
			case "22":
				UAV.speed = 22;
				UAV.flyEnergyConsume = 850;
				break;
			case "50":
				UAV.speed = 50;
				UAV.flyEnergyConsume = 1750;
				break;
		}


		switch (map) {
			case "att48uniform":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node48uniform.txt";


				Individual.selfOwnedNum = 48 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "att532uniform":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node532uniform.txt";


				Individual.selfOwnedNum = 532 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "node1173uniform":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node1173uniform.txt";


				Individual.selfOwnedNum = 1173 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "att48normal":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node48normal.txt";


				Individual.selfOwnedNum = 48 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "att532normal":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node532normal.txt";


				Individual.selfOwnedNum = 532 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "node1173normal":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node1173normal.txt";


				Individual.selfOwnedNum = 1173 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "node60normal":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node60normal.txt";


				Individual.selfOwnedNum = 60 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "node60uniform":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node60uniform.txt";


				Individual.selfOwnedNum = 60 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "node150uniform":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node150uniform.txt";


				Individual.selfOwnedNum = 150 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "node150normal":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node150normal.txt";


				Individual.selfOwnedNum = 150 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "node666uniform":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node666uniform.txt";


				Individual.selfOwnedNum = 666 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
			case "node666normal":
				Map.filepath = "D:\\NSGA2批量试验最终实例\\node666normal.txt";


				Individual.selfOwnedNum = 666 / selfOwnUAVFactor + 1;
				if (Individual.selfOwnedNum % 2 != 0) Individual.selfOwnedNum++;

				break;
		}

		Exchange4.subIterator = subIterator;

		switch (deadline) {
			case "5":
				Node.alpha = 5;
				break;

			case "10":
				Node.alpha = 10;
				break;

			case "15":
				Node.alpha = 15;
				break;
		}

		GenerateNextPopulation.iterator = iterator;


		String ssss = map + " " + mainMethods + " " + speed + " " + deadline + " " + subIterator + " " + iterator + " " + selfOwnUAVFactor + " " + priceMagnification + " " + timeMagni;
		switch (mainMethods) {
			case "VN":
				VnMain1.vnMain(ssss);
				break;
			case "GREEDY":
				GreedyMain2.greedymain(ssss);
				break;
			case "SA":
				SaMain1.samain(ssss);
				break;
			case "NSGA2":
				NSGA2zhuyaochengxu.running(ssss);
		}

	}
}
