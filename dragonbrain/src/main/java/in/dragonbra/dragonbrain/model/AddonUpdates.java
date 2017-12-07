package in.dragonbra.dragonbrain.model;

import java.util.List;
import java.util.Map;

/**
 * @author lngtr
 * @since 7/16/2017
 */
public class AddonUpdates {
    private Map<String, Addon> addons;

    public Map<String, Addon> getAddons() {
        return addons;
    }

    public void setAddons(Map<String, Addon> addons) {
        this.addons = addons;
    }

    public class Addon {
        private List<AddonUpdate> updates;

        public List<AddonUpdate> getUpdates() {
            return updates;
        }

        public void setUpdates(List<AddonUpdate> updates) {
            this.updates = updates;
        }
    }

    public class AddonUpdate {
        private String version;

        private String updateLink;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUpdateLink() {
            return updateLink;
        }

        public void setUpdateLink(String updateLink) {
            this.updateLink = updateLink;
        }
    }
}
