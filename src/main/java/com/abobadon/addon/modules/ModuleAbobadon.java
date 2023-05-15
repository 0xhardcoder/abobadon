package com.abobadon.addon.modules;

import com.abobadon.addon.Addon;
import meteordevelopment.meteorclient.systems.modules.Module;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import meteordevelopment.meteorclient.mixininterface.IClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.PickFromInventoryC2SPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.hit.HitResult.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.*;
public class ModuleAbobadon extends Module {
    public ModuleAbobadon() {
        super(Addon.CATEGORY, "Piston Crystal", "Pushesh crystals into enemies.");
    }

    @Override
    public void OnActivate() {
        PlayerEntity playerEntity = MinecraftClient.getInstance().player;
        Vec3d vec3d = new Vec3d(playerEntity.getPos().x, playerEntity.getPos().y + 2, playerEntity.getPos().z);
        invSwitch(5);
        placeBlock(vec3d, Direction.DOWN);
    }


    private boolean invSwitch(int slot) {
        if (slot >= 0) {
            ScreenHandler handler = mc.player.currentScreenHandler;
            Int2ObjectArrayMap<ItemStack> stack = new Int2ObjectArrayMap<>();
            stack.put(slot, handler.getSlot(slot).getStack());

            mc.getNetworkHandler().sendPacket(new ClickSlotC2SPacket(handler.syncId,
                handler.getRevision(), PlayerInventory.MAIN_SIZE + Managers.HOLDING.slot,
                slot, SlotActionType.SWAP, handler.getSlot(slot).getStack(), stack)
            );
            ((IClientPlayerInteractionManager) mc.interactionManager).syncSelected();
            return true;
        }
        return false;
    }

    private void placeBlock(Vec3d pos, Direction side) {
        BlockHitResult blockHitResult = new BlockHitResult(pos, side, new BlockPos(pos), false);
        
        Packet packet = new PlayerInteractBlockC2SPacket(Hand.MAIN_HAND, blockHitResult);
        client.getNetworkHandler().sendPacket(packet);
    }
}
