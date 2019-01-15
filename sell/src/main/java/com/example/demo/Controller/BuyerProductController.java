package com.example.demo.Controller;

import com.example.demo.VO.ProductInfoVO;
import com.example.demo.VO.ProductVO;
import com.example.demo.VO.ResultVO;
import com.example.demo.dataobject.ProductCategory;
import com.example.demo.dataobject.ProductInfo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.utils.ResultVOUntil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//买家商品
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list()
    {
        //1、查询所有上架商品
        List<ProductInfo> productInfoList=productService.findUpAll();

        //2、查询类目（一次性查询）
//        List<Integer> categoryTypeList=new ArrayList<>();
        //传统方法
//        for(ProductInfo productInfo:productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简做法(java8,lambda)
        List<Integer> categoryTypeList=productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
        //3、数据拼装
        List<ProductVO> productVOList=new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUntil.success(productVOList);

    }

}
