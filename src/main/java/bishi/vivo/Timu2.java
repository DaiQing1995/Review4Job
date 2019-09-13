package bishi.vivo;

import java.io.*;
import java.util.*;

/**
 * Welcome to vivo !
 */

public class Timu2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = br.readLine();
		String[] input = inputStr.split(" ");
		int totalDisk = Integer.parseInt(input[0]);
		int totalMemory = Integer.parseInt(input[1]);
		List<Service> services = parseServices(input[2].split("#"));
		int output = solution(totalDisk, totalMemory, services);
		System.out.println(output);
	}

	private static int solution(int totalDisk, int totalMemory, List<Service> services) {
		int[][][] dp = new int[totalMemory + 1][totalDisk + 1][services.size() + 1];
		for (int i = 0; i < totalMemory + 1; ++i) {
			for (int k = 0; k < services.size() + 1; ++k) {
				dp[i][0][k] = 0;
			}
		}
		for (int j = 0; j < totalDisk + 1; ++j) {
			for (int k = 0; k < services.size() + 1; ++k) {
				dp[0][j][k] = 0;
			}
		}
		for (int k = 1; k < services.size() + 1; ++k) {
			for (int i = 1; i < totalMemory + 1; ++i) {
				for (int j = 1; j < totalDisk + 1; ++j) {
					dp[i][j][k] = Math.max(k > 0 ? dp[i][j][k - 1] : 0,
							i - services.get(k - 1).memory >= 0 && j - services.get(k - 1).disk >= 0
									? dp[i - services.get(k - 1).memory][j - services.get(k - 1).disk][k - 1] + services.get(k - 1).users
									: 0);
				}
			}
		}
//		for (int k = 0;k < services.size() + 1; ++ k) {
//			System.out.println(k + ":");
//			for (int i = 0; i < totalMemory + 1; ++ i) {
//				for (int j = 0; j < totalDisk + 1; ++ j) {
//					System.out.print(dp[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//		}
		return dp[totalMemory][totalDisk][services.size()];
	}

	private static List<Service> parseServices(String[] strArr) {
		if (strArr == null || strArr.length == 0) {
			return new ArrayList<Service>(0);
		}
		List<Service> services = new ArrayList<>(strArr.length);
		for (int i = 0; i < strArr.length; i++) {
			String[] serviceArr = strArr[i].split(",");
			int disk = Integer.parseInt(serviceArr[0]);
			int memory = Integer.parseInt(serviceArr[1]);
			int users = Integer.parseInt(serviceArr[2]);
			services.add(new Service(disk, memory, users));
		}
		return services;
	}

	static class Service {
		private int disk;

		private int memory;

		private int users;

		public Service(int disk, int memory, int users) {
			this.disk = disk;
			this.memory = memory;
			this.users = users;
		}

		public int getDisk() {
			return disk;
		}

		public void setDisk(int disk) {
			this.disk = disk;
		}

		public int getMemory() {
			return memory;
		}

		public void setMemory(int memory) {
			this.memory = memory;
		}

		public int getusers() {
			return users;
		}

		public void setusers(int users) {
			this.users = users;
		}
	}
}