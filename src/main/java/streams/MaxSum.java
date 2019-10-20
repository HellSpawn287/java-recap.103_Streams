package streams;

import java.util.Arrays;
import java.util.List;

public class MaxSum {
    public static void main(String[] args) {
        System.out.println(findMaxSum(Arrays.asList(5, 7, 9, 11)));
    }

    private static int findMaxSum(List<Integer> list) {
        Integer max1 = max(list, 0);
        Integer max2 = max(list, max1);
        return max1 + max2;
    }

    private static int max(List<Integer> list, Integer exclude) {
        int max = 0;
        for (Integer value : list) {
            if (value != exclude && value > max) {
                max = value;
            }
        }
        return max;
    }

//    public static void main(String[] args) {
//        System.out.println(maxFun(Arrays.asList(5,7,9,11)));
//    }
//
//    private static Integer maxFun(List<Integer> list) {
//        Integer max1 = max(list, -1);
//        Integer max2 = max(list, max1);
//        return list.get(max1) + list.get(max2);
//    }
//
//    private static int max(List<Integer> list, Integer excludeIndex) {
//        int max = 0;
//        for(int i=0; i<list.size() && i!=excludeIndex; i++) {
//            Integer value = list.get(i);
//            if(value>max ){
//                max = i;
//            }
//        }
//        return max;
//    }
}
