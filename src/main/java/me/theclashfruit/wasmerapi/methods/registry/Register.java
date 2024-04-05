package me.theclashfruit.wasmerapi.methods.registry;

import me.theclashfruit.wasmer.api.WasmMethod;

import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.wasm.types.Value;

public class Register extends WasmMethod {
    public Register() {
        this.fieldName = "register";
    }

    @Override
    public Value[] execute(Instance inst, Value... args) {
        return null;
    }
}
