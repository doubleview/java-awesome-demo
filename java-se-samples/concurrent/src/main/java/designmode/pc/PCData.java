package designmode.pc;


/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class PCData {
    private final int intData;


    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String id) {
        intData = Integer.valueOf(id);
    }

    public int getData() {
        return intData;
    }

    public String toString() {
        return "data" + intData;
    }
}
