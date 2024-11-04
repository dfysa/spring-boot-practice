package top.fyl.springboot.filter_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUploadInterceptor implements HandlerInterceptor {
    private static final long MAX_FILE_SIZE = 1 * 1024 * 1024; // 1MB
    private static final String[] ALLOWED_TYPES = {"image/jpg", "image/jpeg", "image/png", "image/gif"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查请求是否是 multipart 类型
        if (!(request instanceof MultipartHttpServletRequest)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "请求不是 multipart/form-data 类型");
            return false;
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");

        // 确保文件存在
        if (file == null || file.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "上传的文件不能为空");
            return false;
        }

        // 文件类型检查
        String contentType = file.getContentType();
        if (!isAllowedType(contentType)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "不支持的文件类型: " + contentType);
            return false;
        }

        System.out.println("文件类型检查通过: " + contentType);

        // 文件大小限制
        if (file.getSize() > MAX_FILE_SIZE) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "文件大小超过限制: " + file.getSize() + " bytes");
            return false;
        }
        System.out.println("文件大小检查通过: " + file.getSize() + " bytes");

        // 图片尺寸检查
        if (!checkImageDimensions(file)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "图片尺寸不符合要求");
            return false;
        }
        System.out.println("图片尺寸检查通过");

        // 文件重命名或生成唯一标识
        String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
        request.setAttribute("uniqueFileName", uniqueFileName);
        System.out.println("生成的唯一文件名: " + uniqueFileName);

        return true;
    }

    private boolean isAllowedType(String contentType) {
        for (String type : ALLOWED_TYPES) {
            if (type.equals(contentType)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkImageDimensions(MultipartFile file) throws IOException {
        System.out.println("正在检查图片尺寸: " + file.getOriginalFilename() + ", 文件大小: " + file.getSize() + " bytes");
        BufferedImage img = ImageIO.read(file.getInputStream());
        if (img == null) {
            System.out.println("无法读取图片: " + file.getOriginalFilename());
            return false;
        }

        int width = img.getWidth();
        int height = img.getHeight();
        System.out.println("图片尺寸: " + width + "x" + height);

        return width > 100 && height > 100; // 修改尺寸检查条件
    }

    private String generateUniqueFileName(String originalFilename) {
        return UUID.randomUUID() + "_" + originalFilename;
    }
}
