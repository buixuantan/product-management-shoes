package com.cg.service.uploadImage;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class UploadImageProductServiceImpl implements IUploadImageProductService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map uploadImage(MultipartFile multipartFile, Map option) throws IOException {
        return cloudinary.uploader().upload(multipartFile.getBytes(), option);
    }

    @Override
    public Map destroyImage(String publicId, Map option) throws IOException {
        return cloudinary.uploader().destroy(publicId, option);
    }
}
