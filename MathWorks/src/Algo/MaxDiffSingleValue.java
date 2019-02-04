package Algo;

public class MaxDiffSingleValue {

    public static int solution(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int res = arr[0];
        for(int i : arr) res = Math.min(res, i);
        for(int i = 0; i < arr.length; i++)
            arr[i] = Math.abs(arr[i] - res);
        for(int i : arr) res = Math.max(res, i);
        return res;
    }

}
