/**
 * 
 */
package dream.first.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.yelong.core.model.support.generator.GFieldAndColumnInterceptor;
import org.yelong.core.model.support.generator.GModelAndTable;
import org.yelong.core.model.support.generator.ModelGenerateException;
import org.yelong.core.model.support.generator.ModelGenerator;
import org.yelong.core.model.support.generator.impl.map.DefaultMapModelGenerator;
import org.yelong.core.model.support.generator.impl.pojo.DefaultPOJOModelGenerator;

/**
 * @author PF
 *
 */
public class ModelGenerateUtils {

	public static ModelGenerator pojoModelGenerator = new DefaultPOJOModelGenerator();

	public static ModelGenerator mapModelGenerator = new DefaultMapModelGenerator();

	private static final List<String> IGNORE_FIELD_NAME = new ArrayList<>();

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
	}

	private ModelGenerateUtils() {
	}

	public static void generatePOJOModel(GModelAndTable modelAndTable, File modelFile) throws ModelGenerateException {
		pojoModelGenerator.generate(modelAndTable, modelFile);
	}

	public static void generateMapModel(GModelAndTable modelAndTable, File modelFile) throws ModelGenerateException {
		mapModelGenerator.generate(modelAndTable, modelFile);
	}

}
