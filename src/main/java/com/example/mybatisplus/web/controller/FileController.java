package com.example.mybatisplus.web.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.Personnel;
import com.example.mybatisplus.service.FileService;
import com.example.mybatisplus.service.PersonnelService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    protected ResourceLoader resourceLoader;
    private static final String UPLOAD_DIR = "/src/main/resources/uploads/";
    public FileController(FileService fileService, ResourceLoader resourceLoader) {
        this.fileService = fileService;
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @RequestMapping(value = "/upload/avatar/{username}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> upload(MultipartFile file, HttpServletRequest request ,@PathVariable("username") String username) throws IOException {
        Map<String, String> map = new HashMap();
        map = fileService.upload(file);
        UpdateWrapper<Personnel> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("username",username).set("photo",map.get("url"));
        personnelService.update(updateWrapper);
        return ResponseEntity.ok().body(map);
    }

    @ApiOperation(value = "报名照片上传", notes = "报名照片上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> uploadfile(MultipartFile file, HttpServletRequest request ) throws IOException {
        Map<String, String> map = new HashMap();
        map = fileService.upload(file);
        return ResponseEntity.ok().body(map);
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
        UpdateWrapper<Personnel> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("username",username);
        String avatarUrl = personnelService.getOne(updateWrapper).getPhoto();
        response.put("url", avatarUrl);
        return ResponseEntity.ok(response);
     }


}
