package 京东;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int min = sc.nextInt();
        int max = min;
        List<Integer> min_list = new ArrayList<>();
        List<Integer> max_list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int number = sc.nextInt();
            if (number < min){
                min = number;
                min_list = new ArrayList<>();
                min_list.add(i);
            }else if (number == min){
                min_list.add(i);
            }
            if (number > max){
                max = number;
                max_list = new ArrayList<>();
                max_list.add(i);
            }else if (number == max){
                max_list.add(i);
            }
        }
        int sum_min;
        if (min_list.get(0) < max_list.get(0)){
            sum_min = max_list.get(0) + 1;
            if (sum_min < min_list.get(0) + 1 + ( n - max_list.get(max_list.size() - 1))){
                sum_min = min_list.get(0) + 1 + (n - max_list.get(max_list.size() - 1));
            }
        }else {
            sum_min = min_list.get(0) + 1;
            if (sum_min < max_list.get(0) + 1 + (n - min_list.get(min_list.size() - 1))){
                sum_min = max_list.get(0) + 1 + (n - min_list.get(min_list.size() - 1));
            }
        }
        System.out.println(sum_min);
    }
}


//import java.util.Arrays;
//        import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] arr = new int[n];
//        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
//        int max_idx = 0, min_idx = 0;
//
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = sc.nextInt();
//            if (arr[i] > max){
//                max = arr[i];
//                max_idx = i;
//            }
//            if (arr[i] < min){
//                min = arr[i];
//                min_idx = i;
//            }
//        }
//
////        int step = 0;
////        if (max_idx < n / 2 && max_idx < n / 2){
////            for (int i = 0; i < arr.length; i++) {
////                step++;
////
////            }
////        }
////        System.out.println(max_idx);
////        System.out.println(min_idx);
////        System.out.println(max);
////        System.out.println(min);
//        int countA1 = 0, countA2 = 0;
//        int countB1 = 0, countB2 = 0;
//
//        int step = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] == max){
//                countA1 = i + 1;
//            }
//            if (arr[i] == min){
//                countA2 = i + 1;
//            }
//        }
//        for (int i = arr.length - 1; i >= 0; i--) {
//            step++;
//            if (arr[i] == max){
//                countB1 = step;
//            }
//            if (arr[i] == min){
//                countB2 = step;
//            }
//        }
//        int mymax = countA1, mymin = countA2;
//        boolean state = false;
//        int res;
//        if ((countA1 <= countB1 && countA2 <= countB2) || (countB1 <= countA1 && countB2 <= countA2)){
//            state = true;
//        }
//        if (state == false){
//            res = Math.min(countA1, countB1) + Math.min(countA2, countB2);
//        }else {
//            res = Math.min(Math.max(countA1, countA2), Math.max(countA2, countB2));
////            res = Math.max(mymax,mymin);
//        }
////        System.out.println(countA1);
////        System.out.println(countA2);
////        System.out.println(countB1);
////        System.out.println(countB2);
//        int res1 = Math.min(countA1, countB1) + Math.min(countA2, countB2);
//        int res2 = Math.min(Math.max(countA1, countA2), Math.max(countA2, countB2));
//        System.out.println(Math.min(res1, res2));
////        System.out.println(res);
//
//
//    }
//}
