/**
 * 
 */
package dream.first.model.generator.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.yelong.commons.io.FileUtilsE;
import org.yelong.core.model.pojo.POJOModelResolver;
import org.yelong.core.model.pojo.annotation.AnnotationFieldResolver;
import org.yelong.core.model.pojo.annotation.AnnotationPOJOModelResolver;
import org.yelong.core.model.support.generator.GFieldAndColumnInterceptor;
import org.yelong.core.model.support.generator.GModelAndTable;
import org.yelong.core.model.support.generator.ModelGenerator;
import org.yelong.core.model.support.generator.impl.map.DefaultMapModelGenerator;
import org.yelong.core.model.support.generator.impl.pojo.DefaultPOJOModelGenerator;
import org.yelong.core.model.support.generator.pdm.PDMResolver;
import org.yelong.core.model.support.generator.pdm.defaults.DefaultPDMResolver;

/**
 * @author PF
 *
 */
public class ModelGenerateUtils {

	public static ModelGenerator pojoModelGenerator = new DefaultPOJOModelGenerator();

	public static ModelGenerator mapModelGenerator = new DefaultMapModelGenerator();

	private static final List<String> IGNORE_FIELD_NAME = new ArrayList<>();

	public static POJOModelResolver modelResolver = new AnnotationPOJOModelResolver();

	public static PDMResolver pdmResolver = new DefaultPDMResolver();

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
		pojoModelGenerator.addFieldAndColumnInterceptor(fieldAndColumnInterceptor);
		mapModelGenerator.addFieldAndColumnInterceptor(fieldAndColumnInterceptor);

		pojoModelGenerator.addModelAndTableInterceptor(x -> {
			x.setSuperClassName("dream.first.core.model.BaseModel");
			x.setSuperClassSimpleName("BaseModel<" + x.getModelClassSimpleName() + ">");
			return x;
		});
		mapModelGenerator.addModelAndTableInterceptor(x -> {
			x.setSuperClassName("dream.first.core.model.map.BaseMapModel");
			x.setSuperClassSimpleName("BaseMapModel");
			return x;
		});
	}

	private ModelGenerateUtils() {
	}

	public static void generatePOJOModel(GModelAndTable modelAndTable, File modelFile) throws Exception {
		pojoModelGenerator.generate(modelAndTable, modelFile);
	}

	public static void generateMapModel(GModelAndTable modelAndTable, File modelFile) throws Exception {
		mapModelGenerator.generate(modelAndTable, modelFile);
	}

	public static void generatePOJOModel(String pdmPath, String saveDir) throws Exception {
		generateModel(pdmPath, saveDir, null);
	}

	public static void generateMapModel(String pdmPath, String saveDir) throws Exception {
		generateModel(pdmPath, saveDir, "map");
	}

	private static void generateModel(String pdmPath, String saveDir, String modelType) throws Exception {
		FileUtilsE.requireNonExist(pdmPath);
		File pdmFile = FileUtilsE.getFile(pdmPath);
		System.out.println("正在解析" + pdmFile.getName() + "...");
		List<GModelAndTable> gModelAndTables = pdmResolver.resolve(pdmFile);
		if (CollectionUtils.isEmpty(gModelAndTables)) {
			System.out.println("未发现模型结构...");
			return;
		}
		for (GModelAndTable modelAndTable : gModelAndTables) {
			String modelDir = saveDir;
			String modelClassSimpleName = modelAndTable.getModelClassSimpleName();
			System.out.println("----------" + modelAndTable.getModelClassSimpleName() + "----------");
			// model.java
			String modelFileName = modelClassSimpleName + ".java";
			System.out.println("正在生成" + modelFileName);
			if ("map".equals(modelType)) {
				ModelGenerateUtils.generateMapModel(modelAndTable,
						FileUtilsE.createNewFileOverride(modelDir, modelFileName));
			} else {
				ModelGenerateUtils.generatePOJOModel(modelAndTable,
						FileUtilsE.createNewFileOverride(modelDir, modelFileName));
			}
			System.out.println("----------------------------------------");
		}
		System.out.println("生成完成！");
	}

}
