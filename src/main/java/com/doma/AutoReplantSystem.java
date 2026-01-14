package com.doma;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.math.util.ChunkUtil;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.movement.MovementStatesComponent;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.chunk.WorldChunk;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public class AutoReplantSystem extends EntityEventSystem<EntityStore, BreakBlockEvent> {
    public AutoReplantSystem() {
        super(BreakBlockEvent.class);
    }

    @Override
    public void handle(final int index, @Nonnull final ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull final Store<EntityStore> store, @Nonnull final CommandBuffer<EntityStore> commandBuffer, @Nonnull final BreakBlockEvent event) {
        Ref<EntityStore> playerRef = archetypeChunk.getReferenceTo(index);
        Player player = store.getComponent(playerRef, Player.getComponentType());

        if (player == null || isCrouching(playerRef, store)) {
            return;
        }

        String brokenBlockId = event.getBlockType().getId();
        player.sendMessage(Message.raw(brokenBlockId));

        if (CropRegistry.isRegistered(brokenBlockId)) {
            CropData data = CropRegistry.get(brokenBlockId);
            String seedId = data.seedItemId();

            ItemContainer inventory = player.getInventory().getCombinedEverything();
            int seedCount = inventory.countItemStacks(stack -> stack.getItemId().equals(seedId));

            if (seedCount > 0) {
                inventory.removeItemStack(new ItemStack(seedId, 1));

                HytaleServer.SCHEDULED_EXECUTOR.schedule(() -> {
                    if (player.getWorld() != null) {
                        Vector3i pos = event.getTargetBlock();
                        long chunkIndex = ChunkUtil.indexChunkFromBlock(pos.x, pos.z);
                        WorldChunk chunk = player.getWorld().getChunkIfInMemory(chunkIndex);

                        if (chunk != null) {
                            chunk.setBlock(pos.x, pos.y, pos.z, data.stageZeroBlockId());
                        }
                    }
                }, 50, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return PlayerRef.getComponentType();
    }

    private boolean isCrouching(Ref<EntityStore> playerRef, @Nonnull Store<EntityStore> store) {
        MovementStatesComponent movementComponent = store.getComponent(playerRef, MovementStatesComponent.getComponentType());

        if (movementComponent != null) {
            return movementComponent.getMovementStates().crouching;
        }
        return false;
    }
}