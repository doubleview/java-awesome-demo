package hibernatetest;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
// ָ���ɴ���@Persistent��@Id��@Property����Annotation
@SupportedAnnotationTypes({"Persistent", "Id", "Property"})
public class HibernateAnnotationProcessor
        extends AbstractProcessor {
    // ѭ������ÿ����Ҫ����ĳ������
    public boolean process(Set<? extends TypeElement> annotations
            , RoundEnvironment roundEnv) {
        // ����һ���ļ���������������ɶ�����ļ�
        PrintStream ps = null;
        try {
            // ����ÿ����@Persistent���ε�class�ļ�
            for (Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)) {
                // ��ȡ���ڴ��������
                Name clazzName = t.getSimpleName();
                // ��ȡ�ඨ��ǰ��@Persistent Annotation
                Persistent per = t.getAnnotation(Persistent.class);
                // �����ļ������
                ps = new PrintStream(new FileOutputStream(clazzName
                        + ".hbm.xml"));
                // ִ�����
                ps.println("<?xml version=\"1.0\"?>");
                ps.println("<!DOCTYPE hibernate-mapping PUBLIC");
                ps.println("	\"-//Hibernate/Hibernate "
                        + "Mapping DTD 3.0//EN\"");
                ps.println("	\"http://www.hibernate.org/dtd/"
                        + "hibernate-mapping-3.0.dtd\">");
                ps.println("<hibernate-mapping>");
                ps.print("	<class name=\"" + t);
                // ���per��table()��ֵ
                ps.println("\" table=\"" + per.table() + "\">");
                for (Element f : t.getEnclosedElements()) {
                    // ֻ�����Ա�����ϵ�Annotation
                    if (f.getKind() == ElementKind.FIELD)   // ��
                    {
                        // ��ȡ��Ա��������ǰ��@Id Annotation
                        Id id = f.getAnnotation(Id.class);      // ��
                        // ��@Id Annotation����ʱ���<id.../>Ԫ��
                        if (id != null) {
                            ps.println("		<id name=\""
                                    + f.getSimpleName()
                                    + "\" column=\"" + id.column()
                                    + "\" type=\"" + id.type()
                                    + "\">");
                            ps.println("		<generator class=\""
                                    + id.generator() + "\"/>");
                            ps.println("		</id>");
                        }
                        // ��ȡ��Ա��������ǰ��@Property Annotation
                        Property p = f.getAnnotation(Property.class);  // ��
                        // ��@Property Annotation����ʱ���<property.../>Ԫ��
                        if (p != null) {
                            ps.println("		<property name=\""
                                    + f.getSimpleName()
                                    + "\" column=\"" + p.column()
                                    + "\" type=\"" + p.type()
                                    + "\"/>");
                        }
                    }
                }
                ps.println("	</class>");
                ps.println("</hibernate-mapping>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return true;
    }
}
