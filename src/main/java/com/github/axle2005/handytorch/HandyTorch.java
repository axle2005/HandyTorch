package com.github.axle2005.handytorch;

import com.github.axle2005.handytorch.Listeners.RegisterListeners;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "handytorch",
        name = "HandyTorch",
        description = "Create Light While holding a torch",
        authors = {
                "axle2005"
        }
)
public class HandyTorch {

    @Inject
    private Logger logger;

    private static HandyTorch plugin;
    RegisterListeners regLis;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        plugin = this;
        regLis = new RegisterListeners(this);
        regLis.registerEvent("Move");
    }

    public static HandyTorch getInstance(){
        return plugin;
    }

}
