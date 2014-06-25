package roxia.dao.support;

import roxia.dao.PageQueryProvider;


public class PostgreSqlPageQueryProvider implements PageQueryProvider {

	public String getPagableQuery(String sql, int begin, int count) {
		return sql + " limit " + count + " offset " + begin;
	}

}
