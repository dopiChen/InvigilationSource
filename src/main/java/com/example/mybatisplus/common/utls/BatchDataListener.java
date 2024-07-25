package com.example.mybatisplus.common.utls;


import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.domain.Examination;
import com.example.mybatisplus.service.impl.BatchDataService;
import com.example.mybatisplus.service.impl.BatchServiceImpl;
import com.example.mybatisplus.service.impl.ExaminationDataServiceImpl;
import com.example.mybatisplus.service.impl.ExaminationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class BatchDataListener extends AnalysisEventListener<Batch> {

    private static final int BATCH_COUNT = 100;

    private BatchDataService batchDataService;

    private BatchServiceImpl batchServiceImpl;

    private List<Batch> dataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public BatchDataListener(BatchDataService batchDataService, BatchServiceImpl batchServiceImpl) {
        this.batchDataService = batchDataService;
        this.batchServiceImpl = batchServiceImpl;
    }


    @Override
    public void invoke(Batch data, AnalysisContext context) {
        if (dataList.size() >= BATCH_COUNT) { // 每100条数据批量插入一次数据库
            saveData();
            dataList.clear(); // 清空列表，以便下一批数据
        }
        Batch batch = batchServiceImpl.getById(data.getBatchId());
        if(batch != null) return;

        dataList.add(data);
        log.info("成功插入一条数据:{}",data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 如果还有剩余未满足批量插入条件的数据，最后再进行插入
        if (!dataList.isEmpty()) {
            saveData();
        }
    }

    private void saveData() {
        // 调用 ExcelDataService 中的方法将 dataList 中的数据批量写入数据库
        batchDataService.saveExcelData(dataList);
    }
}
