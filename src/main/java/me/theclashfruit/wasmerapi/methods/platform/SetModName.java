package me.theclashfruit.wasmerapi.methods.platform;

import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.wasm.types.Value;
import com.dylibso.chicory.wasm.types.ValueType;
import me.theclashfruit.wasmer.api.WasmMethod;
import me.theclashfruit.wasmerapi.ModContainerMixinInterface;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.metadata.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SetModName extends WasmMethod {
    public SetModName() {
        this.fieldName = "platform_set_modname";

        this.params = List.of(ValueType.I32, ValueType.I32, ValueType.I32, ValueType.I32);
    }

    @Override
    public Value[] execute(Instance inst, Value... args) {
        String modId = getString(inst, args[0].asInt(), args[1].asInt());
        String modName = getString(inst, args[2].asInt(), args[3].asInt());

        Optional<ModContainer> mod = FabricLoader.getInstance().getModContainer(modId);

        if (mod.isPresent()) {
            ModContainer modContainer = mod.get();
            if (modContainer instanceof ModContainerMixinInterface) {
                ModMetadata originalMetadata = modContainer.getMetadata();

                ModMetadata metadata = new ModMetadata() {
                    @Override
                    public String getType() {
                        return originalMetadata.getType();
                    }

                    @Override
                    public String getId() {
                        return originalMetadata.getId();
                    }

                    @Override
                    public Collection<String> getProvides() {
                        return originalMetadata.getProvides();
                    }

                    @Override
                    public Version getVersion() {
                        return originalMetadata.getVersion();
                    }

                    @Override
                    public ModEnvironment getEnvironment() {
                        return originalMetadata.getEnvironment();
                    }

                    @Override
                    public Collection<ModDependency> getDependencies() {
                        return originalMetadata.getDependencies();
                    }

                    @Override
                    public String getName() {
                        return modName;
                    }

                    @Override
                    public String getDescription() {
                        return originalMetadata.getDescription();
                    }

                    @Override
                    public Collection<Person> getAuthors() {
                        return originalMetadata.getAuthors();
                    }

                    @Override
                    public Collection<Person> getContributors() {
                        return originalMetadata.getContributors();
                    }

                    @Override
                    public ContactInformation getContact() {
                        return originalMetadata.getContact();
                    }

                    @Override
                    public Collection<String> getLicense() {
                        return originalMetadata.getLicense();
                    }

                    @Override
                    public Optional<String> getIconPath(int size) {
                        return originalMetadata.getIconPath(size);
                    }

                    @Override
                    public boolean containsCustomValue(String key) {
                        return originalMetadata.containsCustomValue(key);
                    }

                    @Override
                    public CustomValue getCustomValue(String key) {
                        return originalMetadata.getCustomValue(key);
                    }

                    @Override
                    public Map<String, CustomValue> getCustomValues() {
                        return originalMetadata.getCustomValues();
                    }

                    @Override
                    public boolean containsCustomElement(String key) {
                        return originalMetadata.containsCustomElement(key);
                    }
                };

                ((ModContainerMixinInterface) modContainer).setMetadata(metadata);
            }
        }

        return null;
    }
}
