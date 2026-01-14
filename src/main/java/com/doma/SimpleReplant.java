package com.doma;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class SimpleReplant extends JavaPlugin {
    public SimpleReplant(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    public void setup() {
        super.setup();
        this.getEntityStoreRegistry().registerSystem(new AutoReplantSystem());
    }
}
