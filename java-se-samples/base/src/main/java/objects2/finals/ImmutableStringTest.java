package objects2.finals;


public class ImmutableStringTest {
    public static void main(String[] args) {
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        System.out.println(str1 == str2); // 杈撳嚭false
        System.out.println(str1.equals(str2)); // 杈撳嚭true
        // 涓嬮潰涓ゆ杈撳嚭鐨刪ashCode鐩稿悓
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
    }
}
