<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${ibatisDaoPackagePath}.${ibatisDaoName}" >

  <resultMap id="BaseResultMap" type="${ibatisEntityPackagePath}.${ibatisEntityName}" >
    <#list listMap?if_exists as columnMap>
    <#if columnMap.IS_PRIMARYKEY?? && columnMap.IS_PRIMARYKEY == "yes">
	<id column="${columnMap.COLUMN}" property="${columnMap.PROPERTY}" jdbcType="${columnMap.JDBCTYPE}" />
	<#else>
	<result column="${columnMap.COLUMN}" property="${columnMap.PROPERTY}" jdbcType="${columnMap.JDBCTYPE}" />
	</#if>
	</#list>
  </resultMap>

  <sql id="Base_Column_List" >
  	<#list listMap?if_exists as column>
  		${column.COLUMN}<#if column_has_next>,</#if>
  	</#list>
  </sql>
<#if isCreateMoveSql>
<#--生成动态sql-->
  <select id="selectBySelective" parameterType="${ibatisEntityPackagePath}.${ibatisEntityName}" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${tableName}
    <where>
    <trim suffixOverrides=" and " >
    <#list listMap?if_exists as column>
    	<if test="${column.PROPERTY} != null" >
        ${column.COLUMN} = ${jingHao}{${column.PROPERTY},jdbcType=${column.JDBCTYPE}}<#if column_has_next> and </#if>
      	</if>
    </#list>
    </trim>
    </where>
     <if test="openGroupBy == 1" >
	    group by
    <trim suffixOverrides=", " >
    <#list listMap?if_exists as column>
    	<if test="${column.isGroupByColumn} ==1" >
	    	${column.COLUMN}<#if column_has_next>, </#if>
    	</if>
    </#list>
    </trim>
    </if>
    <if test="openOrderBy == 1" >
	    order by
    <trim suffixOverrides=", " >
    <#list listMap?if_exists as column>
    	<if test="${column.isOrderByColumn} == 1" >
	    	${column.COLUMN} <if test="${column.isOrderByColumnDesc} == 1" >desc</if><#if column_has_next>, </#if>
    	</if>
    </#list>
    </trim>
    </if>
    <if test="limitStart != null and limitEnd != null">
    	limit ${jingHao}{limitStart},${jingHao}{limitEnd}
    </if>
  </select>

  <select id="selectBySelectiveCount" parameterType="${ibatisEntityPackagePath}.${ibatisEntityName}" resultType="int">
    select count(1) from ${tableName}
    <where>
    <trim suffixOverrides=" and " >
    <#list listMap?if_exists as column>
    	<if test="${column.PROPERTY} != null" >
        ${column.COLUMN} = ${jingHao}{${column.PROPERTY},jdbcType=${column.JDBCTYPE}}<#if column_has_next> and </#if>
      	</if>
    </#list>
    </trim>
    </where>
  </select>

  <insert id="insertSelective" parameterType="${ibatisEntityPackagePath}.${ibatisEntityName}" useGeneratedKeys="true" keyProperty="${primaryKeyColumn}" >
    insert into ${tableName}
    <trim prefix="(" suffix=")" suffixOverrides="," >
    	<#list listMap?if_exists as column>
            <if test="${column.PROPERTY} != null" >
            ${column.COLUMN}<#if column_has_next>,</#if>
            </if>
    	</#list>
    </trim>
    values
    <trim prefix="(" suffix=")" suffixOverrides="," >
    	<#list listMap?if_exists as column>
            <if test="${column.PROPERTY} != null" >
            ${jingHao}{${column.PROPERTY},jdbcType=${column.JDBCTYPE}}<#if column_has_next>,</#if>
            </if>
    	</#list>
    </trim>
  </insert>

  <delete id="deleteBySelective" parameterType="${ibatisEntityPackagePath}.${ibatisEntityName}" >
    delete from ${tableName}
    where
    <trim suffixOverrides="  and  " >
    <#list listMap?if_exists as column>
    	<if test="${column.PROPERTY} != null" >
        ${column.COLUMN} = ${jingHao}{${column.PROPERTY},jdbcType=${column.JDBCTYPE}}<#if column_has_next> and </#if>
      </if>
    </#list>
    </trim>
  </delete>
