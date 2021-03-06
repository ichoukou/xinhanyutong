package com.dbkj.meet.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseChargeback<M extends BaseChargeback<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public void setCid(java.lang.Long cid) {
		set("cid", cid);
	}

	public java.lang.Long getCid() {
		return get("cid");
	}

	public void setFee(java.math.BigDecimal fee) {
		set("fee", fee);
	}

	public java.math.BigDecimal getFee() {
		return get("fee");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setGmtCreated(java.util.Date gmtCreated) {
		set("gmt_created", gmtCreated);
	}

	public java.util.Date getGmtCreated() {
		return get("gmt_created");
	}

	public void setGmtModified(java.util.Date gmtModified) {
		set("gmt_modified", gmtModified);
	}

	public java.util.Date getGmtModified() {
		return get("gmt_modified");
	}

}
