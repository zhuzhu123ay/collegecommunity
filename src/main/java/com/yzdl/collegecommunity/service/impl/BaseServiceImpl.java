package com.yzdl.collegecommunity.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzdl.collegecommunity.common.util.Criteriable;
import com.yzdl.collegecommunity.common.util.IPageInfo;
import com.yzdl.collegecommunity.dao.BaseDao;
import com.yzdl.collegecommunity.service.IBaseService;




/**
 * 基础服务类
 * */
@Service
public abstract class BaseServiceImpl<M extends Serializable> implements IBaseService<M> {


	@Autowired
	public abstract BaseDao<M> getDao();

	@Override
	public M findById(Long id) {
		return getDao().findById(id);
	}

	@Override
	public List<M> findAll(Order... orders) {
		return getDao().findAll();
	}

	@Override
	public List<M> findAll(IPageInfo pageInfo, Order... orders) {
		return getDao().findAll(pageInfo, orders);
	}

	@Override
	public List<M> findByExample(M model, Order... orders) {
		return getDao().findByExample(model);
	}

	@Override
	public List<M> findByExample(M model, IPageInfo pageInfo, Order... orders) {
		return getDao().findByExample(model, pageInfo, orders);
	}

	@Override
	public List<M> findByCriteria(Criteriable criteriaObj, Order... orders) {
		return getDao().findByCriteria(criteriaObj, orders);
	}

	@Override
	public List<M> findByCriteria(Criteriable criteriaObj, IPageInfo pageInfo, Order... orders) {
		if (null == pageInfo) {
			return findByCriteria(criteriaObj, orders);
		}
		return getDao().findByCriteria(criteriaObj, pageInfo, orders);
	}

	@Override
	public M findUnique(M model) {
		return getDao().findUnique(model);
	}

	@Override
	public void save(M model) {
		getDao().save(model);
	}
	
	@Override
	public void saveOrUpdate(M model) {
		getDao().saveOrUpdate(model);
	}

	@Override
	public void update(M model) {
		getDao().update(model);
	}

	@Override
	public void deleteObject(M model) {
		getDao().deleteObject(model);
	}
	
	@Override
	public void delete(Long id) {
		getDao().delete(id);
	}

	@Override
	public void batchDelete(List<Long> ids) {
		for (Long k : ids) {
			delete(k);
		}
	}

	@Override
	public void batchDelete(Long[] ids) {
		for (Long k : ids) {
			delete(k);
		}
	}

	@Override
	public void batchSave(List<M> models) {
		for (M m : models) {
			save(m);
		}
	}

	@Override
	public void batchDeleteModel(List<M> models) {
		for (M m : models) {
			deleteObject(m);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {

		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// 返回表示此类型实际类型参数的 Type 对象的数组。
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		return (Class) params[index];
	}

	

}
