package me.peridot.mckdisco.data;

import api.peridot.periapi.configuration.ConfigurationProvider;
import api.peridot.periapi.configuration.langapi.LangAPI;
import me.peridot.mckdisco.MCKDisco;
import org.bukkit.configuration.ConfigurationSection;

public class Config extends ConfigurationProvider {

    private final MCKDisco plugin;

    private LangAPI langAPI;

    public Config(MCKDisco plugin) {
        super(plugin.getConfig());
        this.plugin = plugin;
    }

    public void loadConfig() {
        ConfigurationSection config = plugin.getConfig();

        langAPI = new LangAPI(config);
    }

    public LangAPI getLangAPI() {
        return langAPI;
    }
}
