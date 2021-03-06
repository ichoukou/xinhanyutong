package com.dbkj.meet.model;

import com.dbkj.meet.dic.Constant;
import com.dbkj.meet.model.base.BaseChargeback;
import com.dbkj.meet.utils.SqlUtil;
import com.jfinal.plugin.activerecord.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Chargeback extends BaseChargeback<Chargeback> {
	public static final Chargeback dao = new Chargeback();

	public static final String BEGIN_TIME="beginTime";
	public static final String END_TIME="endTime";
	public static final String NAME_KEY="b.name";

	public Page<Chargeback> getPage(Map<String,Object> paraMap){
		int currentPage=1;
		int pageSize= Constant.DEFAULT_PAGE_SIZE;

		StringBuilder where=new StringBuilder(200);
		List<Object> params=new ArrayList<Object>();

		if(paraMap.containsKey(Constant.CURRENT_PAGE_KEY)){
			currentPage= Integer.parseInt(paraMap.get(Constant.CURRENT_PAGE_KEY).toString());
			paraMap.remove(Constant.CURRENT_PAGE_KEY);
		}
		if(paraMap.containsKey(Constant.PAGE_SIZE_KEY)){
			pageSize= Integer.parseInt(paraMap.get(Constant.PAGE_SIZE_KEY).toString());
			paraMap.remove(Constant.PAGE_SIZE_KEY);
		}


		getParams(paraMap,where,params);
		where.append(" ORDER BY a.gmt_created DESC");

		return paginate(currentPage,pageSize, SqlUtil.getSql("getPage.select",this),
				SqlUtil.getSql("getPage.sqlExceptSelect",this).concat(where.toString()),
				params.toArray(new Object[params.size()]));
	}

	private void getParams(Map<String,Object> paraMap,StringBuilder where,List<Object> params){
		String createTime="a.gmt_created";

		if(paraMap.containsKey(Chargeback.BEGIN_TIME)&&!paraMap.containsKey(Chargeback.END_TIME)){
			where.append(" AND "+createTime+">=?");
			params.add(paraMap.get(Chargeback.BEGIN_TIME));
			paraMap.remove(Chargeback.BEGIN_TIME);
		}else if(!paraMap.containsKey(Chargeback.BEGIN_TIME)&&paraMap.containsKey(Chargeback.END_TIME)){
			where.append(" AND "+createTime+"<=?");
			params.add(paraMap.get(Chargeback.END_TIME));
			paraMap.remove(Chargeback.END_TIME);
		}else if(paraMap.containsKey(Chargeback.BEGIN_TIME)&&paraMap.containsKey(Chargeback.END_TIME)){
			where.append(" AND "+createTime+" BETWEEN ? AND ?");
			params.add(paraMap.get(Chargeback.BEGIN_TIME));
			params.add(paraMap.get(Chargeback.END_TIME));
			paraMap.remove(Chargeback.BEGIN_TIME);
			paraMap.remove(Chargeback.END_TIME);
		}
		if(paraMap.containsKey(Chargeback.NAME_KEY)){
			where.append(" AND ");
			where.append(Chargeback.NAME_KEY);
			where.append(" LIKE ?");
			params.add("%"+paraMap.get(Chargeback.NAME_KEY)+"%");
			paraMap.remove(Chargeback.NAME_KEY);
		}
		for(Map.Entry<String,Object> entry:paraMap.entrySet()){
			String key=entry.getKey();
			Object value=entry.getValue();
			if(value!=null){
				where.append(" AND ");
				where.append(key);
				where.append("=?");
				params.add(value);
			}
		}
	}

	public List<Chargeback> getList(Map<String,Object> paraMap){
		StringBuilder where=new StringBuilder(200);
		List<Object> params=new ArrayList<Object>();

		getParams(paraMap,where,params);

		return find(SqlUtil.getSql("getList",this).concat(where.toString()),params.toArray(new Object[params.size()]));
	}
}
