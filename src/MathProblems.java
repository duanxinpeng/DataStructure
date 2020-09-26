import java.util.HashMap;
import java.util.Objects;

public class MathProblems {
    //
    static class Pair {
        int n, m;

        public Pair(int n, int m) {
            this.n = n;
            this.m = m;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return n == pair.n &&
                    m == pair.m;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n, m);
        }
    }

    static HashMap<Pair, Integer> map = new HashMap<>();

    public static int comb(int n, int m) {
        /**
         * 利用杨辉三角形计算组合数，同时利用HashMap进行优化
         * C(n,m)=C(n,n-m)=C(n-1,m-1)+C(n-1,m)
         */
        if (m == 0)
            return 1;
        if (n == m)
            return 1;
        Pair tmp = new Pair(n, m);
        if (map.containsKey(tmp))
            return map.get(tmp);
        int num1 = comb(n - 1, m - 1);
        int num2 = comb(n-1, m);
        map.put(tmp, num1 + num2);
        return map.get(tmp);
    }

    public static void main(String[] args) {
        System.out.println(comb(20,11));
    }
}
