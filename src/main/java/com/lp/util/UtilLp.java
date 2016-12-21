package com.lp.util;

import com.lp.db.DbFile;
import com.lp.db.MysqlDataType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilLp {
	/**
	 * 字符串，首字母大写
	 * @param str
	 * @return
	 */
	public static String firstToUpperCase(String str) {
		str = str.substring(0, 1).toUpperCase() + str.substring(1);
       return  str;
    }
	
	/**
	 * 字符串，首字母小写
	 * @param str
	 * @return
	 */
	public static String firstToLowerCase(String str) {
		str = str.substring(0, 1).toLowerCase() + str.substring(1);
       return  str;
    }
	
	/**
	 * 判断字符串是否包含数字
	 * @param str
	 * @return true 包含 ， false 不包含
	 */
	public static boolean ishaveDigit(String str) {
		// 判断一个字符串是否含有数字
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 将mysql数据类型，翻译为 java 数据类型
	 * @param dataType
	 * @return
	 */
	public static MysqlDataType getFieldType(String dataType) {
		MysqlDataType[] types = MysqlDataType.values();
		for(int i=0; i<types.length; i++) {
			if(types[i].getMySqlDataType().equalsIgnoreCase(dataType)) {
				return types[i];
			}
		}
		return null;
	}
	
	/**
	 * 获取ibatisDao类名称
	 * @param entityName
	 * @return
	 */
	public static String getIbatitsDaoClassName(String entityName) {
		String interfaceName = entityName+"Dao";
		return interfaceName;
	}
	
	/**
	 * 获取service层接口类名称
	 * @param entityName
	 * @return
	 */
	public static String getAppServiceClassName(String entityName) {
		String interfaceName = entityName+"Service";
		return interfaceName;
	}
	
	/**
	 * 获取service层实现类名称
	 * @param entityName
	 * @return
	 */
	public static String getAppServiceImplClassName(String entityName) {
		String interfaceName = entityName+"ServiceImpl";
		return interfaceName;
	}
	
	/**
	 * 获取service层实现类名称
	 * @param entityName
	 * @return
	 */
	public static String getAppControllerClassName(String entityName) {
		String interfaceName = entityName+"Controller";
		return interfaceName;
	}
	
	/**
	 * 获取 ibatisEntity类名称
	 * @param tableName
	 * @return
	 */
	public static String getIbatisEntityName(String tableName) {
		String entityName = tableNameToHumpStr(tableName);
//		String interfaceName = entityName+"Entity";
		return entityName;
	}
	
	/**
	 * 数据库表名称 格式化为 驼峰标识，首字母大写，如果有数字，放弃包含数字的字符串
	 * @param tableName
	 * @return
	 */
	public static String tableNameToHumpStr(String tableName) {
		StringBuilder s = new StringBuilder();
		if(tableName.indexOf("_") > -1) {//包含下划线
			String[] sArray = tableName.toLowerCase().split("_");
			for(int i=0; i<sArray.length; i++) {
				if(sArray[i].equals(DbFile.singleton.getFileConfig().getDeleteStr())) {
					continue;
				}
				if(!ishaveDigit(sArray[i])) {
					s.append(firstToUpperCase(sArray[i]));
				}
			}
			return s.toString();
		} else {
			return firstToUpperCase(tableName.toLowerCase());
		}
	}
	
	
	public static String getIsOrderByColumn(String str) {
		return "isOrderBy" + columnNameToHumpStr(str);
	}
	
	public static String getIsOrderByColumnDesc(String str) {
		return "isOrderBy" + columnNameToHumpStr(str) + "Desc";
	}
	
	public static String getIsGroupByColumn(String str) {
		return "isGroupBy" + columnNameToHumpStr(str);
	}
	
	/**
	 * 数据库表 字段名称  格式化为 驼峰标识，首字母小写
	 * @param str
	 * @return
	 */
	public static String columnNameToHumpStr(String str) {
		StringBuilder s = new StringBuilder();
		if(str.indexOf("_") > -1) {//包含下划线
			String[] sArray = str.toLowerCase().split("_");
			s.append(firstToLowerCase(sArray[0]));
			for(int i=1; i<sArray.length; i++) {
				s.append(firstToUpperCase(sArray[i]));
			}
			return s.toString();
		} else {
			return firstToLowerCase(str.toLowerCase());
		}
	}
	
	
}
