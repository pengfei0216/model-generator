package dream.first.model.generator.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.yelong.core.model.support.generator.GFieldAndColumnInterceptor;
import org.yelong.core.model.support.generator.GFieldAndColumnWrapper;
import org.yelong.core.model.support.generator.GModelAndTable;

import dream.first.extjs.model.support.generator.js.ExtJSGenerator;
import dream.first.extjs.model.support.generator.js.impl.defaults.v1.DefaultExtJSGenerator_v1;
import dream.first.extjs.model.support.generator.js.impl.defaults.v2.DefaultExtJSGenerator_v2;
import dream.first.extjs.model.support.generator.js.impl.file.DefaultFileModelExtJSGenerator;

public final class ExtJSGenerateUtils {

	public static ExtJSGenerator extjsGenerator = new DefaultExtJSGenerator_v1();

	public static ExtJSGenerator extjsGenerator2 = new DefaultExtJSGenerator_v2();

	public static ExtJSGenerator extjsGenerator_file = new DefaultFileModelExtJSGenerator();

	private static final List<String> IGNORE_FIELD_NAME = new ArrayList<>();

	private ExtJSGenerateUtils() {
	}

	static {
		IGNORE_FIELD_NAME.add("creator");
		IGNORE_FIELD_NAME.add("createTime");
		IGNORE_FIELD_NAME.add("updator");
		IGNORE_FIELD_NAME.add("updateTime");
		IGNORE_FIELD_NAME.add("state");
		GFieldAndColumnInterceptor fieldAndColumnInterceptor = x -> {
			if (x.getFieldName().equals("id")) {
				return new GFieldAndColumnWrapper(x) {

					public boolean isPrimaryKey() {
						return true;
					};

				};
			}
			// 忽略列
			return IGNORE_FIELD_NAME.contains(x.getFieldName()) ? null : x;
		};
		extjsGenerator.addFieldAndColumnInterceptor(fieldAndColumnInterceptor);
		extjsGenerator2.addFieldAndColumnInterceptor(fieldAndColumnInterceptor);
		extjsGenerator_file.addFieldAndColumnInterceptor(fieldAndColumnInterceptor);
	}

	public static void generate_1(GModelAndTable modelAndTable, File jsFile) throws Exception {
		extjsGenerator.generate(modelAndTable, jsFile);
	}

	public static void generate_2(GModelAndTable modelAndTable, File jsFile) throws Exception {
		extjsGenerator2.generate(modelAndTable, jsFile);
	}

	public static void generateFile(GModelAndTable modelAndTable, File jsFile) throws Exception {
		extjsGenerator_file.generate(modelAndTable, jsFile);
	}

}
