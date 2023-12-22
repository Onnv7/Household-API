package com.onnv.household.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.onnv.household.constants.CloudinaryConstant.*;

@Component
@RequiredArgsConstructor
public class CloudinaryUtils {
    private final Cloudinary cloudinary;


    public HashMap<String, String> uploadFileToFolder(String pathName, String fileName, byte[] imageData) throws IOException {
        var file = cloudinary.uploader()
                .upload(imageData,
                        Map.of(
                                PUBLIC_ID, fileName,
                                UPLOAD_PRESET, pathName,
                                OVERWRITE, true
                        ));

        return (HashMap<String, String>) file;
    }

    public void deleteImage(String publicId) throws IOException {
        // Xóa hình ảnh từ Cloudinary bằng cách sử dụng public ID
        Map<String, String> options = ObjectUtils.asMap("invalidate", true);
        cloudinary.uploader().destroy(publicId, options);
    }

}
