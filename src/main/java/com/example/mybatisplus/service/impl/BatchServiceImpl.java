package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.mapper.BatchMapper;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.mapper.BatchMapper;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.service.BatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 批次表 服务实现类
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Service
public class BatchServiceImpl extends ServiceImpl<BatchMapper, Batch> implements BatchService {
    @Autowired
       private BatchMapper batchMapper;
    @Override
    public List<Batch> searchBatch(String keyword) {
        return batchMapper.searchBatch(keyword);
    }

    @Override
    public Page<Batch> pageList(Batch batch, PageDTO dto) {
        QueryWrapper<Batch> wrapper=new QueryWrapper<>();
        if (batch != null && batch.getBatchName() != null && !batch.getBatchName().isEmpty()) {
            wrapper.like("batch_name", batch.getBatchName());
        }
        Page<Batch> page=new Page<>();
        page.setCurrent(dto.getPageNo()).setSize(dto.getPageSize());
        baseMapper.selectPage(page,wrapper);
        return page;
    }
}
