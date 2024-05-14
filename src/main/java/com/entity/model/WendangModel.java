package com.entity.model;

import com.entity.WendangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 文档
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WendangModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 文档编号
     */
    private String wendangUuidNumber;


    /**
     * 文档名称
     */
    private String wendangName;


    /**
     * 文档一级类型
     */
    private Integer wendangTypes;


    /**
     * 文档二级类型
     */
    private Integer wendangErjiTypes;


    /**
     * 文档关键字
     */
    private String wendangGuanjianzi;


    /**
     * 文档文件
     */
    private String wendangFile;


    /**
     * 文档介绍
     */
    private String wendangContent;


    /**
     * 逻辑删除
     */
    private Integer wendangDelete;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 最后修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date updateTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：文档编号
	 */
    public String getWendangUuidNumber() {
        return wendangUuidNumber;
    }


    /**
	 * 设置：文档编号
	 */
    public void setWendangUuidNumber(String wendangUuidNumber) {
        this.wendangUuidNumber = wendangUuidNumber;
    }
    /**
	 * 获取：文档名称
	 */
    public String getWendangName() {
        return wendangName;
    }


    /**
	 * 设置：文档名称
	 */
    public void setWendangName(String wendangName) {
        this.wendangName = wendangName;
    }
    /**
	 * 获取：文档一级类型
	 */
    public Integer getWendangTypes() {
        return wendangTypes;
    }


    /**
	 * 设置：文档一级类型
	 */
    public void setWendangTypes(Integer wendangTypes) {
        this.wendangTypes = wendangTypes;
    }
    /**
	 * 获取：文档二级类型
	 */
    public Integer getWendangErjiTypes() {
        return wendangErjiTypes;
    }


    /**
	 * 设置：文档二级类型
	 */
    public void setWendangErjiTypes(Integer wendangErjiTypes) {
        this.wendangErjiTypes = wendangErjiTypes;
    }
    /**
	 * 获取：文档关键字
	 */
    public String getWendangGuanjianzi() {
        return wendangGuanjianzi;
    }


    /**
	 * 设置：文档关键字
	 */
    public void setWendangGuanjianzi(String wendangGuanjianzi) {
        this.wendangGuanjianzi = wendangGuanjianzi;
    }
    /**
	 * 获取：文档文件
	 */
    public String getWendangFile() {
        return wendangFile;
    }


    /**
	 * 设置：文档文件
	 */
    public void setWendangFile(String wendangFile) {
        this.wendangFile = wendangFile;
    }
    /**
	 * 获取：文档介绍
	 */
    public String getWendangContent() {
        return wendangContent;
    }


    /**
	 * 设置：文档介绍
	 */
    public void setWendangContent(String wendangContent) {
        this.wendangContent = wendangContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getWendangDelete() {
        return wendangDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setWendangDelete(Integer wendangDelete) {
        this.wendangDelete = wendangDelete;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：最后修改时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 设置：最后修改时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
