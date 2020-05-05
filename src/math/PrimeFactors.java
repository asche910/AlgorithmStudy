package math;

/**
 * 求正整数N(N>1)的质因数的个数。 相同的质因数需要重复计算。如120=2*2*2*3*5，共有5个质因数。
 *
 * @see <a href="https://www.nowcoder.com/questionTerminal/20426b85f7fc4ba8b0844cc04807fbd9">Here</a>
 */
public class PrimeFactors {
    public static void main(String[] args) {
        System.out.println(getPrimeFactors(240));
    }

    public static int getPrimeFactors(int n){
        int count = 0;
        for (int j = 2; j * j <= n; j++) {
            while (n % j == 0) {
                System.out.println(j + " " + n);

                n /= j;
                count++;
            }
        }
        if (n > 1) count++;
        return count;
    }
}
