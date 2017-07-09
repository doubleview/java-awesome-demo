package objects2.java8interface;

public class PrintCommand implements Command {
    public void process(int[] target) {
        for (int tmp : target) {
            System.out.println("迭代输出目标数组的元素:" + tmp);
        }
    }
}

