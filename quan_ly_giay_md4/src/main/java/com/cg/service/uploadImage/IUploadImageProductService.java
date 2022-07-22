package com.cg.service.uploadImage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IUploadImageProductService {
    Map uploadImage(MultipartFile multipartFile, Map option) throws IOException;

    Map destroyImage(String publicId, Map option) throws IOException;


}
