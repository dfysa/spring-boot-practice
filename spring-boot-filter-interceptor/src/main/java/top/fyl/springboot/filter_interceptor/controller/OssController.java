package top.fyl.springboot.filter_interceptor.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.fyl.springboot.filter_interceptor.utils.OssUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/oss")
public class OssController {
    @Resource
    private OssUtil ossUtil;

    @PostMapping("/upload")
    public Map<String, Object> uploadFile(HttpServletRequest request) throws Exception {
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        String uniqueFileName = (String) request.getAttribute("uniqueFileName");
        String fileUrl = ossUtil.uploadFile(file, uniqueFileName);

        Map<String, Object> response = new HashMap<>();
        response.put("fileUrl", fileUrl);
        return response;
    }

}

