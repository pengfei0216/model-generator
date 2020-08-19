/**
 * 
 */
package dream.first.demo.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.yelong.commons.io.FileUtilsE;
import org.yelong.core.model.support.generator.GModelAndTable;
import org.yelong.core.model.support.generator.ModelGenerateException;
import org.yelong.core.model.support.generator.pdm.PDMResolver;
import org.yelong.core.model.support.generator.pdm.PDMResolverException;
import org.yelong.core.model.support.generator.pdm.defaults.DefaultPDMResolver;

import dream.first.extjs.model.support.generator.js.ExtjsGenerateException;
import dream.first.extjs.model.support.generator.jsp.JSPGenerateException;

/**
 * @author PF
 *
 */
public class CompleteModelGenerateUtils {

	public static PDMResolver pdmResolver = new DefaultPDMResolver();

	public static void generatePOJO(String pdmPath, String saveDir) throws PDMResolverException, ModelGenerateException,
			IOException, ExtjsGenerateException, JSPGenerateException {
		generate(pdmPath, saveDir, null, false);
	}

	public static void generatePOJO(String pdmPath, String saveDir, boolean differentiateDir)
			throws PDMResolverException, ModelGenerateException, IOException, ExtjsGenerateException,
			JSPGenerateException {
		generate(pdmPath, saveDir, null, differentiateDir);
	}

	public static void generateMap(String pdmPath, String saveDir) throws PDMResolverException, ModelGenerateException,
			IOException, ExtjsGenerateException, JSPGenerateException {
		generate(pdmPath, saveDir, "map", false);
	}

	public static void generateMap(String pdmPath, String saveDir, boolean differentiateDir)
			throws PDMResolverException, ModelGenerateException, IOException, ExtjsGenerateException,
			JSPGenerateException {
		generate(pdmPath, saveDir, "map", differentiateDir);
	}

	public static void generate(String pdmPath, String saveDir, String modelType, boolean differentiateDir)
			throws PDMResolverException, ModelGenerateException, IOException, ExtjsGenerateException,
			JSPGenerateException {
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
			String modelFileName = modelClassSimpleName + ".java";
			System.out.println("正在生成" + modelFileName);
			if ("map".equals(modelType)) {
				ModelGenerateUtils.generateMapModel(modelAndTable,
						FileUtilsE.createNewFileOverride(modelDir, modelFileName));
			} else {
				ModelGenerateUtils.generatePOJOModel(modelAndTable,
						FileUtilsE.createNewFileOverride(modelDir, modelFileName));
			}
			String extjsFileName = modelNamePrefixLowerCase + "Manage.js";
			System.out.println("正在生成" + extjsFileName);
			ExtjsGenerateUtils.generate_2(modelAndTable, FileUtilsE.createNewFileOverride(modelDir, extjsFileName));
			String jspFileName = modelNamePrefixLowerCase + "Manage.jsp";
			System.out.println("正在生成" + jspFileName);
			JSPGenerateUtils.generate(modelAndTable, FileUtilsE.createNewFileOverride(modelDir, jspFileName));
			System.out.println("----------------------------------------");
		}
		System.out.println("生成完成！");
	}

}
