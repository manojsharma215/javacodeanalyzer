package demo.codeanalyzer.javacode.rule;

import java.util.ArrayList;
import java.util.Collection;

import demo.codeanalyzer.common.model.ClassFile;
import demo.codeanalyzer.common.model.ErrorDescription;
import demo.codeanalyzer.common.model.Location;
import demo.codeanalyzer.common.model.Method;
import demo.codeanalyzer.common.rule.AbstractCodeRule;
import demo.codeanalyzer.common.util.CodeAnalyzerUtil;

/**
 * Rule which checks the overriding rule for equal and hashcode.
 *
 * @author Seema Richard (Seema.Richard@ust-global.com)
 * @author Deepa Sobhana (Deepa.Sobhana@ust-global.com)
 */
public class OverrideEqualsHashCode extends AbstractCodeRule {

	@Override
	protected Collection<ErrorDescription> apply(ClassFile clazzInfo) {
		boolean hasEquals = false;
		boolean hasHashCode = false;
		Location errorLoc = null;

		for (Method method : clazzInfo.getMethods()) {
			String methodName = method.getName();
			ArrayList paramList = (ArrayList) method.getParameters();
			if ("equals".equals(methodName) && paramList.size() == 1) {
				if ("java.lang.Object".equals(paramList.get(0))) {
					hasEquals = true;
					errorLoc = method.getLocationInfo();
				}
			} else if ("hashCode".equals(methodName) && method.getParameters().isEmpty()) {
				hasHashCode = true;
			}
		}

		if (hasEquals) {
			if (hasHashCode) {
				return null;
			} else {
				String errorMsg = String.format("%s : The class that overrides equals() should override hashcode()",
					CodeAnalyzerUtil.getSimpleNameFromQualifiedName(clazzInfo.getName()));
				Collection<ErrorDescription> errorList = new ArrayList<ErrorDescription>();
				errorList.add(setErrorDetails(errorMsg, errorLoc));
				return errorList;
			}
		}
		return null;
	}
}
