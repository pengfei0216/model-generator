package dream.first.model.generator.demo;

import org.yelong.core.model.Model;

import dream.first.model.generator.demo.utils.JSPGenerateUtils;

public class JSPGen {

	public static void main(String[] args) throws Exception {
		JSPGenerateUtils.generateDetail(Model.class, "D:\\PowerDesigner\\数模\\model-generate-test\\DemoAnnex2");
	}

}
