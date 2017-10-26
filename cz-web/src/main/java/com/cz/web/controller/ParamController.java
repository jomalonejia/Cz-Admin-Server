package com.cz.web.controller;

import com.cz.api.service.IParamService;
import com.cz.model.param.Param;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/10/26.
 */
@RestController
@RequestMapping("/param")
@Api(value = "/param", description = "Param Controller")
public class ParamController {

    private static Logger _log = LoggerFactory.getLogger(ParamController.class);

    @Autowired
    private IParamService paramService;

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        try {
            List<Param> params = paramService.listParams();
            _log.info(params.toString());
            return ResponseEntity.ok(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
