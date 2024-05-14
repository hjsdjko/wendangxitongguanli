
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 文档
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/wendang")
public class WendangController {
    private static final Logger logger = LoggerFactory.getLogger(WendangController.class);

    @Autowired
    private WendangService wendangService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("wendangDeleteStart",1);params.put("wendangDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = wendangService.queryPage(params);

        //字典表数据转换
        List<WendangView> list =(List<WendangView>)page.getList();
        for(WendangView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WendangEntity wendang = wendangService.selectById(id);
        if(wendang !=null){
            //entity转view
            WendangView view = new WendangView();
            BeanUtils.copyProperties( wendang , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(wendang.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody WendangEntity wendang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wendang:{}",this.getClass().getName(),wendang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            wendang.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<WendangEntity> queryWrapper = new EntityWrapper<WendangEntity>()
            .eq("yonghu_id", wendang.getYonghuId())
            .eq("wendang_uuid_number", wendang.getWendangUuidNumber())
            .eq("wendang_name", wendang.getWendangName())
            .eq("wendang_types", wendang.getWendangTypes())
            .eq("wendang_erji_types", wendang.getWendangErjiTypes())
            .eq("wendang_guanjianzi", wendang.getWendangGuanjianzi())
            .eq("wendang_delete", wendang.getWendangDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WendangEntity wendangEntity = wendangService.selectOne(queryWrapper);
        if(wendangEntity==null){
            wendang.setWendangDelete(1);
            wendang.setInsertTime(new Date());
            wendang.setCreateTime(new Date());
            wendang.setUpdateTime(new Date());
            wendangService.insert(wendang);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WendangEntity wendang, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,wendang:{}",this.getClass().getName(),wendang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            wendang.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<WendangEntity> queryWrapper = new EntityWrapper<WendangEntity>()
            .notIn("id",wendang.getId())
            .andNew()
            .eq("yonghu_id", wendang.getYonghuId())
            .eq("wendang_uuid_number", wendang.getWendangUuidNumber())
            .eq("wendang_name", wendang.getWendangName())
            .eq("wendang_types", wendang.getWendangTypes())
            .eq("wendang_erji_types", wendang.getWendangErjiTypes())
            .eq("wendang_guanjianzi", wendang.getWendangGuanjianzi())
            .eq("wendang_delete", wendang.getWendangDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WendangEntity wendangEntity = wendangService.selectOne(queryWrapper);
        if("".equals(wendang.getWendangFile()) || "null".equals(wendang.getWendangFile())){
                wendang.setWendangFile(null);
        }
        wendang.setUpdateTime(new Date());
        if(wendangEntity==null){
            wendangService.updateById(wendang);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<WendangEntity> list = new ArrayList<>();
        for(Integer id:ids){
            WendangEntity wendangEntity = new WendangEntity();
            wendangEntity.setId(id);
            wendangEntity.setWendangDelete(2);
            list.add(wendangEntity);
        }
        if(list != null && list.size() >0){
            wendangService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<WendangEntity> wendangList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            WendangEntity wendangEntity = new WendangEntity();
//                            wendangEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            wendangEntity.setWendangUuidNumber(data.get(0));                    //文档编号 要改的
//                            wendangEntity.setWendangName(data.get(0));                    //文档名称 要改的
//                            wendangEntity.setWendangTypes(Integer.valueOf(data.get(0)));   //文档一级类型 要改的
//                            wendangEntity.setWendangErjiTypes(Integer.valueOf(data.get(0)));   //文档二级类型 要改的
//                            wendangEntity.setWendangGuanjianzi(data.get(0));                    //文档关键字 要改的
//                            wendangEntity.setWendangFile(data.get(0));                    //文档文件 要改的
//                            wendangEntity.setWendangContent("");//详情和图片
//                            wendangEntity.setWendangDelete(1);//逻辑删除字段
//                            wendangEntity.setInsertTime(date);//时间
//                            wendangEntity.setUpdateTime(sdf.parse(data.get(0)));          //最后修改时间 要改的
//                            wendangEntity.setCreateTime(date);//时间
                            wendangList.add(wendangEntity);


                            //把要查询是否重复的字段放入map中
                                //文档编号
                                if(seachFields.containsKey("wendangUuidNumber")){
                                    List<String> wendangUuidNumber = seachFields.get("wendangUuidNumber");
                                    wendangUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> wendangUuidNumber = new ArrayList<>();
                                    wendangUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("wendangUuidNumber",wendangUuidNumber);
                                }
                        }

                        //查询是否重复
                         //文档编号
                        List<WendangEntity> wendangEntities_wendangUuidNumber = wendangService.selectList(new EntityWrapper<WendangEntity>().in("wendang_uuid_number", seachFields.get("wendangUuidNumber")).eq("wendang_delete", 1));
                        if(wendangEntities_wendangUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WendangEntity s:wendangEntities_wendangUuidNumber){
                                repeatFields.add(s.getWendangUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [文档编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        wendangService.insertBatch(wendangList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
