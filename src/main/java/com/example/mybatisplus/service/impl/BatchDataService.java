package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.domain.Examination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BatchDataService {

    @Autowired
    private BatchServiceImpl batchServiceImpl;

    @Transactional
    public void saveExcelData(List<Batch> dataList) {
        batchServiceImpl.saveOrUpdateBatch(dataList);
    }
}
