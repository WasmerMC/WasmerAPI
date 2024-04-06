package me.theclashfruit.wasmerapi.methods.platform;

import com.google.gson.Gson;
import me.theclashfruit.wasmer.api.WasmMethod;
import me.theclashfruit.wasmer.api.wrapper.*;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.util.List;

public class GetMods extends WasmMethod {
    private final Gson gson = new Gson();

    public GetMods() {
        this.fieldName = "platform_get_mods";

        this.returns = List.of(ValueType.F32, ValueType.F32);
    }

    @Override
    public Integer[] execute(Integer... args) {
        ModContainer[] mods = FabricLoader.getInstance().getAllMods().toArray(ModContainer[]::new);

        String json = gson.toJson(mods);

        byte[] bytes = json.getBytes();
        int ptr = instance.memory().grow(bytes.length);

        for (int i = 0; i < bytes.length; i++) {
            instance.memory().write(ptr + i, Value.i32(bytes[i]));
        }

        return new Integer[] {
            ptr,
            bytes.length
        };
    }
}
