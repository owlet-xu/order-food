package com.orderfood.springboot.seed.bz1.controller;

import com.orderfood.springboot.seed.bz1.contract.model.*;
import com.orderfood.springboot.seed.bz1.contract.service.OrderFoodService;
import com.orderfood.springboot.seed.common.qiqi.HttpRestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuguoyuan on 2018/7/10.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "AppMean Api")
public class OrderFoodController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderFoodService orderFoodService;




    @RequestMapping(value = "/addFood",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "增加or修改食物",notes = "增加or修改食物",response = OrderMeanListController.class
    )
    public ResponseData addFood(@RequestBody OrderFoodInfo orderFoodInfo, BindingResult bindingResult) {
        if ((bindingResult.hasErrors())) {
            throw new HttpRestException(HttpStatus.BAD_REQUEST, "param valiad failed");
        }
        ResponseData data = new ResponseData();
        boolean isSuccess = orderFoodService.save(orderFoodInfo);
        data.setSuccess(isSuccess);
        if (isSuccess) {
            data.setMsg("添加成功");
        }else {
            data.setMsg("添加失败");
        }
        return data;
    }


    /**
     * 这里是带参数查询菜品的接口
     * @param request
     * @return ResponseData
     */
    @RequestMapping(value = "/searchfood",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "这里是带参数分页查询菜单",notes = "带参数查询菜单数据.默认每页10条数据",response = OrderMeanListController.class
    )
    public ResponseData list(@RequestBody(required = false) OrderPageRequest request) {
        OrderPageRepose orderPageRepose = orderFoodService.selectList(request);
        ResponseData data = new ResponseData();
        if(orderPageRepose.getContent().size()!=0){
            data.setSuccess(true);
            data.setData(orderPageRepose);
            data.setMsg("查询成功!");
            return data;
        }else{
            data.setSuccess(false);
            data.setData(orderPageRepose);
            data.setMsg("未查询到数据!");
        }
        return data;
    }





    /**
     * 这里是展示前端的食物类型列表，0 1 2 3 四种食物类型
     * @param request
     * @return ResponseData
     */
    @RequestMapping(value = "/searchgaijiaofan",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "这里是带参数分页查询菜单",notes = "带参数查询菜单数据.默认每页10条数据",response = OrderMeanListController.class
    )
    public ResponseData gaijiaofanlist(@RequestBody(required = false) OrderPageRequest request) {
        OrderPageRepose orderPageRepose = orderFoodService.selectList(request);
        ResponseData data = new ResponseData();
        if(orderPageRepose.getContent().size()!=0){
            data.setSuccess(true);
            data.setData(orderPageRepose);
            data.setMsg("查询成功!");
            return data;
        }
        else{
           data.setSuccess(false);
           data.setMsg("查询失败！");
        }
        return data;
    }



    /**
     * 这里是展示食物详情的接口
     * @param id
     * @return ResponseData
     */
    @RequestMapping(value = "/fooddetail/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "根据id的信息查询数据",notes = "根据id的信息查询菜品详细信息",response = OrderMeanListController.class)
    public ResponseData fooddatial(@PathVariable String id ) {
        ResponseData data = new ResponseData();
        OrderFoodInfo orderFoodInfo = orderFoodService.findById(id);
        if(null!=orderFoodInfo){
            data.setSuccess(true);
            data.setMsg("查询成功！");
            data.setData(orderFoodInfo);
        }else {
            data.setSuccess(false);
            data.setMsg("查询失败！");
            return data;
        }
        return data;
    }


    /**
     * 这里是修改上架下架状态的接口
     * @param orderUpdateStatusInfo
     * @return
     */
    @RequestMapping(value = "/updatestatus",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "这里是上架下状态的接口",notes = "修改上架下架的接口 0下架 1上架",response = OrderMeanListController.class
    )

    public ResponseData updaeById(@RequestBody OrderUpdateStatusInfo orderUpdateStatusInfo){
        ResponseData data = new ResponseData();
        if(!"0".equals(orderUpdateStatusInfo.getStatus())&&!"1".equals(orderUpdateStatusInfo.getStatus())){
            data.setSuccess(false);
            data.setMsg("数据格式不正确！");
        }
        Boolean b = orderFoodService.update(orderUpdateStatusInfo);
        data.setSuccess(b);
        if (b) {
            if (orderUpdateStatusInfo.getStatus().equals("1")){
                data.setMsg("上架成功!");
            }else if(orderUpdateStatusInfo.getStatus().equals("0"))
            {
                data.setMsg("下架成功!");
            }
        }else{
            data.setMsg("操作失败!");
        }

        return data;
    }


    /**
     * 这里是删除菜品的接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "这里是删除菜品的接口",notes = "这里是删除菜品的接口",response = OrderMeanListController.class
    )
    public ResponseData deleteFood(@PathVariable String id){
        ResponseData data = new ResponseData();
        Boolean b = orderFoodService.delete(id);
        if(b){
            data.setMsg("删除成功!");
            data.setSuccess(true);
        }else{
            data.setMsg("删除失败!");
            data.setSuccess(false);
        }
        return data;
    }


    /**
     * 文件上传信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody FileMeta  upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        FileMeta fileMeta = orderFoodService.uploadFile(request, response);
        return fileMeta;
    }


}
