package com.example.mybatisplus.web.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.utls.BatchDataListener;
import com.example.mybatisplus.common.utls.ExaminationDataListener;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.domain.Examination;
import com.example.mybatisplus.model.domain.Personnel;
import com.example.mybatisplus.service.ExaminationService;
import com.example.mybatisplus.service.FileService;
import com.example.mybatisplus.service.PersonnelService;
import com.example.mybatisplus.service.impl.BatchDataService;
import com.example.mybatisplus.service.impl.BatchServiceImpl;
import com.example.mybatisplus.service.impl.ExaminationDataServiceImpl;
import com.example.mybatisplus.service.impl.ExaminationServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    private PersonnelService personnelService;
    protected FileService fileService;
    @Autowired
    BatchDataService batchDataService;
    @Autowired
    BatchServiceImpl batchService;
    @Autowired
    ExaminationServiceImpl examinationService;
    protected ResourceLoader resourceLoader;
    private static final String UPLOAD_DIR = "/src/main/resources/uploads/";


    public FileController(FileService fileService, ResourceLoader resourceLoader) {
        this.fileService = fileService;
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value = "头像上传", notes = "头像上传")
    @RequestMapping(value = "/upload/avatar/{username}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> uploadAvatar(MultipartFile file, HttpServletRequest request, @PathVariable("username") String username) throws IOException {
        Map<String, String> map = new HashMap<>();
        map = fileService.upload(file);
        UpdateWrapper<Personnel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", username).set("photo", map.get("url"));
        personnelService.update(updateWrapper);
        return ResponseEntity.ok().body(map);
    }

    @ApiOperation(value = "考场Excel上传", notes = "考场Excel上传")
    @RequestMapping(value = "/upload/excel", method = RequestMethod.POST)
    public ResponseEntity<String> uploadExcelExamination(MultipartFile file, HttpServletRequest request) throws IOException {
        String response = "";
        if (file.isEmpty()) {
            log.warn("文件为空");
            response = "上传文件为空";
            return ResponseEntity.badRequest().body(response);
        }
        log.info("成功接收文件:{}", file.getOriginalFilename());
        try {
            // 使用 EasyExcel 读取 Excel 文件
            EasyExcel.read(file.getInputStream(), Examination.class, new BatchDataListener(batchDataService,batchService,examinationService)).sheet("Sheet").doRead();
            response = "文件上传成功";
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            log.warn("文件上传失败");
            response = "文件上传失败";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @ApiOperation(value = "批次Excel上传", notes = "批次Excel上传")
    @RequestMapping(value = "/upload/excel/batch", method = RequestMethod.POST)
    public ResponseEntity<String> uploadExcelBatch(MultipartFile file, HttpServletRequest request) throws IOException {
        String response = "";
        if (file.isEmpty()) {
            log.warn("文件为空");
            response = "上传文件为空";
            return ResponseEntity.badRequest().body(response);
        }
        log.info("成功接收文件:{}", file.getOriginalFilename());
        try {
            // 使用 EasyExcel 读取 Excel 文件
            EasyExcel.read(file.getInputStream(), Batch.class, new BatchDataListener(batchDataService,batchService,examinationService)).sheet("Sheet").doRead();
            response = "文件上传成功";
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            log.warn("文件上传失败");
            response = "文件上传失败";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private static String suffix(String fileName) {
        int i = fileName.lastIndexOf('.');
        return i == -1 ? "" : fileName.substring(i + 1);
    }

    @GetMapping("/get/avatar/{username}")
    @ResponseBody
    @ApiOperation(value = "获取头像", notes = "获取用户头像")
    public ResponseEntity<Map<String, Object>> getAvatar(@PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        UpdateWrapper<Personnel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", username);
        String avatarUrl = personnelService.getOne(updateWrapper).getPhoto();
        response.put("url", avatarUrl);
        return ResponseEntity.ok(response);
    }


}
