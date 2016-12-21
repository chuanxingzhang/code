package com.lp.ibatis;


import com.lp.db.Config;
import com.lp.db.DbFile;
import com.lp.freemarker.ftl.TemplatePath;
import com.lp.util.FileUtil;
import com.lp.util.UtilLp;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

public class CreateIbatisEntityFileUtil {

	/**
	 * 
	 * @param tableInfo
	 * @param tableName
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("deprecation")
	public static void createIbatisEntityFile(List<TableColumnInfo> tableInfo, String tableName){
		Configuration configuration = new Configuration();
		configuration.setTemplateLoader(new ClassTemplateLoader(TemplatePath.class));
		Template template = null;
		try {
			template = configuration.getTemplate("ibatisEntity.ftl", Locale.CHINA, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("packagePath", Config.entityPackage);//
		String className = UtilLp.getIbatisEntityName(Config.entityName);
		rootMap.put("className", className);//类名称
		//实体类 属性
		List<Map<String,String>> entityFieldList = new ArrayList<Map<String,String>>();
		//实体类 方法
		List<Map<String,String>> entityMethodList = new ArrayList<Map<String,String>>();
		
		for(int i=0; i<tableInfo.size(); i++) {
			TableColumnInfo columnInfo = tableInfo.get(i);
			String columnCom = columnInfo.getColumnComment();
			String columnName = columnInfo.getColumnName();
			String dataType = columnInfo.getDataType();
			//属性
			Map<String,String> entityFieldMap = new HashMap<String, String>();
			entityFieldMap.put("fieldExplain", columnCom);//实体类字段说明
			String fieldName = UtilLp.columnNameToHumpStr(columnName);//实体类字段 名称
			entityFieldMap.put("fieldName", fieldName);
			String fieldType = UtilLp.getFieldType(dataType).getJavaDataType();//字段数据类型
			entityFieldMap.put("fieldType", fieldType);
			entityFieldList.add(entityFieldMap);//
//			if(false) {
//				//order by
//				entityFieldMap = new HashMap<String, String>();
//				entityFieldMap.put("fieldName", UtilLp.getIsOrderByColumn(columnName));
//				entityFieldMap.put("fieldType", "boolean");
//				entityFieldMap.put("fieldExplain", "true 按照字段 "+fieldName+ " 排序" + ";false 不排序 (默认值 false)");//实体类字段说明
//				entityFieldList.add(entityFieldMap);//
//				//order by 倒序 or 正序
//				entityFieldMap = new HashMap<String, String>();
//				entityFieldMap.put("fieldName", UtilLp.getIsOrderByColumnDesc(columnName));
//				entityFieldMap.put("fieldType", "boolean");
//				entityFieldMap.put("fieldExplain", "按照字段 "+fieldName+ " 排序, true 倒序, false 正序 (默认值 false)");//实体类字段说明
//				entityFieldList.add(entityFieldMap);//
//				//gorup by
//				entityFieldMap = new HashMap<String, String>();
//				entityFieldMap.put("fieldName", UtilLp.getIsGroupByColumn(columnName));
//				entityFieldMap.put("fieldType", "boolean");
//				entityFieldMap.put("fieldExplain", "true 按照字段 "+fieldName+ " 分组" + ";false 不分组 (默认值 false)");//实体类字段说明
//				entityFieldList.add(entityFieldMap);//
//			}
			//方法
			Map<String,String> entityMethodMap = new HashMap<String, String>();
			entityMethodMap.put("retutnType", fieldType);//get 方法 返回类型
			entityMethodMap.put("methodName", UtilLp.firstToUpperCase(fieldName));//get 和 set 方法 方法名
			entityMethodMap.put("fieldName", fieldName);//
			entityMethodMap.put("paramType", fieldType);//set方法参数类型
			entityMethodMap.put("paramName", fieldName);//set方法 参数名
			entityMethodList.add(entityMethodMap);//
//			if(false) {
//				//order by
//				entityMethodMap = new HashMap<String, String>();
//				entityMethodMap.put("retutnType", "boolean");//get 方法 返回类型
//				entityMethodMap.put("methodName", UtilLp.firstToUpperCase(UtilLp.getIsOrderByColumn(columnName)));//get 和 set 方法 方法名
//				entityMethodMap.put("fieldName", UtilLp.getIsOrderByColumn(columnName));//类属性名称
//				entityMethodMap.put("paramType", "boolean");//set方法参数类型
//				entityMethodMap.put("paramName", UtilLp.getIsOrderByColumn(columnName));//set方法 参数名
//				entityMethodMap.put("methodExplain", "true 按照字段 "+fieldName+ " 排序" + ";false 不排序 (默认值 false)");//方法说明
//				entityMethodList.add(entityMethodMap);//
//				//order by 倒序 or 正序
//				entityMethodMap = new HashMap<String, String>();
//				entityMethodMap.put("retutnType", "boolean");//get 方法 返回类型
//				entityMethodMap.put("methodName", UtilLp.firstToUpperCase(UtilLp.getIsOrderByColumnDesc(columnName)));//get 和 set 方法 方法名
//				entityMethodMap.put("fieldName", UtilLp.getIsOrderByColumnDesc(columnName));//类属性名称
//				entityMethodMap.put("paramType", "boolean");//set方法参数类型
//				entityMethodMap.put("paramName", UtilLp.getIsOrderByColumnDesc(columnName));//set方法 参数名
//				entityMethodMap.put("methodExplain", "按照字段 "+fieldName+ " 排序, true 倒序, false 正序 (默认值 false)");//方法说明
//				entityMethodList.add(entityMethodMap);//
//				//group by
//				entityMethodMap = new HashMap<String, String>();
//				entityMethodMap.put("retutnType", "boolean");//get 方法 返回类型
//				entityMethodMap.put("methodName", UtilLp.firstToUpperCase(UtilLp.getIsGroupByColumn(columnName)));//get 和 set 方法 方法名
//				entityMethodMap.put("fieldName", UtilLp.getIsGroupByColumn(columnName));//类属性名称
//				entityMethodMap.put("paramType", "boolean");//set方法参数类型
//				entityMethodMap.put("paramName", UtilLp.getIsGroupByColumn(columnName));//set方法 参数名
//				entityMethodList.add(entityMethodMap);//
//			}
		}
		rootMap.put("entityFieldList", entityFieldList);
		rootMap.put("entityMethodList", entityMethodList);
		//import
		rootMap.put("importList", CreateIbatisFile.getImportList(tableInfo));
		//
		rootMap.put("isCreateMoveSql", true);
		
		FileUtil.writeIbatisFile(template, rootMap, className+".java", Config.entityPackage);//className
		
	}
	
}
