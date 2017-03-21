package com.lp.ibatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.lp.db.Config;
import com.lp.db.DbFile;
import com.lp.db.JavaDataTypePackage;
import com.lp.db.JdbcUtil;
import com.lp.util.UtilLp;


public class CreateIbatisFile {


	/**
	 *
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static List<TableColumnInfo> getTableInfo(String tableName)  {
		List<TableColumnInfo> tableInfo = new ArrayList<TableColumnInfo>();
		Connection conn = JdbcUtil.getOpenConnection();
		//获取表结构信息
		String tableInfoSql = "SELECT * FROM information_schema.columns WHERE table_schema = '" + Config.databaseName + "' AND table_name = '" + tableName + "'";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(tableInfoSql);
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()) {
				TableColumnInfo columnInfo = new TableColumnInfo();
				columnInfo.setColumnName(resultSet.getString("COLUMN_NAME"));//表字段名称
				columnInfo.setColumnComment(resultSet.getString("COLUMN_COMMENT"));//表字段注释
				columnInfo.setDataType(resultSet.getString("DATA_TYPE"));//表字段 数据类型
				columnInfo.setColumnKey(resultSet.getString("COLUMN_KEY"));//表字段 索引类型
				columnInfo.setIsIndex(false);//非索引字段
				tableInfo.add(columnInfo);
			}
			//获取索引信息
			String tableIndexSql = "SHOW INDEX FROM `" + Config.databaseName + "`." + tableName;
			pst = conn.prepareStatement(tableIndexSql);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				for(int i=0; i<tableInfo.size(); i++) {
					TableColumnInfo columnInfo = tableInfo.get(i);
					String Column_name =  resultSet.getString("Column_name");//表字段名称
					if(Column_name.equals(columnInfo.getColumnName())) {
						columnInfo.setNonUnique(resultSet.getString("Non_unique"));//索引是否可以重复 0 不可以，1 可以
						columnInfo.setIndexName(resultSet.getString("Key_name"));//索引名称
						columnInfo.setIndexSeq(resultSet.getString("Seq_in_index"));//索引顺序
						columnInfo.setIsIndex(true);//索引字段
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableInfo;

	}


	/**
	 *
	 * @param tableInfo
	 * @return
	 */
	protected static List<Map<String,Object>> getMethodList(List<TableColumnInfo> tableInfo) {
		List<Map<String,Object>> methodList = new ArrayList<Map<String,Object>>();//

		List<TableColumnInfo> tableInfoCopy1 = getTableInfoCopy(tableInfo);//
		List<TableColumnInfo> tableInfoCopy2 = getTableInfoCopy(tableInfo);//
		//
		for(int i=0; i<tableInfoCopy1.size(); i++) {
			TableColumnInfo columnInfo = tableInfoCopy1.get(i);
			//
			Map<String,Object> methodMap = new HashMap<String, Object>();
			int isIndexUnique = -1;//是否唯一索引 ，0 唯一索引，1 普通索引
			StringBuilder methodNameSuffix = new StringBuilder();//方法名称后缀
			StringBuilder methodExplain = new StringBuilder("根据");//方法注解
			int indexColumnCount = 0;//索引包含几列
			//方法参数list
			List<Map<String,Object>> paramList = new ArrayList<Map<String,Object>>();
			for(int j=0; j<tableInfoCopy2.size();) {
				if("PRI".equals(tableInfoCopy2.get(j).getColumnKey())) {
					Map<String,Object> paramMap = new HashMap<String, Object>();
					paramMap.put("paramName", UtilLp.columnNameToHumpStr(tableInfoCopy2.get(j).getColumnName()));//参数名称
					paramMap.put("paramType", UtilLp.getFieldType(tableInfoCopy2.get(j).getDataType()).getJavaDataType());//参数数据类型
					paramMap.put("paramExplain", tableInfoCopy2.get(j).getColumnComment());//参数说明
					paramMap.put("COLUMN", tableInfoCopy2.get(j).getColumnName());//参数对应的数据库中的 字段名称
					paramMap.put("JDBCTYPE", UtilLp.getFieldType(tableInfoCopy2.get(j).getDataType()).getIbatisJdbcType());//对应的 ibatis jdbcType
					paramList.add(paramMap);
					if(tableInfoCopy2.get(j).getNonUnique().equals("1")) {//索引不是唯一索引
						isIndexUnique = 1;
					} else {//唯一索引
						isIndexUnique = 0;
					}
					//
					indexColumnCount ++;
					//方法名称后缀
					methodNameSuffix.append(UtilLp.tableNameToHumpStr(tableInfoCopy2.get(j).getColumnName()));
					//删除元素
					tableInfoCopy2.remove(j);
				} else {
					j++;
				}
			}
			if(paramList.size() > 0) {
				methodMap.put("paramList", paramList);//方法参数
				methodMap.put("methodNameSuffix", methodNameSuffix);//方法名称后缀
				methodMap.put("isIndexUnique", isIndexUnique);//是否唯一索引 ，0 唯一索引，1 普通索引
				if(isIndexUnique == 0) {
					methodExplain.append("唯一");
				}
				if(indexColumnCount == 1) {
					methodExplain.append("索引 ");
				} else if(indexColumnCount > 1) {
					methodExplain.append("联合索引 ");
				}
				for(int lp=0; lp<paramList.size(); lp++) {
					methodExplain.append(paramList.get(lp).get("paramName"));
					if(lp < paramList.size() - 1) {
						methodExplain.append(",");
					}
				}
				methodExplain.append(" 操作数据");
				methodMap.put("methodExplain", methodExplain.toString());//方法注解
				methodList.add(methodMap);
			}
		}
		return methodList;
	}

