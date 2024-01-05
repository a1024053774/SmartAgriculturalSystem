package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.example.springboot.service.IUserService;
import com.example.springboot.entity.User;
import org.springframework.web.bind.annotation.RestController;

import static javafx.scene.input.KeyCode.R;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


//新增或者更新
    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
        }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
            return userService.removeById(id);
            }

    //查询所有数据
    @GetMapping
    public List<User> findAll() {
            return userService.list();
            }
            //根据ID查询
    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
            return userService.getById(id);
            }
//分页查询
@GetMapping("/page")
public Page<User> findPage(
        @RequestParam Integer pageNum,
        @RequestParam Integer pageSize,
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "") String equid,
        @RequestParam(defaultValue = "") String address)
{
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if (!"".equals(name)) {
        queryWrapper.like("name", name);
    }
    if (!"".equals(equid)) {
        queryWrapper.like("equid", equid);
    }
    if (!"".equals(address)) {
        queryWrapper.like("address", address);
    }
    queryWrapper.orderByAsc("id");
    return userService.page(new Page<>(pageNum, pageSize), queryWrapper);
}
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){

            return userService.removeByIds(ids);
            }
}

