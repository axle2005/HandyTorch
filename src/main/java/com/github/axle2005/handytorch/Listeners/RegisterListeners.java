package com.github.axle2005.handytorch.Listeners;

import com.github.axle2005.handytorch.HandyTorch;
import org.spongepowered.api.Sponge;

public class RegisterListeners {
    HandyTorch plugin;
    ListenerMovePlayer move;


    public RegisterListeners(HandyTorch plugin)
    {
        this.plugin = plugin;

        move = new ListenerMovePlayer(plugin);


    }
    public void registerEvent(String event)
    {

        if(event.equals("Move"))
            Sponge.getEventManager().registerListeners(plugin, move);


    }

}
