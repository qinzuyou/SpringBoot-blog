package com.example.demo1022.label.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1022.article.entity.TArticle;
import com.example.demo1022.article.mapper.TArticleMapper;
import com.example.demo1022.label.entity.TLabel;
import com.example.demo1022.label.entity.TLabelDto;
import com.example.demo1022.label.mapper.TLabelMapper;
import com.example.demo1022.label.service.TLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author user
 * @since 2024-02-07
 */
@Service
@AllArgsConstructor
public class TLabelServiceImpl extends ServiceImpl<TLabelMapper, TLabel> implements TLabelService {

    private  final  TLabelMapper labelMapper;

    private  final TArticleMapper articleMapper;

    public  List<TLabelDto> uidAllLabel(Integer uid){




         List<TLabel> tLabels = uidLabel(uid);

         List<TLabelDto> tLabelDto = BeanUtil.copyToList(tLabels,TLabelDto.class);


         for(TLabelDto item : tLabelDto){
             Integer count = articleMapper.selectJoinCount(
                     new MPJLambdaWrapper<TArticle>()
                             .like(TArticle::getLabelList,item.getName())

             );

             item.setCount(count);

         }

return tLabelDto;




    }



    @Override
    public TLabel deName(String name, Integer uid) {


        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name", name);
        wrapper.eq("u_id", uid);

        return baseMapper.selectOne(wrapper);
    }



    //获取
    @Override
    public List<TLabel> uidLabel(Integer uid){

        if(uid==null){
            QueryWrapper wrapper = new QueryWrapper();

            return  baseMapper.selectList(wrapper);
        }else {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("u_id",uid);
            return  baseMapper.selectList(wrapper);
        }


    }
}


