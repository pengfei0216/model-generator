/**
 * 
 */
package dream.first.v1.utils;

import java.io.File;

import org.yelong.core.model.Model;
import org.yelong.core.model.manage.ModelAndTable;
import org.yelong.core.model.pojo.POJOModelResolver;
import org.yelong.core.model.pojo.annotation.AnnotationFieldResolver;
import org.yelong.core.model.pojo.annotation.AnnotationPOJOModelResolver;
import org.yelong.core.model.support.generator.GWrapperModelAndTableWrapper;

import dream.first.extjs.model.support.generator.js.ExtjsGenerateException;
import dream.first.extjs.model.support.generator.js.ExtjsGenerator;
import dream.first.extjs.model.support.generator.js.impl.defaults.DefaultExtjsGenerator;
import dream.first.extjs.model.support.generator.jsp.JSPGenerateException;
import dream.first.extjs.model.support.generator.jsp.JSPGenerator;
import dream.first.extjs.model.support.generator.jsp.impl.defaults.DefaultJSPGenerator;

/**
 * @author PengFei
 * @date 2020年3月15日下午4:22:56
 * @since 1.0
 */
public class CodeGenerateUtils {

	public static ExtjsGenerator extjsGenerator = new DefaultExtjsGenerator();

	public static JSPGenerator jspGenerator = new DefaultJSPGenerator();

	public static POJOModelResolver modelResolver = new AnnotationPOJOModelResolver();

	static {
		modelResolver.registerFieldResovler(new AnnotationFieldResolver());
	}

	/**
	 * 生成公用的代码：js、jsp
	 * 
	 * @param modelClass
	 * @param codePath
	 * @throws ExtjsGenerateException
	 * @throws JSPGenerateException
	 */
	public static final void generate(Class<? extends Model> modelClass, String codePath)
			throws ExtjsGenerateException, JSPGenerateException {
		ModelAndTable modelAndTable = modelResolver.resolve(modelClass);
		String modelClassSimpleName = modelAndTable.getModelClass().getSimpleName();
		String modelNamePrefixLowerCase = modelClassSimpleName.substring(0, 1).toLowerCase()
				+ modelClassSimpleName.substring(1);
		File jsFile = new File(codePath, modelNamePrefixLowerCase + "Manage.js");
		System.out.println("正在生成" + jsFile.getName());
		extjsGenerator.generate(new GWrapperModelAndTableWrapper(modelAndTable), jsFile);
		File jspFile = new File(codePath, modelNamePrefixLowerCase + "Manage.jsp");
		System.out.println("正在生成" + jspFile.getName());
		jspGenerator.generate(modelClass, jspFile);
		System.out.println("生成完成！");
	}

}
