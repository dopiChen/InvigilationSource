package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Batch;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 批次表 服务类
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
public interface BatchService extends IService<Batch> {

    //根据批次名关键词进行查询
    List<Batch> searchBatch(String keyword);
}
