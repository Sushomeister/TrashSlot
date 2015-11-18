package net.blay09.mods.trashslot.net;

import net.blay09.mods.trashslot.TrashSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkHandler {

    public static SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(TrashSlot.MOD_ID);
    public static final int PROTOCOL_VERSION = 1;

    public static void init() {
        instance.registerMessage(HandlerDelete.class, MessageDelete.class, 1, Side.SERVER);
        instance.registerMessage(HandlerHello.class, MessageHello.class, 2, Side.SERVER);
        instance.registerMessage(HandlerHello.class, MessageHello.class, 3, Side.CLIENT);
    }

    public static EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.side == Side.SERVER ? ctx.getServerHandler().playerEntity : getClientPlayerEntity();
    }

    @SideOnly(Side.CLIENT)
    public static EntityPlayer getClientPlayerEntity() {
        return Minecraft.getMinecraft().thePlayer;
    }

}
