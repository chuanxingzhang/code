package ${packagePath};

import ${importPackage}.${entityName};

import java.util.List;
import java.util.Map;

<#list importList?if_exists as importPackage>  
import ${importPackage.importPackage};
</#list>

import org.apache.ibatis.annotations.Param;

public interface ${interfaceName} {

	public int insertSelective(${entityName} entity);
	
	<#if isCreateMoveSql>
	<#--生成动态sql-->
	public List<${entityName}> selectBySelective(${entityName} entity);
	
	public int selectBySelectiveCount(${entityName} entity);
    
    public int deleteBySelective(${entityName} entity);
    
	<#list methodList?if_exists as method>
	 /**
	  * ${method.methodExplain}
	  <#list method.paramList?if_exists as param>
	  * @param ${param.paramName} ${param.paramExplain}
	  </#list>
	  * @return
	  */
	public <#if method.isIndexUnique == 1>List<${entityName}><#else>${entityName}</#if> selectBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>@Param("${param.paramName}") ${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>);
	
	public int selectBy${method.methodNameSuffix}Count(<#list method.paramList?if_exists as param>@Param("${param.paramName}") ${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>);
	
	 /**
	  * ${method.methodExplain}
	  <#list method.paramList?if_exists as param>
	  * @param ${param.paramName} ${param.paramExplain}
	  </#list>
	  * @return
	  */
	public int updateBy${method.methodNameSuffix}(Map<String,Object> map);
	
	 /**
	  * ${method.methodExplain}
	  <#list method.paramList?if_exists as param>
	  * @param ${param.paramName} ${param.paramExplain}
	  </#list>
	  * @return
	  */
	public int deleteBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>@Param("${param.paramName}") ${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>);
	
	</#list>
	
	<#else>
	<#--不生成动态sql-->
	<#list methodList?if_exists as method>
	 /**
	  * ${method.methodExplain}
	  <#list method.paramList?if_exists as param>
	  * @param ${param.paramName} ${param.paramExplain}
	  </#list>
	  * @return
	  */
	public <#if method.isIndexUnique == 1>List<${entityName}><#else>${entityName}</#if> selectBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>@Param("${param.paramName}") ${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>);
	</#list>
	</#if>
}