package cn.edu.hbue.dao;

public abstract class AbstractDao {
//	private PreparedStatement stmt = null;
//	private ResultSet rs = null;
	
	public Object[] queryDao(String sql, Object[] objectArray) {
		//TODO:添加查询代码
		return objectArray;
	}
	
	public int updateDao(String sql, Object[] objectArray) {
		//TODO:添加更改代码
		int updated_count = 0;
		return updated_count;
	}
	
	abstract public void fillObjectArray();
}
