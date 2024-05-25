package com.schitzpseudonym.smelttouch;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModUtil {
    // Enchantment
    public static void registerEnchantment(String name, Enchantment enchantment) {
        Registry.register(Registries.ENCHANTMENT, new Identifier(EntrypointServer.MOD_ID, name), enchantment);
    }
}
