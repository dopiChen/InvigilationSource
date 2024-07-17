package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Batch;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.PageDTO;

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

    Page<Batch> pageList(Batch batch, PageDTO dto);
}
