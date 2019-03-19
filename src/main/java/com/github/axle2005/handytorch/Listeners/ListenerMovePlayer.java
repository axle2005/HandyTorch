package com.github.axle2005.handytorch.Listeners;

import com.github.axle2005.handytorch.HandyTorch;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.property.block.GroundLuminanceProperty;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;

import java.util.Optional;

public class ListenerMovePlayer {

    HandyTorch plugin;
    BlockState block;

    Task.Builder lightWalkerTask = Sponge.getScheduler().createTaskBuilder();

    public ListenerMovePlayer(HandyTorch plugin) {
        this.plugin = plugin;

    }

    @Listener
    public void onPlayerMove(MoveEntityEvent event, @Root Player p) {
        if (event.getTargetEntity() instanceof Player) {
            Player player = (Player) event.getTargetEntity();
            if(player.getItemInHand(HandTypes.MAIN_HAND).get().getType() == ItemTypes.TORCH){
                glow(p);




            }
        }
    }


    private void glow(Player p) {
        Location pLoc = p.getLocation();
        final Optional<GroundLuminanceProperty> groundLumOpt = p.getLocation()
                .getProperty(GroundLuminanceProperty.class);
        if (groundLumOpt.isPresent()) {
            if (groundLumOpt.get().getValue() < 8) {
                block = pLoc.getBlock();

                    p.sendBlockChange(pLoc.getBlockPosition(),
                            BlockState.builder().blockType(BlockTypes.TORCH).build());

            }

        }
        lightWalkerTask = lightWalkerTask.execute(() -> {
            if(!pLoc.getBlockRelative(Direction.DOWN).getBlockPosition().equals(p.getLocation().getBlockRelative(Direction.DOWN).getBlockPosition())){
                p.resetBlockChange(pLoc.getBlockPosition());
            }
        }).async().delayTicks(8);
        lightWalkerTask.submit(HandyTorch.getInstance());

    }

}
