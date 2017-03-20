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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CreateIbatisDaoFileUtil {

	/**
	 * 
	 * @param tableInfo
	 * @param tableName ibatisDao文件的package路径
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("deprecation")
	public static void createIbatisDaoFile(List<TableColumnInfo> tableInfo, String tableName){
		Configuration configuration = new Configuration();
		configuration.setTemplateLoader(new ClassTemplateLoader(TemplatePath.class));
		Template template = null;
		try {
			template = configuration.getTemplate("ibatisDao.ftl", Locale.CHINA, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		Map<String, Object> rootMap = new HashMap<String, Object>();

		rootMap.put("packagePath", Config.daoPackage);//
		rootMap.put("importPackage", Config.entityPackage);//
		String entityName = Config.entityName;
		String interfaceName = UtilLp.getIbatitsDaoClassName(entityName);
		rootMap.put("interfaceName", interfaceName);//接口名称
		rootMap.put("entityName", entityName);//实体名称
		//
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
				rootMap.put("primaryKeyJavaType", UtilLp.getFieldType(dataType).getJavaDataType());
				rootMap.put("primaryKeyExplain",columnInfo.getColumnComment());
				break;
			}
		}
		//import
		rootMap.put("importList", CreateIbatisFile.getImportList(tableInfo));
		//
		rootMap.put("isCreateMoveSql", true);
		
		FileUtil.writeIbatisFile(template, rootMap, interfaceName+".java", Config.daoPackage);//entityName
	}
	
}


































