package roxia.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import roxia.annotation.DynamicQuery;
import roxia.annotation.NamedQuery;
import roxia.annotation.NativeQuery;
import roxia.annotation.NativeUpdate;
import roxia.annotation.Query;
import roxia.dao.DaoService;
import roxia.dao.DynamicNamedQueryProvider;
import roxia.dao.GenericEntityDao;
import roxia.dao.ModelClassSupport;
import roxia.dao.support.GenericEntityDaoWrapper;
import roxia.service.VelocityTemplateService;

public class QueryInterceptor implements MethodInterceptor {
	
	@Autowired
	@Qualifier("roxiaDaoService")
	private DaoService daoService;
	
	@Autowired
	private VelocityTemplateService templateService;
	
	@Autowired
	private DynamicNamedQueryProvider dnqProvider;
	
	private NamedQueryHandler namedQueryHandler;
	private QueryHandler queryHandler;
	private DynamicQueryHandler dynamicQueryHandler;
	private NativeQueryHandler nativeQueryHandler;
	
	private GenericEntityDaoWrapper daoWrapper;
	
	@PostConstruct
	public void init() throws Exception {
		namedQueryHandler = new NamedQueryHandler(daoService);
		queryHandler = new QueryHandler(daoService);
		dynamicQueryHandler = new DynamicQueryHandler(daoService, templateService, dnqProvider);
		nativeQueryHandler = new NativeQueryHandler(daoService, templateService, dnqProvider);
		daoWrapper = new GenericEntityDaoWrapper(daoService);
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method m = invocation.getMethod();
		Query query = m.getAnnotation(Query.class);
		NamedQuery namedQuery = m.getAnnotation(NamedQuery.class);
		DynamicQuery dynamicQuery = m.getAnnotation(DynamicQuery.class);
		NativeQuery nativeQuery = m.getAnnotation(NativeQuery.class);
		NativeUpdate nativeUpdate = m.getAnnotation(NativeUpdate.class);
		
		if(namedQuery !=null){
			return namedQueryHandler.handleNamedQuery(namedQuery, invocation);
		}else if(query != null){
			return queryHandler.handleQuery(query,invocation);
		}else if(dynamicQuery != null){
			return dynamicQueryHandler.handleDynamicQuery(dynamicQuery, invocation);
		}else if(nativeQuery != null){
			return nativeQueryHandler.handleNativeQuery(nativeQuery, invocation);
		}else if(nativeUpdate != null){
			return nativeQueryHandler.handleNativeUpdate(nativeUpdate, invocation);
		}else if(GenericEntityDao.class.isAssignableFrom(m.getDeclaringClass())){
			if(m.getName().equals("getByPrimaryKey") || 
					m.getName().equals("deleteByPrimaryKey") ||
					m.getName().equals("deleteAllByPrimaryKey")){
				ModelClassSupport mcs = (ModelClassSupport)invocation.getThis();
				Class<?> modelClass = mcs.getModelClass();
				List<Class<?>> list = new ArrayList<Class<?>>();
				List<Object> args = new ArrayList<Object>();
				list.add(Class.class);
				list.addAll(Arrays.asList(m.getParameterTypes()));
				args.add(modelClass);
				args.addAll(Arrays.asList(invocation.getArguments()));
				
				return GenericEntityDaoWrapper.class.getMethod(m.getName(), list.toArray(new Class<?>[]{})).
						invoke(daoWrapper, args.toArray());
			}else{
				return GenericEntityDaoWrapper.class.getMethod(m.getName(), m.getParameterTypes()).
					invoke(daoWrapper, invocation.getArguments());
			}
		}else
			return invocation.proceed();
	}

}


