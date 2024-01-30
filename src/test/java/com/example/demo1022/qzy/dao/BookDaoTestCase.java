package com.example.demo1022.qzy.dao;

import com.example.demo1022.Demo1022Application;

import com.example.demo1022.dao.mapper.BookDao;
import com.example.demo1022.dao.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest(classes = Demo1022Application.class)
public class BookDaoTestCase {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById(){
        System.out.println(bookDao.selectById(1));
    }

    //增
    @Test
    void testAdd(){
        Book book=new Book();
        book.setName("桃花原记");
        book.setType("2");
        book.setDescription("什么是世外桃源");

        bookDao.insert(book);

    }

    //删
    @Test
    void testDelete(){
        bookDao.deleteById(1);
    }

    //改
    @Test
    void testUpdate(){
        Book book=new Book();
        book.setId(3);
        book.setName("桃花原记");
        book.setType("2");
        book.setDescription("什么是世外桃源?陶渊明应该会知道。");

        bookDao.updateById(book);

    }

    //查
    @Test
    void testSelect(){
   System.out.println(bookDao.selectList(null));
    }

    //分页查询
    @Test
    void testSelectPage(){
        System.out.println(bookDao.selectList(null));
    }

    //条件查询
    @Test
    void testGetBY(){

    }

}
