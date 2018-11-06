package com.itmuch.cloud.study.controller;

import com.itmuch.cloud.study.entity.Product;
import com.itmuch.cloud.study.entity.SearchDto;
import com.itmuch.cloud.study.entity.SearchPage;
import com.itmuch.cloud.study.entity.SearchParam;
import com.itmuch.cloud.study.repository.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired
  private ProductRepository productRepository;

  @GetMapping("/{id}")
  public Product findById(@PathVariable Long id) {
    Product product = this.productRepository.findOne(id);
    return product;
  }

  @GetMapping("/list")
  public Map<String, Object> list(){
    Map<String, Object> map = new HashMap();
    List<Product> list = new ArrayList();
    list = productRepository.findAll();
    map.put("msg","success");
    map.put("list",list);
    return map;
  }

  @PostMapping("/create")
  public Map<String, Object> create(@RequestBody Product product){
    Map<String, Object> map = new HashMap();
    productRepository.save(product);
    map.put("msg","success");
    return map;
  }

  @PostMapping("/delete/{id}")
  public void del(@PathVariable Long id){
    productRepository.delete(id);
  }

  @PostMapping("/update")
  public void update(@RequestBody Product product){
    productRepository.save(product);
  }


  @PostMapping("/search")
  public Map<String, Object> search(@RequestBody SearchDto searchDto){
    Map<String, Object> map = new HashMap();
    List<Product> list = new ArrayList();
    list = productRepository.search(searchDto.getSearchName(),searchDto.getSearchType(),searchDto.getSearchStatus());
    map.put("msg","success");
    map.put("list",list);
    return map;
  }

  @PostMapping("/page")
  public Page<Product> pageAll(/*@RequestBody */SearchPage searchPage){
    Sort sort = new Sort(Sort.Direction.ASC,"productSort");
    System.out.println(searchPage.getPageNum());
    System.out.println(searchPage.getPageSize());
    int pageNum = searchPage.getPageNum();
    int pageSize = searchPage.getPageSize();
    Pageable pageable = new PageRequest(pageNum,pageSize,sort);
//    Pageable pageable = new PageRequest(searchPage.getPageNum(),searchPage.getPageSize(),sort);
    Page<Product> page =  productRepository.findAll(pageable);
    return page;
  }

  @PostMapping("/searchPage")
  public Page<Product> searchPage(@RequestBody SearchParam searchParam){
    Sort sort = new Sort(Sort.Direction.ASC,"productSort");
    int a = searchParam.getPageNum();
    Pageable pageable = new PageRequest(searchParam.getPageNum()-1,searchParam.getPageSize(),sort);
//    Page<Product> page =  productRepository.searchPage("保险",null,null,pageable);
    System.out.println(searchParam.getSearchName());
    System.out.println(searchParam.getSearchType());
    Page<Product> page =  productRepository.searchPage(searchParam.getSearchName(),searchParam.getSearchType(),searchParam.getSearchStatus(),pageable);
    return page;
  }

}
