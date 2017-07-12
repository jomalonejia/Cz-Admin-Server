package com.cz.core.session.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.Session;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jomalone_jia on 2017/7/4.
 */
public class HeaderHttpSessionStrategyListener extends HeaderHttpSessionStrategy{

    private static Logger _log = LoggerFactory.getLogger(HeaderHttpSessionStrategyListener.class);

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
    }
}
