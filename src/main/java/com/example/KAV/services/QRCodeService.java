package com.example.KAV.services;

import com.example.KAV.models.usuario.Usuario;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QRCodeService {

    public BufferedImage generateQRCode(Usuario usuario) throws Exception {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(usuario.toString(), BarcodeFormat.QR_CODE, 350, 350);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
