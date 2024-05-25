package com.schitzpseudonym.smelttouch;

import com.schitzpseudonym.smelttouch.enchantment.SmeltingTouch;
import net.minecraft.enchantment.Enchantment;

import static com.schitzpseudonym.smelttouch.ModUtil.registerEnchantment;

public class ModRegistry {

    public static final Enchantment smeltTouch = new SmeltingTouch();

    public static void onInitializeServer() {
        registerEnchantment("smelt_touch", smeltTouch);
        //smeltTouch = registerEnchantment("smelting", smeltTouch);
    }
}
