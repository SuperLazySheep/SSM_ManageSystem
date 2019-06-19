package com.itheima.ssm.service;

import com.itheima.ssm.domain.Syslog;

import java.util.List;

public interface ISyslogService {

    public void save(Syslog syslog) throws Exception;

    public List<Syslog> findAll() throws Exception;
}
