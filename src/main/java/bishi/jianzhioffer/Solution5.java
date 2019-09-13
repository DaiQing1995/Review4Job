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
    .*.*�����,����û��
    	����ǰ����a*,�����ֶ��x*�������a
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
            //1. �ҵ�����ֵ����������, ����star״̬�½���
            if (!starState){
                if (inBdy(pattern, j)){//����ֵ��ȡ
                    exp = expectedValAndLength(pattern, j);
                    if (exp[1] == -1){ //is *
                        starVal = exp[0];
                        starState = true;
                        j = j + 2;
                    }
                }else{//�Ѿ���ֵ��ȡ
                    exp[0] = Integer.MIN_VALUE;
                    exp[1] = Integer.MIN_VALUE;
                }
            }
            //2. �õ���ǰֵ
            char curVal;
            if (inBdy(str, i)){
                curVal = str[i];
            }else{ // exit condition
                //�����ǰֵû����ģ��ǰ����λ��Ϊ.*
                if (!isDotStarRemain(pattern, j, starState, starVal))
                    return false;
                else
                    return true;
            }
            if (isExpectedVal(curVal, exp[0]) && exp[1] != 0){// ƥ�䵽����ֵ
                if (starState){         // x*��״̬����star��״̬
                    if (starVal == '.'){//�����.*״̬������starֵ
                        starVal = curVal;//�����a*a��״̬�� starVal����,starֵ���³��ֵ�ֵ��ͬ
                    }else if (starVal != curVal){//�����Ϊ��a*b��״̬, ��ʾ*��״̬�Ѿ�����
                        starState = false;
                        j --;
                    }
                }
                i ++;
                if (!starState) j ++;
                if (exp[1] == 1)
                    exp[1] = 0;
            }else if(isExpectedVal(curVal, starVal) && starState){//ƥ�䵽��*ֵ
                i ++;
                continue;
            }else if (starState){
                starState = false;
            }else {    //3.ƥ��ʧ��
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