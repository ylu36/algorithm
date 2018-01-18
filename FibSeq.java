import java.util.Arrays;

public class FibSeq{
     static int idx = 2;
     static String[] res;
     static String[] f;

     // check if num is prime
     public static boolean isPrime(long num) {
        for(long i = 2; i * i <= num; i ++) {
            if(num % i == 0) 
                return false;
            }
        return true;
     }

     // arithmetic addition using strings to avoid number overflow
     public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--){
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            sb.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }      
        return sb.reverse().toString();
    }
    
    //generate a fib sequence string with seed_idx fib numbers
    public static String seed(int seed_idx) {
        
        String seed = new String();

        for(int i = 2; i < seed_idx; i ++) {
            f[idx] = addStrings(f[i-2], f[i-1]);
            seed += f[idx];
            idx ++;
        }
        return seed;
    }

    //find the two prime 10-digit fib subsequence
    public static void find(String seed) {
        int cnt = 0;
        
        for(int j = 10; j+10 <= seed.length(); j ++) {

            String sub = seed.substring(j-10, j); 
            if(isPrime(Long.valueOf(sub))) {                
                cnt ++;
                if(cnt == 44722) {
                    res[0] = sub;
                    System.out.println(res[0]);
                }
                if(cnt == 53215) {
                    res[1] = sub;
                    System.out.println(res[1]);
                }
            }       
        } 
    }
     
    public static String check(String a) {
        char[] arr = new char[5];
        for(int i = 0; i < 5; i ++) {
            String sub = a.substring(i*2, 2*i+2);
            arr[i] = (char)(Integer.valueOf(sub)%26 + 'a');
        }
        return Arrays.toString(arr);
    }

    public static void main(String []args) {
        res = new String[2];
        int seed_idx = 4000;   //a seed 4000 ensures > 53215 suitable subsequences are found
        f = new String[seed_idx];
        f[0] = "1";
        f[1] = "1";         
        String s = seed(seed_idx);
        find(s);
        //found the resulting subsequence number to be "9046450429" and "9952635697"
        System.out.println(check(res[0]));
        System.out.println(check(res[1]));
     }
}