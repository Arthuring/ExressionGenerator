import java.util.Random;

public class MainClass {
    private static int maxLevel = 1;//最大递归深度，在本次作业中设定为1，实现了括号嵌套的读者可以尝试更大的参数；
    private static int minTerm = 1, maxTerm = 5;//：表达式的项的个数范围；
    private static int minFactor = 1, maxFactor = 5;//：项的因子个数范围；
    private static int maxPow = 8;//最大幂次限制；
    private static int allowWhite = 0;//：是否允许出现空白字符；
    public static void main(String[] argc){

    }
    public static String getWhite(){
        Random r = new Random(1);
        String ans = "";
        if(allowWhite == 1) {
            int cnt = r.nextInt()%3;
            for(int i = 1; i <= cnt; i++) {
                if(r.nextInt()%2 == 1) {
                    ans += "\t";
                } else {
                    ans += " ";
                }
            }
        }
        return ans;
    }

   public static String getNum(int type, int neg) {
        Random r = new Random(0);
        // type 0 small, 1 big, 2 both
        String ans = "";
        if(neg > 0) {
            //add +-
            int addOrSub = r.nextInt()%3;
            if(addOrSub == 0){
                ans += "+";
            }else if(addOrSub == 1){
                ans += "-";
            }
        }
        int cnt0 = r.nextInt()%4;
        for(int i = 1; i <= cnt0; i++) {
            ans += "0";
        }

        if(type == 0) {
            ans += r.nextInt()%100;
        } else if(type == 1) {
            ans += r.nextInt()%1000000+1000000;
        } else {
            ans += r.nextInt()%10000000;
        }

        return ans;
    }

    public static  String getPow(int exp) {
        Random r = new Random(1);
        String ans = "x";
        if(exp == 1) {
            int ifExp = r.nextInt()%2;
            if(ifExp == 0){
                ans += "**1";
            }
            ans+=getWhite();
            return ans;// 幂次为1时随机省略指数
        }// 空白项、指数符号、空白项
        ans+=getWhite();
        ans+="**";
        ans+=getWhite();
// 随机加上+
        int ifAdd =  r.nextInt()%2;
        if(ifAdd == 1){
            ans+="+";
        }
        ans += exp;
        return ans;
    }

   public static String getExpr(int leftLevel, int termNum, int pow) {
        Random r = new Random(1);
        String ans = "";
            // 空白项
       ans+=getWhite();
        for (int i = 1; i <= termNum; i++) {
            if(i > 1 || r.nextInt()%2 == 1) {
            // 项前±
            ans += "+";
            }
            ans += getWhite();
            ans += getTerm(leftLevel, rd(minFactor, maxFactor), pow);
            ans += getWhite();
        }
        return ans;
    }

   public static String getTerm(int leftLevel, int factorNum, int pow) {
        String ans = "";
        Random r = new Random(1);

        // 随机加上项前正负号
       if(r.nextInt()%3 == 0){
           ans += "+";
       }else if(r.nextInt()%2 == 1) {
           ans += "-";
       }
        ans += getWhite();
        for(int i = 1; i <= factorNum; i++) {
            if(i > 1) {
                // 加上*和空白项
                ans += getWhite();
                ans += "*";
            }
            // 随机因子的种类
            if(r.nextInt(3)== 1 && leftLevel > 0) { // 表达式因子
// 随机表达式因子的最高次幂
                ans += "(" + getExpr(leftLevel - 1, r.nextInt (minTerm, maxTerm), nxtPow) + ")";
            } else if(r.nextInt(2) == 1) { // 幂函数因子
// 随机幂函数因子的最高次幂
                ans += getPow(nxtPow);
            } else { // 常数因子
                ans += getNum(2, 1);
            }
        }
        return ans;
    }

}
