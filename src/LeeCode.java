import java.util.*;

public class LeeCode {
    /**
     * 根据二分查找寻找峰值
     *   上坡必有坡顶
     * 复杂度O(longN)
     */
    public int findPeakElements(int[] nums) {
        int l = 0 , r= nums.length - 1;
        while(l<r){
            int mid = (l+r)/2;
            //下降的说明左边存在峰值
            if(nums[mid]>nums[mid+1])
                r = mid;
            //升序说明右边存在升序
            else
                l = mid+1;
        }
        return l;
    }
    /**
     * 寻找峰值  --数组中某个元素比她左右的元素都大，这个元素就是这个元素的一个峰值
     * @param nums 数组
     * @return 其中一个峰值的下标
     * 复杂度O（n）
     */
    public int findPeakElement(int[] nums) {
       for(int i =0 ; i < nums.length ; i++){
           if(nums[i] > nums[i+1]){
               return i;
           }
       }
            return nums.length-1;
    }
    /**
     * 使用滑动窗口，窗口的大小是s1的长度
     * 如果不是子字符串就进行滑动  不断地更新窗口，直到两个的元素完全吻合
     */
    public boolean checkInclusionUseSlipWindows(String s1, String s2) {
        if(s1.length() > s2.length())
            return false;
        int[]s1map = new int[26];
        int[]s2map = new int[26];
        for(int i =0 ; i < s1.length() ; i++){
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        for(int i =0 ; i < s2.length() -s1.length() ; i++){
            if(matches(s1map,s2map))
                return true;
            s2map[s2.charAt(i + s1.length())- 'a']++;
            s2map[s2.charAt(i) - 'a'] -- ;
        }
        return matches(s1map,s2map);
    }
    /**
     *  使用数组进行存储频率，采用大小为26的数组
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusionUseArray(String s1, String s2) {
        if(s1.length()>s2.length())
            return false;
        int s1map [] = new int[26];
        for(int i = 0 ;i < s1.length() ; i++){
            s1map[s1.charAt(i) - 'a']++;
        }
        for(int i = 0 ; i < s2.length() - s1.length(); i++){
            int []s2map = new int[26];
            for(int j = 0 ; j < s1.length() ; j++){
                s2map[s2.charAt(j+i) - 'a'] ++;
            }
            if(matches(s1map,s2map)){
                return true;
            }
        }
        return false;
    }

    private boolean matches(int[] s1map, int[] s2map) {
        for(int i = 0 ; i < 26 ; i++){
            if(s1map[i]!=s2map[i])
                return false;
        }
        return true;
    }

    /**
     *  字符串的排列 --- 查看是否大的字符串中包含小的字符串的排列 -- 所有的字符都是小写字母
     *  使用排序，将小的字符串进行排序，在大的字符串中截取相同的长度进行排序
     *  比较两个字符串是否相等
     *  只有两个字符串包含相同二点频率的相同字符时，一个字符串才是另一个字符串的排列
     * @param s1 小的字符串
     * @param s2 大的字符串
     * @return 是否包含
     */
    public boolean checkInclusion(String s1, String s2) {
        s1 = sort(s1);
        for(int i =0 ; i <= s2.length()- s1.length() ; i++){
            if(s1.equals(sort(s2.substring(i,i+s1.length())))){
                return true;
            }
        }
        return false;
    }

    private String sort(String s1) {
        char[]arr = s1.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    /**
     *  数组中的最大的三个数的乘积
     * @param nums 数组
     * @return 乘积之后的结果
     */
    public int maximumProduct(int[] nums) {
        if(nums.length < 3 ){
            return  0;
        }

        // Arrays.sort(nums);
        //return Math.max(nums[0]*nums[1]*nums[nums.length-1] , nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3]);


        //求出数组的最大的三个数和最小的两个数就可以求出数组中的三个数最大的乘积
        int min1 = Integer.MAX_VALUE,min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE,max2 = Integer.MIN_VALUE,max3 = Integer.MAX_VALUE;
        for(int num : nums){
            if(num <= min1){
                min1 = num;
            }else if (num <= min2){
                min2 = num;
            }
            //max1 > max2 > max3
            if(num >= max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if(num>=max2){
                max3 = max2;
                max2 = num;
            }else{
                max3 = num;
            }
        }
        return Math.max(max1*max2*max3 , min1*min2*max1);
    }

    private List<List<Integer>> res = new ArrayList<>();
    private boolean[]used;

    /**
     * 回溯
     * @param nums 数组
     * @param depth 深度
     * @param stack 栈
     */
    private void findPermuteUnique(int []nums, int depth , Stack<Integer> stack){
        if(depth == nums.length){
            res.add(new ArrayList<>(stack));
            return;
        }
        for(int i =0 ; i < nums.length ; i++){
            if(!used[i]){
                //因为排序后重复的数字一定不出现在第一个位置
                // 和之前的数相等，且之前的数未使用过，如果使用就会出现相同的元素，出现分支
                // 跳过这种情况
                if(i>0 && nums[i] == nums[i-1] && !used[i-1]){
                    continue;
                }
                used[i] = true;
                stack.add(nums[i]);
                findPermuteUnique(nums,depth+1,stack);
                stack.pop();
                used[i] = false;
            }
        }
    }

    /**
     *  全排列--含重复元素
     * @param nums 数组
     * @return 数组的全排列， 不包含重复的元素
     */
    public List<List<Integer>> permutenUnique(int []nums){
        int len = nums.length;
        if(len == 0){
            return res;
        }
        //排序，之后才有可能发现重复分支
        Arrays.sort(nums);
        used = new boolean[len];
        findPermuteUnique(nums,0,new Stack<>());
        return res;
    }

    /**
     *  全排列 --不包含重复的元素
     * @param nums 数组
     * @return 链表形式的全排列
     */
    public List<List<Integer>> permute (int[]nums){
        int len = nums.length;
        List<List<Integer>>res = new ArrayList<>();
        if(len == 0){
            return res;
        }
        Set<Integer>used = new HashSet<>();
        //使用哈希表检测一个数字是否使用过
        Stack<Integer> stack = new Stack<>();
        backtace(nums,0,len,used,stack,res);
        return res;
    }

    /**
     * 回溯
     * @param nums  数组
     * @param depth 深度
     * @param len 数组长度
     * @param used set，用来保存使用过的下标
     * @param stack 栈
     * @param res 结果保存在链表中
     */
    private void backtace(int[] nums, int depth, int len, Set<Integer> used, Stack<Integer> stack, List<List<Integer>> res) {
        //depth表示当前路径中有多少个元素
        if(depth == len){
            //此时stack已经保存了nums中的所有数字，已经成为了一个排列
            res.add(new ArrayList<>(stack));
            return;
        }
        for(int i= 0 ; i < len ; i++){
            if(!used.contains(i)){
                used.add(i);
                stack.push(nums[i]);
                backtace(nums,depth+1,len,used,stack,res);
                //状态重置
                //释放对最后一个数的占用
                //将最后一个数从当前选取的排列中弹出
                stack.pop();
                used.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        int []nums = new int[]{1,2,3,4};
        LeeCode l = new LeeCode();
        List<List<Integer>> permute = l.permute(nums);
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
        System.out.println("_____________");
        int []num = {1,1,2};
        System.out.println(l.permutenUnique(num));
        System.out.println(l.maximumProduct(nums));
    }
}
