package dream.first.model.generator.demo;

import org.yelong.core.model.Model;

import dream.first.model.generator.demo.utils.CodeGenerateUtils;

/**
 * 
 * @author PengFei
 */
public class CodeGen {

	public static void main(String[] args) throws Exception {
		CodeGenerateUtils.generate_1(Model.class, "D:\\PowerDesigner\\数模\\model");
//		CodeGenerateUtils.generate_2(Model.class, "D:\\PowerDesigner\\数模\\model");
	}

}
