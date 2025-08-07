package com.example.KAV.controllers;

import com.example.KAV.services.usuario.IUsuarioService;
import com.example.KAV.services.QRCodeService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/v1/qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    private final IUsuarioService userService;

    public QRCodeController(QRCodeService qrCodeService, IUsuarioService userService) {
        this.qrCodeService = qrCodeService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public void generateQRCode(HttpServletResponse response, @PathVariable long id) throws Exception {
        BufferedImage image = qrCodeService.generateQRCode(userService.getUser(id));
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