<#--根据索引，生成 select update delete -->
<#list methodList?if_exists as method>
  <select id="selectBy${method.methodNameSuffix}" parameterType="java.lang.String" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${tableName}
    where
    <#list method.paramList?if_exists as param>
        ${param.COLUMN} = ${jingHao}{${param.paramName},jdbcType=${param.JDBCTYPE}}<#if param_has_next> and </#if>
    </#list>
    <#if method.isIndexUnique == 1>
	    <if test="limitStart != null and limitEnd != null">
	    	limit ${jingHao}{limitStart},${jingHao}{limitEnd}
	    </if>
    </#if>
  </select>

  <select id="selectBy${method.methodNameSuffix}Count" parameterType="java.lang.String" resultType="int">
    select count(1) from ${tableName}
    where
    <#list method.paramList?if_exists as param>
        ${param.COLUMN} = ${jingHao}{${param.paramName},jdbcType=${param.JDBCTYPE}}<#if param_has_next> and </#if>
    </#list>
  </select>

  <update id="updateBy${method.methodNameSuffix}" parameterType="java.util.Map" >
    update ${tableName} set
    	<trim suffixOverrides="," >
    	<#list listMap?if_exists as column>
    	<#--如果出现在where条件中的字段，不能出现在 set 中 -->
    	<#assign isFilterParamColumn = "true" />
    	<#if column.COLUMN?lower_case == "create_time" || column.COLUMN?lower_case == "createtime" || column.COLUMN?lower_case == "created_time" || column.COLUMN?lower_case == "createdtime">
    	<#assign isFilterParamColumn = "false" />
    	<#elseif column.COLUMN?lower_case == "update_time" || column.COLUMN?lower_case == "updatetime" || column.COLUMN?lower_case == "updated_time" || column.COLUMN?lower_case == "updatedtime">
    	${column.COLUMN} = NOW(),<#assign isFilterParamColumn = "false" />
    	<#elseif column.COLUMN?lower_case == "modified_time" || column.COLUMN?lower_case == "modifiedtime">
    	${column.COLUMN} = NOW(),<#assign isFilterParamColumn = "false" />
    	<#else>
    	<#list method.paramList?if_exists as param>
    		<#if param.COLUMN == column.COLUMN>
    			<#assign isFilterParamColumn = "false" />
    		</#if>
    	</#list>
    	</#if>
    	<#if isFilterParamColumn == "true">
    	<if test="${column.PROPERTY} != null" >
		${column.COLUMN} = ${jingHao}{${column.PROPERTY},jdbcType=${column.JDBCTYPE}}<#if column_has_next>,</#if>
      	</if>
    	</#if>
    	</#list>
    	</trim>
    where
    <#list method.paramList?if_exists as param>
    	${param.COLUMN} = ${jingHao}{${param.paramName},jdbcType=${param.JDBCTYPE}}<#if param_has_next> and </#if>
    </#list>
  </update>

  <delete id="deleteBy${method.methodNameSuffix}" parameterType="java.lang.String" >
    delete from ${tableName}
    where
    <#list method.paramList?if_exists as param>
    	${param.COLUMN} = ${jingHao}{${param.paramName},jdbcType=${param.JDBCTYPE}}<#if param_has_next> and </#if>
    </#list>
  </delete>
</#list>

<#else>
<#--不生成动态sql-->
    <insert id="insertSelective" parameterType="${ibatisEntityPackagePath}.${ibatisEntityName}" useGeneratedKeys="true" keyProperty="${primaryKeyColumn}" >
	    insert into ${tableName} (<include refid="Base_Column_List" />)
	    values
	    <trim prefix="(" suffix=")" suffixOverrides="," >
	    	<#list listMap?if_exists as column>
	        ${jingHao}{${column.PROPERTY},jdbcType=${column.JDBCTYPE}}<#if column_has_next>,</#if>
	    	</#list>
	    </trim>
  </insert>
	<#--不生成动态sql-->
	<#list methodList?if_exists as method>
	<select id="selectBy${method.methodNameSuffix}" parameterType="java.lang.String" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${tableName}
    where
    <#list method.paramList?if_exists as param>
        ${param.COLUMN} = ${jingHao}{${param.paramName},jdbcType=${param.JDBCTYPE}}<#if param_has_next> and </#if>
    </#list>
  </select>
	</#list>
</#if>
</mapper>