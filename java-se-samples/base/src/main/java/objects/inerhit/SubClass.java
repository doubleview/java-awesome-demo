package objects.inerhit;

class BaseClass {
    public int a = 5;
}

public class SubClass extends BaseClass {
    public int a = 7;

    public void accessOwner() {
        System.out.println(a);
    }

    public void accessBase() {
        // 閫氳繃super鏉ラ檺瀹氳闂粠鐖剁被缁ф壙寰楀埌鐨刟瀹炰緥鍙橀噺
        System.out.println(super.a);
    }

    public static void main(String[] args) {
        SubClass sc = new SubClass();
        sc.accessOwner(); // 杈撳嚭7
        sc.accessBase(); // 杈撳嚭5
    }
}
