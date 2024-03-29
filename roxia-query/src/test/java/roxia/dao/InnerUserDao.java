package roxia.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roxia.annotation.DynamicQuery;
import roxia.annotation.NamedQuery;
import roxia.annotation.NativeQuery;
import roxia.annotation.Query;
import roxia.annotation.QueryParam;
import roxia.model.InnerUser;


@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public interface InnerUserDao extends GenericEntityDao<InnerUser ,Long> {
	
	@NamedQuery
	List<InnerUser> findUsers(Sort[] sorts);
	
	@NamedQuery
	InnerUser findUserByLoginName(@QueryParam("loginName") String loginName);
	
	@Query(value="select u from InnerUser u where u.userName like '%' + :userName + '%'", pagable=false)
	List<InnerUser> findUserByName(int start, int pageSize, @QueryParam("userName") String userName, Sort[] sorts);
	
	@Query("select count(u) from InnerUser u")
	long findUserCount();
	
	@DynamicQuery(pagable = true)
	List<InnerUser> findByLoginName(int start, int pageSize, @QueryParam("loginName") String loginName);
	
	@DynamicQuery(value="InnerUser.findByLoginName", pagable = true)
	Pagination<InnerUser> findByLoginNameP(int start, int pageSize, @QueryParam("loginName") String loginName);
	
	@NativeQuery(pagable=true, alias="userName", clazzes=String.class)
	List<String> findByLoginNameSql(int start, int pageSize, 
			@QueryParam("loginName") String loginName, 
			@QueryParam("userName") String userName, 
			@QueryParam("password") String password);
	
	@NativeQuery(value="InnerUser.findByLoginNameSql", pagable=true, model=InnerUser.class, alias="userName")
	List<InnerUser> findByLoginNameSql1(int start, int pageSize, 
			@QueryParam("loginName") String loginName, 
			@QueryParam("userName") String userName, 
			@QueryParam("password") String password);
}
