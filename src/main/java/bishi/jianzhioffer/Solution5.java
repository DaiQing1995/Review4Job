package bishi.jianzhioffer;

public class Solution5 {

    
    public boolean inBdy(char[] val, int i){
        return i >= val.length ? false : true;
    }
    
    public int getExpectedLength(char[] val,int i){
        if (!inBdy(val, i))
            return 1;
        else{
            if (val[i] == '*')
                return -1;
        }
        return 1;
    }
    
    public boolean isExpectedVal(int val, int expectedVal){
        if (val == expectedVal)
            return true;
        if (expectedVal == '.')
            return true;
        else{
            return false;
        }
    }
    
    public int[] expectedValAndLength(char[] pattern, int index){
        if (!inBdy(pattern, index)){
            return new int[]{0, 0};
        }
        int[] ret = new int[2];
        ret[0] = pattern[index];
        ret[1] = getExpectedLength(pattern, index + 1);
        return ret;
    }
    
    /**
    .*.*在最后,或者没有
    	或者前面有a*,最后出现多个x*，最后还是a
     * @param starVal 
     * @param starState 
    */
    public boolean isDotStarRemain(char[] val, int index, boolean starState, int starVal){
        if (val.length <= index)
            return true;
        int i;
        for (i = index; i < val.length; i += 2){
        	if (i + 1 >= val.length)
        		break;
            if (val[i] != '.' && val[i + 1] != '*' && val[i] != val[i + 1])
                break;
        }
        for (;i < val.length; ++ i) {
        	if (!starState)
        		return false;
        	if (starState && val[i] != starVal)
        		return false;
        }
        return true;
    }
    
    public boolean match(char[] str, char[] pattern)
    {
        int i = 0;
        int j = 0;
        //expected value: exp[0], expected len: exp[1]
        int[] exp = new int[2];
        int starVal = -1;
        boolean starState = false;
        
        while(true){
            //1. 找到期望值和期望长度, 不在star状态下进行
            if (!starState){
                if (inBdy(pattern, j)){//仍有值可取
                    exp = expectedValAndLength(pattern, j);
                    if (exp[1] == -1){ //is *
                        starVal = exp[0];
                        starState = true;
                        j = j + 2;
                    }
                }else{//已经无值可取
                    exp[0] = Integer.MIN_VALUE;
                    exp[1] = Integer.MIN_VALUE;
                }
            }
            //2. 拿到当前值
            char curVal;
            if (inBdy(str, i)){
                curVal = str[i];
            }else{ // exit condition
                //如果当前值没有且模板前面两位不为.*
                if (!isDotStarRemain(pattern, j, starState, starVal))
                    return false;
                else
                    return true;
            }
            if (isExpectedVal(curVal, exp[0]) && exp[1] != 0){// 匹配到了新值
                if (starState){         // x*的状态，有star的状态
                    if (starVal == '.'){//如果是.*状态，更新star值
                        starVal = curVal;//如果是a*a的状态， starVal保留,star值与新出现的值相同
                    }else if (starVal != curVal){//如果换为了a*b的状态, 表示*的状态已经结束
                        starState = false;
                        j --;
                    }
                }
                i ++;
                if (!starState) j ++;
                if (exp[1] == 1)
                    exp[1] = 0;
            }else if(isExpectedVal(curVal, starVal) && starState){//匹配到了*值
                i ++;
                continue;
            }else if (starState){
                starState = false;
            }else {    //3.匹配失败
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
//    	char[] a = {'a', 'a', 'a'};
//    	char[] b = {'a', 'b', '*', 'a', '*', 'c', '*', 'a'};
//    	char[] a = {'a'};
//    	char[] b = {'a', 'b', '*', 'a'};
//    	char[] a = {'a', 'a', 'b'};
//    	char[] b = {'c', '*', 'a', '*', 'b'};
    	char[] a = {'b', 'b', 'b', 'a'};
    	char[] b = {'.', '*', 'a', '*', 'a'};
    	System.out.println(new Solution5().match(a, b));
    }
}