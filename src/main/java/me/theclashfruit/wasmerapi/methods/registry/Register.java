package me.theclashfruit.wasmerapi.methods.registry;

import com.google.gson.Gson;
import me.theclashfruit.wasmer.api.WasmMethod;

import me.theclashfruit.wasmerapi.data.ItemSettings;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import me.theclashfruit.wasmer.api.wrapper.ValueType;

import java.util.List;

import static me.theclashfruit.wasmerapi.WasmerAPI.LOGGER;

public class Register extends WasmMethod {
    private final Gson gson = new Gson();

    public Register() {
        this.fieldName = "registry_register";

        this.params = List.of(ValueType.I32, ValueType.I32, ValueType.I32, ValueType.I32, ValueType.I32);
    }

    @Override
    public Integer[] execute(Integer... args) {
        String[] id = getString(args[1], args[2]).split(":");

        switch (args[0]) {
            case 0 -> Registry.register(Registries.BLOCK, new Identifier(id[0], id[1]), getBlock(args));
            case 1 -> Registry.register(Registries.ITEM, new Identifier(id[0], id[1]), getItem(args));
            case 2 -> Registry.register(Registries.ITEM, new Identifier(id[0], id[1]), getBlockItem(args));
        }

        return null;
    }

    private Item getItem(Integer... args) {
        LOGGER.info(getString(args[3], args[4]));

        ItemSettings iS = gson.fromJson(getString(args[3], args[4]), ItemSettings.class);

        FabricItemSettings settings = new FabricItemSettings();

        if (iS.max_count != 0)
            settings.maxCount(iS.max_count);

        return new Item(settings);
    }

    private BlockItem getBlockItem(Integer... args) {
        LOGGER.info(getString(args[3], args[4]));

        String[] id = getString(args[1], args[2]).split(":");

        return new BlockItem(Registries.BLOCK.get(new Identifier(id[0], id[1])), new FabricItemSettings());
    }

    private Block getBlock(Integer... args) {
        LOGGER.info(getString(args[3], args[4]));

        return new Block(FabricBlockSettings.create());
    }
}
