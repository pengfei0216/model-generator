package dream.first.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.yelong.core.model.support.generator.GFieldAndColumnInterceptor;
import org.yelong.core.model.support.generator.GFieldAndColumnWrapper;
import org.yelong.core.model.support.generator.GModelAndTable;

import dream.first.extjs.model.support.generator.js.ExtjsGenerateException;
import dream.first.extjs.model.support.generator.js.ExtjsGenerator;
import dream.first.extjs.model.support.generator.js.impl.defaults.DefaultExtjsGenerator;
import dream.first.extjs.model.support.generator.js.impl.defaults._2.DefaultExtjsGenerator2;

public final class ExtjsGenerateUtils {

	public static ExtjsGenerator extjsGenerator = new DefaultExtjsGenerator();

	public static ExtjsGenerator extjsGenerator2 = new DefaultExtjsGenerator2();

	private static final List<String> IGNORE_FIELD_NAME = new ArrayList<>();

	private ExtjsGenerateUtils() {
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
	}

	public static void generate_1(GModelAndTable modelAndTable, File jsFile) throws ExtjsGenerateException {
		extjsGenerator.generate(modelAndTable, jsFile);
	}

	public static void generate_2(GModelAndTable modelAndTable, File jsFile) throws ExtjsGenerateException {
		extjsGenerator2.generate(modelAndTable, jsFile);
	}

}
