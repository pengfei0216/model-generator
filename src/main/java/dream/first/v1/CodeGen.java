package dream.first.v1;

import org.yelong.core.model.Model;

import dream.first.extjs.model.support.generator.js.ExtjsGenerateException;
import dream.first.extjs.model.support.generator.jsp.JSPGenerateException;
import dream.first.v1.utils.CodeGenerateUtils;

/**
 * 
 * @author PengFei
 */
public class CodeGen {

	public static void main(String[] args) throws ExtjsGenerateException, JSPGenerateException {
		CodeGenerateUtils.generate(Model.class, "D:\\PowerDesigner\\数模\\model");
	}
	
}
