package com.example.demo1022.articleType.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1022.article.entity.TArticle;
import com.example.demo1022.article.mapper.TArticleMapper;
import com.example.demo1022.articleType.entity.TArticleType;
import com.example.demo1022.articleType.entity.TArticleTypeDto;
import com.example.demo1022.articleType.mapper.TArticleTypeMapper;
import com.example.demo1022.articleType.service.TArticleTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1022.user.entity.TUserDto;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author user
 * @since 2023-11-28
 */
@Service
@AllArgsConstructor
public class TArticleTypeServiceImpl extends ServiceImpl<TArticleTypeMapper, TArticleType> implements TArticleTypeService {
        private  final TArticleTypeMapper articleTypeMapper;
//        private TArticleTypeService articleTypeService;

        @Override
        public List<TArticleTypeDto> uidAllType(Integer uid){

                QueryWrapper wrapper = new QueryWrapper();

               List<TArticleType>  allType = baseMapper.selectList(wrapper);
               List<TArticleTypeDto> dtoList= new ArrayList<>();


//                for(TArticleType item :allType){
//                    TArticleTypeDto dto = BeanUtil.copyProperties(item,TArticleTypeDto.class);
//                    System.out.println(dto);
//
//                    dtoList.add(dto);
//                }


//                TUserDto user2 = BeanUtil.copyProperties(user, TUserDto.class);
//
                List<TArticleTypeDto> articleTypeDto = BeanUtil.copyToList(allType,TArticleTypeDto.class);


            if (uid == null) {
                for (TArticleTypeDto item :articleTypeDto){
                    Integer count = articleTypeMapper.selectJoinCount(
                            new MPJLambdaWrapper<TArticleType>()
                                    .leftJoin(TArticle.class,TArticle::getTId,TArticleType::getTId)

                                    .eq(TArticleType::getName,item.getName())
                    );


                    item.setCount(count);
                }
            }else {
                for (TArticleTypeDto item :articleTypeDto){
                    Integer count = articleTypeMapper.selectJoinCount(
                            new MPJLambdaWrapper<TArticleType>()
                                    .leftJoin(TArticle.class,TArticle::getTId,TArticleType::getTId)
                                    .eq(TArticle::getUId,uid)
                                    .eq(TArticleType::getName,item.getName())
                    );


                    item.setCount(count);
                }
            }







                return articleTypeDto;
        }






}
