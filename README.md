# code


#### 主要用于自动生成 dao mapper service  serviceImpl 及对应的entity，因为主要用于自己的项目所以有点个性，大家可以根据需求进行修改。
entity的生成使用了Lombok,大家可以参考https://projectlombok.org/

具体代码样例如下：

## Mapper中包含，插入 全量更新、选择性更新、删除、批量插入，根据主键查询，一个默认的查询List及count；Mapper文件格式

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.ws.kislev.dao.BusinessOpportunityMapper">

    <resultMap id="BaseResultMap" type="com.ws.kislev.model.BusinessOpportunity">
            <id column="id" property="id" jdbcType="INTEGER"/>
            <result column="uuid" property="uuid" jdbcType="VARCHAR"/>
            <result column="user_name" property="userName" jdbcType="VARCHAR"/>
            <result column="mobile" property="mobile" jdbcType="VARCHAR"/>
            <result column="entrustor_type" property="entrustorType" jdbcType="VARCHAR"/>
            <result column="entrustor_name" property="entrustorName" jdbcType="VARCHAR"/>
            <result column="case_type" property="caseType" jdbcType="VARCHAR"/>
            <result column="case_sub_type" property="caseSubType" jdbcType="VARCHAR"/>
            <result column="province_id" property="provinceId" jdbcType="INTEGER"/>
            <result column="city_id" property="cityId" jdbcType="INTEGER"/>
            <result column="manager_id" property="managerId" jdbcType="INTEGER"/>
            <result column="claim_time" property="claimTime" jdbcType="TIMESTAMP"/>
            <result column="status" property="status" jdbcType="VARCHAR"/>
            <result column="company_id" property="companyId" jdbcType="INTEGER"/>
            <result column="create_time" property="createTime" jdbcType="TIMESTAMP"/>
            <result column="update_time" property="updateTime" jdbcType="TIMESTAMP"/>
            <result column="create_by" property="createBy" jdbcType="INTEGER"/>
            <result column="update_by" property="updateBy" jdbcType="INTEGER"/>
            <result column="remark" property="remark" jdbcType="VARCHAR"/>
            <result column="demand_type" property="demandType" jdbcType="VARCHAR"/>
            <result column="stage" property="stage" jdbcType="VARCHAR"/>
            <result column="case_stage" property="caseStage" jdbcType="VARCHAR"/>
            <result column="case_info" property="caseInfo" jdbcType="VARCHAR"/>
    </resultMap>

    <sql id="Base_Column_List">
    id,uuid,user_name,mobile,entrustor_type,entrustor_name,case_type,case_sub_type,province_id,city_id,manager_id,claim_time,status,company_id,create_time,update_time,create_by,update_by,remark,demand_type,stage,case_stage,case_info
    </sql>

    <insert id="insertSelective" parameterType="com.ws.kislev.model.BusinessOpportunity" useGeneratedKeys="true"
            keyProperty="id">
        insert into kv_business_opportunity
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="id != null">
            id,
            </if>
            <if test="uuid != null">
            uuid,
            </if>
            <if test="userName != null">
            user_name,
            </if>
            <if test="mobile != null">
            mobile,
            </if>
            <if test="entrustorType != null">
            entrustor_type,
            </if>
            <if test="entrustorName != null">
            entrustor_name,
            </if>
            <if test="caseType != null">
            case_type,
            </if>
            <if test="caseSubType != null">
            case_sub_type,
            </if>
            <if test="provinceId != null">
            province_id,
            </if>
            <if test="cityId != null">
            city_id,
            </if>
            <if test="managerId != null">
            manager_id,
            </if>
            <if test="claimTime != null">
            claim_time,
            </if>
            <if test="status != null">
            status,
            </if>
            <if test="companyId != null">
            company_id,
            </if>
            <if test="createTime != null">
            create_time,
            </if>
            <if test="updateTime != null">
            update_time,
            </if>
            <if test="createBy != null">
            create_by,
            </if>
            <if test="updateBy != null">
            update_by,
            </if>
            <if test="remark != null">
            remark,
            </if>
            <if test="demandType != null">
            demand_type,
            </if>
            <if test="stage != null">
            stage,
            </if>
            <if test="caseStage != null">
            case_stage,
            </if>
            <if test="caseInfo != null">
            case_info
            </if>
        </trim>
        values
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="id != null">
            #{id,jdbcType=INTEGER},
            </if>
            <if test="uuid != null">
            #{uuid,jdbcType=VARCHAR},
            </if>
            <if test="userName != null">
            #{userName,jdbcType=VARCHAR},
            </if>
            <if test="mobile != null">
            #{mobile,jdbcType=VARCHAR},
            </if>
            <if test="entrustorType != null">
            #{entrustorType,jdbcType=VARCHAR},
            </if>
            <if test="entrustorName != null">
            #{entrustorName,jdbcType=VARCHAR},
            </if>
            <if test="caseType != null">
            #{caseType,jdbcType=VARCHAR},
            </if>
            <if test="caseSubType != null">
            #{caseSubType,jdbcType=VARCHAR},
            </if>
            <if test="provinceId != null">
            #{provinceId,jdbcType=INTEGER},
            </if>
            <if test="cityId != null">
            #{cityId,jdbcType=INTEGER},
            </if>
            <if test="managerId != null">
            #{managerId,jdbcType=INTEGER},
            </if>
            <if test="claimTime != null">
            #{claimTime,jdbcType=TIMESTAMP},
            </if>
            <if test="status != null">
            #{status,jdbcType=VARCHAR},
            </if>
            <if test="companyId != null">
            #{companyId,jdbcType=INTEGER},
            </if>
            <if test="createTime != null">
            #{createTime,jdbcType=TIMESTAMP},
            </if>
            <if test="updateTime != null">
            #{updateTime,jdbcType=TIMESTAMP},
            </if>
            <if test="createBy != null">
            #{createBy,jdbcType=INTEGER},
            </if>
            <if test="updateBy != null">
            #{updateBy,jdbcType=INTEGER},
            </if>
            <if test="remark != null">
            #{remark,jdbcType=VARCHAR},
            </if>
            <if test="demandType != null">
            #{demandType,jdbcType=VARCHAR},
            </if>
            <if test="stage != null">
            #{stage,jdbcType=VARCHAR},
            </if>
            <if test="caseStage != null">
            #{caseStage,jdbcType=VARCHAR},
            </if>
            <if test="caseInfo != null">
            #{caseInfo,jdbcType=VARCHAR}
            </if>
        </trim>
    </insert>


    <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from kv_business_opportunity
        where
        id = #{id,jdbcType=INTEGER}
    </select>

    <update id="updateByPrimaryKeySelective" parameterType="com.ws.kislev.model.BusinessOpportunity">
        update kv_business_opportunity set
        <trim suffixOverrides=",">
                <if test="id != null">
                id = #{id,jdbcType=INTEGER},
                </if>
                <if test="uuid != null">
                uuid = #{uuid,jdbcType=VARCHAR},
                </if>
                <if test="userName != null">
                user_name = #{userName,jdbcType=VARCHAR},
                </if>
                <if test="mobile != null">
                mobile = #{mobile,jdbcType=VARCHAR},
                </if>
                <if test="entrustorType != null">
                entrustor_type = #{entrustorType,jdbcType=VARCHAR},
                </if>
                <if test="entrustorName != null">
                entrustor_name = #{entrustorName,jdbcType=VARCHAR},
                </if>
                <if test="caseType != null">
                case_type = #{caseType,jdbcType=VARCHAR},
                </if>
                <if test="caseSubType != null">
                case_sub_type = #{caseSubType,jdbcType=VARCHAR},
                </if>
                <if test="provinceId != null">
                province_id = #{provinceId,jdbcType=INTEGER},
                </if>
                <if test="cityId != null">
                city_id = #{cityId,jdbcType=INTEGER},
                </if>
                <if test="managerId != null">
                manager_id = #{managerId,jdbcType=INTEGER},
                </if>
                <if test="claimTime != null">
                claim_time = #{claimTime,jdbcType=TIMESTAMP},
                </if>
                <if test="status != null">
                status = #{status,jdbcType=VARCHAR},
                </if>
                <if test="companyId != null">
                company_id = #{companyId,jdbcType=INTEGER},
                </if>
                <if test="createTime != null">
                create_time = #{createTime,jdbcType=TIMESTAMP},
                </if>
                <if test="updateTime != null">
                update_time = #{updateTime,jdbcType=TIMESTAMP},
                </if>
                <if test="createBy != null">
                create_by = #{createBy,jdbcType=INTEGER},
                </if>
                <if test="updateBy != null">
                update_by = #{updateBy,jdbcType=INTEGER},
                </if>
                <if test="remark != null">
                remark = #{remark,jdbcType=VARCHAR},
                </if>
                <if test="demandType != null">
                demand_type = #{demandType,jdbcType=VARCHAR},
                </if>
                <if test="stage != null">
                stage = #{stage,jdbcType=VARCHAR},
                </if>
                <if test="caseStage != null">
                case_stage = #{caseStage,jdbcType=VARCHAR},
                </if>
                <if test="caseInfo != null">
                case_info = #{caseInfo,jdbcType=VARCHAR}
                </if>
        </trim>
        where
        id = #{id,jdbcType=INTEGER}
    </update>

    <insert id="insertBatch" parameterType="java.util.List">
        insert into kv_business_opportunity
        ( id, uuid, user_name, mobile, entrustor_type, entrustor_name, case_type, case_sub_type, province_id, city_id, manager_id, claim_time, status, company_id, create_time, update_time, create_by, update_by, remark, demand_type, stage, case_stage, case_info)
        values
        <foreach collection="list" item="item" index="index" separator=",">
            ( #{item.id}, #{item.uuid}, #{item.userName}, #{item.mobile}, #{item.entrustorType}, #{item.entrustorName}, #{item.caseType}, #{item.caseSubType}, #{item.provinceId}, #{item.cityId}, #{item.managerId}, #{item.claimTime}, #{item.status}, #{item.companyId}, #{item.createTime}, #{item.updateTime}, #{item.createBy}, #{item.updateBy}, #{item.remark}, #{item.demandType}, #{item.stage}, #{item.caseStage}, #{item.caseInfo})
        </foreach>
    </insert>

    <update id="updateByPrimaryKey" parameterType="com.ws.kislev.model.BusinessOpportunity">
        update kv_business_opportunity set
        <trim suffixOverrides=",">
                id = #{id,jdbcType=INTEGER},
                uuid = #{uuid,jdbcType=VARCHAR},
                user_name = #{userName,jdbcType=VARCHAR},
                mobile = #{mobile,jdbcType=VARCHAR},
                entrustor_type = #{entrustorType,jdbcType=VARCHAR},
                entrustor_name = #{entrustorName,jdbcType=VARCHAR},
                case_type = #{caseType,jdbcType=VARCHAR},
                case_sub_type = #{caseSubType,jdbcType=VARCHAR},
                province_id = #{provinceId,jdbcType=INTEGER},
                city_id = #{cityId,jdbcType=INTEGER},
                manager_id = #{managerId,jdbcType=INTEGER},
                claim_time = #{claimTime,jdbcType=TIMESTAMP},
                status = #{status,jdbcType=VARCHAR},
                company_id = #{companyId,jdbcType=INTEGER},
                create_time = #{createTime,jdbcType=TIMESTAMP},
                update_time = #{updateTime,jdbcType=TIMESTAMP},
                create_by = #{createBy,jdbcType=INTEGER},
                update_by = #{updateBy,jdbcType=INTEGER},
                remark = #{remark,jdbcType=VARCHAR},
                demand_type = #{demandType,jdbcType=VARCHAR},
                stage = #{stage,jdbcType=VARCHAR},
                case_stage = #{caseStage,jdbcType=VARCHAR},
                case_info = #{caseInfo,jdbcType=VARCHAR}
        </trim>
        where
        id = #{id,jdbcType=INTEGER}
    </update>

    <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
        delete from kv_business_opportunity
        where
        id = #{id,jdbcType=INTEGER}
    </delete>

    <sql id="listWhereOptions">
        <where>
            <if test="id != null">
                and id = #{id,jdbcType=INTEGER}
            </if>
            <if test="uuid != null">
                and uuid = #{uuid,jdbcType=VARCHAR}
            </if>
            <if test="userName != null">
                and user_name = #{userName,jdbcType=VARCHAR}
            </if>
            <if test="mobile != null">
                and mobile = #{mobile,jdbcType=VARCHAR}
            </if>
            <if test="entrustorType != null">
                and entrustor_type = #{entrustorType,jdbcType=VARCHAR}
            </if>
            <if test="entrustorName != null">
                and entrustor_name = #{entrustorName,jdbcType=VARCHAR}
            </if>
            <if test="caseType != null">
                and case_type = #{caseType,jdbcType=VARCHAR}
            </if>
            <if test="caseSubType != null">
                and case_sub_type = #{caseSubType,jdbcType=VARCHAR}
            </if>
            <if test="provinceId != null">
                and province_id = #{provinceId,jdbcType=INTEGER}
            </if>
            <if test="cityId != null">
                and city_id = #{cityId,jdbcType=INTEGER}
            </if>
            <if test="managerId != null">
                and manager_id = #{managerId,jdbcType=INTEGER}
            </if>
            <if test="claimTime != null">
                and claim_time = #{claimTime,jdbcType=TIMESTAMP}
            </if>
            <if test="status != null">
                and status = #{status,jdbcType=VARCHAR}
            </if>
            <if test="companyId != null">
                and company_id = #{companyId,jdbcType=INTEGER}
            </if>
            <if test="createTime != null">
                and create_time = #{createTime,jdbcType=TIMESTAMP}
            </if>
            <if test="updateTime != null">
                and update_time = #{updateTime,jdbcType=TIMESTAMP}
            </if>
            <if test="createBy != null">
                and create_by = #{createBy,jdbcType=INTEGER}
            </if>
            <if test="updateBy != null">
                and update_by = #{updateBy,jdbcType=INTEGER}
            </if>
            <if test="remark != null">
                and remark = #{remark,jdbcType=VARCHAR}
            </if>
            <if test="demandType != null">
                and demand_type = #{demandType,jdbcType=VARCHAR}
            </if>
            <if test="stage != null">
                and stage = #{stage,jdbcType=VARCHAR}
            </if>
            <if test="caseStage != null">
                and case_stage = #{caseStage,jdbcType=VARCHAR}
            </if>
            <if test="caseInfo != null">
                and case_info = #{caseInfo,jdbcType=VARCHAR}
            </if>
        </where>
    </sql>

    <select id="selectBySelective" parameterType="java.util.Map" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from kv_business_opportunity
        <include refid="listWhereOptions"/>
    </select>

    <select id="selectBySelectiveCount" parameterType="java.util.Map" resultType="java.lang.Integer">
        select count(1) from kv_business_opportunity
        <include refid="listWhereOptions"/>
    </select>
</mapper>

## dao文件
package com.ws.kislev.dao;

import com.ws.kislev.model.BusinessOpportunity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
* 商机Dao.
*/
public interface BusinessOpportunityMapper {
	/**
	 * 新增商机.
	 *
	 * @param record 实体
	 * @return 主键
	 */
	int insertSelective(BusinessOpportunity record);

	/**
	 * 根据主键返回商机实体.
	 *
	 * @param id  主键
	 * @return BusinessOpportunity
	 */
	BusinessOpportunity selectByPrimaryKey(@Param("id") Integer id);

	/**
     * 选择更新商机.
	 *
	 * @param record  实体
	 * @return 更新状态
	 */
	int updateByPrimaryKeySelective(BusinessOpportunity record);

	/**
	 * 全量更新商机.
	 *
	 * @param record  实体
	 * @return 更新状态
	 */
	int updateByPrimaryKey(BusinessOpportunity record);

	/**
	 * 批量插入商机.
	 *
	 * @param list 实体商机列表
	 */
	void insertBatch(List<BusinessOpportunity> list);

	/**
	 * 根据主键删除数据.
	 *
	 * @param id  主键
	 * @return 删除成功标志位
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 根据查询条件查询出列表.
	 *
	 * @param map  参数集合
	 * @return 商机列表
	 */
	List<BusinessOpportunity> selectBySelective(Map map);

	/**
	 * 根据查询条件查询出数据集合的条数.
	 *
	 * @param map  参数集合
	 * @return 条数
	 */
	Integer selectBySelectiveCount(Map map);
}

## entity文件

package com.ws.kislev.model;

import lombok.Data;
import java.util.Date;
@Data
public class BusinessOpportunity {

	/**
	 * 
	 */
	private Integer id;
   
	/**
	 * UUID
	 */
	private String uuid;
   
	/**
	 * 案件联系人
	 */
	private String userName;
   
	/**
	 * 手机号码
	 */
	private String mobile;
   
	/**
	 * 委托方类型1个人2企业
	 */
	private String entrustorType;
   
	/**
	 * 委托方名称
	 */
	private String entrustorName;
   
	/**
	 * 案件类型1诉讼2非诉
	 */
	private String caseType;
   
	/**
	 * 案件类型同委案
	 */
	private String caseSubType;
   
	/**
	 * 省id
	 */
	private Integer provinceId;
   
	/**
	 * 城市id
	 */
	private Integer cityId;
   
	/**
	 * 负责人ID
	 */
	private Integer managerId;
   
	/**
	 * 市场部负责人认领商机时间
	 */
	private Date claimTime;
   
	/**
	 * 状态
	 */
	private String status;
   
	/**
	 * 所属企业ID
	 */
	private Integer companyId;
   
	/**
	 * 插入时间
	 */
	private Date createTime;
   
	/**
	 * 更新时间
	 */
	private Date updateTime;
   
	/**
	 * 创建人
	 */
	private Integer createBy;
   
	/**
	 * 更新人
	 */
	private Integer updateBy;
   
	/**
	 * 备注
	 */
	private String remark;
   
	/**
	 * 需求类型 找律师还是异地查档
	 */
	private String demandType;
   
	/**
	 * 审理程序
	 */
	private String stage;
   
	/**
	 * 案件阶段
	 */
	private String caseStage;
   
	/**
	 * 案情简介
	 */
	private String caseInfo;
   
}

## service 文件

package com.ws.kislev.service;

import com.ws.kislev.model.BusinessOpportunity;

import java.util.List;
import java.util.Map;


/**
* 商机Service.
*/
public interface BusinessOpportunityService {

	/**
	 * 新增商机.
	 *
	 * @param record 实体
	 * @return 主键
	 */
	int insertSelective(BusinessOpportunity record);

	/**
	 * 根据主键返回商机实体.
	 *
	 * @param id  主键
	 * @return BusinessOpportunity
	 */
	BusinessOpportunity selectByPrimaryKey(Integer id);

	/**
     * 选择更新商机.
	 *
	 * @param record  实体
	 * @return 更新状态
	 */
	int updateByPrimaryKeySelective(BusinessOpportunity record);

	/**
	 * 全量更新商机.
	 *
	 * @param record  实体
	 * @return 更新状态
	 */
	int updateByPrimaryKey(BusinessOpportunity record);

	/**
	 * 批量插入商机.
	 *
	 * @param list 实体商机列表
	 */
	void insertBatch(List<BusinessOpportunity> list);

	/**
	 * 根据主键删除数据.
	 *
	 * @param id  主键
	 * @return 删除成功标志位
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 根据查询条件查询出列表.
	 *
	 * @param map  参数集合
	 * @return 商机列表
	 */
	List<BusinessOpportunity> selectBySelective(Map map);

	/**
	 * 根据查询条件查询出数据集合的条数.
	 *
	 * @param map  参数集合
	 * @return 条数
	 */
	Integer selectBySelectiveCount(Map map);
}

## serviceImpl文件格式

package com.ws.kislev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.ws.kislev.service.BusinessOpportunityService;
import com.ws.kislev.model.BusinessOpportunity;
import com.ws.kislev.dao.BusinessOpportunityMapper;

import com.ws.kislev.common.exception.ExceptionStatus;
import com.ws.kislev.common.exception.ProcessorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

/**
 * Created by chenqian on 2016/9/12.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BusinessOpportunityServiceImpl implements BusinessOpportunityService{

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessOpportunityServiceImpl.class);
    /**
     * BusinessOpportunityMapper
     */
    @Resource
    private BusinessOpportunityMapper businessOpportunityMapper;
	
    /**
     * 新增商机.
     *
     * @param record 实体
     * @return 主键
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int insertSelective(BusinessOpportunity record) {
        try {
            return businessOpportunityMapper.insertSelective(record);
        } catch (Exception e) {
            LOGGER.error("新增商机:" + e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据新增异常");
        }
    }

    /**
     * 根据主键返回商机实体.
     *
     * @param id  主键
     * @return BusinessOpportunity
     */
    @Override
    public BusinessOpportunity selectByPrimaryKey(Integer id) {
        try {
            return businessOpportunityMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("查询商机异常:" + e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据查询异常");
        }
    }

    /**
     * 选择更新商机.
     *
     * @param record  实体
     * @return 更新状态
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(BusinessOpportunity record)  {
        try {
            return businessOpportunityMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            LOGGER.error("更新商机异常:" + e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据更新异常");
        }
    }

    /**
     * 全量更新商机.
     *
     * @param record  实体
     * @return 更新状态
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int updateByPrimaryKey(BusinessOpportunity record)  {
        try {
            return businessOpportunityMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            LOGGER.error("更新商机异常:" + e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据更新异常");
        }
    }

    /**
     * 批量插入商机.
     *
     * @param list 实体商机列表
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void insertBatch(List<BusinessOpportunity> list) {
        try {
            businessOpportunityMapper.insertBatch(list);
        } catch (Exception e) {
            LOGGER.error("插入商机异常:" + e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据插入异常");
        }
    }

    /**
     * 根据主键删除数据.
     *
     * @param id  主键
     * @return 删除成功标志位
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        try {
            return businessOpportunityMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("删除商机异常:" + e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据删除异常");
        }
    }


    /**
     * 根据查询条件查询出列表.
     *
     * @param map  参数集合
     * @return 商机列表
     */
    @Override
    public List<BusinessOpportunity> selectBySelective(Map map) {
        try {
            return businessOpportunityMapper.selectBySelective(map);
        } catch (Exception e) {
            LOGGER.error("查询商机异常:" + e);
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
    public Integer selectBySelectiveCount(Map map) {
        try {
            return businessOpportunityMapper.selectBySelectiveCount(map);
        } catch (Exception e) {
            LOGGER.error("查询商机异常:" + e);
            throw new ProcessorException(ExceptionStatus.EX_1009, "数据查询异常");
        }
    }
}
