/**
 * 
 */
package dream.first.v1;

import java.io.FileNotFoundException;

import org.yelong.core.model.support.generator.ModelGenerateException;
import org.yelong.core.model.support.generator.pdm.PDMResolverException;

import dream.first.v1.utils.ModelGenerateUtils;

/**
 * @author PengFei
 */
public class ModelGen {

	public static void main(String[] args) throws PDMResolverException, ModelGenerateException, FileNotFoundException {
		ModelGenerateUtils.generatePOJOModel("D:\\PowerDesigner\\数模\\generator.pdm", "D:\\PowerDesigner\\数模\\model");
//		ModelGenerateUtils.generateMapModel("D:\\PowerDesigner\\数模\\generator.pdm", "D:\\PowerDesigner\\数模\\model");
	}
	
}
