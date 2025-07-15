package com.example.myweb;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;


public class CloudinaryTest {
    @Test
    public void uploadImg() throws Exception {
        String CLOUDINARY_URL = "cloudinary://449757555464192:ZUkdSt1y8lBldqJZEkeUIzNlrE4@dsfmt4qaq";
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        System.out.println(cloudinary.config.cloudName);

        // Upload the image
        Map params1 = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true,
                "type", "authenticated"
        );
        System.out.println(cloudinary.uploader().upload("https://cloudinary-devs.github.io/cld-docs-assets/assets/images/coffee_cup.jpg", params1));

        // Get the asset details
        Map params2 = ObjectUtils.asMap(
                "quality_analysis", true
        );
        System.out.println(cloudinary.api().resource("coffee_cup", params2));

/*
        dsfmt4qaq
        {asset_folder=, signature=f665f9457589323ceb3a411d3dba8061de5cdecd, format=jpg, resource_type=image, secure_url=https://res.cloudinary.com/dsfmt4qaq/image/private/s--VgJhfOqK--/v1752591245/coffee_cup.jpg, created_at=2025-07-15T14:54:05Z, asset_id=ce8b238425c0ab94e20a41ae20506709, version_id=05778e0942c6d4f4dc97b460411fb985, type=private, display_name=coffee_cup, version=1752591245, url=http://res.cloudinary.com/dsfmt4qaq/image/private/s--VgJhfOqK--/v1752591245/coffee_cup.jpg, public_id=coffee_cup, tags=[], original_filename=coffee_cup, api_key=449757555464192, bytes=694090, width=1000, etag=110ddd485a1e39ba6b420469c2be600c, placeholder=false, height=895}
            {asset_folder=, next_cursor=bc14a8c29960bfdfdfe476a206a5091b0325a4213cf4c626ee545599982f8e15, usage={}, format=jpg, resource_type=image, secure_url=https://res.cloudinary.com/dsfmt4qaq/image/upload/v1752590008/coffee_cup.jpg, created_at=2025-07-15T14:33:28Z, asset_id=6b1eb55917d7643051e7df8254862c3e, type=upload, display_name=coffee_cup, version=1752590008, url=http://res.cloudinary.com/dsfmt4qaq/image/upload/v1752590008/coffee_cup.jpg, public_id=coffee_cup, pages=1, original_filename=coffee_cup, bytes=694090, width=1000, quality_analysis={focus=0.5637094378471375}, etag=110ddd485a1e39ba6b420469c2be600c, derived=[], height=895}
*/
    }
}
