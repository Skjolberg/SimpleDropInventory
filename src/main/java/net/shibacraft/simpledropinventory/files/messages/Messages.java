package net.shibacraft.simpledropinventory.files.messages;

import de.leonhard.storage.Yaml;
import lombok.Getter;
import lombok.Setter;
import net.shibacraft.simpledropinventory.api.chat.TextColor;
import net.shibacraft.simpledropinventory.files.FileManager;


public enum Messages {

    PREFIX("PREFIX", "&8[&dSimpleDropInventory&8]&r "),
    INVALID_ARGUMENT("INVALID_ARGUMENT", "&cThe argument %s is Invalid."),
    NO_PERMISSION("NO_PERMISSION", "&cYou do not have permission to do this."),
    RELOAD("RELOAD", "&aPlugin reloaded!");

    @Getter
    private final String path;

    @Getter
    @Setter
    private String value;

    @Getter
    private static boolean Prefix = true;

    /**
     * Returns a simple message setting colours and prefix.
     * @return String
     */
    public String get(){
        return Prefix ? TextColor.color(PREFIX.getValue() + this.value) : TextColor.color(this.value);
    }

    Messages(String path, String value) {
        this.path = path;
        this.value = value;
    }

    public static void load() {

        Yaml messagesFile = FileManager.getFilesYaml().get("Messages");

        Prefix = !messagesFile.getString("PREFIX").isEmpty();

        for (Messages msg : values()) {

            if(!messagesFile.contains(msg.getPath())){
                messagesFile.set(msg.getPath(), msg.getValue());
                continue;
            }

            String str = messagesFile.getString(msg.getPath());
            msg.setValue(str);
        }

    }
}
