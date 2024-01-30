package com.example.demo1022.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1022.dao.mapper.BookDao;
import com.example.demo1022.dao.service.StudentService;
import com.example.demo1022.dao.entity.Book;
import org.springframework.stereotype.Service;

//服务实现类

@Service
public class StudentServiceImpl extends ServiceImpl<BookDao, Book> implements StudentService {
}
