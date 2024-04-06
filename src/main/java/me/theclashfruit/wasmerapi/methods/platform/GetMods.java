package me.theclashfruit.wasmerapi.methods.platform;

import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.wasm.types.Value;
import com.dylibso.chicory.wasm.types.ValueType;
import com.google.gson.Gson;
import me.theclashfruit.wasmer.api.WasmMethod;
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
    public Value[] execute(Instance inst, Value... args) {
        ModContainer[] mods = FabricLoader.getInstance().getAllMods().toArray(ModContainer[]::new);

        String json = gson.toJson(mods);

        byte[] bytes = json.getBytes();
        int ptr = inst.memory().grow(bytes.length);

        for (int i = 0; i < bytes.length; i++) {
            inst.memory().write(ptr + i, Value.fromFloat(bytes[i]));
        }

        return new Value[] {
            Value.fromFloat(ptr),
            Value.fromFloat(bytes.length)
        };
    }
}
