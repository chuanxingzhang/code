package ${packagePath};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import ${servicePackagePath}.${interfaceNameService};
import ${ibatisEntityPackage}.${entityName};
import ${ibatisDaoPackage}.${ibatisDaoName};

<#list importList?if_exists as importPackage>  
import ${importPackage.importPackage};
</#list>

@Service
public class ${className} implements ${interfaceNameService}{
	
	@Autowired
	private ${ibatisDaoName} ${ibatisDaoVar};
	
    @Override
	public int insertSelective(${entityName} entity) {
		return ${ibatisDaoVar}.insertSelective(entity);
	}

<#if isCreateMoveSql>
	<#--生成动态sql-->
	@Override
	public List<${entityName}> selectBySelective(${entityName} entity) {
		return ${ibatisDaoVar}.selectBySelective(entity);
	}
	
	@Override
	public int selectBySelectiveCount(${entityName} entity) {
		return ${ibatisDaoVar}.selectBySelectiveCount(entity);
	}
    
    @Override
	public int deleteBySelective(${entityName} entity) {
		return ${ibatisDaoVar}.deleteBySelective(entity);
	}

	<#list methodList?if_exists as method>
	 /**
	  * ${method.methodExplain}
	  <#list method.paramList?if_exists as param>
	  * @param ${param.paramName} ${param.paramExplain}
	  </#list>
	  * @return
	  */
	  @Override
	 public <#if method.isIndexUnique == 1>List<${entityName}><#else>${entityName}</#if> selectBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>) {
	 	return ${ibatisDaoVar}.selectBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>${param.paramName}<#if param_has_next>, </#if></#list>);
	 }
	 
	 @Override
	 public int selectBy${method.methodNameSuffix}Count(<#list method.paramList?if_exists as param>${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>) {
	 	return ${ibatisDaoVar}.selectBy${method.methodNameSuffix}Count(<#list method.paramList?if_exists as param>${param.paramName}<#if param_has_next>, </#if></#list>);
	 }
	
	 /**
	  * ${method.methodExplain}
	  <#list method.paramList?if_exists as param>
	  * @param ${param.paramName} ${param.paramExplain}
	  </#list>
	  * @return
	  */
	  @Override
	 public int updateBy${method.methodNameSuffix}(${entityName} entity, <#list method.paramList?if_exists as param>${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>) {
	 	Map<String,Object> map = new HashMap<String, Object>();
		<#--where条件 -->
		<#list method.paramList?if_exists as param>
		map.put("${param.paramName}",${param.paramName});//${param.paramExplain}
		</#list>
		<#--如果出现在where条件中的字段，不能出现在 set 中 -->
	 	<#list listMap?if_exists as columnMap>
	 		<#assign isFilterParamColumn = "false" />
	    	<#list method.paramList?if_exists as param>
	    		<#if param.COLUMN == columnMap.COLUMN>
	    			<#assign isFilterParamColumn = "true" />
	    		</#if>
	    	</#list>
	 		<#if isFilterParamColumn == "false">
		 		<#if columnMap.COLUMN?lower_case == "create_time" || columnMap.COLUMN?lower_case == "createtime" || columnMap.COLUMN?lower_case == "created_time" || columnMap.COLUMN?lower_case == "createdtime">
			    	<#elseif columnMap.COLUMN?lower_case == "update_time" || columnMap.COLUMN?lower_case == "updatetime" || columnMap.COLUMN?lower_case == "updated_time" || columnMap.COLUMN?lower_case == "updatedtime">
			    	<#elseif columnMap.COLUMN?lower_case == "modified_time" || columnMap.COLUMN?lower_case == "modifiedtime">
			    	<#else>
	 	map.put("${columnMap.PROPERTY}", entity.get${columnMap.PROPERTY?cap_first}());//${columnMap.fieldExplain}
	 			</#if>
	 		</#if>
	 	</#list>
		return ${ibatisDaoVar}.updateBy${method.methodNameSuffix}(map);
	 }
	
	 /**
	  * ${method.methodExplain}
	  <#list method.paramList?if_exists as param>
	  * @param ${param.paramName} ${param.paramExplain}
	  </#list>
	  * @return
	  */
	  @Override
	 public int deleteBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>) {
		return ${ibatisDaoVar}.deleteBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>${param.paramName}<#if param_has_next>, </#if></#list>);
	 }
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
	  @Override
	 public <#if method.isIndexUnique == 1>List<${entityName}><#else>${entityName}</#if> selectBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>${param.paramType} ${param.paramName}<#if param_has_next>, </#if></#list>) {
	 	return ${ibatisDaoVar}.selectBy${method.methodNameSuffix}(<#list method.paramList?if_exists as param>${param.paramName}<#if param_has_next>, </#if></#list>);
	 }
	</#list>
</#if>
}