	/**
	 * 克隆
	 * @param tableInfo
	 * @return
	 */
	private static List<TableColumnInfo> getTableInfoCopy(List<TableColumnInfo> tableInfo) {
		List<TableColumnInfo> tableInfoCopy = new ArrayList<TableColumnInfo>();
		for(int i=0; i<tableInfo.size(); i++) {
			if(tableInfo.get(i).getIsIndex()) {
				tableInfoCopy.add( tableInfo.get(i).clone() );
			}
		}
		return tableInfoCopy;
	}

	protected static List<Map<String,String>> getImportList(List<TableColumnInfo> tableInfo) {
		//import
		List<Map<String,String>> importList = new ArrayList<Map<String,String>>();
		JavaDataTypePackage[] javaDataType = JavaDataTypePackage.values();
		for(int i=0; i<tableInfo.size(); i++) {
			String dataType = tableInfo.get(i).getDataType();
			for(int jdt=0; jdt<javaDataType.length; jdt++) {
				if(dataType.equalsIgnoreCase(javaDataType[jdt].getMySqlDataType())) {
					Map<String,String> importMap = new HashMap<String, String>();
					importMap.put("importPackage", javaDataType[jdt].getJavaPackage());
					if(!importList.contains(importMap)) {
						importList.add(importMap);
						break;
					}
				}
			}
		}
		return importList;
	}

	/**
	 *
	 * @param tableInfo
	 * @return
	 */
	protected static List<Map<String,String>> getEntityField(List<TableColumnInfo> tableInfo) {
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		for(int i=0; i<tableInfo.size(); i++) {
			Map<String,String> xmlMap = new HashMap<String, String>();
			TableColumnInfo columnInfo = tableInfo.get(i);
			String columnName = columnInfo.getColumnName();
			String dataType = columnInfo.getDataType();
			String columnCom = columnInfo.getColumnComment();
			String columnKey = columnInfo.getColumnKey();//表字段索引类型
			String isPrimarykey = "";
			if("PRI".equals(columnKey)) {//主键
				isPrimarykey = "yes";
			} else {
				isPrimarykey = "no";
			}
			//
			xmlMap.put("COLUMN", columnName);//数据库字段名
			xmlMap.put("PROPERTY", UtilLp.columnNameToHumpStr(columnName));//属性名
			xmlMap.put("JDBCTYPE", UtilLp.getFieldType(dataType).getIbatisJdbcType());
			xmlMap.put("IS_PRIMARYKEY", isPrimarykey);//是否是 唯一索引

//			xmlMap.put("isOrderByColumn", UtilLp.getIsOrderByColumn(columnName));//
//			xmlMap.put("isOrderByColumnDesc", UtilLp.getIsOrderByColumn(columnName) + "Desc");//true 倒序, false 正序
//
//			xmlMap.put("isGroupByColumn", UtilLp.getIsGroupByColumn(columnName));//

			xmlMap.put("fieldExplain", columnCom);//字段说明
			listMap.add(xmlMap);
		}
		return listMap;
	}
}
