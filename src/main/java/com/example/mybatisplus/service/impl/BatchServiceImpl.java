package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.mapper.BatchMapper;
import com.example.mybatisplus.service.BatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
