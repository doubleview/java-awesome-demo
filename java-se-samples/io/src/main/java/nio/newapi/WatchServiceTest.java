package nio.newapi;

import java.nio.file.*;

public class WatchServiceTest {
    public static void main(String[] args) throws Exception {
        // ��ȡ�ļ�ϵͳ��WatchService����
        WatchService watchService = FileSystems.getDefault().newWatchService();
        // Ϊf:�̸�·��ע�����
        Paths.get("F:/").register(watchService
                , StandardWatchEventKinds.ENTRY_CREATE
                , StandardWatchEventKinds.ENTRY_MODIFY
                , StandardWatchEventKinds.ENTRY_DELETE);
        while (true) {
            // ��ȡ��һ���ļ��Ķ��¼�
            WatchKey key = watchService.take();    //��
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(event.context() + " �ļ������� " + event.kind() + "�¼���");
            }
            // ����WatchKey
            boolean valid = key.reset();
            // �������ʧ�ܣ��˳�����
            if (!valid) {
                break;
            }
        }
    }
}
