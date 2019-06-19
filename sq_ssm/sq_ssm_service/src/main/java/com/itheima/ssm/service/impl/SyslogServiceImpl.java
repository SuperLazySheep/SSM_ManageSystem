package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.ISyslogDao;
import com.itheima.ssm.domain.Syslog;
import com.itheima.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SyslogServiceImpl implements ISyslogService {

        @Autowired
        private ISyslogDao syslogDao;

    /**
     * 保存日志
     * @param syslog
     */
    @Override
    public void save(Syslog syslog) throws Exception {
        syslogDao.save(syslog);
    }

    /**
     * 查询日志
     */
    @Override
    public List<Syslog> findAll() throws Exception {
        return syslogDao.findAll();
    }
}
