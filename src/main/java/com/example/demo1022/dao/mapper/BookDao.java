package com.example.demo1022.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo1022.dao.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookDao extends BaseMapper<Book> {

    @Select("select * from tbl_book where id=#{id}")
    public Book getById(Integer id);



}
