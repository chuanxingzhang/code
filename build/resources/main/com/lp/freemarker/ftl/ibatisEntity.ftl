package ${packagePath};

<#list importList?if_exists as importPackage>  
import ${importPackage.importPackage};
</#list>

public class ${className} {

<#list entityFieldList?if_exists as field>
	/**
	 * ${(field.fieldExplain)!}
	 */
	private ${field.fieldType} ${field.fieldName};
   
</#list>
<#list entityMethodList?if_exists as method>
	/**
	 * ${method.methodExplain?if_exists}
	 */
	public ${method.retutnType} get${method.methodName}() {
   		return ${method.fieldName};
   	}
   	/**
	 * ${method.methodExplain?if_exists}
	 */
	public ${className} set${method.methodName}(${method.paramType} ${method.paramName}) {
   		this.${method.fieldName} = ${method.paramName};
   		return this;
	}
	
</#list>
}
