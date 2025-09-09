package me.manhunt.files;

import me.manhunt.singletons.ExceptionHandler;
import me.manhunt.src.Manhunt;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Arrays;

public abstract class FileManager {
    private File file;
    private YamlConfiguration config;
    private boolean firstCreation;

    public FileManager(@Nullable String path, @NotNull String fileName) {
        try {
            File directory;

            if (path == null)
                directory = new File(Manhunt.PLUGIN.getDataFolder() + "");
            else
                directory = new File(Manhunt.PLUGIN.getDataFolder() + "/" + path);

            boolean create = false;

            if (!directory.exists())
                create = directory.mkdirs();

            file = new File(directory, fileName.toLowerCase().endsWith(".yml") ? fileName : fileName + ".yml");

            if (!file.exists())
                create = file.createNewFile();

            resetConfig();

            createDefaultsIfNotExists(create);
        } catch (Exception e) {
            String[] lines = ExceptionHandler.getInstance().createExceptionReport("File Manager had a severe error", e);

            ExceptionHandler.getInstance().reportException(lines);

            file = null;
        }
    }

    public void createDefaultsIfNotExists(boolean newFile) {
        if (newFile) {
            createDefaults();

            firstCreation = true;

            return;
        }

        verifyIntegrity();
    }

    public abstract void createDefaults();
    public abstract void verifyIntegrity();

    public void set(@NotNull Object key, @Nullable Object value) {
        set(key, value, true);
    }

    public void set(@NotNull Object key, @Nullable Object value, boolean save) {
        if (config == null)
            return;

        config.set(key.toString(), value);

        if (save)
            saveChanges();
    }

    public void addComments(@NotNull Object key, @NotNull String...comments) {
        addComments(key, true, comments);
    }

    public void addComments(@NotNull Object key, boolean save, @NotNull String...comments) {
        if (config == null)
            return;

        config.setComments(key.toString(), Arrays.asList(comments));

        if (save)
            saveChanges();
    }

    public void addInlineComments(@NotNull Object key, @NotNull String...comments) {
        addInlineComments(key, true, comments);
    }

    public void addInlineComments(@NotNull Object key, boolean save, @NotNull String...comments) {
        if (config == null)
            return;

        config.setInlineComments(key.toString(), Arrays.asList(comments));

        if (save)
            saveChanges();
    }

    public boolean hasKey(@NotNull Object key) {
        if (config == null)
            return false;

        return config.contains(key.toString());
    }

    public int getInt(@NotNull Object key) {
        if (config == null)
            return Integer.MIN_VALUE;

        return config.getInt(key.toString());
    }

    public long getLong(@NotNull Object key) {
        if (config == null)
            return Long.MIN_VALUE;

        return config.getLong(key.toString());
    }

    public double getDouble(@NotNull Object key) {
        if (config == null)
            return Double.MIN_VALUE;

        return config.getDouble(key.toString());
    }

    public boolean getBoolean(@NotNull Object key) {
        if (config == null)
            return false;

        return config.getBoolean(key.toString());
    }

    public String getString(@NotNull Object key) {
        if (config == null)
            return null;

        return config.getString(key.toString());
    }

    public Object get(@NotNull Object key) {
        if (config == null)
            return null;

        return config.get(key.toString());
    }

    public ItemStack getItemStack(@NotNull Object key) {
        if (config == null)
            return null;

        return config.getItemStack(key.toString());
    }

    public Location getLocation(@NotNull Object key) {
        if (config == null)
            return null;

        return config.getLocation(key.toString());
    }

    public OfflinePlayer getOfflinePlayer(@NotNull Object key) {
        if (config == null)
            return null;

        return config.getOfflinePlayer(key.toString());
    }

    public Player getPlayer(@NotNull Object key) {
        OfflinePlayer p = getOfflinePlayer(key);

        if (p == null)
            return null;

        return Bukkit.getPlayer(p.getUniqueId());
    }

    public ConfigurationSection getConfigurationSection(@NotNull Object key) {
        if (config == null)
            return null;

        return config.getConfigurationSection(key.toString());
    }

    public void saveChanges() {
        if (config == null)
            return;

        try {
            config.save(file);
        } catch (Exception e) {
            String[] lines = ExceptionHandler.getInstance().createExceptionReport("File Manager had a severe error", e);

            ExceptionHandler.getInstance().reportException(lines);

            file = null;
        }
    }

    public void resetConfig() {
        if (file == null) {
            config = null;

            return;
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean deleteFile() {
        if (file == null)
            return false;

        return file.delete();
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public boolean fileWasCreated() {
        return firstCreation;
    }
}
