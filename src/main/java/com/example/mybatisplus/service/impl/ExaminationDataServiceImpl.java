package com.example.mybatisplus.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.mybatisplus.common.utls.ExaminationDataListener;
import com.example.mybatisplus.model.domain.Examination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminationDataServiceImpl {

    @Autowired
    private ExaminationServiceImpl examinationServiceImpl;

    @Transactional
    public void saveExcelData(List<Examination> dataList) {
        examinationServiceImpl.saveOrUpdateBatch(dataList);
    }
}