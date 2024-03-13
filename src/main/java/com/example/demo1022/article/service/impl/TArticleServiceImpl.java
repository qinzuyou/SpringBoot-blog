package com.example.demo1022.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.MPJMappingWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1022.article.entity.TArticle;
import com.example.demo1022.article.entity.TArticleDto;
import com.example.demo1022.article.entity.TArticleMonth;
import com.example.demo1022.article.entity.TArticleYear;
import com.example.demo1022.article.mapper.TArticleMapper;
import com.example.demo1022.article.service.TArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1022.articleType.entity.TArticleType;
import com.example.demo1022.comment.entity.TCommentDto;
import com.example.demo1022.comment.service.TCommentService;
import com.example.demo1022.label.entity.TLabelDto;
import com.example.demo1022.user.entity.TUser;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author user
 * @since 2023-11-26
 */
@Service
@AllArgsConstructor
public class TArticleServiceImpl extends ServiceImpl<TArticleMapper, TArticle> implements TArticleService {



    private final TArticleMapper tArticleMapper;

    private TCommentService commentService;


    //返回热门文章

    @Override
    public IPage<TArticleDto> hotArticle(int pages, int size){



        IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                new Page<TArticleDto>(pages,size),
                TArticleDto.class,
                new MPJLambdaWrapper<TArticle>()
                        .selectAll(TArticle.class)
                        .selectAs(TUser::getAccount,TArticleDto::getAccount)
                        .selectAs(TUser::getNickname,TArticleDto::getNickname)
                        .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                        .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                        .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                        .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)

                        .orderByDesc(TArticle::getHits)

        );

        List<TArticleDto> list2 =list.getRecords();

        for(TArticleDto item:list2){
            IPage<TCommentDto> commentDto =   commentService.aidCommentCount(1,1,item.getAId());

            int shu = (int) commentDto.getTotal();
            int shu2 = (int) list.getTotal();
            item.setTotal(shu2);
            item.setCommentCount(shu);
        }

        return list;

    }
    //返回推荐文章
    @Override
    public IPage<TArticleDto> Recommend(int pages, int size,String re){
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//
//        queryWrapper.eq("recommend",re);
//
//        return  baseMapper.selectList(queryWrapper);


        IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                new Page<TArticleDto>(pages,size),
                TArticleDto.class,
                new MPJLambdaWrapper<TArticle>()
                        .selectAll(TArticle.class)
                        .selectAs(TUser::getAccount,TArticleDto::getAccount)
                        .selectAs(TUser::getNickname,TArticleDto::getNickname)
                        .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                        .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                        .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                        .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)
                        .eq(TArticle::getRecommend,re)
                        .orderByDesc(TArticle::getAId)

        );

        List<TArticleDto> list2 =list.getRecords();

        for(TArticleDto item:list2){
            IPage<TCommentDto> commentDto =   commentService.aidCommentCount(1,1,item.getAId());

            int shu = (int) commentDto.getTotal();
            int shu2 = (int) list.getTotal();
            item.setTotal(shu2);
            item.setCommentCount(shu);
        }

        return list;

    }
