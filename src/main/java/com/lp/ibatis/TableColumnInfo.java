package com.lp.ibatis;

public class TableColumnInfo implements Cloneable{
	/**
	 * 字段名称 ,例如 DICT_TYPE
	 */
	private String columnName;
	/**
	 * 字段注释
	 */
	private String columnComment;
	/**
	 * 数据类型 例如 varchar
	 */
	private String dataType;
	/**
	 * 字段索引，必须如果是 主键就是 “PRI”
	 */
	private String columnKey;
	/**
	 * true 索引字段，false 非索引字段
	 * 是否是索引字段，只有索引字段，属性   nonUnique，indexName，indexSeq   才有值
	 */
	private boolean isIndex;
	/**
	 * 索引是否可以重复 0 不可以，1 可以
	 */
	private String nonUnique;
	/**
	 * 索引名称
	 */
	private String indexName;
	/**
	 * 索引顺序
	 */
	private String indexSeq;
	
	public TableColumnInfo clone() {
		try {
			return (TableColumnInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	public boolean getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(boolean isIndex) {
		this.isIndex = isIndex;
	}
	public String getNonUnique() {
		return nonUnique;
	}
	public void setNonUnique(String nonUnique) {
		this.nonUnique = nonUnique;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexSeq() {
		return indexSeq;
	}
	public void setIndexSeq(String indexSeq) {
		this.indexSeq = indexSeq;
	}
	

}
