package dream.first.model.generator.demo;

import org.yelong.core.model.Model;

import dream.first.model.generator.demo.utils.ControllerGenerateUtils;

public class ControllerGen {

	public static void main(String[] args) throws Exception {
		ControllerGenerateUtils.generateDefault(Model.class,
				"D:\\PowerDesigner\\数模\\model-generate-test\\DemoAnnex2");
	}

}
