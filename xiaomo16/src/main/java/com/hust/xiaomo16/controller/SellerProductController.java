package com.hust.xiaomo16.controller;

import com.hust.xiaomo16.entity.ProductCategory;
import com.hust.xiaomo16.entity.ProductInfo;
import com.hust.xiaomo16.enums.ResultEnum;
import com.hust.xiaomo16.exception.MyException;
import com.hust.xiaomo16.form.ProductForm;
import com.hust.xiaomo16.service.CategoryService;
import com.hust.xiaomo16.service.ProductService;
import com.hust.xiaomo16.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: XM16
 * @description:
 * @author: Boon Guan
 * @create: 2019-02-16 17:34
 **/

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue="1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage=productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

//    /**
//     * 商品上架
//     * @param productId
//     * @param map
//     * @return
//     */
//    @GetMapping("/on_sale")
//    public ModelAndView onSale(@RequestParam("productId") String productId,
//                               Map<String,Object> map){
//        try{
//            productService.onSale(productId);
//        }catch (MyException e){
//            map.put("msg",e.getMessage());
//            map.put("url","/sell/seller/product/list");
//            return new ModelAndView("common/error",map);
//        }
//        map.put("url","/sell/seller/product/list");
////        map.put()
//        return new ModelAndView("common/success",map);
//    }
//
//    @GetMapping("/off_sale")
//    public ModelAndView offSale(@RequestParam("productId") String productId,
//                                Map<String,Object> map){
//        try{
//            productService.offSale(productId);
//        }catch (MyException e){
//            map.put("msg",e.getMessage());
//            map.put("url","/sell/seller/product/list");
//            return new ModelAndView("common/error",map);
//        }
//        map.put("url","/sell/seller/product/list");
////        map.put()
//        return new ModelAndView("common/success",map);
//    }

    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo=productService.findOne(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList=categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("product/index",map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
//    @CachePut(cacheNames = "product",key = "123")
    @CacheEvict(cacheNames = "product",key = "123")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","xiaomo/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        ProductInfo productInfo=new ProductInfo();
        try{
            //如果productId不为空，说明是已有商品
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo=productService.findOne(form.getProductId());
            }else{
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        }catch (MyException e){
            map.put("msg",e.getMessage());
            map.put("url","/xiaomo/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/xiaomo/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}