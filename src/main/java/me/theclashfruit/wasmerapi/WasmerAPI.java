package me.theclashfruit.wasmerapi;

import me.theclashfruit.wasmer.api.registry.MethodRegistry;
import me.theclashfruit.wasmerapi.methods.registry.Register;
import net.fabricmc.api.ModInitializer;

public class WasmerAPI implements ModInitializer {
    @Override
    public void onInitialize() {
        MethodRegistry.register(new Register());
    }
}
