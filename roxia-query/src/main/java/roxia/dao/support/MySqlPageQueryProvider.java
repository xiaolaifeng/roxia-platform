package roxia.dao.support;

import roxia.dao.PageQueryProvider;


public class MySqlPageQueryProvider implements PageQueryProvider {

	public String getPagableQuery(String sql, int begin, int count) {
		return sql + " limit " + begin + " , " + count;
	}

}
