package com.example.demo1022.label.service;

import com.example.demo1022.label.entity.TLabel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1022.label.entity.TLabelDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2024-02-07
 */
public interface TLabelService extends IService<TLabel> {

   //验证标签重复性
   TLabel  deName(String name,Integer uid);

   //根据uid获取标签数量
   List<TLabel> uidLabel (Integer uid);


   //根据uid获取所有标签数量
   List<TLabelDto> uidAllLabel(Integer uid);


}
