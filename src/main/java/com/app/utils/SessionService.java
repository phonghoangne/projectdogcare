package com.app.utils;


import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import jakarta.servlet.http.HttpSession;



@Service
public class SessionService {

    public void set(String key, Object value) {
        HttpSession session = getCurrentSession();
        if (session != null) {
            session.setAttribute(key, value);
        }
    }

    public Object get(String key) {
        HttpSession session = getCurrentSession();
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }

    public void remove(String key) {
        HttpSession session = getCurrentSession();
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    private HttpSession getCurrentSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest().getSession(false);
    }
}

