package com.asofdate.dispatch.controller;

import com.asofdate.dispatch.entity.ArgumentDefineEntity;
import com.asofdate.dispatch.service.ArgumentService;
import com.asofdate.platform.authentication.JwtService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@RequestMapping(value = "/v1/dispatch/argument/define")
@RestController
public class ArgumentController {
    private final Logger logger = LoggerFactory.getLogger(ArgumentController.class);
    @Autowired
    private ArgumentService argumentService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ArgumentDefineEntity> getArgumentDefine(HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        if (domainId == null) {
            domainId = JwtService.getConnUser(request).getDomainID();
        }
        List<ArgumentDefineEntity> list = argumentService.findAll(domainId);
        return list;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String postArgumentDefine(HttpServletResponse response, HttpServletRequest request) {
        ArgumentDefineEntity argumentDefine = parse(request);

        if (argumentDefine.getArgId().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "参数编码必须由1-30位字母、数字组成", JSONObject.NULL);
        }

        if (argumentDefine.getArgDesc().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "请输入详细的参数描述信息", JSONObject.NULL);
        }

        if (argumentDefine.getArgType() == null) {
            response.setStatus(421);
            return Hret.error(421, "请选择参数类型", JSONObject.NULL);
        }

        if ("1".equals(argumentDefine.getArgType()) && argumentDefine.getArgValue().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "请填写固定参数，参数值", JSONObject.NULL);
        }

        if ("4".equals(argumentDefine.getArgType()) && argumentDefine.getBindAsOfDate() == null) {
            response.setStatus(421);
            return Hret.error(421, "批次类型参数，请选择是否与数据日期绑定", JSONObject.NULL);
        }
        RetMsg retMsg = argumentService.addArgument(argumentDefine);
        if (SysStatus.SUCCESS_CODE == retMsg.getCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(421);
        return Hret.error(retMsg);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    @ResponseBody
    public String deleteArgumentDefine(HttpServletResponse response, HttpServletRequest request) {
        List<ArgumentDefineEntity> args = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(request.getParameter("JSON"));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            ArgumentDefineEntity argumentDefineEntity = new ArgumentDefineEntity();
            argumentDefineEntity.setArgId(jsonObject.getString("arg_id"));
            argumentDefineEntity.setDomainId(jsonObject.getString("domain_id"));
            args.add(argumentDefineEntity);
        }
        RetMsg msg = argumentService.deleteArgument(args);
        if (SysStatus.SUCCESS_CODE == msg.getCode()) {
            return Hret.success(msg);
        }
        response.setStatus(421);
        return Hret.error(msg);
    }

    /*
    * 更新参数定义信息
    * */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String putArgumentDefine(HttpServletResponse response, HttpServletRequest request) {
        ArgumentDefineEntity argumentDefine = parse(request);

        if (argumentDefine.getArgId().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "参数编码必须由1-30位字母、数字组成", JSONObject.NULL);
        }

        if (argumentDefine.getArgDesc().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "请输入详细的参数描述信息", JSONObject.NULL);
        }

        if (argumentDefine.getArgType() == null) {
            response.setStatus(421);
            return Hret.error(421, "请选择参数类型", JSONObject.NULL);
        }

        if ("1".equals(argumentDefine.getArgType()) && argumentDefine.getArgValue().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "请填写固定参数，参数值", JSONObject.NULL);
        }

        if ("4".equals(argumentDefine.getArgType()) && argumentDefine.getBindAsOfDate() == null) {
            response.setStatus(421);
            return Hret.error(421, "批次类型参数，请选择是否与数据日期绑定", JSONObject.NULL);
        }
        RetMsg retMsg = argumentService.updateArgument(argumentDefine);
        if (SysStatus.SUCCESS_CODE == retMsg.getCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(421);
        return Hret.error(retMsg);
    }

    /*
    * 从客户单请求中,获取参数
    * 并转换成ArgumentDefineModel对象
    * */
    private ArgumentDefineEntity parse(HttpServletRequest request) {
        ArgumentDefineEntity argumentDefineEntity = new ArgumentDefineEntity();
        argumentDefineEntity.setArgId(request.getParameter("arg_id"));
        argumentDefineEntity.setArgValue(request.getParameter("arg_value"));
        argumentDefineEntity.setArgType(request.getParameter("arg_type"));
        argumentDefineEntity.setArgDesc(request.getParameter("arg_desc"));
        argumentDefineEntity.setDomainId(request.getParameter("domain_id"));
        argumentDefineEntity.setBindAsOfDate(request.getParameter("bind_as_of_date"));
        String userId = JwtService.getConnUser(request).getUserId();
        argumentDefineEntity.setCreateUser(userId);
        argumentDefineEntity.setModifyUser(userId);
        return argumentDefineEntity;
    }
}
