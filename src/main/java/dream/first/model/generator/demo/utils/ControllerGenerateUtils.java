package dream.first.model.generator.demo.utils;

import java.io.File;

import org.yelong.core.model.Modelable;
import org.yelong.core.model.manage.ModelAndTable;
import org.yelong.core.model.pojo.POJOModelResolver;
import org.yelong.core.model.pojo.annotation.AnnotationFieldResolver;
import org.yelong.core.model.pojo.annotation.AnnotationPOJOModelResolver;
import org.yelong.core.model.support.generator.GModelAndTable;
import org.yelong.core.model.support.generator.GWrapperModelAndTableWrapper;

import dream.first.extjs.model.support.generator.controller.ControllerGenerator;
import dream.first.extjs.model.support.generator.controller.impl.defaults.DefaultControllerGenerator;

public class ControllerGenerateUtils {

	public static ControllerGenerator defaultControllerGenerator = new DefaultControllerGenerator();

	public static POJOModelResolver modelResolver = new AnnotationPOJOModelResolver();

	static {
		modelResolver.registerFieldResovler(new AnnotationFieldResolver());
		defaultControllerGenerator.addModelAndTableInterceptor(x -> {
			x.setSuperClassName("dream.first.extjs.controller.BaseExtJSCrudModelController");
			x.setSuperClassSimpleName("BaseExtJSCrudModelController<" + x.getModelClassSimpleName() + ">");
			return x;
		});
	}

	public static void generateDefault(GModelAndTable modelAndTable, File controllerFile) throws Exception {
		defaultControllerGenerator.generate(modelAndTable, controllerFile);
	}

	public static void generateDefault(Class<? extends Modelable> modelClass, String filePath) throws Exception {
		ModelAndTable modelAndTable = modelResolver.resolve(modelClass);
		String modelClassSimpleName = modelAndTable.getModelClass().getSimpleName();
		File controllerFile = new File(filePath, modelClassSimpleName + "Controller.java");
		System.out.println("正在生成" + controllerFile.getName());
		generateDefault(new GWrapperModelAndTableWrapper(modelAndTable), controllerFile);
		System.out.println("生成完成！");
	}

}
