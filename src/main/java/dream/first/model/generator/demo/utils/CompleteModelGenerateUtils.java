/**
 * 
 */
package dream.first.model.generator.demo.utils;

import java.io.File;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.yelong.commons.io.FileUtilsE;
import org.yelong.core.model.support.generator.GModelAndTable;
import org.yelong.core.model.support.generator.pdm.PDMResolver;
import org.yelong.core.model.support.generator.pdm.defaults.DefaultPDMResolver;

/**
 * @author PF
 *
 */
public class CompleteModelGenerateUtils {

	public static PDMResolver pdmResolver = new DefaultPDMResolver();

	public static void generatePOJO(String pdmPath, String saveDir) throws Exception {
		generate(pdmPath, saveDir, null, false);
	}

	public static void generatePOJO(String pdmPath, String saveDir, boolean differentiateDir) throws Exception {
		generate(pdmPath, saveDir, null, differentiateDir);
	}

	public static void generateMap(String pdmPath, String saveDir) throws Exception {
		generate(pdmPath, saveDir, "map", false);
	}

	public static void generateMap(String pdmPath, String saveDir, boolean differentiateDir) throws Exception {
		generate(pdmPath, saveDir, "map", differentiateDir);
	}

	public static void generateFile(String pdmPath, String saveDir) throws Exception {
		generate(pdmPath, saveDir, "file", false);
	}

	public static void generateFile(String pdmPath, String saveDir, boolean differentiateDir) throws Exception {
		generate(pdmPath, saveDir, "file", differentiateDir);
	}

	public static void generate(String pdmPath, String saveDir, String modelType, boolean differentiateDir)
			throws Exception {
		FileUtilsE.requireNonExist(pdmPath);
		File pdmFile = FileUtilsE.getFile(pdmPath);
		System.out.println("正在解析" + pdmFile.getName() + "...");
		List<GModelAndTable> gModelAndTables = pdmResolver.resolve(pdmFile);
		if (CollectionUtils.isEmpty(gModelAndTables)) {
			System.out.println("未发现模型结构...");
			return;
		}
		for (GModelAndTable modelAndTable : gModelAndTables) {
			String modelDir = differentiateDir ? saveDir + File.separator + modelAndTable.getModelClassSimpleName()
					: saveDir;
			String modelClassSimpleName = modelAndTable.getModelClassSimpleName();
			String modelNamePrefixLowerCase = modelClassSimpleName.substring(0, 1).toLowerCase()
					+ modelClassSimpleName.substring(1);
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
			// controller.java
			String controllerFileName = modelClassSimpleName + "Controller.java";
			System.out.println("正在生成" + controllerFileName);
			ControllerGenerateUtils.generateDefault(modelAndTable,
					FileUtilsE.createNewFileOverride(modelDir, controllerFileName));
			// js
			String extjsFileName = modelNamePrefixLowerCase + "Manage.js";
			System.out.println("正在生成" + extjsFileName);
			if ("file".equals(modelType)) {
				ExtJSGenerateUtils.generateFile(modelAndTable,
						FileUtilsE.createNewFileOverride(modelDir, extjsFileName));
			} else {
				ExtJSGenerateUtils.generate_2(modelAndTable, FileUtilsE.createNewFileOverride(modelDir, extjsFileName));
			}
			// jsp
			String jspFileName = modelNamePrefixLowerCase + "Manage.jsp";
			System.out.println("正在生成" + jspFileName);
			JSPGenerateUtils.generate(modelAndTable, FileUtilsE.createNewFileOverride(modelDir, jspFileName));

			// detail jsp
			String detailJspFileName = modelNamePrefixLowerCase + "Detail.jsp";
			System.out.println("正在生成" + detailJspFileName);
			JSPGenerateUtils.generateDetail(modelAndTable,
					FileUtilsE.createNewFileOverride(modelDir, detailJspFileName));
			System.out.println("----------------------------------------");
		}
		System.out.println("生成完成！");
	}

}
