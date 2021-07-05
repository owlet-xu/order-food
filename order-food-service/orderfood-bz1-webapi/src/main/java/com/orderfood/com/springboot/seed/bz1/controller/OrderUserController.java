package com.orderfood.com.springboot.seed.bz1.controller;

import com.orderfood.springboot.seed.bz1.contract.model.*;
import com.orderfood.springboot.seed.bz1.contract.service.OrderUserService;
import com.orderfood.springboot.seed.common.qiqi.HttpRestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yangxuechao on 2018/7/5.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "AppUser Api")
public class OrderUserController {


    @Autowired
    OrderUserService orderUserService;

    @Autowired
    private MessageSource messageSource;


    /**
     * 注册新用户,需要校验,根据电话,邮件校验
     * @param loginInfo
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "注册一个新用用户!",notes = "注册一个新用户!",response = OrderUserController.class
    )
    public ResponseData regiterUser(@RequestBody LoginInfo loginInfo, BindingResult bindingResult) {
        ResponseData responseData = new ResponseData();
        List<OrderUserInfo> orderUserByPhone = orderUserService.findByPhone(loginInfo.getPhone());
        List<OrderUserInfo> orderUserByEmail = orderUserService.findByEmail(loginInfo.getEmail());
        if ((bindingResult.hasErrors())) {
            throw new HttpRestException(HttpStatus.BAD_REQUEST, "param valiad failed");
        }
        if (0 != orderUserByPhone.size()){
            responseData.setSuccess(false);
            responseData.setMsg("该用户已存在!");
            return responseData;
        }
        if(0!= orderUserByEmail.size()){
            responseData.setSuccess(false);
            responseData.setMsg("该用户已存在!");
            return responseData;
        }
        if(loginInfo.getName().isEmpty()||"".equals(loginInfo.getName())){
            responseData.setSuccess(false);
            responseData.setMsg("用户名不能为空");
            return responseData;
        }
        if(loginInfo.getPassWord().isEmpty()||"".equals(loginInfo.getPassWord())){
            responseData.setSuccess(false);
            responseData.setMsg("密码不能为空");
            return responseData;
        }
        else{
            OrderUserInfo register=orderUserService.save(loginInfo);
            responseData.setSuccess(true);
            responseData.setMsg("注册成功!");
            responseData.setData(register);
            return responseData;
        }
    }


    /**
     *
     * @param loginInfo
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "用户的登陆",notes = "用户的登陆",response = OrderUserController.class
    )
    public ResponseData login(@RequestBody LoginInfo loginInfo, BindingResult bindingResult) {
        ResponseData responseData = new ResponseData();
//        根据电话号码查询,如果数据不止一条,说明数据有误
        List<OrderUserInfo> orderUser = orderUserService.findByPhone(loginInfo.getPhone());
        OrderUserInfo user = null;
        if(orderUser.size()>1){
            responseData.setMsg("用户数据有误,请核实!");
            responseData.setSuccess(false);
        }else{
            for (OrderUserInfo u: orderUser
                    ) {
                user  = u;
            }
//            如果数据没有查询到,那么说明没有注册
         if(0 == orderUser.size()){
            responseData.setMsg("用户未注册!");
            responseData.setSuccess(false);
        }
        else if(!"".equals(user)) {
//             前台获取的密码和数据库查询的密码相等,成功
             if (user.getPassWord().equals(loginInfo.getPassWord())) {
                 responseData.setSuccess(true);
                 responseData.setMsg("登陆成功!");
                 responseData.setData(orderUser);
             } else {
                 responseData.setSuccess(false);
                 responseData.setMsg("密码错误!");
             }
         }
        }
        return responseData;
    }

    /**
     *
     * @param orderUserPageRequest
     * @return
     */
    @RequestMapping(value = "/searchuser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "用户的分页查询",notes = "用户信息的分页查询",response = OrderUserController.class
    )
    public ResponseData userList(@RequestBody(required = false)OrderUserPageRequest orderUserPageRequest){
        ResponseData responseData = new ResponseData();
        OrderUserPageRepose orderUserPageRepose = orderUserService.selectlist(orderUserPageRequest);
        if(orderUserPageRepose.getContent().size()!=0){
            responseData.setMsg("查询成功!");
            responseData.setSuccess(true);
            responseData.setData(orderUserPageRepose);
        }else{
            responseData.setMsg("查询失败!");
            responseData.setSuccess(false);
        }
        return responseData;
    }


    /**
     * 修改用户的信息
     * @param orderUserInfo
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "修改用户信息",notes = "修改用户信息",response = OrderUserController.class
    )
    public ResponseData updateUser(@RequestBody(required = false)OrderUserInfo orderUserInfo){
        ResponseData responseData = new ResponseData();
        Boolean b = orderUserService.update(orderUserInfo);
        if(b){
            responseData.setSuccess(true);
            responseData.setMsg("修改成功!");
        }else{
            responseData.setMsg("修改失败!");
            responseData.setSuccess(false);
        }

        return responseData;
    }


    /**
     * 通用接口,方便单独测试的
     * @param id
     * @return
     */
    @RequestMapping(value = "/userInfo{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "根据id查询用户信息",notes = "根据用户查询id信息",response = OrderUserController.class
    )
    public ResponseData userInfo(@PathVariable String id){
        ResponseData responseData = new ResponseData();
        OrderUserInfo orderUserInfo = orderUserService.findById(id);
        if(null!=orderUserInfo&&!"".equals(orderUserInfo)){
            responseData.setMsg("查询成功!");
            responseData.setSuccess(true);
            responseData.setData(orderUserInfo);
        }else{
            responseData.setMsg("查询失败!");
            responseData.setSuccess(false);
            responseData.setData(orderUserInfo);
        }
        return responseData;
    }

}
