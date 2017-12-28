package com.cz.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jomalone_jia on 2017/7/26.
 */
public class BaseController {


    protected Logger _log = LoggerFactory.getLogger(this.getClass());

    /*@Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected ServletContext application;

    protected <T> Page<T> getPage() {
        return getPage(10);
    }

    protected <T> Page<T> getPage(int size) {
        int _size = size, _index = 1;
        if (request.getParameter("_size") != null) {
            _size = Integer.parseInt(request.getParameter("_size"));
        }
        if (request.getParameter("_index") != null) {
            _index = Integer.parseInt(request.getParameter("_index"));
        }
        return new Page<T>(_index, _size);
    }*/
}
