package com.example.backend.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.backend.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${server.port:8080}")
    private String port;

    private static final String ip = "http://localhost";

    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String rootFilePath = System.getProperty("user.dir") + "/Backend/src/main/resources/files/" + uuid + "_" + originalFilename;
        Path path = Path.of(rootFilePath);
        file.transferTo(path);

        return Result.success(ip + ":" + port + "/files/" + uuid);
    }


    @GetMapping("/{uuid}")
    public void getFile(@PathVariable String uuid, HttpServletResponse response) {

        String basePath = System.getProperty("user.dir") + "/Backend/src/main/resources/files/";

        File dir = new File(basePath);
        if (!dir.exists()) {
            System.out.println("No directory\n");
        }
        List<String> files = Arrays.asList(dir.list());
        String fileName = files.stream().filter(name -> name.contains(uuid)).findAny().orElse("");

        try (OutputStream os = response.getOutputStream()) {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);
                os.write(bytes);
                os.flush();
            }
        } catch (IOException e) {
            System.err.println("文件下载失败");
        }
    }
}
