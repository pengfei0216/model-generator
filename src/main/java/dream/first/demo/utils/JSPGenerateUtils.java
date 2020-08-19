package dream.first.demo.utils;

import java.io.File;

import org.yelong.core.model.support.generator.GModelAndTable;

import dream.first.extjs.model.support.generator.jsp.JSPGenerateException;
import dream.first.extjs.model.support.generator.jsp.JSPGenerator;
import dream.first.extjs.model.support.generator.jsp.impl.defaults.DefaultJSPGenerator;

public class JSPGenerateUtils {

	public static JSPGenerator jspGenerator = new DefaultJSPGenerator();

	public static void generate(GModelAndTable modelAndTable, File jspFile) throws JSPGenerateException {
		jspGenerator.generate(modelAndTable, jspFile);

	}

}
