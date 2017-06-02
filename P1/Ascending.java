import java.util.*;

public class Ascending {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        for (int i = 1; i <= 10; i++) {
            System.out.print("Enter Input " + i + ": ");
            String in = s.nextLine();
            System.out.println("Output " + i + ": " + solve(in));
        }
    }
    
    public static String solve(String in) {
        ArrayList<Integer> nums = new ArrayList<>();
        int zeroes = 0;
        for (int i = 1; i < in.length(); i++) {
            int x = Integer.parseInt(in.substring(i, i+1));
            if (x != 0) {
                nums.add(x);
            } else {
                zeroes++;
            }
        }
        Collections.sort(nums);
        int len = Integer.parseInt(in.substring(0,1));
        //System.out.println(nums);
        //System.out.println(zeroes);
        
        String out = "";
        while (nums.size() > 0) {
            System.out.println(zeroes);
            int usableZeroes = zeroes - (len-1);
            System.out.println(usableZeroes);
            for (int i = 0; i < len - usableZeroes; i++) {
                out += nums.get(i);
            }
            for (int i = 0; i < usableZeroes; i++) {
                out += 0;
            }
            zeroes -= usableZeroes;
            for (int i = 0; i < len - usableZeroes; i++) {
                nums.remove(i);
            }
            out += " ";
            System.out.println(out);
            System.out.println(nums);
        }
        
        return out;
    }
}