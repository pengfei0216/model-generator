/**
 * 
 */
package dream.first.model.generator.demo.utils;

import java.io.File;

import org.yelong.core.model.Modelable;
import org.yelong.core.model.manage.ModelAndTable;
import org.yelong.core.model.pojo.POJOModelResolver;
import org.yelong.core.model.pojo.annotation.AnnotationFieldResolver;
import org.yelong.core.model.pojo.annotation.AnnotationPOJOModelResolver;
import org.yelong.core.model.support.generator.GWrapperModelAndTableWrapper;

/**
 * @author PengFei
 */
public class CodeGenerateUtils {

	public static POJOModelResolver modelResolver = new AnnotationPOJOModelResolver();

	static {
		modelResolver.registerFieldResovler(new AnnotationFieldResolver());
	}

	public static final void generate_1(Class<? extends Modelable> modelClass, String codePath)
			throws Exception {
		ModelAndTable modelAndTable = modelResolver.resolve(modelClass);
		String modelClassSimpleName = modelAndTable.getModelClass().getSimpleName();
		String modelNamePrefixLowerCase = modelClassSimpleName.substring(0, 1).toLowerCase()
				+ modelClassSimpleName.substring(1);
		File jsFile = new File(codePath, modelNamePrefixLowerCase + "Manage.js");
		System.out.println("正在生成" + jsFile.getName());
		ExtJSGenerateUtils.generate_1(new GWrapperModelAndTableWrapper(modelAndTable), jsFile);
		File jspFile = new File(codePath, modelNamePrefixLowerCase + "Manage.jsp");
		System.out.println("正在生成" + jspFile.getName());
		JSPGenerateUtils.generate(new GWrapperModelAndTableWrapper(modelAndTable), jspFile);
		System.out.println("生成完成！");
	}

	public static final void generate_2(Class<? extends Modelable> modelClass, String codePath)
			throws Exception {
		ModelAndTable modelAndTable = modelResolver.resolve(modelClass);
		String modelClassSimpleName = modelAndTable.getModelClass().getSimpleName();
		String modelNamePrefixLowerCase = modelClassSimpleName.substring(0, 1).toLowerCase()
				+ modelClassSimpleName.substring(1);
		File jsFile = new File(codePath, modelNamePrefixLowerCase + "Manage.js");
		System.out.println("正在生成" + jsFile.getName());
		ExtJSGenerateUtils.generate_2(new GWrapperModelAndTableWrapper(modelAndTable), jsFile);
		File jspFile = new File(codePath, modelNamePrefixLowerCase + "Manage.jsp");
		System.out.println("正在生成" + jspFile.getName());
		JSPGenerateUtils.generate(new GWrapperModelAndTableWrapper(modelAndTable), jspFile);
		System.out.println("生成完成！");
	}

	public static final void generate_file(Class<? extends Modelable> modelClass, String codePath)
			throws Exception {
		ModelAndTable modelAndTable = modelResolver.resolve(modelClass);
		String modelClassSimpleName = modelAndTable.getModelClass().getSimpleName();
		String modelNamePrefixLowerCase = modelClassSimpleName.substring(0, 1).toLowerCase()
				+ modelClassSimpleName.substring(1);
		File jsFile = new File(codePath, modelNamePrefixLowerCase + "Manage.js");
		System.out.println("正在生成" + jsFile.getName());
		ExtJSGenerateUtils.generateFile(new GWrapperModelAndTableWrapper(modelAndTable), jsFile);
		File jspFile = new File(codePath, modelNamePrefixLowerCase + "Manage.jsp");
		System.out.println("正在生成" + jspFile.getName());
		JSPGenerateUtils.generate(new GWrapperModelAndTableWrapper(modelAndTable), jspFile);
		System.out.println("生成完成！");
	}

}
