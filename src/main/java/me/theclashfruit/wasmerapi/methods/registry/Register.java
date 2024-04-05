package me.theclashfruit.wasmerapi.methods.registry;

import me.theclashfruit.wasmer.api.WasmMethod;

import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.wasm.types.Value;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.theclashfruit.wasmerapi.WasmerAPI.LOGGER;

public class Register extends WasmMethod {
    public Register() {
        this.fieldName = "registry_register";
    }

    @Override
    public Value[] execute(Instance inst, Value... args) {
        var id = getString(inst, args[1].asInt(), args[2].asInt()).split(":");

        switch (args[0].asInt()) {
            case 0 -> Registry.register(Registries.BLOCK, new Identifier(id[0], id[1]), null);
            case 1 -> Registry.register(Registries.ITEM, new Identifier(id[0], id[1]), getItem(inst, args));
        }

        return null;
    }

    private Item getItem(Instance inst, Value... args) {
        LOGGER.info(getString(inst, args[3].asInt(), args[4].asInt()));

        return new Item(new FabricItemSettings());
    }
}
