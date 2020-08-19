/**
 * 
 */
package dream.first.demo;

import java.io.IOException;

import org.yelong.core.model.support.generator.ModelGenerateException;
import org.yelong.core.model.support.generator.pdm.PDMResolverException;

import dream.first.demo.utils.CompleteModelGenerateUtils;
import dream.first.extjs.model.support.generator.js.ExtjsGenerateException;
import dream.first.extjs.model.support.generator.jsp.JSPGenerateException;

public class ModelGenerateMain {

	public static void main(String[] args) throws PDMResolverException, ModelGenerateException, IOException,
			ExtjsGenerateException, JSPGenerateException {
		CompleteModelGenerateUtils.generatePOJO(
				"F:\\developer\\PowerDesigner\\数模\\model-generate-test\\generate-test.pdm",
				"F:\\developer\\PowerDesigner\\数模\\model-generate-test", true);// true、false为是否为每一个模型创建一个目录。如果一次性生成的模型过多推荐使用true

	}

}
