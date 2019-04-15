package io.caoxx123.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.caoxx123.pojo.Category;

public interface CategoryMapper {
	@Insert("insert into category_ (name) values(#{name})")
	public int add(Category category);

	@Delete("delete from category_ where id=#{id}")
	public void delete(Category category);

	@Update("update category_ set name=#{name}")
	public int update(Category category);

	@Select("select * from category_ where id =#{id}")
	public Category getCategory(int id);

	@Select("select * from category_")
	public List<Category> list();
}
