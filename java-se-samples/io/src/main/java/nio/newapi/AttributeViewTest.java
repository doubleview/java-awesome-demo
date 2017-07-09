package nio.newapi;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Date;
import java.util.List;

public class AttributeViewTest {
    public static void main(String[] args) throws Exception {
        // ��ȡ��Ҫ�������ļ�
        Path testPath = Paths.get("props.txt");
        // ��ȡ���ʻ������Ե�BasicFileAttributeView
        BasicFileAttributeView basicView = Files.getFileAttributeView(
                testPath, BasicFileAttributeView.class);
        // ��ȡ���ʻ������Ե�BasicFileAttributes
        BasicFileAttributes basicAttribs = basicView.readAttributes();
        // �����ļ��Ļ�������
        System.out.println("����ʱ�䣺" + new Date(basicAttribs
                .creationTime().toMillis()));
        System.out.println("������ʱ�䣺" + new Date(basicAttribs
                .lastAccessTime().toMillis()));
        System.out.println("����޸�ʱ�䣺" + new Date(basicAttribs
                .lastModifiedTime().toMillis()));
        System.out.println("�ļ���С��" + basicAttribs.size());
        // ��ȡ�����ļ�������Ϣ��FileOwnerAttributeView
        FileOwnerAttributeView ownerView = Files.getFileAttributeView(
                testPath, FileOwnerAttributeView.class);
        // ��ȡ���ļ��������û�
        System.out.println(ownerView.getOwner());
        // ��ȡϵͳ��guest��Ӧ���û�
        UserPrincipal user = FileSystems.getDefault().
                getUserPrincipalLookupService().lookupPrincipalByName("guest");
        // �޸��û�
        ownerView.setOwner(user);
        // ��ȡ�����Զ������Ե�FileOwnerAttributeView
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(
                testPath, UserDefinedFileAttributeView.class);
        List<String> attrNames = userView.list();
        // �������е��Զ�������
        for (String name : attrNames) {
            ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
            userView.read(name, buf);
            buf.flip();
            String value = Charset.defaultCharset().decode(buf).toString();
            System.out.println(name + "--->" + value);
        }
        // ���һ���Զ�������
        userView.write("������", Charset.defaultCharset().encode("Java����"));
        // ��ȡ����DOS���Ե�DosFileAttributeView
        DosFileAttributeView dosView = Files.getFileAttributeView(testPath
                , DosFileAttributeView.class);
        // ���ļ��������ء�ֻ��
        dosView.setHidden(true);
        dosView.setReadOnly(true);
    }
}