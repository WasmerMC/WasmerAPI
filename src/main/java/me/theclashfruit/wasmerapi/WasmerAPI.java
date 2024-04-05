package me.theclashfruit.wasmerapi;

import me.theclashfruit.wasmer.api.registry.MethodRegistry;
import me.theclashfruit.wasmerapi.methods.registry.Register;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WasmerAPI implements ModInitializer {

    public static final String MOD_ID = "wasmerapi";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        MethodRegistry.register(new Register());
    }
}
