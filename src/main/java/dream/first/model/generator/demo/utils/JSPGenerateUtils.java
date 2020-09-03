package dream.first.model.generator.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.yelong.core.model.Modelable;
import org.yelong.core.model.manage.ModelAndTable;
import org.yelong.core.model.pojo.POJOModelResolver;
import org.yelong.core.model.pojo.annotation.AnnotationFieldResolver;
import org.yelong.core.model.pojo.annotation.AnnotationPOJOModelResolver;
import org.yelong.core.model.support.generator.GFieldAndColumnInterceptor;
import org.yelong.core.model.support.generator.GModelAndTable;
import org.yelong.core.model.support.generator.GWrapperModelAndTableWrapper;

import dream.first.extjs.model.support.generator.jsp.JSPGenerator;
import dream.first.extjs.model.support.generator.jsp.impl.defaults.DefaultJSPGenerator;
import dream.first.extjs.model.support.generator.jsp.impl.detail.DetailJSPGenerator;

public class JSPGenerateUtils {

	public static JSPGenerator jspGenerator = new DefaultJSPGenerator();

	public static JSPGenerator detailJspGenerator = new DetailJSPGenerator();

	public static POJOModelResolver modelResolver = new AnnotationPOJOModelResolver();

	private static final List<String> IGNORE_FIELD_NAME = new ArrayList<>();

	static {
		modelResolver.registerFieldResovler(new AnnotationFieldResolver());
	}

	static {
		IGNORE_FIELD_NAME.add("id");
		IGNORE_FIELD_NAME.add("creator");
		IGNORE_FIELD_NAME.add("createTime");
		IGNORE_FIELD_NAME.add("updator");
		IGNORE_FIELD_NAME.add("updateTime");
		IGNORE_FIELD_NAME.add("state");
		GFieldAndColumnInterceptor fieldAndColumnInterceptor = x -> {
			return IGNORE_FIELD_NAME.contains(x.getFieldName()) ? null : x;
		};
		detailJspGenerator.addFieldAndColumnInterceptor(fieldAndColumnInterceptor);
	}

	public static void generate(GModelAndTable modelAndTable, File jspFile) throws Exception {
		jspGenerator.generate(modelAndTable, jspFile);
	}

	public static void generateDetail(GModelAndTable modelAndTable, File jspFile) throws Exception {
		detailJspGenerator.generate(modelAndTable, jspFile);
	}

	public static void generateDetail(Class<? extends Modelable> modelClass, String filePath)
			throws Exception {
		ModelAndTable modelAndTable = modelResolver.resolve(modelClass);
		String modelClassSimpleName = modelAndTable.getModelClass().getSimpleName();
		String modelNamePrefixLowerCase = modelClassSimpleName.substring(0, 1).toLowerCase()
				+ modelClassSimpleName.substring(1);
		File controllerFile = new File(filePath, modelNamePrefixLowerCase + "Detail.jsp");
		System.out.println("正在生成" + controllerFile.getName());
		generateDetail(new GWrapperModelAndTableWrapper(modelAndTable), controllerFile);
		System.out.println("生成完成！");
	}

}
