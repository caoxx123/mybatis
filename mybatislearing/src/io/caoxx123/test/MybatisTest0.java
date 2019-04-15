package io.caoxx123.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import io.caoxx123.pojo.Category;
import io.caoxx123.pojo.Product;

public class MybatisTest0 {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		listAll(getSqlsession(resource));
	}

	// 获取sqlsession
	private static SqlSession getSqlsession(String resource) throws IOException {
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}

	// 遍历数据表
	private static void listAll(SqlSession session) {
		List<Category> cs = session.selectList("listCategory");
		for (Category category : cs) {
			System.out.println(category.getName());
		}
	}

	// 给表中增加数据对象
	private static void addCategory(SqlSession session, Category category) {
		if (category.getName() != null) {
			session.insert("addCategory", category);
			session.commit();
			session.close();
		} else {
			System.out.println("分类名称为空，请检查");
		}
	}

	// 删除方法
	private static void deleteCategory(SqlSession sqlSession, Category category) {
		if (category.getId() == 0) {
			System.out.println("ID错误删除失败");
		} else {
			sqlSession.delete("deleteCategory", category);
		}
	}

	// 修改方法
	private static void updateCategory(SqlSession sqlSession, Category category) {
		if (category.getId() == 0 && category.getName() == null) {
			System.out.println("category对象错误，请检查");
		} else {
			sqlSession.update("updateCategory", category);
		}
	}
	//根据id查询
	private static Category getCategoryByID(SqlSession sqlSession, int id) {
		if (id == 0) {
			System.out.println("");
			return null;
		} else {
			Category category = sqlSession.selectOne("getCategory", id);
			return category;
		}
	}
	private static List<Category> listCategoryByIdAndName(SqlSession sqlSession,HashMap<String,Object> params){
		List<Category> cs=sqlSession.selectList("listCategoryByIdAndName",params );
		return cs;
	}
}
