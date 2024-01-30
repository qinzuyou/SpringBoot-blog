package com.example.demo1022.dao.controller;

import com.example.demo1022.dao.entity.Book;
import com.example.demo1022.dao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {


    @Autowired

    private StudentService studentService;

//    返回所有书本
    @GetMapping("/allBook")
    public List<Book> findAllBook(){
        return studentService.list();
    }

    //根据学号查询书本
    @GetMapping("/findBySno/{id}")
    public Book findBySno(@PathVariable("id") Integer id){

        return studentService.getById(id);
    }

    //根据学号删除书本信息（此方法可以使用软件postman进行测试）
    @DeleteMapping("/deleteBySno/id")
    public boolean deleteBySno(@PathVariable("id") Integer sno){
        return  studentService.removeById(sno);
    }



    //增加一个书本信息（此方法可以使用软件postman进行测试）,注意学号自增
    @PostMapping("/addBook")
    public boolean add(@RequestBody Book book){
        return studentService.save(book);
    }



    //根据学号修改学生信息（此方法可以使用软件postman进行测试），注意学号自增
    @PutMapping("/updateBook")
    public boolean update(@RequestBody Book book){
        return studentService.updateById(book);
    }


}
