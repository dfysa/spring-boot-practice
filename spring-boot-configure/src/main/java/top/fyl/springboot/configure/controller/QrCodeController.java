package top.fyl.springboot.configure.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.configure.service.QrCodeService;

/**
 * @author dfysa
 * @data 23/9/2024 下午4:33
 * @description
 */
@RestController
@AllArgsConstructor
public class QrCodeController {
    private final QrCodeService qrCodeService;
    @GetMapping("/qrcode")

    public void qrcode() {
        qrCodeService.generateQrCode();
    }
}

