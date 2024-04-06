package me.theclashfruit.wasmerapi;

import me.theclashfruit.wasmer.api.registry.MethodRegistry;
import me.theclashfruit.wasmerapi.methods.registry.Register;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import static me.theclashfruit.wasmerapi.WasmerAPI.LOGGER;

public class WasmerAPIPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        MethodRegistry.register(new Register());

        LOGGER.info("Registered all methods.");
    }
}
