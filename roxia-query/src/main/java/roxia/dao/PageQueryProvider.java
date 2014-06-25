package roxia.dao;

public interface PageQueryProvider {
	String getPagableQuery(String sql, int begin, int count);
}
