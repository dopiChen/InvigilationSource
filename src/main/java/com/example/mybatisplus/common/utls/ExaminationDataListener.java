package com.example.mybatisplus.common.utls;

import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.Examination;
import com.example.mybatisplus.service.impl.BatchServiceImpl;
import com.example.mybatisplus.service.impl.ExaminationDataServiceImpl;
import com.example.mybatisplus.service.impl.ExaminationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class ExaminationDataListener extends AnalysisEventListener<Examination> {

    private static final int BATCH_COUNT = 100;

    private ExaminationDataServiceImpl examinationDataServiceImpl;

    private BatchServiceImpl batchServiceImpl;

    private ExaminationServiceImpl examinationServiceImpl;

    private List<Examination> dataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public ExaminationDataListener(ExaminationDataServiceImpl examinationDataServiceImpl, BatchServiceImpl batchServiceImpl, ExaminationServiceImpl examinationServiceImpl) {
        this.examinationDataServiceImpl = examinationDataServiceImpl;
        this.batchServiceImpl = batchServiceImpl;
        this.examinationServiceImpl = examinationServiceImpl;
    }


    @Override
    public void invoke(Examination data, AnalysisContext context) {
        if (dataList.size() >= BATCH_COUNT) { // 每100条数据批量插入一次数据库
            saveData();
            dataList.clear(); // 清空列表，以便下一批数据
        }
        //检查响应的批次是否存在，如果不存在则不加入dataList
        if (batchServiceImpl.getById(data.getBatchId()) == null) {
            log.warn("该考试{}对应批次不存在：{}",data.getExamRoom(),data.getBatchId());
            return;
        }
        List<Examination> sameRoomList = examinationServiceImpl.list(new QueryWrapper<Examination>().eq("exam_room", data.getExamRoom()));
        if (sameRoomList != null && !sameRoomList.isEmpty()) {
            for (Examination examination : sameRoomList) {
                if (!(data.getEndTime().isBefore(examination.getFromTime()) || data.getFromTime().isAfter(examination.getEndTime()))) {
                    log.warn("该考试{}与已有考试冲突：{}",data.getExamRoom(),examination.getExamId());
                    return;
                }
            }
        }
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
        examinationDataServiceImpl.saveExcelData(dataList);
    }
}
