package in.dragonbra.dragonbrain.controller;

import com.google.gson.Gson;
import in.dragonbra.dragonbrain.model.AddonUpdates;
import in.dragonbra.dragonbrain.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @author lngtr
 * @since 7/16/2017
 */
@Controller
@RequestMapping("/panda-extension")
public class PandaExtensionController {

    private static final Logger logger = LogManager.getLogger(PandaExtensionController.class);

    @Autowired private Gson gson;

    @Value("${panda-extension.update-file}")
    private String updateFilePath;

    @Value("${panda-extension.id}")
    private String extensionId;

    @Value("${panda-extension.dir}")
    private String extensionDir;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public void update(HttpServletResponse response) {
        HttpUtils.writeToResponse(updateFilePath, response);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void download(HttpServletResponse response,
                         @RequestParam(name = "v", required = false) String version) {
        try {
            if (StringUtils.isBlank(version)) {
                AddonUpdates updates = gson.fromJson(new FileReader(updateFilePath), AddonUpdates.class);

                List<AddonUpdates.AddonUpdate> updateList = updates.getAddons().get(extensionId).getUpdates();

                updateList.sort(Comparator.comparing(AddonUpdates.AddonUpdate::getVersion));

                version = updateList.get(updateList.size() - 1).getVersion();
            }
        } catch (IOException ex) {
            logger.info("Error reading update file", ex);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        String fileName = String.format("panda-extension-%s.xpi", version);

        response.setHeader("Content-disposition", "attachment; filename="+ fileName);

        String file = extensionDir + fileName;

        HttpUtils.writeToResponse(file, response);
    }
}