//    文章归档
    @Override
    public List<TArticleYear>  Stats(Integer uid){

        TArticle article = new TArticle();

        QueryWrapper<TArticle> wrapper = new QueryWrapper<>(article);
        wrapper.last("limit 1");
        wrapper.orderByDesc("a_id");


        TArticle lastArticle = baseMapper.selectOne(wrapper);

        QueryWrapper wrapper2 = new QueryWrapper();

        wrapper2.last("limit 1");

        TArticle firstArticle = baseMapper.selectOne(wrapper2);


        //获取当前年月日,以此创建日期目录
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        System.out.println(sdf.format(date));

        //最新的一年
        int newYear =  lastArticle.getCreateTime().getYear();
        //最旧的一年
        int oldYear=firstArticle.getCreateTime().getYear();


//      int len = newYear-oldYear;
//      if (len<=0) len=1;

        //年份集合
        List<TArticleYear> years = new ArrayList<>();




        for(int i=newYear;i>=oldYear;i--){
            //月份集合
            List<TArticleMonth> months = new ArrayList<>();

            //筛选一月份到十二月份
            for(int j=12;j>=1;j--){
                        System.out.println(i+"-"+j+"-1"+"/"+i+"-"+j+"-31");
                LocalDateTime startTime;
                LocalDateTime endTime;
                        if(j>9){
                           startTime = LocalDateTime.parse(i+"-"+j+"-01"+" 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                             endTime = LocalDateTime.parse(i+"-"+j+"-31"+" 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        }else {
                            startTime = LocalDateTime.parse(i+"-0"+j+"-01"+" 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                             endTime = LocalDateTime.parse(i+"-0"+j+"-31"+" 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        }
                 //根据日期筛选
                QueryWrapper wrapper3 = new QueryWrapper();
                wrapper3.orderByDesc("a_id");
                wrapper3.between("create_time",startTime,endTime);
                List<TArticle> list = baseMapper.selectList(wrapper3);
                List<TArticleDto> articleDto = BeanUtil.copyToList(list, TArticleDto.class);

               for(TArticleDto item:articleDto){


                    item.setArticleDay(item.getCreateTime().getDayOfMonth() );
               }



                //月份
                TArticleMonth month = new TArticleMonth(j,articleDto);

                months.add(month);

//                System.out.println(startTime+"/"+endTime);
//
//
//                System.out.println(list);
            }

            //年份
            TArticleYear year = new TArticleYear(i,months);
            years.add(year);
        }


//        System.out.println("**************");
//
//        System.out.println(firstArticle);
//        System.out.println(lastArticle);
//        System.out.println("**************");


        return years;
    }




    //根据文章id查
    @Override
    public TArticleDto aidArticle(Integer aid){

        TArticleDto list =tArticleMapper.selectJoinOne(

                TArticleDto.class,
                new MPJLambdaWrapper<TArticle>()
                        .selectAll(TArticle.class)
                        .selectAs(TUser::getAccount,TArticleDto::getAccount)
                        .selectAs(TUser::getNickname,TArticleDto::getNickname)
                        .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                        .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                        .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)
                        .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                        .eq(TArticle::getAId,aid)

        );

        return  list;

    }







    //根据文章类型查询文章
    @Override
    public IPage<TArticleDto> TypeArticle(int pages,int size,String type){

        if(type.isEmpty()){
            IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                    new Page<TArticleDto>(pages,size),
                    TArticleDto.class,
                    new MPJLambdaWrapper<TArticle>()
                            .selectAll(TArticle.class)
                            .selectAs(TUser::getAccount,TArticleDto::getAccount)
                            .selectAs(TUser::getNickname,TArticleDto::getNickname)
                            .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                            .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                            .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                            .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)
                            .orderByDesc(TArticle::getAId)

            );

            List<TArticleDto> list2 =list.getRecords();

            for(TArticleDto item:list2){
                IPage<TCommentDto> commentDto =   commentService.aidCommentCount(1,1,item.getAId());

                int shu = (int) commentDto.getTotal();
                int shu2 = (int) list.getTotal();
                item.setTotal(shu2);
                item.setCommentCount(shu);
            }

            return  list;
        }else {
            IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                    new Page<TArticleDto>(pages,size),
                    TArticleDto.class,
                    new MPJLambdaWrapper<TArticle>()
                            .selectAll(TArticle.class)
                            .selectAs(TUser::getAccount,TArticleDto::getAccount)
                            .selectAs(TUser::getNickname,TArticleDto::getNickname)
                            .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                            .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                            .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                            .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)
                            .eq(TArticleType::getName,type)
                            .orderByDesc(TArticle::getAId)

            );

            List<TArticleDto> list2 =list.getRecords();

            for(TArticleDto item:list2){
                IPage<TCommentDto> commentDto =   commentService.aidCommentCount(1,1,item.getAId());

                int shu = (int) commentDto.getTotal();
                int shu2 = (int) list.getTotal();
                item.setTotal(shu2);
                item.setCommentCount(shu);
            }

            return  list;
        }



    }


    //模糊查询
    @Override
    public IPage<TArticleDto> searchArticle(int pages,int size,String val){





        IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                new Page<TArticleDto>(pages,size),
                TArticleDto.class,
                new MPJLambdaWrapper<TArticle>()
                        .selectAll(TArticle.class)
                        .selectAs(TUser::getAccount,TArticleDto::getAccount)
                        .selectAs(TUser::getNickname,TArticleDto::getNickname)
                        .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                        .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                        .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                        .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)
                        .like(TArticleType::getName,val)
                        .or()
                        .like(TArticle::getContent,val)
                        .or()
                        .like(TArticle::getTitle,val)

                        .orderByDesc(TArticle::getAId)

        );

        List<TArticleDto> list2 =list.getRecords();

        for(TArticleDto item:list2){
            IPage<TCommentDto> commentDto =   commentService.aidCommentCount(1,1,item.getAId());

            int shu = (int) commentDto.getTotal();
            int shu2 = (int) list.getTotal();
            item.setTotal(shu2);
            item.setCommentCount(shu);
        }

        return  list;

    }



    //根据标签模糊查询
    @Override
    public IPage<TArticleDto> labelArticle(int pages,int size,String val){





        IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                new Page<TArticleDto>(pages,size),
                TArticleDto.class,
                new MPJLambdaWrapper<TArticle>()
                        .selectAll(TArticle.class)
                        .selectAs(TUser::getAccount,TArticleDto::getAccount)
                        .selectAs(TUser::getNickname,TArticleDto::getNickname)
                        .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                        .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                        .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                        .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)
                        .like(TArticle::getLabelList,val)


                        .orderByDesc(TArticle::getAId)

        );

        List<TArticleDto> list2 =list.getRecords();

        for(TArticleDto item:list2){
            IPage<TCommentDto> commentDto =   commentService.aidCommentCount(1,1,item.getAId());

            int shu = (int) commentDto.getTotal();
            int shu2 = (int) list.getTotal();
            item.setTotal(shu2);
            item.setCommentCount(shu);
        }

        return  list;

    }

    //根据用户id返回
    @Override
    public IPage<TArticleDto> uidArticle(int pages,int size,Integer uid,String type){
    System.out.println(type);
        if(type==""){
            IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                    new Page<TArticleDto>(pages,size),
                    TArticleDto.class,
                    new MPJLambdaWrapper<TArticle>()
                            .selectAll(TArticle.class)
                            .selectAs(TUser::getAccount,TArticleDto::getAccount)
                            .selectAs(TUser::getNickname,TArticleDto::getNickname)
                            .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                            .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                            .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                            .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)
                            .eq(TArticle::getUId,uid)
                            .orderByDesc(TArticle::getAId)

            );


            return  list;


        }else {
            IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                    new Page<TArticleDto>(pages,size),
                    TArticleDto.class,
                    new MPJLambdaWrapper<TArticle>()
                            .selectAll(TArticle.class)
                            .selectAs(TUser::getAccount,TArticleDto::getAccount)
                            .selectAs(TUser::getNickname,TArticleDto::getNickname)
                            .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                            .selectAs(TArticleType::getName,TArticleDto::getTypeName)
                            .leftJoin(TUser.class,TUser::getUid,TArticle::getUId)
                            .leftJoin(TArticleType.class,TArticleType::getTId,TArticle::getTId)
                            .eq(TArticle::getUId,uid)
                            .eq(TArticleType::getName,type)
                            .orderByDesc(TArticle::getAId)

            );


            return  list;
        }



    }


    //下一篇
    @Override
    public  TArticle articleNext(Integer aid){
        QueryWrapper wrapper = new QueryWrapper();
    System.out.println(aid);

    //小于aid
        wrapper.lt("a_id",aid);
        wrapper.orderByAsc("a_id");
        wrapper.last("LIMIT 1");
        return  baseMapper.selectOne(wrapper);
    }

//上一篇
    @Override
    public  TArticle articlePrevious( Integer aid){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("a_id",aid);

        //大于aid
        wrapper.orderByDesc("a_id");
        wrapper.last("LIMIT 1");


        return  baseMapper.selectOne(wrapper);
    }
}
