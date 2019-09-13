package bishi.aiqiyi;

public class Main {
    
    public static char[] getPlusOne(char[] data){
        if (data == null)
            return null;
        char[] newChar = new char[data.length + 1];
        newChar[0] = '0';
        int index = data.length - 1;
        int newIndex = data.length;
        int value = (data[index --] - '0') + 1;
        newChar[newIndex --] = (char)((int)'0' + value % 10);
        int jinwei = value / 10;
        while(index >= 0){
            value = (data[index --] - (int)'0') + jinwei;
            newChar[newIndex --] = (char)((int)'0' + value % 10);
            jinwei = value / 10;
        }
        if (jinwei != 0){
            newChar[0] = (char)((int)'0' + jinwei);
        }
        return newChar;
    }
    
    public static void main(String[] args) {
        char[] data = new char[]{'1', '2', '3'};
        char[] ret = getPlusOne(data);
        int start = ret[0] == '0' ? 1 : 0;
        for (int i = start; i < ret.length; ++ i)
            System.out.print(ret[i]);
        System.out.println();
    }
}