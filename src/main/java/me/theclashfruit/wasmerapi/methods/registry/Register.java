package me.theclashfruit.wasmerapi.methods.registry;

import com.dylibso.chicory.wasm.types.ValueType;
import com.google.gson.Gson;
import me.theclashfruit.wasmer.api.WasmMethod;

import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.wasm.types.Value;
import me.theclashfruit.wasmerapi.data.ItemSettings;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

import static me.theclashfruit.wasmerapi.WasmerAPI.LOGGER;

public class Register extends WasmMethod {
    private final Gson gson = new Gson();

    public Register() {
        this.fieldName = "registry_register";

        this.params = List.of(ValueType.I32, ValueType.I32, ValueType.I32, ValueType.I32, ValueType.I32);
    }

    @Override
    public Value[] execute(Instance inst, Value... args) {
        String[] id = getString(inst, args[1].asInt(), args[2].asInt()).split(":");

        switch (args[0].asInt()) {
            case 0 -> Registry.register(Registries.BLOCK, new Identifier(id[0], id[1]), getBlock(inst, args));
            case 1 -> Registry.register(Registries.ITEM, new Identifier(id[0], id[1]), getItem(inst, args));
            case 2 -> Registry.register(Registries.ITEM, new Identifier(id[0], id[1]), getBlockItem(inst, args));
        }

        return null;
    }

    private Item getItem(Instance inst, Value... args) {
        LOGGER.info(getString(inst, args[3].asInt(), args[4].asInt()));

        ItemSettings iS = gson.fromJson(getString(inst, args[3].asInt(), args[4].asInt()), ItemSettings.class);

        FabricItemSettings settings = new FabricItemSettings();

        if (iS.max_count != 0)
            settings.maxCount(iS.max_count);

        return new Item(settings);
    }

    private BlockItem getBlockItem(Instance inst, Value... args) {
        LOGGER.info(getString(inst, args[3].asInt(), args[4].asInt()));

        String[] id = getString(inst, args[1].asInt(), args[2].asInt()).split(":");

        return new BlockItem(Registries.BLOCK.get(new Identifier(id[0], id[1])), new FabricItemSettings());
    }

    private Block getBlock(Instance inst, Value... args) {
        LOGGER.info(getString(inst, args[3].asInt(), args[4].asInt()));

        return new Block(FabricBlockSettings.create());
    }
}
