/**
 * jicheng
 * <h5>描述</h5>
 **/
public class jicheng {
    public static void main(String[] args) {
        test(4);
    }

    public static void test(int n){
        if(n > 2){
            test(n - 1);
        }
        System.out.println("n = "+n);
    }

}
