/**
 * 
 */
package dream.first.model.generator.demo;

import dream.first.model.generator.demo.utils.CompleteModelGenerateUtils;

public class CompleteGen {

	public static void main(String[] args) throws Exception {
		CompleteModelGenerateUtils.generatePOJO(
				"D:\\PowerDesigner\\数模\\model-generate-test\\generate-test.pdm",
				"D:\\PowerDesigner\\数模\\model-generate-test", true);// true、false为是否为每一个模型创建一个目录。如果一次性生成的模型过多推荐使用true
	}

}
