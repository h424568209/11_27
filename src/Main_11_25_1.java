import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main_11_25_1 {

    //DNA序列--找出S中最短没有出现在DNA序列s中的DNA片段长度
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        System.out.println(fun(str));
    }
    //DNA序列  在字符串中找含有最大比例的G和C
    public void tt(){
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String str = input.next();
            int n = input.nextInt();
            int max = 0;
            int maxBeginIndex = 0;
            for (int i = 0; i <= str.length() - n; i++) {
                int count = 0;
                for (int j = i; j < i + n; j++) {
                    if (str.charAt(j) == 'G' || str.charAt(j) == 'C') count++;
                }
                if (count > max) {
                    maxBeginIndex = i;
                    max = count;
                }
            }
            System.out.println(str.substring(maxBeginIndex, maxBeginIndex + n));
        }
    }
    private static int fun(String str) {
        for(int i = 1 ; i <= str.length() ; i++){
            Set<String>rq = new TreeSet<>();
            for(int j = 0 ; j < str.length() - i ; j++){
                rq.add(str.substring(j,j+i)); // 长度为i的子串
            }
            if(rq.size()<Math.pow(4,i)){  //长度为i的子串没有出现，返回i
                return  i;
            }
        }
        //字符串包含了所有长度为1的子串，所以此处返回1
        return 1;
    }
}
