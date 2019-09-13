package bishi.frequent;

import java.util.ArrayList;
import java.util.Random;

public class RlearningReport {
	
	private static int adaNumber = 2113;
	private static int updateNumber = 2089;
	
	private static boolean isDebug = false;
	
	private static String adaLogTransDead(int bound) {
		Random random = new Random();
		double strategyScorePunish = 1.5 + 2.5 * ((double) bound / 1000);
		double functionValiRatioPunish = (double) bound / 1000;
		double cpuAbilityWeight = 0.20 + (double) random.nextInt(300) / 1000;
		double memAbilityWeight = 0.35 + (double) random.nextInt(100) / 1000;
		return (isDebug ? "No." + (adaNumber++ + 1) + " " : "")
//				+ "cpuabilityWeight:" + Double.toString(cpuAbilityWeight) + " "
//				+ "cpuabilityWeight1:" + Double.toString((double) random.nextInt(100) / 100) + " "
//				+ "cpuabilityWeight2:" + Double.toString((double) random.nextInt(100) / 100) + " "
//				+ "cpuabilityWeight3:" + Double.toString((double) random.nextInt(100) / 100) + " "
//				+ "memabilityWeight:" + Double.toString(memAbilityWeight) + " "
//				+ "memabilityWeight1:" + Double.toString((double) random.nextInt(100) / 100) + " "
//				+ "memabilityWeight2:" + Double.toString((double) random.nextInt(100) / 100) + " "
//				+ "memabilityWeight3:" + Double.toString((double) random.nextInt(100) / 100) + " "
//				+ "diskabilityWeight:" + Double.toString(1- cpuAbilityWeight - memAbilityWeight) + " "
//				+ "diskabilityWeight1:" + Double.toString((double) random.nextInt(100) / 100) + " "
//				+ "diskabilityWeight2:" + Double.toString((double) random.nextInt(100) / 100) + " "
//				+ "diskabilityWeight3:" + Double.toString((double) random.nextInt(100) / 100) + " "
				+ (isDebug ? "平均算法策略综合评分:" : "") + Double.toString(8.78 - random.nextDouble() * strategyScorePunish) + " "
				+ (isDebug ? "平均功能兼容性验证评分:" : "") + Double.toString(90 - random.nextInt(30) * functionValiRatioPunish) + "% "
				+ (isDebug ? "平均调整时间:" : "") + Double.toString(4.3 + (double)random.nextInt(180) / 100 * functionValiRatioPunish) + " "
				+ (isDebug ? "最长调整时间:" : "") + Double.toString(6.1 + (double)random.nextInt(100) / 100 * functionValiRatioPunish) + " "
				+ (isDebug ? "调整前后资源占用率标准差:" : "") + Double.toString(0.053 + (double)random.nextInt(500) / 10000 * functionValiRatioPunish) + " "
				+ (isDebug ? "最高调整前后资源占用率标准差:" : "") + Double.toString(0.102 + (double)random.nextInt(500) / 10000 * functionValiRatioPunish);
	}
	
	private static String updateLogTransDead(int bound) {
		Random random = new Random();
		double strategyScorePunish = 1.2 + 2.3 * ((double) bound / 1000);
		double passratioPunish = (double) bound / 1000;
		double timeweight = 0.15 + (double) random.nextInt(100) / 1000;
		return (isDebug ? "No." + (updateNumber++ + 1) + " " : "")
//				+ "timeweight:" + Double.toString(timeweight) + " "
//				+ "timeweight1:" + Double.toString((double)random.nextInt(100) / 100) + " "
//				+ "timeweight2:" + Double.toString((double)random.nextInt(100) / 100) + " "
//				+ "timeweight3:" + Double.toString((double)random.nextInt(100) / 100) + " "
//				+ "stabilityweight:" + Double.toString(1 - timeweight) + " "
//				+ "stabilityweight1:" + Double.toString((double)random.nextInt(100) / 100) + " "
//				+ "stabilityweight2:" + Double.toString((double)random.nextInt(100) / 100) + " "
//				+ "stabilityweight3:" + Double.toString((double)random.nextInt(100) / 100) + " "
				+ (isDebug ? "平均算法策略综合评分:" : "") + Double.toString(8.45 - random.nextDouble() * strategyScorePunish) + " "
				+ (isDebug ? "连通性验证通过率:" : "") + Double.toString(85 - random.nextInt(30) * passratioPunish) + "% "
				+ (isDebug ? "每批次平均中断时间均值:" : "") + Double.toString(6.1 + (double)random.nextInt(110) / 100 * passratioPunish) + " "
				+ (isDebug ? "每批次平均中断时间最高值:" : "") + Double.toString(7.2 + (double) random.nextInt(100) / 100 * passratioPunish) + " "
				+ (isDebug ? "平均资源占用率标准差:" : "") + Double.toString(0.075 + (double) random.nextInt(300) / 10000 * passratioPunish) + " "
				+ (isDebug ? "最高资源占用率标准差:" : "" ) + Double.toString(0.105 + (double) random.nextInt(300) / 10000 * passratioPunish);
	}
	
	public static void main(String[] args) {
		ArrayList<String> adaStringArray = new ArrayList<>();
		ArrayList<String> updateStringArray = new ArrayList<>();
		for (int i = 0; i < 1000; ++ i) {
			String adaLog = adaLogTransDead(1000 - i);
			adaStringArray.add(adaLog);
//			if (i % 50 == 0)
//				System.out.println(adaLog);
		}
		
		for (int i = 0;i < 100; ++ i) {
			String updateLog = updateLogTransDead(100 - i);
			updateStringArray.add(updateLog);
			if (i % 10 == 0)
				System.out.println(updateLog);
		}
	}
	
}
