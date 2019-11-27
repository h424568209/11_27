import java.util.*;

public class Main_11_27_1 {
    public int getValue(int[] gifts, int n) {
   //      write code here
     Map<Integer,Integer>map = new HashMap<>();
     for(int i =0 ; i < n ; i++){
         map.put(gifts[i],map.getOrDefault(gifts[i],0)+1);
     }
        System.out.println(map);
     for(int i =0 ; i < n ; i++){
         if(map.get(gifts[i])>n/2){
             return gifts[i];
         }
     }
     return 0;

    }

    public static void main(String[] args) {
        int []nums = {1,2,3,3,2,3,2,1,1,1,1,1,1};
        Main_11_27_1 m = new Main_11_27_1();
        System.out.println(m.getValue(nums,nums.length));
    }
}
