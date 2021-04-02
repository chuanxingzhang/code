package ${packagePath};

import com.github.pagehelper.PageHelper;
import com.le.common.constant.Constants;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import ${servicePackagePath}.${interfaceNameService};
import ${ibatisEntityPackage}.${entityName};
import ${ibatisDaoPackage}.${ibatisDaoName};
import lombok.extern.slf4j.Slf4j;
import com.le.common.exception.ExceptionStatus;
import com.le.common.exception.ProcessorException;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

/**
 * ${entityExplain}ServiceImpl.
 *
 * @author ${creator}
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ${className} implements ${interfaceNameService} {

    /**
     * ${ibatisDaoName}
     */
    @Resource
    private ${ibatisDaoName} ${ibatisDaoVar};

    /**
     * 新增${entityExplain}.
     *
     * @param record 实体
     * @return 主键
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int insertSelective(${entityName} record) {
        try {
            return ${ibatisDaoVar}.insertSelective(record);
        } catch (Exception e) {
            log.error("新增${entityExplain}:" , e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据新增异常");
        }
    }

    /**
     * 根据主键返回${entityExplain}实体.
     *
     * @param ${primaryKeyProperty}  主键
     * @return ${entityName}
     */
    @Override
    public ${entityName} getByPrimaryKey(${primaryKeyJavaType} ${primaryKeyProperty}) {
        try {
            return ${ibatisDaoVar}.selectByPrimaryKey(${primaryKeyProperty});
        } catch (Exception e) {
            log.error("查询${entityExplain}异常:" , e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据查询异常");
        }
    }

    /**
     * 选择更新${entityExplain}.
     *
     * @param record  实体
     * @return 更新状态
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(${entityName} record)  {
        try {
            return ${ibatisDaoVar}.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            log.error("更新${entityExplain}异常:" , e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据更新异常");
        }
    }

    /**
     * 全量更新${entityExplain}.
     *
     * @param record  实体
     * @return 更新状态
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int updateByPrimaryKey(${entityName} record)  {
        try {
            return ${ibatisDaoVar}.updateByPrimaryKey(record);
        } catch (Exception e) {
            log.error("更新${entityExplain}异常:" , e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据更新异常");
        }
    }

    /**
     * 批量插入${entityExplain}.
     *
     * @param list 实体${entityExplain}列表
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void insertBatch(List<${entityName}> list) {
        try {
            ${ibatisDaoVar}.insertBatch(list);
        } catch (Exception e) {
            log.error("插入${entityExplain}异常:" , e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据插入异常");
        }
    }

    /**
     * 根据主键删除数据.
     *
     * @param ${primaryKeyProperty}  主键
     * @return 删除成功标志位
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int deleteByPrimaryKey(${primaryKeyJavaType} ${primaryKeyProperty}) {
        try {
            return ${ibatisDaoVar}.deleteByPrimaryKey(${primaryKeyProperty});
        } catch (Exception e) {
            log.error("删除${entityExplain}异常:" , e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据删除异常");
        }
    }


    /**
     * 根据查询条件查询出列表.
     *
     * @param map  参数集合
     * @return ${entityExplain}列表
     */
    @Override
    public List<${entityName}> listBySelective(Map map) {
        try {
            return ${ibatisDaoVar}.selectBySelective(map);
        } catch (Exception e) {
            log.error("查询${entityExplain}异常:" , e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据查询异常");
        }
    }

    /**
     * 根据查询条件查询出列表.
     *
     * @param map  参数集合
     * @param pageIndex  开始页数
     * @param pageSize  长度
     * @return ${entityExplain}列表
    */
    @Override
    public List<${entityName}> listBySelective(Map map, Integer pageIndex, Integer pageSize) {
       try {
            PageHelper.startPage(pageIndex == null || pageIndex < 0 ? 1 : pageIndex, pageSize == null
                 || pageSize < 1 ? Constants.PAGESIZE_10 : pageSize, false);
           return ${ibatisDaoVar}.selectBySelective(map);
       } catch (Exception e) {
           log.error("查询${entityExplain}异常:" , e);
           throw new ProcessorException(ExceptionStatus.EX_1009, "数据查询异常");
       }
    }

    /**
     * 根据查询条件查询出数据集合的条数.
     *
     * @param map  参数集合
     * @return 条数
     */
    @Override
    public Integer countBySelective(Map map) {
        try {
            return ${ibatisDaoVar}.selectBySelectiveCount(map);
        } catch (Exception e) {
            log.error("查询${entityExplain}异常:" , e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据查询异常");
        }
    }
}
