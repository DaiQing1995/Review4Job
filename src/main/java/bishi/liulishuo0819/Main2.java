package bishi.liulishuo0819;


import java.util.ArrayList;
import java.util.Scanner;
public class Main2 {
	
	private static class Train{
		private int arriveTime;
		private int leaveTime;
		public Train(int start, int end) {
			this.arriveTime = start;
			this.leaveTime = end;
		}
	}
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        String[] inStation = in.nextLine().split(" ");
        String[] outStation = in.nextLine().split(" ");
        ArrayList<Train> trains = new ArrayList<>();
        for (int i = 0; i < inStation.length; ++ i) {
        	trains.add(new Train(Integer.parseInt(inStation[i]), Integer.parseInt(outStation[i])));
        }
        int max = 1;
        for (int i = 0; i < trains.size(); ++ i) {
        	int tmp = 1;
        	for (int j = 0; j < trains.size(); ++ j) {
        		if (i == j)
        			continue;
        		if (trains.get(i).arriveTime < trains.get(j).leaveTime && trains.get(i).arriveTime >= trains.get(j).arriveTime)
        			tmp ++;
        		else if (trains.get(i).arriveTime <= trains.get(j).arriveTime && trains.get(i).leaveTime > trains.get(j).arriveTime)
        			tmp ++;
        	}
        	if (tmp > max)
        		max = tmp;
        }
        System.out.println(max);
    }
}