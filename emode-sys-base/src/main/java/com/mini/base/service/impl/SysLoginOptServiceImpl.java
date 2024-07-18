package com.mini.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysLoginOpt;
import com.mini.base.mapper.SysLoginOptMapper;
import com.mini.base.model.dto.SysLoginOptDTO;
import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.query.SysLoginOptQuery;
import com.mini.base.service.ISysLoginOptService;
import com.mini.common.utils.AddressByIpUtil;
import com.mini.common.utils.http.ServletUtil;
import com.mini.common.utils.webmvc.IDGenerator;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author zhl
 * @create 2024/7/18 15:31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysLoginOptServiceImpl implements ISysLoginOptService {

    private final SysLoginOptMapper sysLoginOptMapper;

    @Async
    @Override
    public void addLoginOptInfo(SysLoginOptDTO dto) {
        SysLoginOpt sysLoginOpt = new SysLoginOpt();
        final HttpServletRequest request = dto.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        // 获取浏览器信息
        String browserName = userAgent.getBrowser().getName();
        // 获取系统信息
        String systemOs = userAgent.getOperatingSystem().getName();
        // 获取ip
        String clientIP = ServletUtil.getClientIP(request);
        // 获取地址信息
        String region = AddressByIpUtil.getIpPossessionByFile(clientIP);

        // 封装信息
        sysLoginOpt.setId(IDGenerator.next());
        sysLoginOpt.setUsername(dto.getUsername());
        sysLoginOpt.setOptIp(clientIP);
        sysLoginOpt.setOptAddress(region);
        sysLoginOpt.setBrowser(browserName);
        sysLoginOpt.setSystemOs(systemOs);
        sysLoginOpt.setStatus(dto.getStatus());
        sysLoginOpt.setOptType(dto.getOptType());
        sysLoginOpt.setOptMsg(dto.getOptMsg());
        sysLoginOpt.setOptTime(LocalDateTime.now());

        try {
            sysLoginOptMapper.insert(sysLoginOpt);
        } catch (Exception e) {
            log.error("记录登录操作信息异常，username:{}", e.getMessage());
        }
    }

    @Override
    public IPage<SysLoginOptInfoDTO> selectPage(SysLoginOptQuery query) {
        return sysLoginOptMapper.selectPage(query, query.build());
    }

}
