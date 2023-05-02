package turniplabs.projectiles.mixin;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBow;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import turniplabs.projectiles.ProjectilesKt;
import turniplabs.projectiles.item.ItemElementalArrow;

import java.util.Random;

@Mixin(value = ItemBow.class, remap = false)
public abstract class ItemBowMixin {

    private final ItemElementalArrow[] arrowTypes = new ItemElementalArrow[]{(ItemElementalArrow) ProjectilesKt.getARROW_EGG(), (ItemElementalArrow) ProjectilesKt.getARROW_FIRE(), (ItemElementalArrow) ProjectilesKt.getARROW_ICE(), (ItemElementalArrow) ProjectilesKt.getARROW_SULPHUR(), (ItemElementalArrow) ProjectilesKt.getARROW_LIGHTNING(), (ItemElementalArrow) ProjectilesKt.getARROW_TELEPORT()};

    private final Random mixinRand = new Random();

    @Inject(method = "onItemRightClick", at = @At("TAIL"))
    private void projectiles_RightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, CallbackInfoReturnable<ItemStack> cir) {
        for (ItemElementalArrow itemArrows : arrowTypes) {
            if (entityplayer.inventory.consumeInventoryItem(itemArrows.itemID)) {
                world.playSoundAtEntity(entityplayer, "random.bow", 1.0f, 1.0f / (mixinRand.nextFloat() * 0.4f + 0.8f));
                itemArrows.shootArrow();
                break;
            }
        }
    }
}
