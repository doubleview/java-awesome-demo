package part2;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;

public class PooledTest {

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        byte[] data = new byte[1024];
        for (int i = 0; i < 8000000; i++) {
            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            byteBuf.writeBytes(data);
            byteBuf.release();
        }
        System.out.println(StrUtil.format("consume : {} ms ", System.currentTimeMillis() - now));
    }
}
