import java.util.*;

public class Numbers36 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        for (int i = 1; i <= 2; i++) {
            System.out.print("Enter Input " + i + ": ");
            String[] in = s.nextLine().split(",");
            System.out.println("Output " + i + ": " + solve(in[0].trim(), Integer.parseInt(in[1].trim())));
        }
    }
    static ArrayList<String> solve(String b36, int n) {
        ArrayList<String> out = new ArrayList<>();
        ArrayList<Character> num = new ArrayList<>();
        for (int i = 0; i < b36.length(); i++) {
            num.add(b36.charAt(i));
        }
        ArrayList<ArrayList<Character>> all = perms(num);
        HashMap<ArrayList<Character>, Long> scores = new HashMap<>();
        //System.out.println(all);
        
        for (int i = 0; i < all.size(); i++) {
            scores.put(all.get(i), evalNum(all.get(i)));
        }
        
        Collections.sort(all, (s1, s2) -> scores.get(s1).compareTo(scores.get(s2)));
        out.add(str(all.get(0)));
        out.add(str(all.get(all.size()-1)));
        out.add(str(all.get(all.size()-50)));
        out.add(str(all.get(n-1)));
        
        double avg = Math.round((scores.get(all.get(0)) + scores.get(all.get(all.size()-1))) / 2.0);
        ArrayList<Character> closest = null;
        double cval = Double.MAX_VALUE;
        for (ArrayList<Character> i : all) {
            double dist = Math.abs((double)scores.get(i) - avg);
            if (dist < cval) {
                cval = dist;
                closest = i;
            }
        }
        out.add(str(closest));
        
        return out;
    }
    static String str(ArrayList<Character> x) {
        String rv = "";
        for (int i = 0; i < x.size(); i++) {
            if (i == 0 && x.get(i) == '0') {
                continue;
            }
            rv += "" + x.get(i);
        }
        return rv;
    }
    static long evalNum(ArrayList<Character> num) {
        long res = 0;
        for (int i = num.size()-1; i >= 0; i--) {
            long mult = (long)Math.pow(36, num.size()-1 - i);
            if (Character.isDigit(num.get(i))) {
                res += mult*Integer.parseInt("" + num.get(i));
            } else {
                res += mult*((int)num.get(i) - 55);
            }
        }
        return res;
    }
    static <T> ArrayList<ArrayList<T>> perms(ArrayList<T> list) {
        if (list.size() == 0) {
            ArrayList<ArrayList<T>> result = new ArrayList<ArrayList<T>>();
            result.add(new ArrayList<T>());
            return result;
        }

        ArrayList<ArrayList<T>> returnMe = new ArrayList<ArrayList<T>>();

        T firstElement = list.remove(0);

        ArrayList<ArrayList<T>> recursiveReturn = perms(list);
        for (ArrayList<T> li : recursiveReturn) {
            for (int index = 0; index <= li.size(); index++) {
                ArrayList<T> temp = new ArrayList<T>(li);
                temp.add(index, firstElement);
                returnMe.add(temp);
            }
        }
        return returnMe;
    }
}