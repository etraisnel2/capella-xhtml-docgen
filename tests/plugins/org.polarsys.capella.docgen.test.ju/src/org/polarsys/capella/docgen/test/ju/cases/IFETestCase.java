package org.polarsys.capella.docgen.test.ju.cases;

import java.util.Collection;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.polarsys.capella.docgen.test.ju.reporter.CapellaDocGenHtmlDomainElementReporter;

@RunWith(Parameterized.class)
public class IFETestCase extends AbstractCapellaDocGenTest {

	private static String NAME = "IFE";

	@Override
	public String getProjectName() {
		return NAME;
	}

	@Override
	protected String getModelName() {
		return NAME;
	}

	@Parameters(name = "IFE - {0}")
	public static Collection<Object[]> data() {
		Path path = new Path(
				"/model/" + NAME + "/" + NAME + "." + CapellaDocGenHtmlDomainElementReporter.TEST_RESULTS_FILE_EXTENSION);
		return getTestParameters(path);
	}

	@Parameter
	public static String elementID;

	@Parameter(1)
	public static String expectedContent;

	@Override
	@Test
	public void test() throws Exception {
		String generatedContent = getReporter().getTestResults().get(elementID);
		assertNotNull("Expected object " + elementID + " cannot be found", generatedContent);
		String gifIconGeneratedContent = generatedContent.replaceAll("img src=\"(../)?../icon/(.*?).gif\"", "img src=\"$1../icon/$2.png\"");
		assertEquals("Object " + elementID + " does not match", expectedContent, gifIconGeneratedContent);
	}

	@Override
	protected void tearDown() throws Exception {
//		super.tearDown();
	}
}