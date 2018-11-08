package in.dragonbra.dragonbrain.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lngtr
 * @since 2017-10-01
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static void writeToResponse(String file, HttpServletResponse response) {
        writeToResponse(new File(file), response);
    }

    public static void writeToResponse(File file, HttpServletResponse response) {
        try {
            // get your file as InputStream
            InputStream is = new FileInputStream(file);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());

            response.flushBuffer();
        } catch (IOException ex) {
            logger.info(String.format("Error writing file to output stream. Filename was '%s'", file), ex);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
