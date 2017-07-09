package xslt;

import java.io.File;
import java.io.FileInputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

public class TestXSLT {

	@Test
	public void testTransform() throws Exception{
		String xsltString = "C:/CCRC_VIEW/e615406_maint.emea.2.5b/inter_fsgen/data/fsgen/templates/InDesign/US/" +
				"FS_US_PF_EQ_INTL_1.xsl";
		String xmlString = "C:/temp/xml/ExtractorRestfulServiceTest_result.xml";
		String resultString = "C:/temp/xml/result.xml";


		TransformerFactory trFactory = TransformerFactory.newInstance();
		System.out.println(trFactory.getClass().getName());
		Transformer transformer = trFactory.newTransformer(new StreamSource(new FileInputStream(xsltString)));
		transformer.transform(new StreamSource(new File(xmlString)), new StreamResult(new File(resultString)));
		System.out.println("complete");
	}
}
