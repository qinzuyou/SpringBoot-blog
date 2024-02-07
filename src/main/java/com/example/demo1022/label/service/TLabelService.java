package com.example.demo1022.label.service;

import com.example.demo1022.label.entity.TLabel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2024-02-07
 */
public interface TLabelService extends IService<TLabel> {
   TLabel  deName(String name);
}
