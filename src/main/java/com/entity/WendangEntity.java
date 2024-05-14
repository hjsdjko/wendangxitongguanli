package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 文档
 *
 * @author 
 * @email
 */
@TableName("wendang")
public class WendangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WendangEntity() {

	}

	public WendangEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 文档编号
     */
    @TableField(value = "wendang_uuid_number")

    private String wendangUuidNumber;


    /**
     * 文档名称
     */
    @TableField(value = "wendang_name")

    private String wendangName;


    /**
     * 文档一级类型
     */
    @TableField(value = "wendang_types")

    private Integer wendangTypes;


    /**
     * 文档二级类型
     */
    @TableField(value = "wendang_erji_types")

    private Integer wendangErjiTypes;


    /**
     * 文档关键字
     */
    @TableField(value = "wendang_guanjianzi")

    private String wendangGuanjianzi;


    /**
     * 文档文件
     */
    @TableField(value = "wendang_file")

    private String wendangFile;


    /**
     * 文档介绍
     */
    @TableField(value = "wendang_content")

    private String wendangContent;


    /**
     * 逻辑删除
     */
    @TableField(value = "wendang_delete")

    private Integer wendangDelete;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 最后修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "update_time",fill = FieldFill.UPDATE)

    private Date updateTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：文档编号
	 */
    public String getWendangUuidNumber() {
        return wendangUuidNumber;
    }
    /**
	 * 获取：文档编号
	 */

    public void setWendangUuidNumber(String wendangUuidNumber) {
        this.wendangUuidNumber = wendangUuidNumber;
    }
    /**
	 * 设置：文档名称
	 */
    public String getWendangName() {
        return wendangName;
    }
    /**
	 * 获取：文档名称
	 */

    public void setWendangName(String wendangName) {
        this.wendangName = wendangName;
    }
    /**
	 * 设置：文档一级类型
	 */
    public Integer getWendangTypes() {
        return wendangTypes;
    }
    /**
	 * 获取：文档一级类型
	 */

    public void setWendangTypes(Integer wendangTypes) {
        this.wendangTypes = wendangTypes;
    }
    /**
	 * 设置：文档二级类型
	 */
    public Integer getWendangErjiTypes() {
        return wendangErjiTypes;
    }
    /**
	 * 获取：文档二级类型
	 */

    public void setWendangErjiTypes(Integer wendangErjiTypes) {
        this.wendangErjiTypes = wendangErjiTypes;
    }
    /**
	 * 设置：文档关键字
	 */
    public String getWendangGuanjianzi() {
        return wendangGuanjianzi;
    }
    /**
	 * 获取：文档关键字
	 */

    public void setWendangGuanjianzi(String wendangGuanjianzi) {
        this.wendangGuanjianzi = wendangGuanjianzi;
    }
    /**
	 * 设置：文档文件
	 */
    public String getWendangFile() {
        return wendangFile;
    }
    /**
	 * 获取：文档文件
	 */

    public void setWendangFile(String wendangFile) {
        this.wendangFile = wendangFile;
    }
    /**
	 * 设置：文档介绍
	 */
    public String getWendangContent() {
        return wendangContent;
    }
    /**
	 * 获取：文档介绍
	 */

    public void setWendangContent(String wendangContent) {
        this.wendangContent = wendangContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getWendangDelete() {
        return wendangDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setWendangDelete(Integer wendangDelete) {
        this.wendangDelete = wendangDelete;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：最后修改时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
	 * 获取：最后修改时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Wendang{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", wendangUuidNumber=" + wendangUuidNumber +
            ", wendangName=" + wendangName +
            ", wendangTypes=" + wendangTypes +
            ", wendangErjiTypes=" + wendangErjiTypes +
            ", wendangGuanjianzi=" + wendangGuanjianzi +
            ", wendangFile=" + wendangFile +
            ", wendangContent=" + wendangContent +
            ", wendangDelete=" + wendangDelete +
            ", insertTime=" + insertTime +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
        "}";
    }
}
