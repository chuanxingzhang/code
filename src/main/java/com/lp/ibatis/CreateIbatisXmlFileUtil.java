package com.lp.ibatis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.lp.db.Config;
import com.lp.db.DbFile;
import com.lp.freemarker.ftl.TemplatePath;
import com.lp.util.FileUtil;
import com.lp.util.UtilLp;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CreateIbatisXmlFileUtil {

	/**
	 *
	 * @param tableInfo
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("deprecation")
	public static void createIbatisXmlFile(List<TableColumnInfo> tableInfo, String tableName){
		Configuration configuration = new Configuration();
		configuration.setTemplateLoader(new ClassTemplateLoader(TemplatePath.class));
		Template template = null;
		try {
			template = configuration.getTemplate("ibatisXml.ftl", Locale.CHINA, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("jingHao", "#");
		//
		rootMap.put("tableName", tableName);
		//
		rootMap.put("ibatisDaoPackagePath", Config.daoPackage);
		rootMap.put("ibatisDaoName", Config.entityName);
		//
		rootMap.put("ibatisEntityPackagePath",Config.entityPackage);
		String ibatisEntityName = UtilLp.getIbatisEntityName(Config.entityName);
		rootMap.put("ibatisEntityName", ibatisEntityName);
		//实体类 属性
		for(int i=0; i<tableInfo.size(); i++) {
			TableColumnInfo columnInfo = tableInfo.get(i);
			String columnName = columnInfo.getColumnName();
			String dataType = columnInfo.getDataType();
			String columnKey = columnInfo.getColumnKey();//表字段索引类型
			if("PRI".equals(columnKey)) {//主键
				//
				String primaryKeyIbatisJdbcType = UtilLp.getFieldType(dataType).getIbatisJdbcType();
				rootMap.put("primaryKeyColumn", columnName);
				rootMap.put("primaryKeyProperty", UtilLp.columnNameToHumpStr(columnName));
				rootMap.put("primaryKeyIbatisJdbcType", primaryKeyIbatisJdbcType);
				rootMap.put("primaryKeyIbatisJavaType", UtilLp.getFieldType(dataType).getPrimaryKeyIbatisJavaType());
				break;
			}
		}
		//
		List<Map<String,String>> listMap = CreateIbatisFile.getEntityField(tableInfo);
		rootMap.put("listMap", listMap);
		//
		List<Map<String,Object>> methodList = CreateIbatisFile.getMethodList(tableInfo);
		rootMap.put("methodList", methodList);//

		FileUtil.writeIbatisFile(template, rootMap, UtilLp.getIbatitsDaoClassName(Config.entityName)+"Mapper.xml", Config.mapperPackage);//ibatisEntityName
	}

}
