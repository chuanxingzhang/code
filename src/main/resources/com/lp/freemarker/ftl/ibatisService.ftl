package ${packagePath};

import ${importPackage}.${entityName};

import java.util.List;
import java.util.Map;


/**
* ${entityExplain}Service.
*/
public interface ${interfaceName} {

	/**
	 * 新增${entityExplain}.
	 *
	 * @param record 实体
	 * @return 主键
	 */
	int insertSelective(${entityName} record);

	/**
	 * 根据主键返回${entityExplain}实体.
	 *
	 * @param ${primaryKeyColumn}  主键
	 * @return ${entityName}
	 */
	${entityName} selectByPrimaryKey(${primaryKeyJavaType} ${primaryKeyColumn});

	/**
     * 选择更新${entityExplain}.
	 *
	 * @param record  实体
	 * @return 更新状态
	 */
	int updateByPrimaryKeySelective(${entityName} record);

	/**
	 * 全量更新${entityExplain}.
	 *
	 * @param record  实体
	 * @return 更新状态
	 */
	int updateByPrimaryKey(${entityName} record);

	/**
	 * 批量插入${entityExplain}.
	 *
	 * @param list 实体${entityExplain}列表
	 */
	void insertBatch(List<${entityName}> list);

	/**
	 * 根据主键删除数据.
	 *
	 * @param ${primaryKeyColumn}  主键
	 * @return 删除成功标志位
	 */
	int deleteByPrimaryKey(${primaryKeyJavaType} ${primaryKeyColumn});

	/**
	 * 根据查询条件查询出列表.
	 *
	 * @param map  参数集合
	 * @return ${entityExplain}列表
	 */
	List<${entityName}> selectBySelective(Map map);

	/**
	 * 根据查询条件查询出数据集合的条数.
	 *
	 * @param map  参数集合
	 * @return 条数
	 */
	Integer selectBySelectiveCount(Map map);
}